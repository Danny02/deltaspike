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
package org.apache.deltaspike.data.impl.handler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class CdiQueryContextHolder
{

    private final ThreadLocal<CdiQueryInvocationContext> context = new ThreadLocal<CdiQueryInvocationContext>();

    public void set(CdiQueryInvocationContext context)
    {
        this.context.set(context);
    }

    @Produces
    public CdiQueryInvocationContext get()
    {
        return context.get();
    }

    public void dispose()
    {
        CdiQueryInvocationContext ctx = context.get();
        if (ctx != null)
        {
            ctx.cleanup();
        }
        context.remove();
    }

}
