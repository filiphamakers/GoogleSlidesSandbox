package be.uzleuven.presentations.slides;

import be.uzleuven.presentations.slides.elements.shapes.Box;

import java.util.ArrayList;
import java.util.List;

public class Slide {

    private final Layout layout;

    private Slide(Layout layout){
        this.layout = layout;
    }

    public Layout getLayout(){
        return layout;
    }

    public static class Builder {

        Layout layout = Layout.TITLE_AND_TWO_COLUMNS;
        List<Box> shapes = new ArrayList<>(1);

        public Builder addShape(Box shape){
            shapes.add(shape);
            return this;
        }

        public Builder setLayout(Layout layout){
            this.layout = layout;
            return this;
        }

        public Slide build(){
            return new Slide(layout);
        }
    }

    public static enum Layout{
        TITLE_AND_TWO_COLUMNS
    }
}
