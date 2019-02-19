package com.tm.liveresultapi.competitor;

import java.util.UUID;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler
public class CompetitorEventHandler {

	@HandleBeforeCreate
	public void handleCreateCompetitor(Competitor competitor) {
		competitor.setId(UUID.randomUUID().toString());
	}
}
