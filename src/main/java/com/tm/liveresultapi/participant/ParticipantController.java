package com.tm.liveresultapi.participant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RequestMapping(path="/meets/{meet_id}/events/{event_id}/participants")
@RepositoryRestController
@RestController
public class ParticipantController {

	@Autowired
	ParticipantService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Participant> getAll(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId){
		return service.getParticipants(meetId, eventId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Participant create(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId, 
			@RequestBody Participant participant) {
		return service.create(meetId, eventId, participant);
	}
	
	@GetMapping("/{participant_id}")
	@ResponseStatus(HttpStatus.OK)
	public Participant get(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId,
			@PathVariable("participant_id") String participantId) {
		return service.get(meetId, eventId, participantId);
	}
	
	@PutMapping("/{participant_id}")
	@ResponseStatus(HttpStatus.OK)
	public Participant update(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId,
			@PathVariable("participant_id") String participantId,
			@RequestBody Participant participant) {
		return service.update(meetId, eventId, participantId, participant);
	}
	
	@DeleteMapping("/{participant_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId,
			@PathVariable("participant_id") String participantId) {
		service.delete(meetId, eventId, participantId);
	}
}
