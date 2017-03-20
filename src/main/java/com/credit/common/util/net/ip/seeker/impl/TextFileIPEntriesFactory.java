package com.credit.common.util.net.ip.seeker.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.credit.common.util.net.ip.seeker.IPLocation;


public class TextFileIPEntriesFactory implements IPEntriesFactory
{
    private static final Logger logger = LoggerFactory.getLogger(TextFileIPEntriesFactory.class);

    private String ipTextFile;
    
    public TextFileIPEntriesFactory(String ipTextFile)
    {
        this.ipTextFile = ipTextFile;
    }
    
    @Override
    public IPEntry[] createIPEntries()
    {
        try
        {
            return loadFromFile(ipTextFile);
        }
        catch(Exception e)
        {
            logger.error("TextFileIPEntriesFactory::createIPEntries::load from file fail!", e);
        }
        
        return null;
    }
    
    private IPEntry[] loadFromFile(String file) throws Exception
    {
        InputStreamReader streamReader = null;
        FileInputStream fileStream   = null;
        BufferedReader bufferReader = null;
        
        try 
        {
            fileStream   = new FileInputStream(new File(file));
            streamReader = new InputStreamReader(fileStream, "GBK");
            bufferReader = new BufferedReader(streamReader);

            
            List<IPEntry> ipList = new ArrayList<IPEntry>();
            
            //过滤header
            String line = bufferReader.readLine();
            while ((line = bufferReader.readLine()) != null && line.trim().length() > 0) 
            {
                ipList.add(parseLine(line));
            }
            
            return ipList.toArray(new IPEntry[0]);
        } 
        finally
        {
            if (null != bufferReader)
            {
                bufferReader.close();
            }

            if (null != streamReader)
            {
                streamReader.close();
            }
            
            if (null != fileStream)
            {
                fileStream.close();
            }
        }
    }
    
    
    private IPEntry parseLine(String line)
    {
        String[] values = StringUtils.split(line, "\t");
        
        String startAddress = values[0];
        String endAddress   = values[1];
        String country      = values[2];
        String area         = values.length > 3 ? values[3] : "";
        
        if (country.equalsIgnoreCase("CZ88.NET"))
        {
            country = "";
        }
        
        if (area.equalsIgnoreCase("CZ88.NET"))
        {
            area = "";
        }
        
        return new IPEntry(startAddress, endAddress, new IPLocation(country, area));
    }
}
