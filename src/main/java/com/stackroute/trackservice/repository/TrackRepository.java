package com.stackroute.trackservice.repository;

import com.stackroute.trackservice.domain.Track;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//
public interface TrackRepository extends MongoRepository<Track, Integer> {
    // @Query annotation used to create our own query with a suitable method name.
//    @Query(value = "select * from Track where name =?")
    public List<Track> findByName(String name);

    public Track findById(int id);

}
