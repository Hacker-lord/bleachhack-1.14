package bleach.hack.utils;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class WorldUtils {

	public static List<Block> NONSOLID_BLOCKS = Arrays.asList(
			Blocks.AIR, Blocks.LAVA, Blocks.SEAGRASS,
			Blocks.WATER, Blocks.VINE, Blocks.TALL_SEAGRASS,
			Blocks.SNOW, Blocks.TALL_GRASS, Blocks.FIRE, Blocks.GRASS);
		
	public static boolean doesAABBTouchBlock(AxisAlignedBB aabb, Block block) {
		for(int x = (int) Math.floor(aabb.minX); x < Math.ceil(aabb.maxX); x++) {
			for(int y = (int) Math.floor(aabb.minY); y < Math.ceil(aabb.maxY); y++) {
				for(int z = (int) Math.floor(aabb.minZ); z < Math.ceil(aabb.maxZ); z++) {
					if(Minecraft.getInstance().world.getBlockState(new BlockPos(x, y, z)).getBlock() == block) {
						return true;
					}
				}
			}
		}
		return false;
	}
}