package com.stackroute.trackservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.GlobalException;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
//@RunWith(SpringRunner.class) class annotation used to tell JUnit to run the unit tests in Spring's testing supports

@WebMvcTest
// @WebMvcTest is a type of an integration test that only starts a specified slice of Spring Application
// and thus its execution time is significantly lower compared to full end-to-end tests.
public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    //@MockBean annotation used to add mock objects to the SpringApplicationContext
    private TrackService trackService;
    //Inject the mocks as dependencies into TrackController
    @InjectMocks
    private TrackController trackController;
    private List<Track> list = null;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        //MockitoAnnotations.initMocks(this) method has to called to initialize annotated fields.
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(new GlobalException()).build();
        track = new Track();
        track.setId(26);
        track.setName("Jonny");
        track.setComments("track is nice");
        list = new ArrayList();
        list.add(track);
    }

    @After
    public void tearDown() throws Exception {
        track=null;
        list=null;
    }

    @Test
    public void giveninputshouldReturnsaveTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService,times(1)).saveTrack(any());
    }

    @Test
    public void givenInputShouldReturnsaveUserFailure() throws Exception {
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService,times(1)).saveTrack(track);
    }

    @Test
    public void givenInputShouldgetTrackById() throws Exception {
        when(trackService.getById(1)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService,times(1)).getById(1);
    }


    @Test
    public void givenStringShouldfindByName() throws Exception {
        when(trackService.findByName("RajVishnu")).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/name")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(list)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService,times(1)).findByName(any());
    }

    @Test
    public void givenIntegerShoulddeleteTrackById() throws Exception {
        when(trackService.deleteTrackById(1)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
//        verify(trackService,times(2)).getById(1);
    }

    @Test
    public void givenInputShouldUpdatetrackUpdatedById() throws Exception {
        when(trackService.updateTrackById(1, track)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
//        verify(trackService,times(1));
    }

    @Test
    public void givenInputshouldReturngetAllTracks() throws Exception {
        when(trackService.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService,times(1)).getAllTracks();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}