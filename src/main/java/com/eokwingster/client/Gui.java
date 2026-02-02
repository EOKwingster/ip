package com.eokwingster.client;

import java.util.function.Consumer;

import com.eokwingster.responsor.Response;

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

/**
 * Manage initialization and running of main window.
 */
public class Gui {
    private static final int MAIN_WIDTH = 400;
    private static final int MAIN_HEIGHT = 600;
    private static final int DIALOG_SPACING = 20;
    private static final int DIALOG_PADDING = 10;
    private static final double INPUT_BAR_ANCHOR = 20d;
    private static final double INPUT_FIELD_HEIGHT = 10;
    private static final double SCROLL_PANE_BOTTOM_ANCHOR = INPUT_BAR_ANCHOR + INPUT_FIELD_HEIGHT + 20;

    private Consumer<String> inputSender;

    private Stage stage;
    private VBox dialogContainer;
    private TextField inputField;

    /**
     * Initialize the main window.
     * @param stage Stage passed from the main class
     */
    public void init(Stage stage) {
        this.stage = stage;

        // create components
        AnchorPane rootPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        HBox inputBar = new HBox();
        inputField = new TextField();
        Button sendButton = new Button("Send");
        Scene scene = new Scene(rootPane, MAIN_WIDTH, MAIN_HEIGHT);

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
        AnchorPane.setBottomAnchor(inputBar, INPUT_BAR_ANCHOR);
        AnchorPane.setLeftAnchor(inputBar, INPUT_BAR_ANCHOR);
        AnchorPane.setRightAnchor(inputBar, INPUT_BAR_ANCHOR);
        scrollPane.setPadding(new Insets(
                0,
                0,
                SCROLL_PANE_BOTTOM_ANCHOR,
                0));
        inputField.setPrefHeight(INPUT_FIELD_HEIGHT);
        HBox.setHgrow(inputField, Priority.ALWAYS);

        // set appearance
        stage.setTitle("Wee");
        inputField.setPromptText("Input text ...");

        // set action
        sendButton.setOnAction(event -> handleInput());
        inputField.setOnAction(event -> handleInput());
    }

    /**
     * Show the window.
     */
    public void show() {
        stage.show();
    }

    /**
     * Converts response to a DialogBox and add into dialogContainer.
     * @param response response returned by responsor pipeline
     * @see DialogBox
     */
    public void addResponse(Response response) {
        dialogContainer.getChildren().add(new DialogBox(response.toString(), false));
    }

    /**
     * Converts user input to a DialogBox and add into dialogContainer.
     * @param input user input
     * @see DialogBox
     */
    public void addInput(String input) {
        dialogContainer.getChildren().add(new DialogBox(input, true));
    }

    /**
     * Close the window.
     */
    public void close() {
        stage.close();
    }

    private void handleInput() {
        String input = inputField.getText();
        inputField.clear();
        this.addInput(input);
        inputSender.accept(input);
    }

    /**
     * Handle the response sent from server
     * @param response
     */
    public void handleResponse(Response response) {
        this.addResponse(response);
        if (response.tags().contains(Response.Tag.EXIT)) {
            this.close();
        }
    }

    public void setServerInputHandler(Consumer<String> inputSender) {
        this.inputSender = inputSender;
    }
}
