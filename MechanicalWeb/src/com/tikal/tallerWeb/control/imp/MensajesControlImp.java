/**
 *   Copyright 2013 Nekorp
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

package com.tikal.tallerWeb.control.imp;

import org.springframework.stereotype.Service;

import com.tikal.tallerWeb.control.MensajesControl;

/**
 *
 */
@Service
public class MensajesControlImp implements MensajesControl {

    @Override
    public void reportaError(String error) {
        //hacer como que hacer pero no hacer nada
    }

    @Override
    public void reportarAlerta(Long folio, String mensaje) {
        //hace como que hace pero tampoco hace nada
    }
}
