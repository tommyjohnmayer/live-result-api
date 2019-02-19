package com.tm.liveresultapi.result;

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
@RequestMapping(path="/meets/{meet_id}/events/{event_id}/participants/{participant_id}/results")
@RepositoryRestController
@RestController
public class ResultController {

	@Autowired
	ResultService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Result> getAll(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId,
			@PathVariable("participant_id") String participantId){
		return service.getResults(meetId, eventId, participantId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Result create(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId,
			@PathVariable("participant_id") String participantId, 
			@RequestBody Result result) {
		return service.create(meetId, eventId, participantId, result);
	}
	
	@GetMapping("/{result_id}")
	@ResponseStatus(HttpStatus.OK)
	public Result get(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId,
			@PathVariable("participant_id") String participantId,
			@PathVariable("result_id") String resultId) {
		return service.get(meetId, eventId, participantId, resultId);
	}
	
	@PutMapping("/{result_id}")
	@ResponseStatus(HttpStatus.OK)
	public Result update(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId,
			@PathVariable("participant_id") String participantId,
			@PathVariable("result_id") String resultId,
			@RequestBody Result result) {
		return service.update(meetId, eventId, participantId, resultId, result);
	}
	
	@DeleteMapping("/{result_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId,
			@PathVariable("participant_id") String participantId,
			@PathVariable("result_id") String resultId) {
		service.delete(meetId, eventId, participantId, resultId);
	}
}
