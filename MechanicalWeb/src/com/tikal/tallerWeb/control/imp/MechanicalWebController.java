package com.tikal.tallerWeb.control.imp;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MechanicalWebController {

	@RequestMapping(value={"/prueba"}, method = RequestMethod.GET)
	public String prueba(HttpServletRequest request, 	HttpServletResponse response) throws IOException{
//		response.getWriter().println("llega");
		return "akdsjf";
	}
}
