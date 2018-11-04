
package org.terasology.WorldGeneration;

import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.engine.SimpleUri;
import org.terasology.registry.In;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;
import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;

@RegisterWorldGenerator(id = "WorldGen", displayName = "World Generator ")
public class WorldGenerator2 extends BaseFacetedWorldGenerator
{
    public WorldGenerator2(SimpleUri uri)
    {
        super(uri);
    }

    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    @Override
    protected WorldBuilder createWorld()
    {
        return new WorldBuilder(worldGeneratorPluginLibrary)
                .addProvider(new SurfaceProvider())
                .addProvider(new SeaLevelProvider(0))
                .addProvider(new MountainsProvider())
                .addRasterizer(new WorldRasterizer2())
                .addPlugins();
    }
}
