package com.atlassian.plugins.tabsplugin.tabsrestplugin;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

//import template renderer for tabs.vm template
import com.atlassian.templaterenderer.TemplateRenderer;

public class TabsServlet extends HttpServlet{

	private final TemplateRenderer renderer;

	public TabsServlet(TemplateRenderer renderer)
	{
	  this.renderer = renderer;
	}
	
	//GET method for my TabsServlet returned tabs.vm template with restfull-table on tab 2
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		renderer.render("tabs.vm", res.getWriter());
	}
}