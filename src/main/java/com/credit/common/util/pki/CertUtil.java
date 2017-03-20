/*
 * Created on 2005-4-26
 *
 * @author xuji
 * 
 * Copyright (C) 2005, HC SOFT.
 */
package com.credit.common.util.pki;

import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 验证PKCS7签名 注意：此类目前使用了jdk中sun.security包中的类，
 * 
 * 根据Sun公司的声明: sun.* Packages * 中的类可能有问题，且以后不一定兼容。 
 * 目前测试下来，Windows/Linux平台上的jdk1.3、1.4都正常，但不排除以后可能出问题的情况。
 * 如果以后有问题，可能需要修改此类，建议使用bouncy castle jce中的PKCS7SignedData,
 * 或者参考jdk1.3中反编译出来的代码实现。
 * 
 * @author xuji
 * @version 0.9
 * @since 0.9
 */
public class CertUtil
{
    private static Logger logger = LoggerFactory.getLogger(CertUtil.class.getName());

    /**
     * 验证签名，验签时比较整个证书
     * 
     * @param b64SignedData
     *            base64编码的签名数据
     * @param b64Cert
     *            base64编码的证书
     * @param signTxt
     *            实际的签名数据
     * @param signDate
     *            返回PKCS7中的签名时间，为null表示不需要
     * @throws Exception
     */
    public static void verfiySign(String b64SignedData, String b64Cert, String signTxt, Date signDate) throws Exception
    {
        verfiySignEx(b64SignedData, buildCert(b64Cert), signTxt, signDate, false);
    }

    /**
     * 验证签名，验签时比较整个证书
     * 
     * @param b64SignedData
     *            base64编码的签名数据
     * @param adminCert
     *            证书
     * @param signTxt
     *            实际的签名数据
     * @param signDate
     *            返回PKCS7中的签名时间，为null表示不需要
     * @throws Exception
     */
    public static void verfiySign(String b64SignedData, Certificate adminCert, String signTxt, Date signDate) throws Exception
    {
        verfiySignEx(b64SignedData, adminCert, signTxt, signDate, false);
    }

    /**
     * 验证签名，验签时仅比较公钥
     * 
     * @param b64SignedData
     *            base64编码的签名数据
     * @param b64Cert
     *            base64编码的证书
     * @param signTxt
     *            实际的签名数据
     * @param signDate
     *            返回PKCS7中的签名时间，为null表示不需要
     * @throws Exception
     */
    public static void verfiySigneByPubKey(String b64SignedData, String b64Cert, String signTxt, Date signDate) throws Exception
    {
        verfiySignEx(b64SignedData, buildCert(b64Cert), signTxt, signDate, true);
    }

    /**
     * 验证签名，验签时仅比较公钥
     * 
     * @param b64SignedData
     *            base64编码的签名数据
     * @param adminCert
     *            证书
     * @param signTxt
     *            实际的签名数据
     * @param signDate
     *            返回PKCS7中的签名时间，为null表示不需要
     * @throws Exception
     */
    public static void verfiySigneByPubKey(String b64SignedData, Certificate adminCert, String signTxt, Date signDate) throws Exception
    {
        verfiySignEx(b64SignedData, adminCert, signTxt, signDate, true);
    }

