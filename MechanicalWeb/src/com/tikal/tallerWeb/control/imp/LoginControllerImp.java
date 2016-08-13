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
package com.tikal.tallerWeb.control.imp;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.tikal.tallerWeb.control.LoginController;
import com.tikal.tallerWeb.control.MensajesControl;
import com.tikal.tallerWeb.control.StartupController;
import com.tikal.tallerWeb.rest.util.CustomRestErrorHandler;
//import com.tikal.tallerWeb.rest.util.RestTemplateFactory;
import com.tikal.tallerWeb.servicio.login.LoginService;

import technology.tikal.accounts.model.authentication.AuthenticationRequest;
import technology.tikal.accounts.model.session.SessionInfo;

/**
 *
 * @author Nekorp
 */
@Controller
public class LoginControllerImp implements LoginController {
    private static final Logger LOGGER = Logger.getLogger(LoginControllerImp.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private MensajesControl mensajesControl;
//    @Autowired
//    @Qualifier("accounts-RestTemplateFactory")
//    private RestTemplateFactory accountsFactory;
//    @Autowired
//    @Qualifier("taller-RestTemplateFactory")
//    private RestTemplateFactory tallerFactory;
//    @Autowired
//    @Qualifier("customer-RestTemplateFactory")
//    private RestTemplateFactory customerFactory;
//    @Autowired
    private CustomRestErrorHandler errorHandler;
    
    private StartupController parent;
    private boolean iniciado;
    
    @Override
    public boolean login(String usuario, char[] password, String otp) {
        try {
            AuthenticationRequest request = new AuthenticationRequest();
            request.setUser(usuario);
            request.setPassword(new String(password));
            if (!StringUtils.isEmpty(otp)) {
                request.setOtp(Integer.valueOf(otp));
            }
            SessionInfo info = loginService.login(request);
//            tallerFactory.setUsername(info.getUser());
//            tallerFactory.setPassword(info.getToken());
//            tallerFactory.setErrorHandler(errorHandler);
//            tallerFactory.init();
//
//            customerFactory.setUsername(info.getUser());
//            customerFactory.setPassword(info.getToken());
//            customerFactory.setErrorHandler(errorHandler);
//            customerFactory.init();
            if (parent != null) {
                parent.afterLogin();
            }
            iniciado = false;
            return true;
        } catch(ResourceAccessException ex) {
            LoginControllerImp.LOGGER.error("error en login" + ex.getMessage());
        } catch (HttpClientErrorException e) {
            mensajesControl.reportaError("Usuario o pasword no validos");
        }
        return false;
    }

    @Override
    public synchronized void start(StartupController parent) {
        if (!iniciado) {
//            accountsFactory.init();
            iniciado = true;
            this.parent = parent;
        }
    }

    @Override
    public void cancel() {
//        accountsFactory.shutdown();
        System.exit(0);
    }

    @Override
    public void finish() {
        
    }
}
