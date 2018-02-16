package be.vdab.presentations.slides;

import be.vdab.presentations.slides.elements.shapes.Box;

import java.util.ArrayList;
import java.util.List;

public class Slide {

    private final Layout layout;

    private Slide(Layout layout){
        this.layout = layout;
    }

    public static class SlideBuilder {

        Layout layout = Layout.TITLE_AND_TWO_COLUMNS;
        List<Box> shapes = new ArrayList<>(1);

        public SlideBuilder addShape(Box shape){
            shapes.add(shape);
            return this;
        }

        public SlideBuilder setLayout(Layout layout){
            this.layout = layout;
            return this;
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
