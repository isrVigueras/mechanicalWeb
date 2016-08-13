/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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
package com.tikal.tallerWeb.data.access;

import com.tikal.tallerWeb.rest.util.Callback;

import technology.tikal.customers.model.Customer;

/**
 *
 * @author Nekorp
 */
public interface CustomerDAO {
    /**
     * Guarda o actualiza un cliente.
     * Si el cliente no tiene id al terminar de guardar se le asignara un id.
     * @param dato el cliente a guarda.
     */
    void guardar(Customer dato);
    /**
     * busca un cliente dado un nombre, este metodo es asyncrono.
     * @param name
     * @param cmd 
     */
    void buscar(String name, Callback<Customer[]> cmd);
    
    Customer cargar(Long id);
    
    /**
     * consulta todos los clientes, ignora por el momento temas de paginacion.
     * @return la lista de todos los clientes.
     */
    Customer[] consultaTodos();
    
    /**
     * consulta todos de manera asyncrona
     * @param cmd 
     */
    void consultaTodos(Callback<Customer[]> cmd);
}
