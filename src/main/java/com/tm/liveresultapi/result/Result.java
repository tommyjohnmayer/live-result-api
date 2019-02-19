package com.tm.liveresultapi.result;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.cloud.datastore.Key;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="results")
public class Result {
	
	@Id
	@JsonIgnore
	private Key entityKey;
	
	private String id;
	private String meet;
	private String event;
	private String competitor;
	private String participant;
	private String time;

}
