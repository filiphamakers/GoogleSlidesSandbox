package be.vdab.makers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.slides.v1.Slides;
import com.google.api.services.slides.v1.model.*;

public class ContentMaker {
    private static int currentTextBoxId = 1;

    public void createNewContentForSlide(Slides slidesService, Presentation presentation, CreateSlideResponse slide)
        throws IOException{

        List<Request> requests = new ArrayList<>();
        String textBoxId = String.format("%s%s","MyTextBox_", currentTextBoxId++);
        String slideId = slide.getObjectId();
        String presentationId = presentation.getPresentationId();
        Dimension pt350 = new Dimension().setMagnitude(350.0).setUnit("PT");
        requests.add(new Request()
                .setCreateShape(new CreateShapeRequest()
                        .setObjectId(textBoxId)
                        .setShapeType("TEXT_BOX")
                        .setElementProperties(new PageElementProperties()
                                .setPageObjectId(slideId)
                                .setSize(new Size()
                                        .setHeight(pt350)
                                        .setWidth(pt350))
                                .setTransform(new AffineTransform()
                                        .setScaleX(1.0)
                                        .setScaleY(1.0)
                                        .setTranslateX(350.0)
                                        .setTranslateY(100.0)
                                        .setUnit("PT")))));

        // Insert text into the box, using the object ID given to it.
        requests.add(new Request()
                .setInsertText(new InsertTextRequest()
                        .setObjectId(textBoxId)
                        .setInsertionIndex(0)
                        .setText("New Box Text Inserted")));

        // Execute the requests.
        BatchUpdatePresentationRequest body =
                new BatchUpdatePresentationRequest().setRequests(requests);
        BatchUpdatePresentationResponse response =
                slidesService.presentations().batchUpdate(presentationId, body).execute();
        CreateShapeResponse createShapeResponse = response.getReplies().get(0).getCreateShape();
        System.out.println("Created textbox with ID: " + createShapeResponse.getObjectId());
    }
}
