package com.tm.liveresultapi.meet;

import com.tm.liveresultapi.event.Event;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Descendants;
import lombok.*;

@Getter
@Setter
public class Meet extends RawMeet{

	@Descendants
	private List<Event> events = new ArrayList<>();
	
}
