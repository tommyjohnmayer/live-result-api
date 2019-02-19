package com.tm.liveresultapi.result;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Key;

@Repository
public interface ResultRepository extends DatastoreRepository<Result, Key> {

}
