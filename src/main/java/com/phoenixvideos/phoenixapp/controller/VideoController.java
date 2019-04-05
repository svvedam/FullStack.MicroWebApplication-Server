package com.phoenixvideos.phoenixapp.controller;

import com.phoenixvideos.phoenixapp.model.User;
import com.phoenixvideos.phoenixapp.model.Video;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
public class VideoController {

    Set<Video> returnList = new HashSet<>();




    @GetMapping("/videos/all")
    public ResponseEntity<Set<Video>> getAllVideos() {

//        returnList.add(new Video("Video1");
//        returnList.add("Video2");
//        returnList.add("Video3");
//        returnList.add("Video4");
//        returnList.add("Video5");
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<Video> getVideo(@PathVariable Long id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PutMapping("/videos/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody Video video) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/video/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteVideo(@PathVariable Long id) {

    }




}