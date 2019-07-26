package com.stackroute.muzixapplication.controller;

import com.stackroute.muzixapplication.domain.Music;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import com.stackroute.muzixapplication.service.MusicService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController //created restcontroller annotation
@RequestMapping(value = "api/v1") //set path as api/v1
@Api(value = "Music Application") //swagger api description
public class MusicController {

    @Autowired
    MusicService musicService; //autowired Musicservice

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }
    //api operation value
    @ApiOperation(value = "Add an track")
    @PostMapping(value = "/save") //post mapping for saving the tracks
    public ResponseEntity<?> saveTrack(@ApiParam(value = "Track object store in database table", required = true) @Valid @RequestBody Music music)
    {
        ResponseEntity responseEntity;
        try {
            musicService.saveTrack(music);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @ApiOperation(value = "Update a track")
    @PutMapping(value = "/update/{trackId}") //put mapping for updating tracks
    public ResponseEntity<?> updateTrack(@ApiParam(value = "track Id to update Music object", required = true) @PathVariable int trackId,
                                         @ApiParam(value = "Update music object", required = true) @Valid @RequestBody Music music) {
        ResponseEntity responseEntity;
        try {
            musicService.updateTrack(music,trackId);
            responseEntity = new ResponseEntity<String>("Successfully updated", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "View a list of available tracks", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/get") //get mapping for getting all tracks
    public ResponseEntity<?> getAllTracks(@ApiParam(value = "Track object store in database table", required = true) @Valid @RequestBody Music music) {
        return new ResponseEntity<List<Music>>(musicService.getAllTracks(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{trackId}") //delete mapping for deleting track by id
    public ResponseEntity<?> deleteTrack(@ApiParam(value = "deleting row from table by trackId", required = true) @PathVariable int trackId) {
        ResponseEntity responseEntity;
        try {
            musicService.deleteTrack(trackId);
            responseEntity = new ResponseEntity<String>("Succesfully deleted", HttpStatus.NO_CONTENT);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;

    }

    @ApiOperation(value = "View a list of available tracks by track name", response = ResponseEntity.class)
    @GetMapping("/name/{trackName}")
    public ResponseEntity<?> getTrackByName(@ApiParam(value = "getting track by track name", required = true)@PathVariable String trackName) {
        ResponseEntity responseEntity;
        try {
            musicService.getTrackByName(trackName);
            responseEntity=new ResponseEntity<List<Music>>(musicService.getAllTracks(),HttpStatus.OK);
        }
        catch (TrackNotFoundException ex)
        {
           responseEntity= new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
   @GetMapping(value = "/top") //get mapping for getting all tracks
    public ResponseEntity<?> getTopTracks(@ApiParam(value = "Track object store in database table", required = true) @Valid @RequestBody Music music) {
     return new ResponseEntity<String>(musicService.getTopTracks(),HttpStatus.OK);

   }
}