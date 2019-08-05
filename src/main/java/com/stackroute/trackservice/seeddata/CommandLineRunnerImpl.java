//package com.stackroute.trackservice.seeddata;
//
//import com.stackroute.trackservice.domain.Track;
//import com.stackroute.trackservice.repository.TrackRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//@Component
//@PropertySource("classpath:application.properties")
//public class CommandLineRunnerImpl implements CommandLineRunner {
//
//    @Value("${track1.id}")
//    private int id;
//    @Value("${track1.name}")
//    private String name;
//    @Value("${track1.comments}")
//    private String comments;
//
//
//    Track track1=new Track();
//    private TrackRepository trackRepository;
//    @Autowired
//    public CommandLineRunnerImpl(TrackRepository trackRepository) {
//        this.trackRepository = trackRepository;
//    }
//    @Override
//    public void run(String... args) throws Exception {
//
//        track1.setId(id);
//        track1.setName(name);
//        track1.setComments(comments);
//        trackRepository.save(track1);
//    }
//}
