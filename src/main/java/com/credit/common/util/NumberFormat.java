package com.credit.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 号码格式化
 */
public final class NumberFormat
{

    private static final Pattern PHONE_PATTERN = Pattern
            .compile("\\d*1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$");

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d$");

    /**
     * 格式化电话号码，包括手机号码
     */
    public static final String formatNumber(final String number) throws Exception
    {
        if (StringUtils.isEmpty(number))
        {
            return null;
        }

        char[] numberChars = number.trim().toCharArray();
        String tagNumber = "";
        Matcher matcher = null;
        for (char numberchar : numberChars)
        {
            matcher = NUMBER_PATTERN.matcher(String.valueOf(numberchar).trim());
            if (matcher.find())
            {
                tagNumber += String.valueOf(numberchar).trim();
            }
        }

        if (tagNumber.length() == 0)
        {
            throw new Exception("无效号码[" + number + "]");
        }
        matcher = PHONE_PATTERN.matcher(tagNumber);
        if (matcher.find())
        {
            return tagNumber.substring(tagNumber.length() - 11);
        }
        if (tagNumber.startsWith("00"))
        {
            return tagNumber.substring(1);
        }
        if (tagNumber.startsWith("400"))
        {
            return tagNumber;
        }
        if (tagNumber.length() > 8 && !tagNumber.startsWith("0"))
        {
            return "0" + tagNumber;
        }
        return tagNumber;
    }

    public static final boolean isPhoneNumber(final String phone) throws Exception
    {
        if (StringUtils.isEmpty(phone))
        {
            throw new Exception("手机号不能为空");
        }
        String tagPhone = formatNumber(phone);
        Matcher matcher = PHONE_PATTERN.matcher(tagPhone);
        if (matcher.matches())
        {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println(formatNumber("+8618616626650"));
        System.out.println(formatNumber("2120675501"));
        System.out.println(isPhoneNumber("2120675501"));
    }
}