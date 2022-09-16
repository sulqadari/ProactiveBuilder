package ru.mos.beeline.proactive.builder;

import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import java.nio.charset.StandardCharsets;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Build_button;

    @FXML
    private TextArea TextString_textArea;

    @FXML
    private TextField IconID_textField;

    @FXML
    private TextField Duration_textField;

    @FXML
    private TextArea TextAttribute_textArea;

    @FXML
    private TextField FrameID_textField;

    @FXML
    private TextArea TLV_textArea;

    @FXML
    private CheckBox ImmediateResp_checkBox;

    @FXML
    void buildTlv(ActionEvent event)
    {
        String displayText = "";
        String commandDetails = "8103218102";
        String textString = "";
        String iconId = "";
        String ImmediateResp = "";
        String duration = "";
        String textAttribute = "";
        String frameID = "";

        TLV_textArea.clear();
        displayText += commandDetails;

        // get text string
        textString = TextString_textArea.getText();
        byte[] bytes = textString.getBytes(StandardCharsets.UTF_16BE);
        textString = "8D" + String.format("%02X", bytes.length) + Utils.hexify(bytes);

        displayText += textString;

        // get icon identifier
        iconId = IconID_textField.getText().replaceAll("\\s+", "");
        if (iconId.length() == 4)
        {
            iconId = "1E02" + iconId;
        }

        displayText += iconId;

        // is immediate response is checked?
        if (ImmediateResp_checkBox.isSelected())
        {
            ImmediateResp = "2B00";
        }
        
        displayText += ImmediateResp;

        // get duration
        duration = Duration_textField.getText().replaceAll("\\s+", "");
        if (duration.length() >= 4)
        {
            duration = "04" + String.format("%02X", duration.length() / 2) + duration;
        }

        displayText += duration;

        // get text attribute;
        textAttribute = TextAttribute_textArea.getText().replaceAll("\\s+", "");
        if (textAttribute.length() >= 8)
        {
            textAttribute = "50" + String.format("%02X", textAttribute.length() / 2) + textAttribute;
        }

        displayText += textAttribute;

        // get Frame identifier
        frameID = FrameID_textField.getText().replaceAll("\\s+", "");
        if (frameID.length() > 1)
        {
            frameID = "6801" + frameID.substring(0, 2);
        }

        displayText += frameID;
        displayText = "81" + String.format("%02X", displayText.length() / 2) + displayText;

        TLV_textArea.setText(displayText);
    }
    
    // @FXML
    // void initialize()
    // {
    //     assert Build_button != null : "fx:id=\"Build_button\" was not injected: check your FXML file 'fxmlProactiveBuilder.fxml'.";
    //     assert TextString_textArea != null : "fx:id=\"TextString_textArea\" was not injected: check your FXML file 'fxmlProactiveBuilder.fxml'.";
    //     assert IconID_textField != null : "fx:id=\"IconID_textField\" was not injected: check your FXML file 'fxmlProactiveBuilder.fxml'.";
    //     assert Duration_textField != null : "fx:id=\"Duration_textField\" was not injected: check your FXML file 'fxmlProactiveBuilder.fxml'.";
    //     assert TextAttribute_textArea != null : "fx:id=\"TextAttribute_textArea\" was not injected: check your FXML file 'fxmlProactiveBuilder.fxml'.";
    //     assert FrameID_textField != null : "fx:id=\"FrameID_textField\" was not injected: check your FXML file 'fxmlProactiveBuilder.fxml'.";
    //     assert TLV_textArea != null : "fx:id=\"TLV_textArea\" was not injected: check your FXML file 'fxmlProactiveBuilder.fxml'.";
    //     assert ImmediateResp_checkBox != null : "fx:id=\"ImmediateResp_checkBox\" was not injected: check your FXML file 'fxmlProactiveBuilder.fxml'.";
    // }
}

