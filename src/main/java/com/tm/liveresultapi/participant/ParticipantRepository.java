package com.tm.liveresultapi.participant;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Key;

@Repository
public interface ParticipantRepository extends DatastoreRepository<Participant, Key>{

}
