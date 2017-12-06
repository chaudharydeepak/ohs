package com.dc.ehs.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to handle custom exceptions for standard error messages.
 * @author Deepak Chaudhary
 *
 */
@Controller
public class CustomErrorPageController
{
	/**
	 * Method invoked when system generates 403 access denied errors.
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user)
	{

		ModelAndView model = new ModelAndView();

		if (user != null)
		{
			model.addObject("msg", "     Hi " + user.getName() + ", you do not have permission to access this page!");
		} else
		{
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}
