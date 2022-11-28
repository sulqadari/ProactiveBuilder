package ru.mos.beeline.proactive.builder;

import java.nio.charset.StandardCharsets;

public class GetInput
{
    String commandDetails = "";
    
    public void setQualifier(String quialifier)
    {
        commandDetails = "810323" + quialifier + "82";
    }

    public void setTextString(String textString, String dcs)
    {
        byte[] bytes;
        if (dcs.equalsIgnoreCase("08"))
            bytes = textString.getBytes(StandardCharsets.UTF_16BE);
        else
            bytes = textString.getBytes(StandardCharsets.US_ASCII);

        
        textString = "8D" + String.format("%02X", (bytes.length + 1)) + dcs + Utils.hexify(bytes);
        commandDetails += textString;
    }
    
    public void setResponseLength(String respLen)
    {
        respLen = respLen.replaceAll("\\s+", "");
        respLen = "9102" + respLen;
        commandDetails += respLen;
    }

    public void setDefaultText(String defText, String dcs)
    {
        byte[] bytes;
        
        if (dcs.equalsIgnoreCase("08"))
            bytes = defText.getBytes(StandardCharsets.UTF_16BE);
        else
            bytes = defText.getBytes(StandardCharsets.US_ASCII);

        if (defText.length() > 0)
            defText = "17" + String.format("%02X", (bytes.length + 1)) + dcs + Utils.hexify(bytes);
        
        commandDetails += defText;
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

    public void setTextAttribute(String textAttribute)
    {
        textAttribute = textAttribute.replaceAll("\\s+", "");
        if (textAttribute.length() >= 8)
        {
            textAttribute = "50" + String.format("%02X", textAttribute.length() / 2) + textAttribute;
        }

        commandDetails += textAttribute;
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

    public void setDuration(String duration)
    {
        duration = duration.replaceAll("\\s+", "");
        if (duration.length() >= 4)
        {
            duration = "0402" + duration.subSequence(0, 4);
        }

        commandDetails += duration;
    }

    public String getTlv()
    {
        commandDetails = String.format("%02X", commandDetails.length() / 2) + commandDetails;
        return commandDetails;
    }
}
