package be.uzleuven.database;

import be.uzleuven.presentations.slides.Slide;
import com.google.api.services.slides.v1.Slides;
import com.google.api.services.slides.v1.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GoogleSlidesClient {

    private static final Slides googleSlidesService = GoogleSlidesServiceInitializer.getInstance();

    public static void save(be.uzleuven.presentations.Presentation uzPresentation){
        com.google.api.services.slides.v1.model.Presentation googlePresentation
                = new com.google.api.services.slides.v1.model.Presentation()
                    .setTitle(uzPresentation.getTitle());
        try {
            googlePresentation = googleSlidesService.presentations().create(googlePresentation)
                    .setFields("presentationId")
                    .execute();
            List<Slide> slides = uzPresentation.getSlides();
            if (!slides.isEmpty()) {
                saveSlides(slides, googlePresentation);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void saveSlides(List<Slide> uzSlides,
                                  com.google.api.services.slides.v1.model.Presentation googlePresentation){

        List<Request> requests = new ArrayList<>(1);
        for (Slide uzSlide : uzSlides) {
            CreateSlideRequest googleSlide = SlideConverter.convert(new CreateSlideRequest(), uzSlide);
            requests.add(new Request()
                    .setCreateSlide(googleSlide));
        }
        pushToServer(requests,googlePresentation);
    }

    private static void pushToServer(List<Request> requests,
                                     com.google.api.services.slides.v1.model.Presentation googlePresentation){
        BatchUpdatePresentationRequest body =
                new BatchUpdatePresentationRequest().setRequests(requests);
        try {
            String presentationId = googlePresentation.getPresentationId();
            BatchUpdatePresentationResponse response =
                    googleSlidesService.presentations().batchUpdate(presentationId, body).execute();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
