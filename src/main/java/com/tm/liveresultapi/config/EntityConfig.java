package com.tm.liveresultapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.cloud.datastore.KeyFactory;
import com.tm.liveresultapi.competitor.CompetitorEventHandler;
import com.tm.liveresultapi.meet.MeetEventHandler;

@Configuration
public class EntityConfig {
	
	@Value( "${spring.cloud.gcp.datastore.project-id}" )
	private String appId;
	
	@Bean
	MeetEventHandler meetEventHandler() {
		return new MeetEventHandler();
	}
	  
	@Bean
	CompetitorEventHandler competitorEventHandler() {
		return new CompetitorEventHandler();
	}
	  
	@Bean
	KeyFactory keyFactory() {
		return new KeyFactory(appId);
	}
}
