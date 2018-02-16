package be.uzleuven.database;

import be.uzleuven.presentations.slides.Slide;
import com.google.api.services.slides.v1.model.CreateSlideRequest;

import java.util.UUID;

class SlidePopulator {

    CreateSlideRequest populate(CreateSlideRequest googleSlide, Slide slide){
        googleSlide
        return googleSlide;
    }

    private void setIdandInsertionIndex(CreateSlideRequest googleSlide){
        googleSlide
                .setObjectId(UUID.randomUUID().toString())
                .setInsertionIndex(1);
    }

}
