package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> findByName(String name) throws TrackNotFoundException;

    public Track getById(int id) throws TrackNotFoundException;

    public List<Track> getAllTracks() throws Exception;

    public Track deleteTrackById(int id) throws TrackNotFoundException;

    public Track updateTrackById(int id, Track track) throws TrackNotFoundException;
}
