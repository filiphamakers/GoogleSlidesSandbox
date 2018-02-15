package be.vdab;

import be.vdab.makers.UZPresentation;

import java.io.IOException;

public class Quickstart {

    public static void main(String[] args) throws IOException {
        UZPresentation presentation = new UZPresentation.UZPresentationBuilder()
                .setTitle("Nog een Presentatie")
                .createUZPresentation();
    }
}
