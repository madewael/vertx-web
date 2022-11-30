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

package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.auth.authentication.TokenCredentials;
import io.vertx.ext.web.BearerAuthHandler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.HttpException;

/**
 * @author <a href="mailto:madewael@gmail.com">Mattias De Wael</a>
 */
public class BearerAuthHandlerImpl extends HTTPAuthorizationHandler<AuthenticationProvider> implements BearerAuthHandler {

    public BearerAuthHandlerImpl(AuthenticationProvider authProvider, String realm) {
        super(authProvider, Type.BEARER, realm);
    }

    @Override
    public void authenticate(RoutingContext context, Handler<AsyncResult<User>> handler) {

        parseAuthorization(context, parseAuthorization -> {
            if (parseAuthorization.failed()) {
                handler.handle(Future.failedFuture(parseAuthorization.cause()));
                return;
            }

            authProvider.authenticate(new TokenCredentials(parseAuthorization.result()), authn -> {
                if (authn.failed()) {
                    handler.handle(Future.failedFuture(new HttpException(401, authn.cause())));
                } else {
                    handler.handle(authn);
                }
            });
        });
    }
}
