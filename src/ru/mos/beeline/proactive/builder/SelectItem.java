package ru.mos.beeline.proactive.builder;

import java.nio.charset.StandardCharsets;

public class SelectItem
{
    String commandDetails = "810324";
    
    public void setQualifier(String quialifier)
    {
        commandDetails += quialifier + "82";
    }

    public void setAlphaId(String alpha)
    {
        byte[] bytes = alpha.getBytes(StandardCharsets.UTF_16BE);
        if (alpha.length() > 0)
        {
            //0074006500730074
            byte temp = 0;
            // for (int i = 0; i < bytes.length; i += 2)  // replace bytes in accordance with 102 221, annex A;
            // {
            //     temp = bytes[i];
            //     bytes[i] = bytes[i + 1];
            //     bytes[i + 1] = (byte)temp;
            // }

            alpha = "05" + String.format("%02X", (bytes.length + 1)) + "80" + Utils.hexify(bytes);
        }
        commandDetails += alpha;
    }

    public void setItem(String inputStr)
    {
        String[] items = inputStr.split(";");
        byte[] bytes;
        short id = 1;
        String tag = "8F";
 
        for(String item : items)
        {
            bytes = item.getBytes(StandardCharsets.UTF_16BE);
            
            if (id > 1)
                tag = "0F";

            inputStr = tag + String.format("%02X", (bytes.length + 2)) + String.format("%02X", id++) + "80" + Utils.hexify(bytes);
            commandDetails += inputStr;
        }
    }

    public void setNextActionIndicatorList(String nai)
    {
        nai = nai.replaceAll("\\s+", "");

        if (nai.length() > 0)
            nai = "18" + String.format("%02X", nai.length() / 2) + nai;

        commandDetails += nai;
    }

    public void setItemId(String itemId)
    {
        itemId = itemId.replaceAll("\\s+", "");

        if (itemId.length() == 2)
        {
            itemId = "1001" + itemId;
        }

        commandDetails += itemId;
    }

    public void setIconId(String iconId)
    {
        iconId = iconId.replaceAll("\\s+", "");
        if (iconId.length() == 4)
        {
            iconId = "1E02" + iconId;
        }

        commandDetails += iconId;
    }

    public void setItemIconIdList(String iconIdList)
    {
        iconIdList = iconIdList.replaceAll("\\s+", "");
        if (iconIdList.length() > 1)
        {
            iconIdList = "1F" + String.format("%02X", iconIdList.length() / 2) + iconIdList;
        }

        commandDetails += iconIdList;
    }

    public void setTextAttribute(String textAttribute)
    {
        textAttribute = textAttribute.replaceAll("\\s+", "");
        if (textAttribute.length() >= 8)
        {
            textAttribute = "50" + String.format("%02X", textAttribute.length() / 2) + textAttribute;
        }

        commandDetails += textAttribute;
    }

    public void setItemTextAttribute(String textAttributeList)
    {
        textAttributeList = textAttributeList.replaceAll("\\s+", "");
        if (textAttributeList.length() >= 8)
        {
            textAttributeList = "51" + String.format("%02X", textAttributeList.length() / 2) + textAttributeList;
        }

        commandDetails += textAttributeList;
    }

    public void setFrameID(String frameID)
    {
        frameID = frameID.replaceAll("\\s+", "");
        if (frameID.length() > 1)
        {
            frameID = "6801" + frameID.substring(0, 2);
        }

        commandDetails += frameID;
    }

    public String getTlv()
    {
        commandDetails = "8B" + String.format("%02X", commandDetails.length() / 2) + commandDetails;
        return commandDetails;
    }

    public void clear()
    {
        commandDetails = "810324";
    }
}
