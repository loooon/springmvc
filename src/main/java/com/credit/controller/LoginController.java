package com.credit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.common.result.ResultJson;
import com.credit.common.util.RandomUtil;
import com.credit.common.util.security.DigestUtils;
import com.credit.common.web.session.UserSessionFactory;



/**
 * Created by Michael Chan on 3/8/2017.
 */
@Controller
public class LoginController extends AbstractBaseController
{

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String signIn()
    {
        return "login";
    }

    @RequestMapping("/logout")
    public String signOut(HttpSession httpSession)
    {
		UserSessionFactory.clearUserSession(httpSession);
        return "redirect:login";
    }
}
