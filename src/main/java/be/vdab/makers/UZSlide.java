package be.vdab.makers;

import com.google.api.services.slides.v1.Slides;
import com.google.api.services.slides.v1.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UZSlide {

    private List<Request> requests = new ArrayList<>();

    private UZSlide(Layout layout){
        requests.add(new Request()
                .setCreateSlide(new CreateSlideRequest()
                        .setObjectId(UUID.randomUUID().toString())
                        .setInsertionIndex(1)
                        .setSlideLayoutReference(new LayoutReference()
                                .setPredefinedLayout(layout.toString()))));
    }

    public CreateSlideResponse createNewSlideForPresentation(Slides slidesService, Presentation presentation) throws IOException{

        String presentationId = presentation.getPresentationId();
        BatchUpdatePresentationRequest body =
                new BatchUpdatePresentationRequest().setRequests(requests);
        BatchUpdatePresentationResponse response =
                slidesService.presentations().batchUpdate(presentationId, body).execute();
        CreateSlideResponse createSlideResponse = response.getReplies().get(0).getCreateSlide();
        System.out.println("Created slide with ID: " + createSlideResponse.getObjectId());

        return createSlideResponse;

    }

    public static class SlideBuilder{
        private Layout layout = Layout.TITLE_AND_TWO_COLUMNS;

        public SlideBuilder setLayout(Layout layout){
            this.layout = layout;
            return this;
        }

        public UZSlide createSlide(){
            return new UZSlide(layout);
        }
    }

    public static enum Layout{
        TITLE_AND_TWO_COLUMNS("TITLE_AND_TWO_COLUMNS");

        private String layout;

        Layout(String layout){
            this.layout = layout;
        }

        @Override
        public String toString(){
            return layout;
        }
    }

}
