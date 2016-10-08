/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.tikal.tallerWeb.data.access.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.googlecode.objectify.ObjectifyService;
import com.tikal.tallerWeb.DataStoreClass;

import technology.tikal.taller.automotriz.model.auto.Auto;
import technology.tikal.taller.automotriz.model.auto.Equipamiento;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndexAutoData;

/**
 * @author Nekorp
 */
@Service
public class AutoDAOImp // implements AutoDAO
{

	// @Autowired
	// @Qualifier("taller-RestTemplateFactory")
	// private RestTemplateFactory factory;
	//
	public void guardar(Auto dato) {
		// como no tengo idea si es nuevo o no primero se intenta como si ya
		// existiera, si regresa 404 se crea.
		ObjectifyService.ofy().save().entity(dato).now();

		try {
			Map<String, Object> map = new HashMap<>();
			map.put("numeroSerie", dato.getNumeroSerie());
			// factory.getTemplate().postForLocation(factory.getRootUlr() +
			// "/autos/{numeroSerie}", dato, map);
			Key customerKey = KeyFactory.createKey("Auto", dato.getNumeroSerie());
			Entity car = new Entity("Auto", customerKey);
			car.setProperty("color", dato.getColor());
			car.setProperty("equipamiento", dato.getEquipamiento());
			car.setProperty("marca", dato.getMarca());
			car.setProperty("modelo", dato.getModelo());
			car.setProperty("placas", dato.getPlacas());
			car.setProperty("tipo", dato.getTipo());
			car.setProperty("version", dato.getVersion());

			DataStoreClass.datastore.put(car);

		} catch (HttpClientErrorException ex) {

		}
	}

	public List<ServicioIndexAutoData> buscar(final String numeroSerie, final List<ServicioIndexAutoData> cmd) {
		if (StringUtils.isEmpty(numeroSerie)) {
			return new ArrayList<ServicioIndexAutoData>();
		}
		ServicioIndexAutoData sia= new ServicioIndexAutoData();
		// PaginaIndexAuto indice =
		// factory.getTemplate().getForObject(factory.getRootUlr() +
		// "/index/auto", PaginaIndexAuto.class);
		List<ServicioIndexAutoData> respuesta = new LinkedList<>();
		Query query = new Query("Auto");
		query.addFilter("numeroSerie", FilterOperator.EQUAL, numeroSerie);
		PreparedQuery pq = DataStoreClass.datastore.prepare(query);

		Entity e = pq.asSingleEntity();
		if (e != null) {
			sia.setNumeroSerie((String) e.getProperty("numeroSerie"));
			sia.setPlacas((String) e.getProperty("placas"));
			sia.setTipo((String) e.getProperty("tipo"));
			respuesta.add(sia);
			return respuesta;
		}
		return null;
		// for(ServicioIndexAutoData x: indice.getItems()) {
		// if(x.getNumeroSerie().startsWith(StringUtils.upperCase(numeroSerie)))
		// {
		// respuesta.add(x);
		// }
		// }
	}

	public Auto cargar(String numeroSerie) {
		if (StringUtils.isEmpty(numeroSerie)) {
			return new Auto();
		}
		// PaginaIndexAuto indice =
		// factory.getTemplate().getForObject(factory.getRootUlr() +
		// "/index/auto", PaginaIndexAuto.class);
		List<ServicioIndexAutoData> respuesta = new LinkedList<>();
		Query query = new Query("Auto");
		query.addFilter("name", FilterOperator.EQUAL, numeroSerie);
		PreparedQuery pq = DataStoreClass.datastore.prepare(query);

		Entity e = pq.asSingleEntity();
		if (e != null) {
			Auto res = new Auto();
			res.setColor((String) e.getProperty("color"));
			res.setEquipamiento((Equipamiento) e.getProperty("equipamiento"));
			res.setMarca((String) e.getProperty("marca"));
			res.setModelo((String) e.getProperty("modelo"));
			res.setNumeroSerie((String) e.getProperty("numeroSerie"));
			res.setPlacas((String) e.getProperty("placas"));
			res.setTipo((String) e.getProperty("tipo"));
			res.setVersion((String) e.getProperty("version"));
			return res;
		}
		return null;
		// Map<String, Object> map = new HashMap<>();
		// map.put("id", numeroSerie);
		// Auto r = factory.getTemplate().getForObject(factory.getRootUlr() +
		// "/autos/{id}", Auto.class, map);
		// return r;
	}

	// @Override
	// public List<ServicioIndexAutoData> getIndiceAutos() {
	// PaginaIndexAuto r =
	// factory.getTemplate().getForObject(factory.getRootUlr() + "/index/auto",
	// PaginaIndexAuto.class);
	// return r.getItems();
	// }

	public void getIndiceAutos(final List<ServicioIndexAutoData> cmd) {
		// Thread task = new AsyncRestCall<List<ServicioIndexAutoData>>() {
		
		// @Override
		// public List<ServicioIndexAutoData> executeCall() {
		// return getIndiceAutos();
		// }

		// @Override
		// public Callback getCallBack() {
		// return cmd;
		// }
		// };
		// task.start();
	}
}
