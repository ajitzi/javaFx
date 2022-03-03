package ajitzi.color.controller;

import ajitzi.color.model.Color;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ColorController implements Initializable {

    private final Color color = new Color(0, 0, 0);

    @FXML
    private Label labelRed;

    @FXML
    private Label labelGreen;

    @FXML
    private Label labelBlue;

    @FXML
    private Label labelHex;

    @FXML
    private TextField textFieldRed;

    @FXML
    private Slider sliderRed;

    @FXML
    private TextField textFieldGreen;

    @FXML
    private Slider sliderGreen;

    @FXML
    private TextField textFieldBlue;

    @FXML
    private Slider sliderBlue;

    @FXML
    private TextField textFieldHex;

    @FXML
    private Rectangle rectColor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initSlider(sliderRed);
        initSlider(sliderGreen);
        initSlider(sliderBlue);

        initFieldRGB(textFieldRed);
        initFieldRGB(textFieldGreen);
        initFieldRGB(textFieldBlue);

        initFieldHex();
    }

    private void initSlider(Slider s) {
        TextField textfield = s.equals(sliderRed) ? textFieldRed : s.equals(sliderGreen) ? textFieldGreen : textFieldBlue;
        Label label = s.equals(sliderRed) ? labelRed : s.equals(sliderGreen) ? labelGreen : labelBlue;

        s.setMin(0);
        s.setMax(255);
        s.valueProperty().addListener((observableValue, number, t1) -> {

            if (s.equals(sliderRed)) {
                color.setRed((int) s.getValue());
            } else if (s.equals(sliderGreen)) {
                color.setGreen((int) s.getValue());
            } else {
                color.setBlue((int) s.getValue());
            }

            textFieldHex.setText(color.getHexValue());
            rectColor.setFill(Paint.valueOf(color.getHexValue()));
            textfield.setText(String.valueOf((int) s.getValue()));
            label.setTextFill(javafx.scene.paint.Color.web("#000000"));
            labelHex.setTextFill(javafx.scene.paint.Color.web("#000000"));
        });
    }


    private void initFieldRGB(TextField t) {
        Label label = t.equals(textFieldRed) ? labelRed : t.equals(textFieldGreen) ? labelGreen : labelBlue;
        Slider slider = t.equals(textFieldRed) ? sliderRed : t.equals(textFieldGreen) ? sliderGreen : sliderBlue;

        t.setOnKeyTyped(keyEvent -> {
            try {
                if (t.equals(textFieldRed)) {
                    color.setRed( Integer.parseInt(t.getText()) );
                }
                else if (t.equals(textFieldGreen)) {
                    color.setGreen( Integer.parseInt(t.getText()) );
                } else {
                    color.setBlue( Integer.parseInt(t.getText()) );
                }

                rectColor.setFill(Paint.valueOf(color.getHexValue()));
                textFieldHex.setText(color.getHexValue());
                slider.setValue(Double.parseDouble(t.getText()));
                t.end();
                System.out.println(color);
                labelHex.setTextFill(javafx.scene.paint.Color.web("#000000"));
                label.setTextFill(javafx.scene.paint.Color.web("#000000"));
            } catch (Exception e) {
                label.setTextFill(javafx.scene.paint.Color.web("#FF0000"));
            }
        });
    }


    private void initFieldHex() {
        textFieldHex.setOnKeyTyped(keyEvent -> {
            try {
                color.setHexValue(textFieldHex.getText());
                sliderRed.setValue(color.getRed());
                sliderGreen.setValue(color.getGreen());
                sliderBlue.setValue(color.getBlue());
                textFieldRed.setText(String.valueOf(color.getRed()));
                textFieldGreen.setText(String.valueOf(color.getGreen()));
                textFieldBlue.setText(String.valueOf(color.getBlue()));

                labelHex.setTextFill(javafx.scene.paint.Color.web("#000000"));
                textFieldHex.end();
            } catch (Exception e) {
                labelHex.setTextFill(javafx.scene.paint.Color.web("#FF0000"));
            }
        });
    }
}