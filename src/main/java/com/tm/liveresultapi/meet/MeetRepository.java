package com.tm.liveresultapi.meet;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins="*")
@RepositoryRestResource(path="meets", excerptProjection=NoEvents.class)
public interface MeetRepository extends DatastoreRepository<Meet, String> {

	@Override
	@RestResource(exported=false)
	default Page<Meet> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
