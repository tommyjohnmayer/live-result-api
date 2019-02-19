package com.tm.liveresultapi.event;

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
@RequestMapping(path="/meets/{meet_id}/events")
@RepositoryRestController
@RestController
public class EventController {
	
	@Autowired
	EventService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Event> getAll(
			@PathVariable("meet_id") String meetId){
		return service.getEventsForMeet(meetId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Event create(
			@PathVariable("meet_id") String meetId, 
			@RequestBody Event event) {
		return service.create(meetId, event);
	}
	
	@GetMapping("/{event_id}")
	@ResponseStatus(HttpStatus.OK)
	public Event get(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId) {
		return service.get(meetId, eventId);
	}
	
	@PutMapping("/{event_id}")
	@ResponseStatus(HttpStatus.OK)
	public Event update(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId,
			@RequestBody Event event) {
		return service.update(meetId, eventId, event);
	}
	
	@DeleteMapping("/{event_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(
			@PathVariable("meet_id") String meetId,
			@PathVariable("event_id") String eventId) {
		service.delete(meetId, eventId);
	}
	
}
