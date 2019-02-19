package com.tm.liveresultapi.competitor;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins="*")
public interface CompetitorRepository extends DatastoreRepository<Competitor, String> {

}
