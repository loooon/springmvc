package com.credit.common.web.servlet.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener
{
    public void sessionCreated(HttpSessionEvent httpSessionEvent)
    {
        SessionContext.addSession(httpSessionEvent.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
    {
        HttpSession session = httpSessionEvent.getSession();
        SessionContext.delSession(session);
    }
}
