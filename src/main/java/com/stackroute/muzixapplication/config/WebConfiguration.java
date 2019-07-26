package com.stackroute.muzixapplication.config;

import com.stackroute.muzixapplication.domain.Music;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.service.MusicService;
import org.apache.catalina.servlets.WebdavServlet;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class WebConfiguration {

//  MusicService musicService;
//    public WebConfiguration(MusicService musicService)
//    {
//        this.musicService=musicService;
//    }
//    //using ContextRefreshedEvent
//    @EventListener
//    public void seedData(ContextRefreshedEvent contextRefreshedEvent) {
//        try {
//            musicService.saveTrack(new Music(1, "chainsmokers", "Excellent"));
//            musicService.saveTrack(new Music(2, "one direction", "Excellent"));
//            musicService.saveTrack(new Music(3, "vidyavox", "Excellent"));
//        }
//        catch (TrackAlreadyExistsException ex) {
//            ex.printStackTrace();
//        }
//    }
    @Bean
    ServletRegistrationBean h2servletRegistration() //for h2 console view
    {
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new WebServlet());
        servletRegistrationBean.addUrlMappings("/console/*");
        return servletRegistrationBean;
    }


}
