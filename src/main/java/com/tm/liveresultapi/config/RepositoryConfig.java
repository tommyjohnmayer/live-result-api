package com.tm.liveresultapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.tm.liveresultapi.competitor.Competitor;
import com.tm.liveresultapi.meet.Meet;
import com.tm.liveresultapi.meet.RawMeet;


@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
	
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Meet.class, Competitor.class, RawMeet.class);
    }
}
