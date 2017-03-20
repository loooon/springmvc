package com.credit.common.util.net.ip.seeker;

public class IPLocation
{
    private static final String[] NOT_OVERSEA_AREA =
    {
        "山东", "江苏", "安徽", "浙江", "福建", "上海",
        "广东", "广西", "海南", "湖北", "湖南", "河南",
        "江西", "北京", "天津", "河北", "山西", "内蒙古",
        "宁夏", "新疆", "青海", "陕西", "甘肃", "四川",
        "云南", "贵州", "西藏", "重庆", "辽宁", "吉林",
        "黑龙江", "同济", "华南理工", "成都", "西华大学", "华中科技",
        "武汉", "大连", "东北林业大学", "南开大学", "暨南大学", "华南理工",
        "华东", "南京", "东南", "哈尔滨", "郑州", "清华", 
        "西安", "中国", 
    };
    
    private String country;
    private String area;
    private boolean oversea = true;
    
    public IPLocation(String country, String area)
    {
        this.country = country;
        this.area    = area;
        
        for(int i = 0; i < NOT_OVERSEA_AREA.length; i++)
        {
            if (country.contains(NOT_OVERSEA_AREA[i]))
            {
                oversea = false;
            }
        }
    }

    public String getCountry()
    {
        return country;
    }

    public String getArea()
    {
        return area;
    }
    
    public boolean isOversea()
    {
        return oversea;
    }

    @Override
    public String toString()
    {
        return country + area;
    }
}
