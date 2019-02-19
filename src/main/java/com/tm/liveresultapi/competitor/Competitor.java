package com.tm.liveresultapi.competitor;


import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="competitors")
@Getter
@Setter
public class Competitor {

	@Id
	private String id;
	
	private String name;
	
	private String division;

}
