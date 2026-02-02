package com.eokwingster.client;

import javafx.stage.Stage;

public class Gui {
    private final Stage stage;

    public Gui(Stage stage) {
        this.stage = stage;
        init();
    }

    private void init() {

    }

    public void show() {
        stage.show();
    }
}
