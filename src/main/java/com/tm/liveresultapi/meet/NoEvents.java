package com.tm.liveresultapi.meet;

import java.time.LocalDate;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "no_events", types = { Meet.class })
public interface NoEvents {

	public String getId();

	public String getName();
	
	public LocalDate getDate();
	
}
