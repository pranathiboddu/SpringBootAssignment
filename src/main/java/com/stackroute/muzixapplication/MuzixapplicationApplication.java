package com.stackroute.muzixapplication;

import com.stackroute.muzixapplication.domain.Music;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//implemented commandlinerunner and extended SpringBootServletInitializer
public class MuzixapplicationApplication
{
 //implemented methods
	/*MusicService musicService;
	@Autowired
	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(MuzixapplicationApplication.class);}*/

	public static void main(String[] args) {
		SpringApplication.run(MuzixapplicationApplication.class, args);
	}
/*
	//setting values to the music
	@Override
	public void run (String...args) throws Exception
		{
			try {
				musicService.saveTrack(new Music(6, "chainsmokers", "Excellent"));
				musicService.saveTrack(new Music(7, "one direction", "Excellent"));
				musicService.saveTrack(new Music(8, "vidyavox", "Excellent"));
			}
			catch (TrackAlreadyExistsException ex) {
				ex.printStackTrace();
			}
		}*/
	}





