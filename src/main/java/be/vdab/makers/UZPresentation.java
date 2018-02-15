package be.vdab.makers;

import com.google.api.services.slides.v1.Slides;
import com.google.api.services.slides.v1.model.Presentation;

import java.io.IOException;

public class UZPresentation {

    private static Slides slidesService = UZSlidesService.slidesService;
    private Presentation googlePresentation;

    private UZPresentation(Presentation googlePresentation){
        this.googlePresentation = googlePresentation;
        try {
            googlePresentation = slidesService.presentations().create(googlePresentation)
                    .setFields("presentationId")
                    .execute();
        }catch (IOException ex) {

        }
    }

    public static class UZPresentationBuilder{
        private String title = "My Presentation";
        private Presentation googlePresentation;

        public UZPresentationBuilder(){
            googlePresentation = new Presentation();
        }

        public UZPresentationBuilder setTitle(String title){
            googlePresentation.setTitle(title);
            return this;
        }

        public UZPresentationBuilder addUZSlide(UZSlide slide){
            //insert code here
            return this;
        }

        public UZPresentation createUZPresentation(){
            return new UZPresentation(googlePresentation);
        }
    }

}
