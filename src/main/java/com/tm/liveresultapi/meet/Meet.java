package com.tm.liveresultapi.meet;

import com.tm.liveresultapi.event.Event;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Descendants;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Meet extends RawMeet{
	public Meet(RawMeet meet) {
		this.setId(meet.getId());
		this.setName(meet.getName());
		this.setDate(meet.getDate());
	}

	@Descendants
	private List<Event> events = new ArrayList<>();
	
}
