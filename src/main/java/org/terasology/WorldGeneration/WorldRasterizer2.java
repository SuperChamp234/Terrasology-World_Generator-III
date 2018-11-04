/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.WorldGeneration;

import org.terasology.world.generation.WorldRasterizer;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.math.geom.Vector3i;
import org.terasology.math.ChunkMath;

public class WorldRasterizer2 implements WorldRasterizer
{
    private Block dirt, grass, stone , sand , snow;

    @Override
    public void initialize()
    {
        dirt = CoreRegistry.get(BlockManager.class).getBlock("Core:Dirt");
        grass = CoreRegistry.get(BlockManager.class).getBlock("Core:Grass");
        stone = CoreRegistry.get(BlockManager.class).getBlock("Core:Stone");
        snow = CoreRegistry.get(BlockManager.class).getBlock("Core:snow");
        sand = CoreRegistry.get(BlockManager.class).getBlock("Core:sand");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion)
    {
        //Placing the blocks
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);

        for (Vector3i position : chunkRegion.getRegion()) {
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x, position.z);

            if (position.y < surfaceHeight - 5) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), stone);
            }
            else if (position.y < surfaceHeight - 1) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), dirt);
            }
            else if (position.y < surfaceHeight && surfaceHeight < 80) {
                if (position.y > -3 && position.y < 3) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), sand);
                } else {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), grass);
                }
            } else if (position.y < surfaceHeight && surfaceHeight >= 50) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), snow);
            }
        }
    }
}
