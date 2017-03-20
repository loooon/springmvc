package com.credit.common.util.net.ip.seeker.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.credit.common.util.net.ip.IPUtil;
import com.credit.common.util.net.ip.seeker.IPLocation;


public class IPEntry
{
    private static final Logger logger = LoggerFactory.getLogger(IPEntry.class);
    
    private long       startAddress = 0;
    private long       endAddress   = 0;
    private IPLocation location;

    public IPEntry(String startText, String endText, IPLocation location)
    {
        try
        {
            this.startAddress = IPUtil.ip2long(startText);
            this.endAddress   = IPUtil.ip2long(endText);
            this.location     = location;
        }
        catch(Exception e)
        {
            logger.error(String.format("IPEntry::new IPEntry[%s-%s] fail!", startText, endText), e);
        }
    }

    public IPEntry(long startAddress, long endAddress, IPLocation location)
    {
        this.startAddress = startAddress;
        this.endAddress   = endAddress;
        this.location     = location;
    }

    
    
    @Override
    public String toString()
    {
        return String.format("[%s-%s]:%s", IPUtil.long2ip(startAddress), IPUtil.long2ip(endAddress), location.toString());
    }

    public long getStartAddress()
    {
        return startAddress;
    }

    public void setStartAddress(long startAddress)
    {
        this.startAddress = startAddress;
    }

    public long getEndAddress()
    {
        return endAddress;
    }

    public void setEndAddress(long endAddress)
    {
        this.endAddress = endAddress;
    }

    public IPLocation getLocation()
    {
        return location;
    }

    public void setLocation(IPLocation location)
    {
        this.location = location;
    }
}
