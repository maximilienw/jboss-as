/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.messaging;

import java.util.HashMap;
import java.util.Map;

import org.jboss.as.controller.AttributeDefinition;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.server.operations.ServerWriteAttributeOperationHandler;
import org.jboss.dmr.ModelNode;

/**
 * Write attribute handler for attributes that update HornetQServerControl.
 *
 * @author Brian Stansberry (c) 2011 Red Hat Inc.
 */
public class DivertConfigurationWriteHandler extends ServerWriteAttributeOperationHandler {

    public static final DivertConfigurationWriteHandler INSTANCE = new DivertConfigurationWriteHandler();

    private final Map<String, AttributeDefinition> attributes = new HashMap<String, AttributeDefinition>();
    private DivertConfigurationWriteHandler() {
        for (AttributeDefinition attr : CommonAttributes.DIVERT_ATTRIBUTES) {
            attributes.put(attr.getName(), attr);
        }
    }

    @Override
    protected void validateValue(String name, ModelNode value) throws OperationFailedException {
        AttributeDefinition attr = attributes.get(name);
        attr.getValidator().validateParameter(name, value);
    }

    @Override
    protected void validateResolvedValue(String name, ModelNode value) throws OperationFailedException {
        // no-op, as we are not going to apply this value until the server is reloaded, so allow the
        // any system property to be set between now and then
    }

    @Override
    protected boolean applyUpdateToRuntime(OperationContext context, ModelNode operation, String attributeName,
                                           ModelNode newValue, ModelNode currentValue) throws OperationFailedException {
        return true;
    }

}
