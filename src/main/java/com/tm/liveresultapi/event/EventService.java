package com.tm.liveresultapi.event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.PathElement;
import com.tm.liveresultapi.meet.Meet;
import com.tm.liveresultapi.participant.ParticipantService;

@Service
public class EventService {
	
	@Autowired
	DatastoreRepository<Event, Key> eventRepo;
	
	@Autowired
	DatastoreRepository<Meet, String> meetRepo;
	
	@Autowired
	ParticipantService participantService;
	
	@Autowired
	KeyFactory keyFactory;
	
	public List<Event> getEventsForMeet(String meetId){
		Optional<Meet> meet = meetRepo.findById(meetId);
		if(meet.isPresent()) {
			return meet.get().getEvents();
		}
		throw new ResourceNotFoundException("meet does not exist");
	}
	
	public Event create(String meetId, Event event) {
		String id = UUID.randomUUID().toString();
		event.setEntityKey(
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.setKind("events").newKey(id));
		event.setId(id);
		event.setMeet(meetId);
		return eventRepo.save(event);
	}

	public Event get(String meetId, String eventId) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.setKind("events").newKey(eventId);
		Optional<Event> event = eventRepo.findById(entityKey);
		if(event.isPresent()) {
			return event.get();
		}
		throw new ResourceNotFoundException("event does not exist");
	}

	public Event update(String meetId, String eventId, Event event) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.setKind("events").newKey(eventId);
		Optional<Event> existingEvent = eventRepo.findById(entityKey);
		if(existingEvent.isPresent()) {
			Event exists = existingEvent.get();
			exists.setName(event.getName());
			return eventRepo.save(exists);
		}
		throw new ResourceNotFoundException("event does not exist");
	}

	public void delete(String meetId, String eventId) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.setKind("events").newKey(eventId);
		Optional<Event> existingEvent = eventRepo.findById(entityKey);
		if(!existingEvent.isPresent()) {
			throw new ResourceNotFoundException("event does not exist");
		}
		participantService.deleteAll(existingEvent.get().getParticipants());
		eventRepo.delete(existingEvent.get());
	}

	public void deleteAll(List<Event> events) {
		participantService.deleteAll(
				events.stream().flatMap(event -> 
					event.getParticipants().stream()).collect(Collectors.toList()));
		eventRepo.deleteAll(events);
	}
}
