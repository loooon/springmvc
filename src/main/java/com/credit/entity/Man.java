package com.credit.entity;

/**
 * Created by Michael Chan on 3/20/2017.
 */
import javax.persistence.*;
@Entity
@Table(name = "tb_man")
public class Man
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer userId;

    @Column(name = "no")
    private Integer userNo;

    @Column(name = "name")
    private String userName;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserNo()
    {
        return userNo;
    }

    public void setUserNo(Integer userNo)
    {
        this.userNo = userNo;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
