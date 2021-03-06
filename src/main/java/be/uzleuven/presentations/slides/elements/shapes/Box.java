package be.uzleuven.presentations.slides.elements.shapes;

import be.uzleuven.presentations.slides.contents.Content;
import be.uzleuven.presentations.slides.contents.Text;

public class Box implements Shape {

    private final Content content;

    private Box(Content content){
        this.content = content;
    }

    public static class ShapeBuilder{

        private Content content = new Text("");

        public ShapeBuilder addContent(Content content){
            this.content = content;
            return this;
        }

        public Box build(){
            return new Box(content);
        }
    }
}
