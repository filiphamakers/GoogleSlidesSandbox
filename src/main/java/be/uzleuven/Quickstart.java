package be.uzleuven;

import be.uzleuven.database.GoogleSlidesClient;
import be.uzleuven.makers.UZPresentation;
import be.uzleuven.makers.UZSlide;
import be.uzleuven.presentations.Presentation;
import be.uzleuven.presentations.slides.Slide;

import java.io.IOException;

public class Quickstart {

    public static void main(String[] args) throws IOException {
        Presentation.Builder builder = new Presentation.Builder();
        for (int i = 0; i < 5; i++) {
            builder.addSlide(new Slide.Builder().build());
        }
        Presentation presentation = builder.build();
        GoogleSlidesClient.save(presentation);
    }
}
