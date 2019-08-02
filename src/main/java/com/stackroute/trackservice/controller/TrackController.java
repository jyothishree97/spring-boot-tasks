package com.stackroute.trackservice.controller;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TrackController
{
    private TrackService trackService;

    public TrackController() {
    }

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> setTrack(@RequestBody Track track){
        Track savedTrack=trackService.saveTrack(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    public  ResponseEntity<?>  getTrackById(@PathVariable int id){
        Track obtainedTrack=trackService.getById(id);
        return new ResponseEntity<>(obtainedTrack, HttpStatus.OK);
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks()
    {
        List<Track> trackList=trackService.getAllTracks();
        return new ResponseEntity<>(trackList,HttpStatus.OK);
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id){
        Track deletedTrack=trackService.deleteTrackById(id);
        return new ResponseEntity<>(deletedTrack,HttpStatus.OK);
    }
    @PutMapping("track")
    public ResponseEntity<?> trackUpdate(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        Track trackupdated=trackService.UpdateTrack(track);
        responseEntity=new ResponseEntity("successfully updated",HttpStatus.CREATED);
        return responseEntity;
    }
}
