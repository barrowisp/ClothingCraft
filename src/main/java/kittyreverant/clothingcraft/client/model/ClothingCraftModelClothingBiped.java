package kittyreverant.clothingcraft.client.model;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public abstract class ClothingCraftModelClothingBiped
  extends ModelBiped
{
  public abstract void setShoesShowing(boolean paramBoolean);
  
  public abstract void setLegsShowing(boolean paramBoolean);
  
  protected void renderNonLayeredTexture(ModelRenderer piece, float f5, ResourceLocation texture)
  {
    renderLayeredTexture(piece, f5, new ResourceLocation[] { texture });
  }
  





  protected void renderLayeredTexture(ModelRenderer piece, float f5, ResourceLocation[] textures)
  {
    for (ResourceLocation texture : textures) {
      FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
      piece.render(f5);
    }
  }
  





  protected void renderNonLayeredTexture(ModelRenderer piece, float f5, ResourceLocation texture, int renderColor)
  {
    renderLayeredTexture(piece, f5, new ResourceLocation[] { texture }, new int[] { renderColor });
  }
  






  protected void renderLayeredTexture(ModelRenderer piece, float f5, ResourceLocation[] textures, int[] renderColors)
  {
    int i = 0;
    for (ResourceLocation texture : textures) {
      FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
      
      GL11.glPushMatrix();
      
      int hex = renderColors[i];
      float r = hex >> 16 & 0xFF;
      float g = hex >> 8 & 0xFF;
      float b = hex >> 0 & 0xFF;
      

      GL11.glColor3f(r / 255.0F, g / 255.0F, b / 255.0F);
      
      piece.render(f5);
      
      GL11.glPopMatrix();
      i++;
    }
  }
  

@Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
  super.render(entity, f, f1, f2, f3, f4, f5);
  }





  protected void renderLayeredTexture(ModelRenderer piece, float f5, ResourceLocation[] textures, int[] renderColors, boolean[] shouldRender)
  {
    int i = 0;
    for (ResourceLocation texture : textures)
      if (shouldRender[i] == false) {
        i++;
      }
      else
      {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        
        GL11.glPushMatrix();
        
        int hex = renderColors[i];
        float r = hex >> 16 & 0xFF;
        float g = hex >> 8 & 0xFF;
        float b = hex >> 0 & 0xFF;
        

        GL11.glColor3f(r / 255.0F, g / 255.0F, b / 255.0F);
        
        piece.render(f5);
        
        GL11.glPopMatrix();
        
        i++;
      }
  }
  
  protected void copyRotationAndAngles(ModelRenderer from, ModelRenderer to) {
    from.rotateAngleX = to.rotateAngleX;
    from.rotateAngleY = to.rotateAngleY;
    from.rotateAngleZ = to.rotateAngleZ;
    
    from.rotationPointX = to.rotationPointX;
    from.rotationPointY = to.rotationPointY;
    from.rotationPointZ = to.rotationPointZ;
  }
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\client\model\ClothingCraftModelClothingBiped.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */