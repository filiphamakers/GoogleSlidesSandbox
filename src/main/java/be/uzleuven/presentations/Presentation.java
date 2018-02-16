package be.uzleuven.presentations;

import be.uzleuven.presentations.slides.Slide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Presentation {
    private final String title;
    private final List<Slide> slides;

    private Presentation(String title, List<Slide> slides){
        this.title = title;
        this.slides = slides;
    }

    public String getTitle(){
        return title;
    }

    public List<Slide> getSlides(){
        return Collections.unmodifiableList(slides);
    }

    public static class Builder{

        private String title = "My UZ Presentation";
        private List<Slide> slides = new ArrayList<>(1);

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder addSlide(Slide slide){
            slides.add(slide);
            return this;
        }

        public Presentation build(){
            return new Presentation(title, slides);
        }
    }

}
