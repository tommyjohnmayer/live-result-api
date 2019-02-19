package com.tm.liveresultapi.result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.PathElement;
import com.tm.liveresultapi.participant.Participant;

@Service
public class ResultService {
	
	@Autowired
	DatastoreRepository<Participant, Key> participantRepo;
	
	@Autowired
	DatastoreRepository<Result, Key> resultRepo;
	
	@Autowired
	KeyFactory keyFactory;

	public List<Result> getResults(String meetId, String eventId, String participantId) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.addAncestor(PathElement.of("events", eventId))
				.setKind("participants").newKey(participantId);
		Optional<Participant> existing = participantRepo.findById(entityKey);
		if(existing.isPresent()) {
			return existing.get().getResults();
		}
		throw new ResourceNotFoundException("event does not exist");
	}

	public Result create(String meetId, String eventId, String participantId, Result result) {
		String id = UUID.randomUUID().toString();
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.addAncestor(PathElement.of("events", eventId))
				.addAncestor(PathElement.of("participants", participantId))
				.setKind("results").newKey(id);
		result.setEntityKey(entityKey);
		result.setId(id);
		result.setParticipant(participantId);
		result.setEvent(eventId);
		result.setMeet(meetId);
		return resultRepo.save(result);
	}

	public Result get(String meetId, String eventId, String participantId, String resultId) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.addAncestor(PathElement.of("events", eventId))
				.addAncestor(PathElement.of("participants", participantId))
				.setKind("results").newKey(resultId);
		Optional<Result> result = resultRepo.findById(entityKey);
		if(result.isPresent()) {
			return result.get();
		}
		throw new ResourceNotFoundException("result does not exist");
	}
	
	public Result update(String meetId, String eventId, String participantId, String resultId, Result result) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.addAncestor(PathElement.of("events", eventId))
				.addAncestor(PathElement.of("participants", participantId))
				.setKind("results").newKey(resultId);
		Optional<Result> existingParticipant = resultRepo.findById(entityKey);
		if(existingParticipant.isPresent()) {
			Result exists = existingParticipant.get();
			exists.setTime(result.getTime());
			return resultRepo.save(exists);
		}
		throw new ResourceNotFoundException("result does not exist");
	}

	public void delete(String meetId, String eventId, String participantId, String resultId) {
		Key entityKey = 		
				keyFactory.reset()
				.addAncestor(PathElement.of("meets", meetId))
				.addAncestor(PathElement.of("events", eventId))
				.addAncestor(PathElement.of("participants", participantId))
				.setKind("results").newKey(resultId);
		Optional<Result> existing = resultRepo.findById(entityKey);
		if(!existing.isPresent()) {
			throw new ResourceNotFoundException("result does not exist");
		}
		resultRepo.delete(existing.get());
	}

	public void deleteAll(List<Result> results) {
		resultRepo.deleteAll(results);
	}

}