    /**
     * 验证签名信息
     * 
     * @param b64SignedData
     *            base64编码的PKCS7数据
     * @param b64Cer
     *            ：base64编码的管理员证书
     * @param signTxt
     *            操作描述（签名数据）
     * @param signDate
     *            返回PKCS7中的签名时间，为null表示不需要
     * @param onlyVerifyPubKey
     *            是否只验证公钥
     * @throws Exception
     */
    public static void verfiySignEx(String b64SignedData, Certificate adminCert, String signTxt, Date signDate, boolean onlyVerifyPubKey) throws Exception
    {

        // 客户端负责删除回车符号
        /*byte[] signedData = null;
        try
        {
            signedData = Base64.decode(b64SignedData.getBytes());
        }
        catch (Exception e)
        {
            String err = "解析base64编码的签名数据失败，数据不是base64编码格式（可能数据被篡改）。原因：" + e.getMessage();
            logger.error(err, e);
            throw new Exception(err);
        }

        try
        {
            PKCS7 p7 = new PKCS7(signedData);

            // 先验证证书是否一致
            // 虽然PKCS7规范中证书是可选的，但目前客户端通过CSP签名，其中总是包含证书
            X509Certificate[] signerCerts = p7.getCertificates();
            if ((signerCerts == null) || (signerCerts.length < 1))
                throw new Exception("错误的PKCS7签名格式，缺少签名者的证书");

            // 判断证书是否一致：遍历PKCS7中的证书，找到一致的为止
            // 否则抛出异常。遍历是必须的，因为即使只有一个客户端签名，
            // 这里仍可能会有多张证书（相同的CAPICOM，有些客户端环境下
            // 会提交证书链，有些不会，原因未知）
            boolean bMatchCert = false;
            for (int i = 0; i < signerCerts.length; i++)
            {
                // 如果onlyVerifyPubKey为真，仅比较证书公钥，否则比较整个证书
                if (onlyVerifyPubKey)
                {
                    if (adminCert.getPublicKey().equals(signerCerts[i].getPublicKey()))
                    {
                        bMatchCert = true;
                        break;
                    }
                }
                else
                {
                    if (adminCert.equals(signerCerts[i]))
                    {
                        bMatchCert = true;
                        break;
                    }
                }

            }
            if (!bMatchCert)
                throw new Exception("签名用的证书与系统中对应的该用户的证书不一致");

            // 验证签名
            SignerInfo[] singerInfos = p7.verify();

            if ((singerInfos == null) || (singerInfos.length == 0))
            {
                throw new Exception("pkcs7 验证失败");
            }

            // PKCS7数据验证成功，再验证签名数据是否是期望数据
            String signContent = new String(p7.getContentInfo().getContentBytes(), "UTF-16LE");
            if (!signTxt.equals(signContent))
            {
                throw new Exception("签名数据与期望数据不一致，可能数据已被篡改");
            }

            // 如果需要签名时间，设置之
            if (signDate != null)
            {
                Date d = (Date) singerInfos[0].getAuthenticatedAttributes().getAttributeValue("SigningTime");
                signDate.setTime(d.getTime());
            }

        }
        catch (Exception e)
        {
            String err = "签名验证失败，原因：" + e.getMessage();
            logger.error(err, e);
            throw new Exception(err, e);
        }*/
    }

