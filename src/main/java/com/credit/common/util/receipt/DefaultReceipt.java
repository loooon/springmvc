/**
 * @(#)DefaultReceipt.java
 *
 * @author xuji
 * @version 1.0 2014-7-10
 *
 * Copyright (C) 2012,2014 , PING' AN, Inc.
 */
package com.credit.common.util.receipt;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class DefaultReceipt extends AbstractReceipt
{
    private static final Logger logger = LoggerFactory.getLogger(DefaultReceipt.class);
    
    private static final int POS_X_TRANSTIME = 83;
    private static final int POS_Y_TRANSTIME = 81;
    private static final int POS_X_SELLER    = 153;
    private static final int POS_Y_SELLER    = 119;
    private static final int POS_X_WEBSITE   = 153;
    private static final int POS_Y_WEBSITE   = 164;
    private static final int POS_X_ORDER     = 153;
    private static final int POS_Y_ORDER     = 207;
    private static final int POS_X_PRICE     = 153;
    private static final int POS_Y_PRICE     = 251;
    
    private String seller;
    private String website;
    private String order;
    private long   price;
    private Date transTime;
    
    private Font font  = new Font("新宋体", Font.TRUETYPE_FONT, 12);
    private Color color = Color.BLACK;
    
    public String getSeller()
    {
        return seller;
    }

    public void setSeller(String seller)
    {
        this.seller = seller;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public long getPrice()
    {
        return price;
    }

    public void setPrice(long price)
    {
        this.price = price;
    }

    public Date getTransTime()
    {
        return transTime;
    }

    public void setTransTime(Date transTime)
    {
        this.transTime = transTime;
    }

    public void setFont(Font font)
    {
        this.font = font;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    protected Color getColor()
    {
        return color;
    }

    @Override
    protected Font getFont()
    {
        return font;
    }

    @Override
    public BufferedImage getBackgroundImage()
    {
        InputStream is = null;
        
        try
        {
            is = getClass().getResourceAsStream("receipt.jpg");
            
            
            return ImageIO.read(is);
        }
        catch(Exception e)
        {
            logger.error("DefaultReceipt::getBackgroundImage::加载receipt模板失败!", e);
        }
        finally
        {
            if (null != is)
            {
                try
                {
                    is.close();
                }
                catch(Exception ioe)
                {
                    logger.error("DefaultReceipt::getBackgroundImage::close stream fail!", ioe);
                }
            }
        }
        
        return null;
    }

    @Override
    public List<ReceiptText> getReceiptTexts()
    {
        List<ReceiptText> list = new ArrayList<ReceiptText>();
        
        list.add(new ReceiptText(getOrder(), POS_X_ORDER, POS_Y_ORDER));
        list.add(new ReceiptText(getSeller(), POS_X_SELLER, POS_Y_SELLER));
        list.add(new ReceiptText(getWebsite(), POS_X_WEBSITE, POS_Y_WEBSITE));
        
        String time = new SimpleDateFormat("yyyy-MM-dd").format(getTransTime());
        list.add(new ReceiptText(time, POS_X_TRANSTIME, POS_Y_TRANSTIME));
        
        String priceText = String.format("%.2f元", getPrice() / 100.0);
        list.add(new ReceiptText(priceText, POS_X_PRICE, POS_Y_PRICE));
        
        return list;
    }
    
    public static void main(String[] args)
    {
        try
        {
            String priceText = String.format("%.2f元", 99909 / 100.0);
            System.out.println(priceText);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}


/**
 * $Log: DefaultReceipt.java,v $
 * 
 * @version 1.0 2014-7-10 
 *
 */