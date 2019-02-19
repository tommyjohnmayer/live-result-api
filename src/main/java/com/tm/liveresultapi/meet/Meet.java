package com.tm.liveresultapi.meet;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tm.liveresultapi.event.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Descendants;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

import lombok.*;

@Entity(name="meets")
@Getter
@Setter
public class Meet {
	@Id
	private String id;

	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@Descendants
	private List<Event> events = new ArrayList<>();
	
}