    /**
     * 根据BER编码的证书数据构造一个证书类
     * 
     * @param b64Cert
     *            BER编码的证书数据
     * @return 证书类
     * @throws Exception
     */
    public static Certificate buildCert(String b64Cert) throws Exception
    {
        try
        {
            byte[] certData =  Base64.decode(b64Cert);
            
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return cf.generateCertificate(new ByteArrayInputStream(certData));
            
        }
        catch (Exception e)
        {
            throw new Exception("无效证书", e);
        }
    }
    
    
    public static void main(String[] args)
    {
        try
        {
           
           String b64Cert = "MIIF3TCCBMWgAwIBAgIHI1sg+sl85jANBgkqhkiG9w0BAQUFADBfMQswCQYDVQQG"+
                         "EwJDTjEqMCgGA1UEChMhV29TaWduIGVDb21tZXJjZSBTZXJ2aWNlcyBMaW1pdGVk"+
                         "MSQwIgYDVQQDExtXb1NpZ24gQ2xhc3MgMyBPViBTZXJ2ZXIgQ0EwHhcNMTMxMTI2"+
                         "MDQxMjEzWhcNMTQxMTI4MTU1ODIxWjCBgTELMAkGA1UEBhMCQ04xEjAQBgNVBAgM"+
                         "CeS4iua1t+W4gjESMBAGA1UEBwwJ5LiK5rW35biCMS0wKwYDVQQKDCTkuIrmtbfl"+
                         "h63lronnvZHnu5znp5HmioDmnInpmZDlhazlj7gxGzAZBgNVBAMTEmFwcGx5LnRy"+
                         "dXN0dXRuLm9yZzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALGnZhtf"+
                         "snQFCkjTLXCF8+wVSuqru0Fkf0R7TXaY6IU5r1opkWsgdMtwQ7cRL67brselSlOq"+
                         "7CgjdEJWDs27MY6Mo723IUX3bRaTLxKcULDh/l/A2yx4x6vYfXm8jV+f5F8bV4Q/"+
                         "/vBllUNYf4SZJapiuNQQzhCYxHQl0DzlCUPePY7TB/tQyRnY48B3UJZtT3wWu7Zk"+
                         "/21846/nwA4gylKqJ7rRHPE9i4C4RAvDp4E5V/KhyEtuilRFO56VxDc5QlYSk8h+"+
                         "bh+GrW6tWKaMY37BHabO7S7zrnxod9YmTjgFy+iBlVMfh2XffUS7syfka9HCQ6qn"+
                         "4ybj5tuEb1SNmNMCAwEAAaOCAnkwggJ1MA8GA1UdEwEB/wQFMAMCAQAwCwYDVR0P"+
                         "BAQDAgOoMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjAdBgNVHQ4EFgQU"+
                         "V9P+zMkj7zdl3FxHbIlARFxi+LwwHwYDVR0jBBgwFoAUYi6B2eNCeRSjzdlUim74"+
                         "3pWqj5gwKwYDVR0RBCQwIoISYXBwbHkudHJ1c3R1dG4ub3Jnggx0cnVzdHV0bi5v"+
                         "cmcwgfIGA1UdIASB6jCB5zAIBgZngQwBAgIwgdoGDSsGAQQBgptRAQMCAQIwgcgw"+
                         "KQYIKwYBBQUHAgEWHWh0dHA6Ly93d3cud29zaWduLmNvbS9wb2xpY3kvMIGaBggr"+
                         "BgEFBQcCAjCBjTAoFiFXb1NpZ24gZUNvbW1lcmNlIFNlcnZpY2VzIExpbWl0ZWQw"+
                         "AwIBARphTGltaXRlZCBMaWFiaWxpdHksIHNlZSB0aGUgV29TaWduIENlcnRpZmlj"+
                         "YXRpb24gQXV0aG9yaXR5IFBvbGljeSBhdCBodHRwOi8vd3d3Lndvc2lnbi5jb20v"+
                         "cG9saWN5LzA0BgNVHR8ELTArMCmgJ6AlhiNodHRwOi8vY3Jscy53b3NpZ24uY29t"+
                         "L3NlcnZlci0zLmNybDB7BggrBgEFBQcBAQRvMG0wMwYIKwYBBQUHMAGGJ2h0dHA6"+
                         "Ly9vY3NwLndvc2lnbi5jb20vY2xhc3MzL3NlcnZlci9jYTA2BggrBgEFBQcwAoYq"+
                         "aHR0cDovL2FpYS53b3NpZ24uY29tL2NsYXNzMy5zZXJ2ZXIuY2EuY2VyMCEGA1Ud"+
                         "EgQaMBiGFmh0dHA6Ly93d3cud29zaWduLmNvbS8wDQYJKoZIhvcNAQEFBQADggEB"+
                         "AIJugEWR4eXFYnyXUtGWNfUfq4V1bfwu05jDjv1IqaYJw1huzKVENByOyEOQ5edu"+
                         "S3xcj2Umk09RCnh7rcCvMA88rDht7E9OcfRQe5ZhUi0oRW67uezeJ1OtW22ceiGB"+
                         "hF8Zu3m+5wCSZ2IZ2fB2lOnG19Vr3zwqSZofULHpaTc+wuh9ZsxaNDG5HEwJynLo"+
                         "dDBKaiSX4BGom/mSs6GgO3BNW5i2EZfW2Ij/IXpN4vMjC5mK/cdTIeTby4e7MZsD"+
                         "yF2gS5YLwDgNo7Di9vmE1HvGtzTFAha+9G1VL37fybFJ0yRrp6BH7t80hJ+B6bFx"+
                         "u8i4a0er/NAL0VANab+Pabg=";
           
           
           
           
           X509Certificate cert = (X509Certificate)buildCert(b64Cert);
           String dn = cert.getSubjectDN().toString();
           System.out.println(dn);
           
           
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
