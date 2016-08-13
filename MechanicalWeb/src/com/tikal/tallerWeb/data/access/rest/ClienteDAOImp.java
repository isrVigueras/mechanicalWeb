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
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.tikal.tallerWeb.control.MensajesControl;
import com.tikal.tallerWeb.data.access.ClienteDAO;
import com.tikal.tallerWeb.modelo.pagination.PaginaCliente;
import com.tikal.tallerWeb.rest.util.AsyncRestCall;
import com.tikal.tallerWeb.rest.util.Callback;
//import com.tikal.tallerWeb.rest.util.RestTemplateFactory;

import technology.tikal.taller.automotriz.model.cliente.Cliente;

/**
 * @author Nekorp
 */
@Service
public class ClienteDAOImp implements ClienteDAO {
//    @Autowired
//    @Qualifier("taller-RestTemplateFactory")
//    private RestTemplateFactory factory;
    @Autowired
    private MensajesControl mensajesControl;
    @Override
    public void guardar(Cliente dato) {
//        if (dato.getId() == null) {
//            URI resource = factory.getTemplate().postForLocation(factory.getRootUlr() + "/clientes", dato);
//            String[] uri = StringUtils.split(resource.toString(), '/');
//            String id = uri[uri.length - 1];
//            dato.setId(Long.valueOf(id));
//        } else {
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", dato.getId());
//            factory.getTemplate().postForLocation(factory.getRootUlr() + "/clientes/{id}", dato, map);
//        }
    }

    @Override
    public List<Cliente> consultaTodos() {
//        PaginaCliente r = factory.getTemplate().getForObject(factory.getRootUlr() + "/clientes", PaginaCliente.class);
//        return r.getItems();
    	return null;
    }

    @Override
    public void buscar(final String name, final Callback<List<Cliente>> cmd) {
        Thread task = new AsyncRestCall<List<Cliente>>() {
            @Override
            public List<Cliente> executeCall() {
                try {
                    String url;
                    if (StringUtils.isEmpty(name)) {
                        return new LinkedList<>();
                    } else {
//                        url = factory.getRootUlr() + "/clientes?filtroNombre={nombre}";
                    }
                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre", name);
//                    PaginaCliente r = factory.getTemplate().getForObject(url, PaginaCliente.class, map);
//                    return r.getItems();
                    return null;
                } catch(ResourceAccessException e) {
                    mensajesControl.reportaError("Error de comunicacion con el servidor");
                    return new LinkedList<>();
                }
            }

            @Override
            public Callback getCallBack() {
                return cmd;
            }
        };
        task.start();
    }
    
    @Override
    public Cliente cargar(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
//        Cliente r = factory.getTemplate().getForObject(factory.getRootUlr() + "/clientes/{id}", Cliente.class, map);
//        return r;
        return null;
    }

//    @Override
//    public Cliente buscarUnico(String name) {
//        String url;
//        if (StringUtils.isEmpty(name)) {
//            return null;
//        } else {
//            url = getRootUlr() + "/clientes?filtroNombre={nombre}";
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("nombre", name);
//        PaginaCliente r = getTemplate().getForObject(url, PaginaCliente.class, map);
//        if (r.getItems().size() != 1) { //no se encontro un unico resultado
//            return null;
//        }
//        return r.getItems().get(0);
//    }

    

}
