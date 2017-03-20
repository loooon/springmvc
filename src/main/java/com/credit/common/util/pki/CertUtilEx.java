
/**
* File: CertUtilExt.java
*
* Purpose: 
*
* @author xuji
* @version 1.0 2007-6-16
*
* Copyright (C) 2004, 2008, HC, Inc.
*
*/

package com.credit.common.util.pki;

import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;

public class CertUtilEx
{
    //对数据做签名
    public static byte[] sign(PrivateKey key, byte[] Data, String algorithm) throws Exception
    {
        byte[] signData = null;
        Signature sig = Signature.getInstance(algorithm);
        sig.initSign(key);
        sig.update(Data); 
        signData = sig.sign();
        
        return signData;
    }
    
    public static String getCertItem(X509Certificate cert, String name)
    {
        String result = null;

        if (null == cert)
        {
            return null;
        }
        
        if (name.equals("SN"))
        {
            result = cert.getSerialNumber().toString();
        }
        else
        {
            String dn = cert.getSubjectDN().toString();
            result    = getItemFromDn(dn, name);
        }
        
        return result;
    }
    
    private static String getItemFromDn(String dn, String item)
    {
        String ret = "";
        String[] items = dn.split("[,]");
        for (int i = 0; i < items.length; i++)
        {
            String itemStr = items[i];
            if (itemStr.startsWith(item + "="))
            {
                ret = itemStr.substring((item + "=").length());
            }
        }
       
        return ret;
    }
    
}




/**
 * $Log: CertUtilEx.java,v $
 * Revision 1.1  2010/05/17 03:51:25  xuji
 * *** empty log message ***
 *
 * Revision 1.3  2007/11/27 03:43:14  yingzf
 * *** empty log message ***
 *
 * Revision 1.2  2007/11/19 07:22:49  yingzf
 * *** empty log message ***
 *
 * Revision 1.1  2007/10/09 01:04:32  xuji
 * *** empty log message ***
 *
 * Revision 1.1  2007/06/18 03:13:12  xuji
 * *** empty log message ***
 *
 * @ Revision history
 * @ -------------------------------------------------------------------------
 * @ Version      Date              Author               Note
 * @ -------------------------------------------------------------------------
 * @ 1.0          2007-6-16          xuji             create
 * @
 * @
 */
