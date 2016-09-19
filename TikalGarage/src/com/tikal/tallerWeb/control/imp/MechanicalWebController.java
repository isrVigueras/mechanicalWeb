package com.tikal.tallerWeb.control.imp;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.com.google.gson.Gson;

@Controller
public class MechanicalWebController {

	@RequestMapping(value = { "/prueba" }, method = RequestMethod.GET)
	public void prueba(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().println("llega");
		// return "akdsjf";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {

		String name = "Israel";
		String email = "correo@falso.com";

		Key customerKey = KeyFactory.createKey("DatosClienteOS", email);

		Entity customer = new Entity("DatosClienteOS", customerKey);
		customer.setProperty("name", name);
		customer.setProperty("email", email);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(customer);
		response.getWriter().write("Elemento agregado");

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listCustomer(HttpServletResponse response) throws IOException {

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("DatosClienteOS").addSort("email", Query.SortDirection.DESCENDING);
		List<Entity> customers = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10));

		Gson g= new Gson();
		String b= g.toJson(customers);
		
		response.getWriter().print(b);


	}
}
