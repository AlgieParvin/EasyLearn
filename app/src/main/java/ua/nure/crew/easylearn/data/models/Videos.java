package ua.nure.crew.easylearn.data.models;

import  ua.nure.crew.easylearn.exceptions.ContentTypeException;
import  ua.nure.crew.easylearn.exceptions.InitializationException;
import  ua.nure.crew.easylearn.data.xmlHandlers.XmlParsable;

import java.util.ArrayList;
import java.util.List;

public class Videos implements XmlParsable {
    private boolean initialized;
    private List<Video> videos;


    public Videos() { this.videos = new ArrayList<>(); }

    public Videos(List<Video> videos){
        this.videos = new ArrayList<>(videos);
    }

    public List<Video> getVideos(){
        return videos;
    }

    public void addVideo(Video video){ videos.add(video); }

    @Override
    public void addData(XmlParsable part) throws ContentTypeException, InitializationException {
        if (initialized)
            throw new InitializationException("Already initialized.");

        if (part instanceof Video)
        {
            Video video = (Video) part;
            this.videos.add(video);
        }
        else
        {
            throw new ContentTypeException("Only videos can be added.");
        }
    }

    @Override
    public void endInit() {
        initialized = true;
    }
}
