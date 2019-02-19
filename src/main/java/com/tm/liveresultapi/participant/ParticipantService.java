package com.tm.liveresultapi.participant;

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
import com.tm.liveresultapi.event.Event;
import com.tm.liveresultapi.result.ResultService;

@Service
public class ParticipantService {
	
	@Autowired
	DatastoreRepository<Event, Key> eventRepo;
	
	@Autowired
	DatastoreRepository<Participant, Key> participantRepo;
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	KeyFactory keyFactory;

	public List<Participant> getParticipants(String meetId, String eventId) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.setKind("events").newKey(eventId);
		Optional<Event> existingEvent = eventRepo.findById(entityKey);
		if(existingEvent.isPresent()) {
			return existingEvent.get().getParticipants();
		}
		throw new ResourceNotFoundException("event does not exist");
	}
	
	public Participant create(String meetId, String eventId, Participant participant) {
		String id = UUID.randomUUID().toString();
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.addAncestor(PathElement.of("events", eventId))
				.setKind("participants").newKey(id);
		participant.setEntityKey(entityKey);
		participant.setId(id);
		participant.setEvent(eventId);
		participant.setMeet(meetId);
		return participantRepo.save(participant);
	}

	public Participant get(String meetId, String eventId, String participantId) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.addAncestor(PathElement.of("events", eventId))
				.setKind("participants").newKey(participantId);
		Optional<Participant> participant = participantRepo.findById(entityKey);
		if(participant.isPresent()) {
			return participant.get();
		}
		throw new ResourceNotFoundException("participant does not exist");
	}

	public Participant update(String meetId, String eventId, String participantId, Participant participant) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.addAncestor(PathElement.of("events", eventId))
				.setKind("participants").newKey(participantId);
		Optional<Participant> existingParticipant = participantRepo.findById(entityKey);
		if(existingParticipant.isPresent()) {
			Participant exists = existingParticipant.get();
			exists.setDivision(participant.getDivision());
			exists.setOrder(participant.getOrder());
			return participantRepo.save(exists);
		}
		throw new ResourceNotFoundException("participant does not exist");
	}

	public void delete(String meetId, String eventId, String participantId) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.addAncestor(PathElement.of("events", eventId))
				.setKind("participants").newKey(participantId);
		Optional<Participant> existing = participantRepo.findById(entityKey);
		if(!existing.isPresent()) {
			throw new ResourceNotFoundException("participant does not exist");
		}
		resultService.deleteAll(existing.get().getResults());
		participantRepo.delete(existing.get());
	}

	public void deleteAll(List<Participant> participants) {
		resultService.deleteAll(
				participants.stream().flatMap(
						participant -> participant.getResults().stream()).collect(Collectors.toList()));
		participantRepo.deleteAll(participants);
	}

}
