package com.stackroute.muzixapplication.service;

import com.stackroute.muzixapplication.domain.Music;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

//created interface
public interface MusicService {
    //defined different methods

    public Music saveTrack(Music music) throws TrackAlreadyExistsException; //method for saving tracks

    public void deleteTrack(int trackId) throws TrackNotFoundException; //method for deleting track

    public List<Music> getAllTracks(); //method for getting all tracks

    public boolean updateTrack(Music music,int trackId); //method for updating track

    public List<Music> getTrackByName(String trackName) throws TrackNotFoundException;

    public String getTopTracks();
}
