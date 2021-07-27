/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.api.command.datatypes;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

public enum EntityClassById implements IDatatypeFor<Class<? extends Entity>> {
    INSTANCE;

    @Override
    public Class<? extends Entity> get(IDatatypeContext ctx) throws CommandException {
        Class<? extends Entity> entity = (Class<? extends Entity>) EntityList.stringToClassMapping.get(ctx.getConsumer().getString());
        if (entity == null) {
            throw new IllegalArgumentException("no entity found by that id");
        }
        return entity;
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        return new TabCompleteHelper()
                .append(getEntityNameList().stream().map(Object::toString))
                .filterPrefixNamespaced(ctx.getConsumer().getString())
                .sortAlphabetically()
                .stream();
    }
    
    private List<String> getEntityNameList() {
        Set<String> set = EntityList.stringToClassMapping.keySet();
        List<String> list = Lists.<String>newArrayList();

        for (String s : set) {
            Class <? extends Entity > oclass = (Class)EntityList.stringToClassMapping.get(s);

            if ((oclass.getModifiers() & 1024) != 1024) {
                list.add(s);
            }
        }

        return list;
    }
}
