package com.tm.liveresultapi.event;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.google.cloud.datastore.Key;

@RepositoryRestResource(exported=false)
public interface EventRepository extends DatastoreRepository<Event, Key> {
	
}
