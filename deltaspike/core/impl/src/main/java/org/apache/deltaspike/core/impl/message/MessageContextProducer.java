/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.deltaspike.core.impl.message;

import org.apache.deltaspike.core.api.message.LocaleResolver;
import org.apache.deltaspike.core.api.message.MessageContext;
import org.apache.deltaspike.core.api.message.MessageInterpolator;
import org.apache.deltaspike.core.api.message.MessageResolver;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

@ApplicationScoped
@SuppressWarnings("UnusedDeclaration")
public class MessageContextProducer
{
    @Inject
    private LocaleResolver localeResolver;

    @Inject
    private MessageInterpolator messageInterpolator;

    @Inject
    private MessageResolver messageResolver;

    @Produces
    @Typed(MessageContext.class) // needed for _not_ serving as LocaleResolver!
    @Dependent
    protected MessageContext createDefaultMessageContext()
    {
        MessageContext messageContext = new DefaultMessageContext();

        messageContext.setMessageInterpolator(messageInterpolator);
        messageContext.setLocaleResolver(localeResolver);
        messageContext.setMessageResolver(messageResolver);

        return messageContext;
    }
}
