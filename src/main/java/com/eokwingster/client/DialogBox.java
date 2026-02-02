package com.eokwingster.client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Contains avatar and text of one dialog
 */
public class DialogBox extends HBox {
    private static final String USER_DEFAULT_AVATAR = "textures/avatar/user_default.png";
    private static final String WEE_DEFAULT_AVATAR = "textures/avatar/wee_default.png";
    private static final int VERTICAL_PADDING = 10;
    private static final int HORIZONTAL_PADDING = 20;
    private static final int AVATAR_RADIUS = 22;

    private final Label text;
    private final ImageView avatar;

    DialogBox(String s, boolean isUser) {
        text = new Label(s);
        text.setWrapText(true);
        text.setPadding(new Insets(VERTICAL_PADDING, HORIZONTAL_PADDING, VERTICAL_PADDING, HORIZONTAL_PADDING));
        if (isUser) {
            avatar = new ImageView(new Image(USER_DEFAULT_AVATAR));
            this.getChildren().addAll(text, avatar);
            this.setAlignment(Pos.TOP_RIGHT);
        } else {
            avatar = new ImageView(new Image(WEE_DEFAULT_AVATAR));
            this.getChildren().addAll(avatar, text);
            this.setAlignment(Pos.TOP_LEFT);
        }
        avatar.setFitHeight(AVATAR_RADIUS * 2);
        avatar.setFitWidth(AVATAR_RADIUS * 2);
        Circle circle = new Circle(AVATAR_RADIUS, AVATAR_RADIUS, AVATAR_RADIUS);
        avatar.setClip(circle);
    }
}
