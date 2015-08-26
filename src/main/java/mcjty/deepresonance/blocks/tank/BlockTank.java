package mcjty.deepresonance.blocks.tank;

import mcjty.deepresonance.blocks.base.ElecGenericBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Map;

/**
 * Created by Elec332 on 20-8-2015.
 */
public class BlockTank extends ElecGenericBlockBase {

    public BlockTank(String name) {
        super(Material.rock, TileTank.class, name);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileTank) {
            TileTank tank = (TileTank) tile;
            for (Map.Entry<ITankHook, ForgeDirection> entry : tank.getConnectedHooks().entrySet()) {
                entry.getKey().hook(tank, entry.getValue());
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float sidex, float sidey, float sidez) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileTank){
            TileTank tank = (TileTank)tile;
            ForgeDirection direction = ForgeDirection.getOrientation(side);
            int i = tank.settings.get(direction);
            if (i < 2)
                i++;
            else
                i = 0;
            tank.settings.put(direction, i);
            tank.markDirty();
            return true;
        }
        return super.onBlockActivated(world, x, y, z, player, side, sidex, sidey, sidez);
    }
}