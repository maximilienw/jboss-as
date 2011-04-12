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
package org.jboss.as.cli.handlers;

import java.util.List;

import org.jboss.as.cli.CommandContext;

/**
 *
 * @author Alexey Loubyansky
 */
public class BatchHandler extends CommandHandlerWithHelp {

    public BatchHandler() {
        super("batch", new SimpleTabCompleter(new String[]{}));
    }

    /* (non-Javadoc)
     * @see org.jboss.as.cli.handlers.CommandHandlerWithHelp#doHandle(org.jboss.as.cli.CommandContext)
     */
    @Override
    protected void doHandle(CommandContext ctx) {

        if (ctx.isBatchMode()) {
            ctx.printLine("Can't start a new batch while in batch mode.");
            return;
        }

        String name = null;
        if (ctx.hasArguments()) {
            name = ctx.getArguments().get(0);
        }

        ctx.startBatch(name);

        List<String> batch = ctx.getCurrentBatch();
        if (!batch.isEmpty()) {
            for (int i = 0; i < batch.size(); ++i) {
                String line = batch.get(i);
                ctx.printLine("#" + (i + 1) + ' ' + line);
            }
        }
        return;
    }
}