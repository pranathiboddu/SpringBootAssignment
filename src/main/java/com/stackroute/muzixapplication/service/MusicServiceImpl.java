package com.stackroute.muzixapplication.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.muzixapplication.domain.Music;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import com.stackroute.muzixapplication.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Track;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService {

    MusicRepository musicRepository;

    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    //method to save track
    @Override
    public Music saveTrack(Music music) throws TrackAlreadyExistsException //handling exceptions
    {
        if (musicRepository.existsById(music.getTrackId())) {
            throw new TrackAlreadyExistsException();
        }
        Music savedTrack = musicRepository.save(music);
        if (savedTrack == null) {
            throw new TrackAlreadyExistsException();
        }
        return savedTrack;

    }

    //method to delete track
    @Override
    public void deleteTrack(int trackId) throws TrackNotFoundException {
        if (!musicRepository.existsById(trackId)) {
            throw new TrackNotFoundException();
        }
        musicRepository.deleteById(trackId);

    }

    //metod to get all tracks
    @Override
    public List<Music> getAllTracks() {
        return musicRepository.findAll();
    }


    //method to update track by id
    @Override
    public boolean updateTrack(Music music, int trackId) {
        Optional<Music> optionalMusic = musicRepository.findById(trackId);
        if (!optionalMusic.isPresent()) {
            return false;
        } else {
            music.setTrackId(trackId);
            musicRepository.save(music);
            return true;
        }
    }

    //method to track music by name
    @Override
    public List<Music> getTrackByName(String trackName) throws TrackNotFoundException {
        List<Music> optionalMusic = musicRepository.findTrackByName(trackName);
        if (optionalMusic.isEmpty())  //checking if track exists or not
        {
            throw new TrackNotFoundException();
        } else {
            List<Music> musicTrack = musicRepository.findTrackByName(trackName);
            return musicTrack;
        }
    }

    //method to get top tracks
    @Override
    public String getTopTracks() {
        RestTemplate restTemplate = new RestTemplate();
        final String ROOT_URI = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=de80a51b86794f96b196ee844c2e073f&format=json";
        ObjectMapper mapper = new ObjectMapper();
        String topTracks = restTemplate.getForObject(ROOT_URI, String.class);
//        Music m = new Music();
        try {
//            converting string as a json node
            JsonNode rootNode = mapper.readTree(topTracks);
            ArrayNode arrayNode = (ArrayNode) rootNode.path("tracks").path("track");
            //iterate the JSON array
            for (int i = 0; i < arrayNode.size(); i++) {
                //get a new Music object and fill it with data using setters
                Music music = new Music();
                music.setTrackId(i);
                music.setTrackName(arrayNode.get(i).path("name").asText());
                music.setTrackComments(arrayNode.get(i).path("artist").path("name").asText());
                //save the track to database
                musicRepository.save(music);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topTracks;
    }
}