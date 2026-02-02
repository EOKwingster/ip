package com.eokwingster.client;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui {
    private static final int MAIN_WIDTH = 400;
    private static final int MAIN_HEIGHT = 600;
    private static final int DIALOG_SPACING = 20;
    private static final int DIALOG_PADDING = 10;
    private static final double INPUT_BAR_BOTTOM_ANCHOR = 10d;
    private static final double INPUT_BAR_SIDE_ANCHOR = 20d;
    private static final int INPUT_BAR_SPACING = 10;

    private final Stage stage;
    private AnchorPane rootPane;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private HBox inputBar;
    private TextField inputField;
    private Button sendButton;
    private Scene scene;

    public Gui(Stage stage) {
        this.stage = stage;
        init();
    }

    private void init() {
        // create components
        rootPane = new AnchorPane();
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        inputBar = new HBox();
        inputField = new TextField();
        sendButton = new Button("Send");
        scene = new Scene(rootPane, MAIN_WIDTH, MAIN_HEIGHT);

        // set inclusion relationship
        stage.setScene(scene);
        rootPane.getChildren().addAll(scrollPane, inputBar);
        scrollPane.setContent(dialogContainer);
        inputBar.getChildren().addAll(inputField, sendButton);

        // set arrangement
        AnchorPane.setTopAnchor(scrollPane, 0d);
        AnchorPane.setBottomAnchor(scrollPane, 0d);
        AnchorPane.setLeftAnchor(scrollPane, 0d);
        AnchorPane.setRightAnchor(scrollPane, 0d);
        scrollPane.setFitToWidth(true);
        dialogContainer.setSpacing(DIALOG_SPACING);
        dialogContainer.setPadding(new Insets(DIALOG_PADDING));
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
        AnchorPane.setBottomAnchor(inputBar, INPUT_BAR_BOTTOM_ANCHOR);
        AnchorPane.setLeftAnchor(inputBar, INPUT_BAR_SIDE_ANCHOR);
        AnchorPane.setRightAnchor(inputBar, INPUT_BAR_SIDE_ANCHOR);
        inputBar.setSpacing(INPUT_BAR_SPACING);
        HBox.setHgrow(inputField, Priority.ALWAYS);

        // set appearance
        stage.setTitle("Wee");
        inputField.setPromptText("Input text ...");
    }

    public void show() {
        stage.show();
    }
}
