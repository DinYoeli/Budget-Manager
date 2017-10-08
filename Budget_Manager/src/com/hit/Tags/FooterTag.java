package com.hit.Tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FooterTag extends SimpleTagSupport {
	   public void doTag() throws JspException, IOException {
	      JspWriter out = getJspContext().getOut();
	      out.println("<h4> All rights belong to Maor Siso, Daniel Sasson, Din Yoeli </h4>");
	   }
	}