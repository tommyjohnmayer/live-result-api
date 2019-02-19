package com.tm.liveresultapi.participant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Descendants;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.cloud.datastore.Key;
import com.tm.liveresultapi.result.Result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="participants")
public class Participant {

	@Id
	@JsonIgnore
	private Key entityKey;
	
	private String id;
	private String meet;
	private String event;
	private String competitor;
	private String division;
	private Long order;
	@Descendants
	private List<Result> results = new ArrayList<>();
	
}
