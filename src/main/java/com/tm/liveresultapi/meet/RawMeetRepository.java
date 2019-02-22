package com.tm.liveresultapi.meet;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins="*")
@RepositoryRestResource(exported=false, itemResourceRel="meet", collectionResourceRel="meets")
public interface RawMeetRepository extends DatastoreRepository<RawMeet, String> {

}
