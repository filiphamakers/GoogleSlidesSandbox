package be.vdab;

import be.vdab.makers.UZPresentation;
import be.vdab.makers.UZSlide;

import java.io.IOException;

public class Quickstart {

    public static void main(String[] args) throws IOException {
        UZPresentation presentation = new UZPresentation("Met twee slides");
        presentation.addUZSlide(new UZSlide());
        presentation.addUZSlide(new UZSlide());
    }
}
