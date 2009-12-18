/** 
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the new BSD License.
 */

package steamcondenser.steam.packets;

/**
 * @author Sebastian Staudt
 */
public class S2A_INFO2_Packet extends S2A_INFO_BasePacket
{
    protected short appId;
    protected String gameVersion;
    protected short serverPort;
    protected String serverTags;
    protected String tvName;
    protected short tvPort;

    public S2A_INFO2_Packet(byte[] dataBytes)
    {
	super(SteamPacket.S2A_INFO2_HEADER, dataBytes);

	this.networkVersion = this.contentData.getByte();
	this.serverName = this.contentData.getString();
	this.mapName = this.contentData.getString();
	this.gameDir = this.contentData.getString();
	this.gameDescription = this.contentData.getString();
	this.appId = Short.reverseBytes(this.contentData.getShort());
	this.numberOfPlayers = this.contentData.getByte();
	this.maxPlayers = this.contentData.getByte();
	this.numberOfBots = this.contentData.getByte();
	this.dedicated = this.contentData.getByte();
	this.operatingSystem = this.contentData.getByte();
	this.passwordProtected = this.contentData.getByte() == 1;
	this.secure = this.contentData.getByte() == 1;
	this.gameVersion = this.contentData.getString();

	if(this.contentData.remaining() > 0)
	{
	    byte extraDataFlag = this.contentData.getByte();

	    if((extraDataFlag & 0x80) == 1)
	    {
		this.serverPort = Short.reverseBytes(this.contentData.getShort());
	    }

	    if((extraDataFlag & 0x40) == 1)
	    {
		this.tvPort = Short.reverseBytes(this.contentData.getShort());
		this.tvName = this.contentData.getString();
	    }

	    if((extraDataFlag & 0x20) == 1)
	    {
		this.serverTags = this.contentData.getString();
	    }
	}
    }
}
