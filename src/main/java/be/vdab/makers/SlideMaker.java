package be.vdab.makers;

import com.google.api.services.slides.v1.Slides;
import com.google.api.services.slides.v1.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SlideMaker {
    private static int currentSlideId = 1;


    public CreateSlideResponse createNewSlideForPresentation(Slides slidesService, Presentation presentation) throws IOException{
        UUID uuid = UUID.randomUUID();
        uuid.toString();
        List<Request> requests = new ArrayList<>();
        String slideId = String.format("%s%s","MyNewSlide_", currentSlideId++);

        requests.add(new Request()
                .setCreateSlide(new CreateSlideRequest()
                        .setObjectId(slideId)
                        .setInsertionIndex(1)
                        .setSlideLayoutReference(new LayoutReference()
                                .setPredefinedLayout("TITLE_AND_TWO_COLUMNS"))));

        String presentationId = presentation.getPresentationId();
        BatchUpdatePresentationRequest body =
                new BatchUpdatePresentationRequest().setRequests(requests);
        BatchUpdatePresentationResponse response =
                slidesService.presentations().batchUpdate(presentationId, body).execute();
        CreateSlideResponse createSlideResponse = response.getReplies().get(0).getCreateSlide();
        System.out.println("Created slide with ID: " + createSlideResponse.getObjectId());
        return createSlideResponse;

    }
}
