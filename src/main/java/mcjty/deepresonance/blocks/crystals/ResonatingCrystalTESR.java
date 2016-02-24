package mcjty.deepresonance.blocks.crystals;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mcjty.deepresonance.DeepResonance;
import mcjty.deepresonance.blocks.collector.EnergyCollectorTileEntity;
import mcjty.lib.gui.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class ResonatingCrystalTESR extends TileEntitySpecialRenderer<ResonatingCrystalTileEntity> {
    ResourceLocation redhalo = new ResourceLocation(DeepResonance.MODID, "textures/effects/redhalo.png");

    @Override
    public void renderTileEntityAt(ResonatingCrystalTileEntity tileEntity, double x, double y, double z, float time, int breakTime) {

        if (tileEntity.isGlowing()) {
            GlStateManager.pushMatrix();

            GlStateManager.enableRescaleNormal();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.enableBlend();

            GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
            GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
            this.bindTexture(redhalo);
            RenderHelper.renderBillboardQuad(0.6f);

            GlStateManager.popMatrix();
        }
    }
}
