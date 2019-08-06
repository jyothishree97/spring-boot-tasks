package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackNotFoundException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Primary

public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        Track savedTrack = trackRepository.save(track);
        if(savedTrack==null)
        {
            throw new TrackAlreadyExistsException("No response from the database") ;
        }
        return savedTrack;
    }

    @Override
    public Track getById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            Track retrivedTrack = trackRepository.findById(id);
            return retrivedTrack;

        } else {
            throw new TrackNotFoundException("Track Not Found Exception");
        }
    }

    @Profile("dev")
    @Override
    public List<Track> findByName(String name) throws TrackNotFoundException {
        if (trackRepository.findByName(name).isEmpty()) {
            throw new TrackNotFoundException("track not found");
        } else {
            List<Track> retrivedTrack = trackRepository.findByName(name);
            return retrivedTrack;
        }

    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        if (trackRepository.findAll().isEmpty()) {
            throw new Exception("Internal server error");
        } else {
            List<Track> alltrack = trackRepository.findAll();
            return alltrack;
        }
    }

    @Override
    public Track deleteTrackById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            Track deleteTrack = trackRepository.findById(id);
            trackRepository.deleteById(id);
            return deleteTrack;

        } else {
            throw new TrackNotFoundException("Track not found");
        }

    }

    @Override
    public Track updateTrackById(int id, Track track) throws TrackNotFoundException {

        if (trackRepository.existsById(id)) {
            Track trackUpdate = trackRepository.save(track);
            return trackUpdate;

        } else {
            throw new TrackNotFoundException("Track not found");
        }
    }
}
