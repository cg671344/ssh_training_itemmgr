package com.bjsxt.drp.web.itemmgr.actions; 

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChangeLanguageAction extends Action {
	
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
    	System.out.println("flag1");
    	String lan = request.getParameter("lan");
    	Locale currentLocale = null;
    	if ("en".equals(lan)) {
    		currentLocale = new Locale("en", "US");
    		System.out.println("en+us");
    	}else if ("zh".equals(lan)) {
    		currentLocale = new Locale("zh", "CN");
    		System.out.println("zh+cn");
    	}
    	//物料管理
    	System.out.println("flag2");
    	request.getSession().setAttribute(Globals.LOCALE_KEY, currentLocale);
    	System.out.println("flag3");
    	return mapping.findForward("success");
    } 	
}
