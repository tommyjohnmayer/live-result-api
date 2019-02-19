package com.tm.liveresultapi.meet;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.tm.liveresultapi.event.EventService;

@RepositoryEventHandler
public class MeetEventHandler {
	
	@Autowired
	EventService eventService;

	@HandleBeforeCreate
	public void handleCreate(Meet entity) {
		entity.setId(UUID.randomUUID().toString());
	}
	
	@HandleBeforeDelete
	public void handleDelete(Meet entity) {
		eventService.deleteAll(entity.getEvents());
	}
}
