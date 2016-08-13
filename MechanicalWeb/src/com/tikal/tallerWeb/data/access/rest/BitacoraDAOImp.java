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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tikal.tallerWeb.data.access.BitacoraDAO;
//import com.tikal.tallerWeb.rest.util.RestTemplateFactory;

import technology.tikal.taller.automotriz.model.servicio.bitacora.Evento;

/**
 * @author Nekorp
 */
@Service
public class BitacoraDAOImp implements BitacoraDAO {

//    @Autowired
//    @Qualifier("taller-RestTemplateFactory")
//    private RestTemplateFactory factory;
    
//    @Override
    public List<Evento> cargar(Long idServicio) {
        Map<String, Object> map = new HashMap<>();
        map.put("idServicio", idServicio);
//        Evento[] r = factory.getTemplate().getForObject(factory.getRootUlr() + "/servicios/{idServicio}/bitacora", Evento[].class, map);
//        return Arrays.asList(r);
        return null;
    }

    @Override
    public List<Evento> guardar(Long idServicio, List<Evento> datos) {
        Map<String, Object> map = new HashMap<>();
        map.put("idServicio", idServicio);
//        Evento[] r = factory.getTemplate().postForObject(factory.getRootUlr() + "/servicios/{idServicio}/bitacora", datos, Evento[].class, map);
//        return Arrays.asList(r);
        return null;
    }

}
