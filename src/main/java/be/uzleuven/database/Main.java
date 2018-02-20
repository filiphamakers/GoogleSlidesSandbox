package be.uzleuven.database;

import com.google.api.services.slides.v1.Slides;
import com.google.api.services.slides.v1.model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm");
        String timeStamp = dtf.format(LocalDateTime.now()).toString();

        Slides slidesService = GoogleSlidesServiceInitializer.getInstance();
        Presentation presentation = new Presentation().setTitle(timeStamp);
        List<Request> requests = new ArrayList<>(1);

        try {
            presentation = slidesService.presentations().create(presentation)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //make some blank slides
        for (int i = 0; i < 3; i++) {
            String shapeType = "TEXT_BOX";
            if (i==1){
                shapeType = "RECTANGLE";
            }
            if (i==2){
                shapeType = "TRIANGLE";
            }

            CreateSlideRequest slide = new CreateSlideRequest();
            CreateShapeRequest shape = new CreateShapeRequest().setObjectId(UUID.randomUUID().toString());
            InsertTextRequest text = new InsertTextRequest().setObjectId(UUID.randomUUID().toString());

            Dimension pt350 = new Dimension().setMagnitude(350.0).setUnit("PT");
            shape.setShapeType(shapeType)
                    .setElementProperties(new PageElementProperties()
                            .setSize(new Size()
                                    .setHeight(pt350)
                                    .setWidth(pt350))
                            .setPageObjectId(slide.getObjectId()));

            text.setObjectId(shape.getObjectId()).setText("Dit is een test");

            requests.add(new Request().setCreateSlide(slide));
            requests.add(new Request().setCreateShape(shape));
            requests.add(new Request().setInsertText(text));
        }

        BatchUpdatePresentationRequest body =
                new BatchUpdatePresentationRequest().setRequests(requests);
        try {
            BatchUpdatePresentationResponse response =
                    slidesService.presentations().batchUpdate(presentation.getPresentationId(), body).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
