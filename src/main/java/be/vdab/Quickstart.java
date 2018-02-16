package be.vdab;

import be.vdab.makers.UZPresentation;
import be.vdab.makers.UZSlide;

import java.io.IOException;

public class Quickstart {

    public static void main(String[] args) throws IOException {
        UZPresentation presentation = new UZPresentation("Met twee slides");
        for (int i = 0; i < 2; i++) {
            presentation.addUZSlide(new UZSlide());
        }

    }
}
