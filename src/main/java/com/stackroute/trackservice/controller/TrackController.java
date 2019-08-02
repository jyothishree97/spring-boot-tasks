package com.stackroute.trackservice.controller;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackNotFoundException;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TrackController {
    private TrackService trackService;

    public TrackController() {
    }

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> setTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("succefully created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable String name) {
        ResponseEntity responseEntity;
        try {
            List<Track> trackDetails = trackService.getTrackByName(name);
            responseEntity = new ResponseEntity<List<Track>>(trackDetails, HttpStatus.CREATED);
        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            Track trackDetails = trackService.getById(id);
            responseEntity = new ResponseEntity<Track>(trackDetails, HttpStatus.CREATED);
        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() {
        ResponseEntity responseEntity;
        try {
            List<Track> trackDetails = trackService.getAllTracks();
            responseEntity = new ResponseEntity(trackDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("tracks/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            Track trackDetails = trackService.getById(id);
            responseEntity = new ResponseEntity<Track>(trackDetails, HttpStatus.CREATED);
        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @PutMapping("tracks/{id}")
    public ResponseEntity<?> trackUpdateById(@PathVariable int id ,@RequestBody Track track) {

        ResponseEntity responseEntity;
        try {
            Track trackDetails = trackService.updateTrackById(id,track);
            responseEntity = new ResponseEntity<Track>(trackDetails, HttpStatus.UPGRADE_REQUIRED);
        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return responseEntity;
    }
}
