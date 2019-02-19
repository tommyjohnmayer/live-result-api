package com.tm.liveresultapi.meet;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins="*")
public interface MeetRepository extends DatastoreRepository<Meet, String> {
	
}
