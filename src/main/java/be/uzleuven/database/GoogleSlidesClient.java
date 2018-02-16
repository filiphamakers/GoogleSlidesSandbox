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
            String slideId = UUID.randomUUID().toString();
            requests.add(new Request()
                    .setCreateSlide(new CreateSlideRequest()
                            .setObjectId(slideId)
                            .setInsertionIndex(1)
                            .setSlideLayoutReference(new LayoutReference()
                                    .setPredefinedLayout(LayoutConverter.convert(uzSlide.getLayout())))));
        }

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

    private static class LayoutConverter{
        private static String convert(Slide.Layout layout){
            switch (layout){
                case TITLE_AND_TWO_COLUMNS: return "TITLE_AND_TWO_COLUMNS";
                default: throw new IllegalArgumentException();
            }
        }
    }
}
