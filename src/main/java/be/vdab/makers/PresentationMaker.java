package be.vdab.makers;

import com.google.api.services.slides.v1.Slides;
import com.google.api.services.slides.v1.model.Presentation;

import java.io.IOException;

public class PresentationMaker {

    public Presentation createNewPresentation(Slides slidesService, String title) throws IOException{
        Presentation presentation = new Presentation()
                .setTitle(title);

            presentation = slidesService.presentations().create(presentation)
                    .setFields("presentationId")
                    .execute();

        System.out.println("Created presentation with ID: " + presentation.getPresentationId());
        return presentation;
    }
}
