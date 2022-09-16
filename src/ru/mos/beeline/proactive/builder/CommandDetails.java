package ru.mos.beeline.proactive.builder;

public class CommandDetails
{
    private String headerTag = "81";
    private String length = "03";
    private String typeOfCommand;
    private String commandQualifier;
    private String destinationDevice;

    public String getHeaderTag()
    {
        return this.headerTag;
    }

    public String getLength()
    {
        return this.length;
    }

    public String getTypeOfCommand()
    {
        return this.typeOfCommand;
    }

    public String getCommandQualifier()
    {
        return this.commandQualifier;
    }

    public String getDestinationDevice()
    {
        return this.destinationDevice;
    }

    public void setTypeOfCommand(String type)
    {
        this.typeOfCommand = type;
    }

    public void setCommandQualifier(String quialifier)
    {
        this.commandQualifier = quialifier;
    }

    public void setDestinationDevice(String device)
    {
        this.destinationDevice = device;
    }
}
