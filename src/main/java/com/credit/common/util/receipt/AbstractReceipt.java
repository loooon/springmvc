/**
 * @(#)AbstractReceipt.java
 *
 * @author xuji
 * @version 1.0 2014-7-11
 *
 * Copyright (C) 2012,2014 , PING' AN, Inc.
 */
package com.credit.common.util.receipt;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public abstract class AbstractReceipt implements IReceipt
{
    
    @Override
    public BufferedImage createReceiptImage()
    {
        BufferedImage img = getBackgroundImage();
        Graphics graphics = img.createGraphics();
        
        List<ReceiptText> textList = getReceiptTexts();
        for (ReceiptText text : textList) 
        {
            drawText(graphics, text);
        }
        
        return img;
    }
    
    protected void drawText(Graphics graphics, ReceiptText text)
    {
        graphics.setColor(getColor());
        graphics.setFont(getFont());
        graphics.drawString(text.getText(), text.getX(), text.getY());
    }
    
    protected abstract BufferedImage getBackgroundImage();
    protected abstract List<ReceiptText> getReceiptTexts();
    protected abstract Font getFont();
    protected abstract Color getColor();
}


/**
 * $Log: AbstractReceipt.java,v $
 * 
 * @version 1.0 2014-7-11 
 *
 */