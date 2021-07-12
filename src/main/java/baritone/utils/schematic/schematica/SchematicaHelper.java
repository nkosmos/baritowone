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

package baritone.utils.schematic.schematica;

import java.util.Optional;

import com.github.lunatrius.schematica.Schematica;
import com.github.lunatrius.schematica.proxy.ClientProxy;

import baritone.api.schematic.IStaticSchematic;
import baritone.api.utils.BetterBlockPos;
import baritonex.utils.XTuple;

public enum SchematicaHelper {
    ;

    public static boolean isSchematicaPresent() {
        try {
            Class.forName(Schematica.class.getName());
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError ex) {
            return false;
        }
    }

    public static Optional<XTuple<IStaticSchematic, BetterBlockPos>> getOpenSchematic() {
        return Optional.ofNullable(ClientProxy.schematic)
                .map(world -> new XTuple<>(new SchematicAdapter(world), world.position));
    }

}
