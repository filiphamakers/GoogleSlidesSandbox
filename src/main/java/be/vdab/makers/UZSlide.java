package be.vdab.makers;

import com.google.api.services.slides.v1.model.CreateSlideRequest;

import java.util.UUID;

public class UZSlide {

    private CreateSlideRequest createSlideRequest;

    public UZSlide(){
        createSlideRequest = new CreateSlideRequest()
                .setObjectId(UUID.randomUUID().toString())
                .setInsertionIndex(1);
    }

    public void setLayout(Layout layout){
        createSlideRequest.getSlideLayoutReference().setPredefinedLayout(layout.toString());
    }

    CreateSlideRequest getCreateSlideRequest() {
        return createSlideRequest;
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
