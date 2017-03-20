package com.credit.common.util.net.ip.seeker.impl;

import java.util.Arrays;
import java.util.Comparator;

import com.credit.common.util.net.ip.IPUtil;
import com.credit.common.util.net.ip.seeker.IPLocation;
import com.credit.common.util.net.ip.seeker.IPSeeker;


public class QQWryIPSeeker implements IPSeeker
{
    private IPEntry[] ipEntries = null;
    //用于排序和二分法查找的IP条目比较器实例
    private IPEntryComparator entryComparator = new IPEntryComparator();
    
    public QQWryIPSeeker(IPEntriesFactory factory)
    {
        this(factory.createIPEntries());
    }
    
    public QQWryIPSeeker(IPEntry[] entries)
    {
        this.ipEntries = entries;
        
        if (null != ipEntries)
        {
            Arrays.sort(ipEntries, entryComparator);
        }
    }
    
    public QQWryIPSeeker(String ipTextFile)
    {
        this(new TextFileIPEntriesFactory(ipTextFile));
    }
    
    @Override
    public IPLocation getIPLocation(String ip) throws Exception
    {
        IPEntry ipEntry = find(ip);
        
        if (null != ipEntry)
        {
            return ipEntry.getLocation();
        }
        
        return null;
    }

    public IPEntry find(String ipText) throws Exception
    {
        return find(IPUtil.ip2long(ipText));
    }
    
    public IPEntry find(long ip)
    {
        if (null == ipEntries)
        {
            return null;
        }
        
        IPEntry tempEntry = new IPEntry(ip, ip, null);
        int i = Arrays.binarySearch(ipEntries, tempEntry, entryComparator);
        if (i < 0) 
        {
            return null;
        }
        else 
        {
            return ipEntries[i];
        }
    }
    
    /**
     * 用于排序的IP条目对比器
     *
     */
    class IPEntryComparator implements Comparator<IPEntry>
    {
        public int compare(IPEntry entry0, IPEntry entry1)
        {
            if (entry1.getEndAddress() > entry0.getEndAddress()) 
            {
                return -1;
            }
            else if (entry0.getStartAddress() > entry1.getStartAddress()) 
            {
                return 1;
            }
            else 
            {
                return 0;
            }
        }
    }
    
    public static void main(String[] args)
    {
        try
        {
            IPSeeker seeker = new QQWryIPSeeker("e:\\IPData.txt");
            
            IPLocation location = seeker.getIPLocation("61.129.51.45");
            System.out.println(location.toString());
            
            location = seeker.getIPLocation("8.8.8.8");
            System.out.println(location.toString());
            
            location = seeker.getIPLocation("192.168.1.1");
            System.out.println(location.toString());
            
            location = seeker.getIPLocation("219.145.66.204");
            System.out.println(location.toString());
            
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
