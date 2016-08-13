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

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.tikal.tallerWeb.data.access.AutoDAO;
import com.tikal.tallerWeb.modelo.pagination.PaginaIndexAuto;
import com.tikal.tallerWeb.rest.util.AsyncRestCall;
import com.tikal.tallerWeb.rest.util.Callback;
//import com.tikal.tallerWeb.rest.util.RestTemplateFactory;

import technology.tikal.taller.automotriz.model.auto.Auto;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndexAutoData;

/**
 * @author Nekorp
 */
@Service
public class AutoDAOImp //implements AutoDAO 
{

//    @Autowired
//    @Qualifier("taller-RestTemplateFactory")
//    private RestTemplateFactory factory;
//    
    public void guardar(Auto dato) {
        // como no tengo idea si es nuevo o no primero se intenta como si ya existiera, si regresa 404 se crea.
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("numeroSerie", dato.getNumeroSerie());
//            factory.getTemplate().postForLocation(factory.getRootUlr() + "/autos/{numeroSerie}", dato, map);
        } catch (HttpClientErrorException ex) {
            HttpStatus status = ex.getStatusCode();
            if (status != HttpStatus.NOT_FOUND) {
                throw ex; 
            }
//            URI resource = factory.getTemplate().postForLocation(factory.getRootUlr() + "/autos", dato);
//            String[] uri = StringUtils.split(resource.toString(), '/');
//            String id = uri[uri.length - 1];
//            dato.setNumeroSerie(id);
        }
    }

    public void buscar(final String numeroSerie, final Callback<List<ServicioIndexAutoData>> cmd) {
        Thread task = new AsyncRestCall<List<ServicioIndexAutoData>>() {
            @Override
            public List<ServicioIndexAutoData> executeCall() {
                if (StringUtils.isEmpty(numeroSerie)) {
                    return new LinkedList<>();
                } 
//                PaginaIndexAuto indice = factory.getTemplate().getForObject(factory.getRootUlr() + "/index/auto", PaginaIndexAuto.class);
                List<ServicioIndexAutoData> respuesta = new LinkedList<>();
//                for(ServicioIndexAutoData x: indice.getItems()) {
//                    if(x.getNumeroSerie().startsWith(StringUtils.upperCase(numeroSerie))) {
//                        respuesta.add(x);
//                    }
//                }
                return respuesta;
            }

            @Override
            public Callback getCallBack() {
                return cmd;
            }
        };
        task.start();
    }
    
    public Auto cargar(String numeroSerie) {
    	return null;
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", numeroSerie);
//        Auto r = factory.getTemplate().getForObject(factory.getRootUlr() + "/autos/{id}", Auto.class, map);
//        return r;
    }

//    @Override
//    public List<ServicioIndexAutoData> getIndiceAutos() {
//        PaginaIndexAuto r = factory.getTemplate().getForObject(factory.getRootUlr() + "/index/auto", PaginaIndexAuto.class);
//        return r.getItems();
//    }

    public void getIndiceAutos(final Callback<List<ServicioIndexAutoData>> cmd) {
//        Thread task = new AsyncRestCall<List<ServicioIndexAutoData>>() {
            
//            @Override
//            public List<ServicioIndexAutoData> executeCall() {
//                return getIndiceAutos();
//            }

//            @Override
//            public Callback getCallBack() {
//                return cmd;
//            }
//        };
//        task.start();
    }
}
