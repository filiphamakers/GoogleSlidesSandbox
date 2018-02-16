package be.vdab.makers;

import com.google.api.services.slides.v1.Slides;
import com.google.api.services.slides.v1.model.BatchUpdatePresentationRequest;
import com.google.api.services.slides.v1.model.BatchUpdatePresentationResponse;
import com.google.api.services.slides.v1.model.Presentation;
import com.google.api.services.slides.v1.model.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UZPresentation {

    private static Slides slidesService = UZSlidesService.slidesService;
    private List<Request> requests = new ArrayList<>();
    private Presentation googlePresentation;

    public UZPresentation(String title){
        googlePresentation = new Presentation().setTitle(title);
        try {
            googlePresentation = slidesService.presentations().create(googlePresentation)
                    .setFields("presentationId")
                    .execute();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String getTitle(){
        return googlePresentation.getTitle();
    }

    public void addUZSlide(UZSlide slide){
        requests.add(new Request()
                .setCreateSlide(slide.getCreateSlideRequest()));
        updatePresentation();
    }

    private void updatePresentation(){
        String presentationId = googlePresentation.getPresentationId();
        BatchUpdatePresentationRequest body =
                new BatchUpdatePresentationRequest().setRequests(requests);
        try {
            BatchUpdatePresentationResponse response =
                    slidesService.presentations().batchUpdate(presentationId, body).execute();
        } catch(IOException ex){
            ex.printStackTrace();
        } finally {
            requests = new ArrayList<>(1);
        }
    }

}
