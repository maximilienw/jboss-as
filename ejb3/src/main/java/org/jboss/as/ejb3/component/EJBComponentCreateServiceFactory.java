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

package org.jboss.as.ejb3.component;

import org.jboss.as.ee.component.BasicComponentCreateService;
import org.jboss.as.ee.component.ComponentConfiguration;
import org.jboss.as.ee.component.ComponentCreateServiceFactory;
import org.jboss.as.ejb3.component.messagedriven.MessageDrivenComponentCreateService;
import org.jboss.as.ejb3.component.singleton.SingletonComponentCreateService;
import org.jboss.as.ejb3.component.stateful.StatefulSessionComponentCreateService;
import org.jboss.as.ejb3.component.stateless.StatelessComponentCreateServiceFactory;
import org.jboss.as.ejb3.component.stateless.StatelessSessionComponentCreateService;
import org.jboss.as.ejb3.deployment.EjbJarConfiguration;

/**
 * User: jpai
 */
public abstract class EJBComponentCreateServiceFactory implements ComponentCreateServiceFactory {

    protected EjbJarConfiguration ejbJarConfiguration;

    public void setEjbJarConfiguration(EjbJarConfiguration ejbJarConfiguration) {
        if (ejbJarConfiguration == null) {
            throw new IllegalArgumentException("EjbJarConfiguration cannot be null");
        }
        this.ejbJarConfiguration = ejbJarConfiguration;
    }

}
