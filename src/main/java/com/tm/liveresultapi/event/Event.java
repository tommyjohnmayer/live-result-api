package com.tm.liveresultapi.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Descendants;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.cloud.datastore.Key;
import com.tm.liveresultapi.participant.Participant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="events")
public class Event {

	@Id
	@JsonIgnore
	private Key entityKey;
	
	private String id;
	private String name;
	private String meet;
	
	@Descendants
	private List<Participant> participants = new ArrayList<>();
	
}
