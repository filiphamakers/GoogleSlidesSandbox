package be.vdab.presentations;

import be.vdab.presentations.slides.Slide;

import java.util.ArrayList;
import java.util.List;

public class Presentation {
    private final String title;
    private final List<Slide> slides;

    private Presentation(String title, List<Slide> slides){
        this.title = title;
        this.slides = slides;
    }

    public static class UZPresenationBuilder{

        private String title = "My UZ Presentation";
        private List<Slide> slides = new ArrayList<>(1);

        public UZPresenationBuilder setTitle(String title){
            this.title = title;
            return this;
        }

        public UZPresenationBuilder addSlide(Slide slide){
            slides.add(slide);
            return this;
        }

        public Presentation build(){
            return new Presentation(title, slides);
        }
    }

}
