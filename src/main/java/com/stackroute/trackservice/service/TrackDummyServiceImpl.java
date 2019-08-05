package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackNotFoundException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier
public class TrackDummyServiceImpl implements TrackService {

    private TrackRepository trackRepository;
    @Autowired
    public TrackDummyServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        return null;
    }

    @Override
    public List<Track> findByName(String name) throws TrackNotFoundException {
        return null;
    }

    @Profile("prod")
    @Override
    public Track getById(int id) throws TrackNotFoundException {
        return null;
    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        return null;
    }

    @Override
    public Track deleteTrackById(int id) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track updateTrackById(int id, Track track) throws TrackNotFoundException {
        return null;
    }
}
