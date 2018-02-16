package be.uzleuven.database;

import be.uzleuven.presentations.slides.Slide;
import com.google.api.services.slides.v1.model.CreateSlideRequest;
import com.google.api.services.slides.v1.model.LayoutReference;

import java.util.UUID;

/**
 * Provides methods that convert properties from be.uzleuven.Slide to their counterparts from
 * com.google.api.services.slides.v1.model.CreateSlideRequest
 */
class SlideConverter {

    static CreateSlideRequest convert(CreateSlideRequest googleSlide, Slide slide){
       setObjectId(googleSlide);
       setInsertionIndex(googleSlide);
       convertLayout(googleSlide, slide);
       return googleSlide;
    }

    private static void setObjectId(CreateSlideRequest googleSlide){
        googleSlide.setObjectId(UUID.randomUUID().toString());
    }

    private static void setInsertionIndex(CreateSlideRequest googleSlide){
        googleSlide.setInsertionIndex(1);
    }

    private static void convertLayout(CreateSlideRequest googleSlide, Slide slide){
        googleSlide.getSlideLayoutReference()
                .setPredefinedLayout(LayoutConverter.convert(slide.getLayout()));
    }

    private static class LayoutConverter{
        private static String convert(Slide.Layout layout){
            switch (layout){
                case TITLE_AND_TWO_COLUMNS: return "TITLE_AND_TWO_COLUMNS";
                default: throw new IllegalArgumentException();
            }
        }
    }

}
