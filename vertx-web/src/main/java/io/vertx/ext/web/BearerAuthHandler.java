/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.ext.web;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.web.handler.AuthenticationHandler;
import io.vertx.ext.web.handler.impl.BearerAuthHandlerImpl;

/**
 * An auth handler that provides HTTP Bearer Authentication support.
 *
 * @author <a href="mailto:madewael@gmail.com">Mattias De Wael</a>
 */
@VertxGen
public interface BearerAuthHandler extends AuthenticationHandler {

  /**
   * The default realm to use
   */
  String DEFAULT_REALM = "vertx-web";

    /**
     * Create a bearer auth handler
     *
     * @param authProvider the auth provider to use
     * @return the auth handler
     */
    static BearerAuthHandler create(AuthenticationProvider authProvider) {

        return create(authProvider, DEFAULT_REALM);
    }

    /**
     * Create a basic auth handler, specifying realm
     *
     * @param authProvider the auth service to use
     * @param realm        the realm to use
     * @return the auth handler
     */
    static BearerAuthHandler create(AuthenticationProvider authProvider, String realm) {
        return new BearerAuthHandlerImpl(authProvider, realm);
    }
}
