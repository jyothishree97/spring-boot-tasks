package com.stackroute.trackservice.repository;

import com.stackroute.trackservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@RunWith(SpringRunner.class) class annotation used to tell JUnit to run the unit tests in Spring's testing supports
//@DataJpaTest
//@DataJPATest class annoation used to test JPA and Hibernate layer
@SpringBootTest
// The @SpringBootTest annotation, Spring Boot provides a convenient way to start up an application context
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUP() {
        track = new Track();
        track.setId(21);
        track.setName("Rajkumar");
        track.setComments("Nice track");
    }

    @After
    public void tearDown() {
        trackRepository.deleteAll();
    }

    @Test
    public void testSaveTrack() {
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId());
        Assert.assertEquals(21, fetchTrack.getId());

    }

    @Test
    public void findByName() {
//        trackRepository.save(track);
        List<Track> fetchTrack = trackRepository.findByName("Rajkumar");
        Assert.assertEquals("Rajkumar", track.getName());
    }

    @Test
    public void findById() {
//        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId());
        Assert.assertEquals(21, track.getId());
    }

    @Test
    public void testSaveTrackFailure() {
        Track testtrack = new Track(41, "vishnu", "good");
//        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId());
        List<Track> fetchTrack1 = trackRepository.findByName(track.getName());
        Assert.assertNotSame(testtrack, fetchTrack);
        Assert.assertNotEquals(testtrack, fetchTrack1);
    }

}