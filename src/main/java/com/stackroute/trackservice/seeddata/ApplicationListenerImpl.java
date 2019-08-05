//package com.stackroute.trackservice.seeddata;
//
//import com.stackroute.trackservice.domain.Track;
//import com.stackroute.trackservice.repository.TrackRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//@Component
//@PropertySource("classpath:application.properties")
//public class ApplicationListenerImpl implements ApplicationListener {
//
//    @Value("${track.id}")
//    private int id;
//    @Value("${track.name}")
//    private String name;
//    @Value("${track.comments}")
//    private String comments;
//
//    Track track=new Track();
//    private TrackRepository trackRepository;
//    @Autowired
//    public ApplicationListenerImpl(TrackRepository trackRepository) {
//        this.trackRepository = trackRepository;
//    }
//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//
//        track.setId(id);
//        track.setName(name);
//        track.setComments(comments);
//        trackRepository.save(track);
//    }
//}
