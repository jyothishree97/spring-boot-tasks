package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackNotFoundException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TrackServiceTest {

    private Track track;
    //Create a mock for trackRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setName("RajVishnu");
        track.setId(61);
        track.setComments("good");
        list = new ArrayList<>();
        list.add(track);

    }


    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);
        //verify here verifies that userRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }


    @Test(expected = TrackAlreadyExistsException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);
    }

    @Test
    public void getAllTracks() throws Exception {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> trackList = trackService.getAllTracks();
        Assert.assertEquals(list, trackList);
    }

    @Test
    public void getById() throws TrackNotFoundException {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        //First, it will check id exists or not if exists and executes next otherwise throws exception
        when(trackRepository.existsById(10)).thenReturn(true);
        when(trackRepository.findById(10)).thenReturn(track);
        Track track1 = trackService.getById(10);
        Assert.assertEquals(track, track1);
    }

    @Test
    public void findByName() throws TrackNotFoundException {
        //stubbing the mock to return specific data
        when(trackRepository.findByName(any())).thenReturn((list));
        List<Track> fetchTrack = trackService.findByName("rajvishnu");
        Assert.assertEquals(list, fetchTrack);

    }

    @Test
    public void deleteTrackById() throws TrackNotFoundException {
        //stubbing the mock to return specific data
        //First, it will check id exists or not if exists and executes next otherwise throws exception
        when(trackRepository.existsById(61)).thenReturn(true);
        when(trackRepository.findById(61)).thenReturn((track));
        Track deleteTrackById = trackService.getById(61);
        Assert.assertEquals(track, deleteTrackById);
    }

    @Test
    public void updateTrackById() throws TrackAlreadyExistsException {
        //stubbing the mock to return specific data
        //First, it will check id exists or not if exists and executes next otherwise throws exception
        when(trackRepository.existsById(101)).thenReturn(true);
        when(trackRepository.save(track)).thenReturn(track);
        Track updateTrackById = trackService.saveTrack(track);
        Assert.assertEquals(track, updateTrackById);
    }
}