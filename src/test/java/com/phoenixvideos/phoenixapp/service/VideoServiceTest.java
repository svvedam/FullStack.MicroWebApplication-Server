package com.phoenixvideos.phoenixapp.service;

import com.phoenixvideos.phoenixapp.PhoenixappApplicationTests;
import com.phoenixvideos.phoenixapp.model.Video;
import com.phoenixvideos.phoenixapp.repository.UserRepository;
import com.phoenixvideos.phoenixapp.repository.VideoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PhoenixappApplicationTests.class)
public class VideoServiceTest {
    @MockBean
    private VideoRepository videoRepository;
    @MockBean
    private UserRepository userRepository;

    private VideoService service;

    @Before
    public void setup(){
        this.service = new VideoService(videoRepository, userRepository);
    }

    @Test
    public void getAllVideosTest() throws Exception {

        String testPath = "https://s3.us-east-2.amazonaws.com/phoenix.videos/BigBuckBunny.mp4";
        String testPath2 = "https://s3.us-east-2.amazonaws.com/phoenix.videos/IMG_4786.mp4";
        ArrayList<Video> videos = new ArrayList<>();

        videos.add(new Video("BigBuckBunny", testPath, 2L));
        videos.add(new Video("Zipcode window", testPath2,3L));

        BDDMockito
                .given(videoRepository.findAll())
                .willReturn(videos);

        Iterable<Video> returnedVideos = service.index();

        Iterator<Video> iterator = returnedVideos.iterator();

        Video video1 = iterator.next();
        Video video2 = iterator.next();

        boolean hasNext = iterator.hasNext();
        Assert.assertEquals(video1.getId().longValue(), 2L);
        Assert.assertEquals(video2.getId().longValue(), 3L);

    }

    @Test
    public void getAllVideosWhenNoVideosPresentTest() throws Exception {

        ArrayList<Video> videos = new ArrayList<>();
        BDDMockito
                .given(videoRepository.findAll())
                .willReturn(videos);

        Iterable<Video> returnedVideos = service.index();

        Iterator<Video> iterator = returnedVideos.iterator();

        boolean hasNext = iterator.hasNext();
        Assert.assertEquals(hasNext, false);
    }

    @Test
    public void getVideoTest() throws Exception {
        String testPath = "https://s3.us-east-2.amazonaws.com/phoenix.videos/BigBuckBunny.mp4";
        Video video = new Video("BigBuckBunny", testPath, 2L);

        BDDMockito
                .given(videoRepository.findById(2L))
                .willReturn( Optional.of(video));

        Video returnedVideo = service.getVideo(2L);
        Assert.assertEquals(video, returnedVideo);
    }
    @Test
    public void getVideoNegetiveTest() throws Exception {
        String testPath = "https://s3.us-east-2.amazonaws.com/phoenix.videos/BigBuckBunny.mp4";
        String testPath2 = "https://s3.us-east-2.amazonaws.com/phoenix.videos/IMG_4786.mp4";
        ArrayList<Video> videos = new ArrayList<>();

        videos.add(new Video("BigBuckBunny", testPath, 2L));
        videos.add(new Video("Zipcode window", testPath2,3L));

        BDDMockito
                .given(videoRepository.findById(2L))
                .willReturn( Optional.empty());

        Video video = service.getVideo(2L);
        Assert.assertEquals(video, null);
    }
}