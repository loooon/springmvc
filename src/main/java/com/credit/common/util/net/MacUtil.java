/**
 * @(#)MacUtil.java
 *
 * @author xuji
 * @version 1.0 2009-1-8
 *
 * Copyright (C) 2000,2009 , HC, Inc.
 */
package com.credit.common.util.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class MacUtil
{
    public static String getMacAddress()
    {
        String mac = "";
        String os = System.getProperty("os.name");
        try
        {
            if(os.startsWith("Windows"))
            {
                mac = windowsParseMacAddress(windowsRunIpConfigCommand());
            }
            else if(os.startsWith("Linux"))
            {
                mac = linuxParseMacAddress(linuxRunIfConfigCommand());
            }
            else if(os.startsWith("Mac OS X"))
            {
                mac = osxParseMacAddress(osxRunIfConfigCommand());
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    
        return mac.replaceAll("-",":");
    }

    /*
     * Linux stuff
     */
    private static String linuxParseMacAddress(String ipConfigResponse) throws ParseException
    {
        String localHost = null;
        try 
        {
            localHost = InetAddress.getLocalHost().getHostAddress();
        }
        catch(java.net.UnknownHostException ex)
        {
            ex.printStackTrace();
            throw new ParseException(ex.getMessage(), 0);
        }
        StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
        String lastMacAddress = null;

        while(tokenizer.hasMoreTokens())
        {
            String line = tokenizer.nextToken().trim();
            boolean containsLocalHost = line.indexOf(localHost) >= 0;
            // see if line contains IP address
            if(containsLocalHost && lastMacAddress != null)
            {
                return lastMacAddress;
            }

            // see if line contains MAC address
            int macAddressPosition = line.indexOf("HWaddr");
            if(macAddressPosition <= 0) continue;

            String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
            if(linuxIsMacAddress(macAddressCandidate))
            {
                lastMacAddress = macAddressCandidate;
                continue;
            }
        }

        ParseException ex = new ParseException("cannot read MAC address for " + localHost + " from [" + ipConfigResponse + "]", 0);
        ex.printStackTrace();
        throw ex;
    }


    private static boolean linuxIsMacAddress(String macAddressCandidate)
    {
        Pattern macPattern = Pattern.compile("[0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0 -9a-fA-F]{2}[-:][0-9a-fA-F]{2}");
        Matcher m = macPattern.matcher(macAddressCandidate);
        return m.matches();
    }

    private static String linuxRunIfConfigCommand() throws IOException
    {
        Process p = Runtime.getRuntime().exec("ifconfig");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

        StringBuffer buffer= new StringBuffer();
        for (;;)
        {
            int c = stdoutStream.read();
            if (c == -1) break;
            buffer.append((char)c);
        }
        
        String outputText = buffer.toString();
        stdoutStream.close();
        return outputText;
    }

    /*
     * Windows stuff
     */
    private static String windowsParseMacAddress(String ipConfigResponse) throws ParseException
    {
    
        String localHost = null;
        try
        {
            localHost = InetAddress.getLocalHost().getHostAddress();
        }
        catch(java.net.UnknownHostException ex)
        {
            ex.printStackTrace();
            throw new ParseException(ex.getMessage(), 0);
        }

        StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
        String lastMacAddress = null;

        while(tokenizer.hasMoreTokens())
        {
            String line = tokenizer.nextToken().trim();

            // see if line contains IP address
            if(line.endsWith(localHost) && lastMacAddress != null)
            {
                return lastMacAddress;
            }

            // see if line contains MAC address
            int macAddressPosition = line.indexOf(":");
            if(macAddressPosition <= 0) continue;

            String macAddressCandidate = line.substring(macAddressPosition + 1).trim();
            if(windowsIsMacAddress(macAddressCandidate))
            {
                lastMacAddress = macAddressCandidate;
                continue;
            }
        }
    
        ParseException ex = new ParseException("cannot read MAC address from [" + ipConfigResponse + "]", 0);
        ex.printStackTrace();
        throw ex;
    }


    private static boolean windowsIsMacAddress(String macAddressCandidate)
    {
        Pattern macPattern = Pattern.compile("[0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0 -9a-fA-F]{2}[-:][0-9a-fA-F]{2}");
        Matcher m = macPattern.matcher(macAddressCandidate);
        return m.matches();
    }

    private static String windowsRunIpConfigCommand() throws IOException
    {
        Process p = Runtime.getRuntime().exec("ipconfig /all");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

        StringBuffer buffer= new StringBuffer();
        for (;;)
        {
            int c = stdoutStream.read();
            if (c == -1) break;
            buffer.append((char)c);
        }
        String outputText = buffer.toString();
        stdoutStream.close();
        return outputText;
    }

    /*
     * Mac OS X Stuff
     */
    private static String osxParseMacAddress(String ipConfigResponse) throws ParseException
    {
        String localHost = null;

        try
        {
            localHost = InetAddress.getLocalHost().getHostAddress();
        }
        catch(java.net.UnknownHostException ex)
        {
            ex.printStackTrace();
            throw new ParseException(ex.getMessage(), 0);
        }
        StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
        
        while(tokenizer.hasMoreTokens())
        {
            String line = tokenizer.nextToken().trim();
            // see if line contains MAC address
            int macAddressPosition = line.indexOf("ether");
            if(macAddressPosition != 0) continue;
            String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
            if(osxIsMacAddress(macAddressCandidate))
            {
                return macAddressCandidate;
            }
        }

        ParseException ex = new ParseException("cannot read MAC address for " + localHost + " from [" + ipConfigResponse + "]", 0);
        ex.printStackTrace();
        throw ex;
    }

    private static boolean osxIsMacAddress(String macAddressCandidate)
    {
        Pattern macPattern = Pattern.compile("[0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0 -9a-fA-F]{2}[-:][0-9a-fA-F]{2}");
        Matcher m = macPattern.matcher(macAddressCandidate);
        return m.matches();
    }

    private static String osxRunIfConfigCommand() throws IOException
    {
        Process p = Runtime.getRuntime().exec("ifconfig");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
        StringBuffer buffer= new StringBuffer();
        for (;;)
        {
            int c = stdoutStream.read();
            if (c == -1) break;
            buffer.append((char)c);
        }
        String outputText = buffer.toString();
        stdoutStream.close();
        return outputText;
    }
}


/**
 * $Log: MacUtil.java,v $
 * Revision 1.1  2010/05/17 03:51:26  xuji
 * *** empty log message ***
 *
 * Revision 1.1  2009/01/11 12:58:50  xuji
 * *** empty log message ***
 *
 * 
 * @version 1.0 2009-1-8 
 *
 */