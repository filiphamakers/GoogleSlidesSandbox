package be.uzleuven;

import be.uzleuven.database.GoogleSlidesClient;
import be.uzleuven.makers.UZPresentation;
import be.uzleuven.makers.UZSlide;
import be.uzleuven.presentations.Presentation;
import be.uzleuven.presentations.slides.Slide;

import java.io.IOException;

public class Quickstart {

    public static void main(String[] args) throws IOException {
        Presentation.Builder presentationBuilder = new Presentation.Builder();

        Slide.Builder slideBuilder2 = new Slide.Builder();
        Slide slide2 = slideBuilder2.setLayout(Slide.Layout.TITLE_AND_TWO_COLUMNS).build();
        presentationBuilder.addSlide(slide2);

        Presentation presentation = presentationBuilder
                .build();
        GoogleSlidesClient.save(presentation);
    }
}
