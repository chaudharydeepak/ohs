package com.dc.ehs.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dc.ehs.helper.EhsHelper;

@Controller
@RequestMapping("/welcome")
public class ApplicationController
{

	@Autowired
	EhsHelper ehsHelper;

	private static final Logger LOGGER = Logger.getLogger(ApplicationController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String loadHome(ModelMap model)
	{

		LOGGER.info("invoked loadHome ");
		/* return doc search view */
		return "home";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String loadObservation(ModelMap model)
	{
		LOGGER.info("invoked loadObservation ");
		/* return doc search view */
		return "admin/observation";
	}

	@RequestMapping(value = "/admin/manageuser", method = RequestMethod.GET)
	public String loadUser(ModelMap model)
	{
		LOGGER.info("invoked loadUser ");
		model.addAttribute("userList", ehsHelper.loadAllUsers("all"));
		/* return doc search view */
		return "admin/manageuser";
	}
}
