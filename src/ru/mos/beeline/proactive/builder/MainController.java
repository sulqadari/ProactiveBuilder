package ru.mos.beeline.proactive.builder;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class MainController
{
    DisplayText displayText = new DisplayText();
    SelectItem selectItem = new SelectItem();

    @FXML
    private Button dispText_build_button;

    @FXML
    private TextArea dispText_textString_textArea;

    @FXML
    private TextArea dispText_textAttribute_textArea;

    @FXML
    private TextField dispText_iconID_textField;

    @FXML
    private CheckBox dispText_immediateResp_checkBox;

    @FXML
    private TextField dispText_duration_textField;

    @FXML
    private TextField dispText_frameID_textField;

    @FXML
    private TextArea dispText_tlv_textArea;

    @FXML
    private TextArea selectItem_tlv_textArea;

    @FXML
    private Button selectItem_build_button;

    @FXML
    private TextField selectItem_qualifier_textFiled;

    @FXML
    private TextField selectItem_itemID_textFiled;

    @FXML
    private TextField selectItem_iconID_textFiled;

    @FXML
    private TextField selectItem_frameID_textFiled;

    @FXML
    private TextField selectItem_alphaID_textFiled;

    @FXML
    private TextArea selectItem_item_textArea;

    @FXML
    private TextField selectItem_nai_textField;

    @FXML
    private TextField selectItem_iconIdList_textFiled;

    @FXML
    private TextArea selectItem_textAttribute_textArea;

    @FXML
    private TextArea selectItem_textAttributeList_textArea;

    @FXML
    void displayTextBuildTlv(ActionEvent event)
    {
        String tlv = "";
        dispText_tlv_textArea.clear();
        
        // get text string
        displayText.setTextString(dispText_textString_textArea.getText());
        // get icon identifier
        displayText.setIconId(dispText_iconID_textField.getText());
        // get immediate response
        displayText.setImmediateResp(dispText_immediateResp_checkBox.isSelected());
        // get duration
        displayText.setDuration(dispText_duration_textField.getText());
        // get text attribute;
        displayText.setTextAttribute(dispText_textAttribute_textArea.getText());
        // get Frame identifier
        displayText.setFrameID(dispText_frameID_textField.getText());
        tlv = displayText.getTlv();
        dispText_tlv_textArea.setText(tlv);
    }

    @FXML
    void selectItemBuildTlv(ActionEvent event)
    {
        String tlv = "";
        selectItem_tlv_textArea.clear();

        // get Command quialifier
        selectItem.setQualifier(selectItem_qualifier_textFiled.getText());
        selectItem.setAlphaId(selectItem_alphaID_textFiled.getText());
        selectItem.setItem(selectItem_item_textArea.getText());
        selectItem.setNextActionIndicatorList(selectItem_nai_textField.getText());
        selectItem.setItemId(selectItem_itemID_textFiled.getText());
        selectItem.setIconId(selectItem_iconID_textFiled.getText());
        selectItem.setItemIconIdList(selectItem_iconIdList_textFiled.getText());
        selectItem.setTextAttribute(selectItem_textAttribute_textArea.getText());
        selectItem.setItemTextAttribute(selectItem_textAttributeList_textArea.getText());
        selectItem.setFrameID(selectItem_frameID_textFiled.getText());
        tlv = selectItem.getTlv();
        selectItem_tlv_textArea.setText(tlv);
        selectItem.clear();
    }
}

