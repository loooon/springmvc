/**
 * @(#)ReceiptText.java
 *
 * @author xuji
 * @version 1.0 2014-7-10
 *
 * Copyright (C) 2012,2014 , PING' AN, Inc.
 */
package com.credit.common.util.receipt;
/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class ReceiptText
{
    private String text;
    private int x;
    private int y;

    public ReceiptText(String text, int x, int y)
    {
        this.text = text;
        this.x    = x;
        this.y    = y;
    }
    
    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    
}


/**
 * $Log: ReceiptText.java,v $
 * 
 * @version 1.0 2014-7-10 
 *
 */