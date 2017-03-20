/**
 * @(#)Shell.java
 *
 * @author xuji
 * @version 1.0 2007-11-2
 *
 * Copyright (C) 2000,2007 , HC, Inc.
 */
package com.credit.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.Arrays;

/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class Shell
{
    private static final int BUFFER_SIZE = 1024 * 16;
    
    public static int exec(String cmd, StringBuffer out, StringBuffer err) throws Exception
    {
        Process process = run(cmd);
        return doExec(process, out, err);
    }
    
    public static int exec(String[] cmd, StringBuffer out, StringBuffer err) throws Exception
    {
        Process process = run(cmd);
        return doExec(process, out, err);
    }
    
    public static int exec(String[] cmd, String[] env, File dir, StringBuffer out, StringBuffer err) throws Exception
    {
        Process process = run(cmd, env, dir);
        return doExec(process, out, err);
    }
    
    private static Process run(String cmd) throws Exception
    {
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
        
        return process;
    }
    
    private static Process run(String[] cmd) throws Exception
    {
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
        
        return process;
    }
    
    private static Process run(String[] cmd, String[] env, File dir) throws Exception
    {
        Process process = Runtime.getRuntime().exec(cmd, env, dir);
        return process;
    }
    
    private static int doExec(Process process, StringBuffer out, StringBuffer err) throws Exception
    {
        int result = -1;
        BufferedInputStream is = null;
        BufferedInputStream es = null;

        byte[] buffer   = new byte[BUFFER_SIZE];

        try
        {
            result = process.exitValue();
            
            is = new BufferedInputStream(process.getInputStream());
            is.read(buffer);
            out.append(new String(buffer));
            
            Arrays.fill(buffer, (byte)0x00);
            es = new BufferedInputStream(process.getErrorStream());
            es.read(buffer);
            
        }
        finally
        {
            if (null != is)
            {
                is.close();
            }
            
            if (null != es)
            {
                es.close();
            }
            
            if (null != process)
            {
                process.destroy();
            }
        }
        
        return result;
    }
}


/**
 * $Log: Shell.java,v $
 * Revision 1.1  2010/05/17 03:51:26  xuji
 * *** empty log message ***
 *
 * Revision 1.1  2007/11/02 10:36:54  xuji
 * *** empty log message ***
 *
 * 
 * @version 1.0 2007-11-2 
 *
 */