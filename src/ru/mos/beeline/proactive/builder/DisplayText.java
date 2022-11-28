package ru.mos.beeline.proactive.builder;

import java.nio.charset.StandardCharsets;

public class DisplayText extends ProactiveCommand
{
    String commandDetails = "";

    public void setTextString(String textString, String dcs)
    {
        byte[] bytes;
        commandDetails = "8103218102";
        if (dcs.equalsIgnoreCase("08"))
            bytes = textString.getBytes(StandardCharsets.UTF_16BE);
        else
            bytes = textString.getBytes(StandardCharsets.US_ASCII);
        
        textString = "8D" + String.format("%02X", (bytes.length + 1)) + dcs + Utils.hexify(bytes);
        commandDetails += textString;
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

    public void setImmediateResp(boolean immediateResp)
    {
        if (immediateResp)
        {
            commandDetails += "2B00";
        }
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

    public void setTextAttribute(String textAttribute)
    {
        textAttribute = textAttribute.replaceAll("\\s+", "");
        if (textAttribute.length() >= 8)
        {
            textAttribute = "D0" + String.format("%02X", textAttribute.length() / 2) + textAttribute;
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

    public String getTlv()
    {
        commandDetails = "81" + String.format("%02X", commandDetails.length() / 2) + commandDetails;
        return commandDetails;
    }
}
