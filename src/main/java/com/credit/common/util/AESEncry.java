package com.credit.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class AESEncry {
	/*
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	private static String sKey = "*psTNSWhx([Y*JPW";
	private static String ivParameter = "Bko9F(C@wPrrw8^q";
	private static String CHARSET = "utf-8";
	private static AESEncry instance = null;

	private AESEncry() {

	}

	public static AESEncry getInstance() {
		if (instance == null) {
			instance = new AESEncry();
		}
		return instance;
	}

	// 加密
	public String encrypt(String sSrc) {
		String result = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes(CHARSET));
			result = new BASE64Encoder().encode(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		// 此处使用BASE64做转码。
		return result;
	}

	// 解密
	public String decrypt(String sSrc) {
		String originalString = null;
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			originalString = new String(original, CHARSET);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return originalString;
	}

	public static void main(String[] args) {
		// 需要加密的字串
		String cSrc = String.valueOf(System.currentTimeMillis());
		System.out.println(cSrc + "  长度为" + cSrc.length());
		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AESEncry.getInstance().encrypt(cSrc);
		System.out.println("加密后的字串是：" + enString + "长度为" + enString.length());

		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis();
		String DeString = AESEncry.getInstance().decrypt(enString);
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
	}
}