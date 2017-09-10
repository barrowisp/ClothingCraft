package kittyreverant.clothingcraft.client.model;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ClothingCraftModelClothingPuffed extends ClothingCraftModelClothingBiped
{
  public static final int ID = 1;
  
  public ClothingCraftModelClothingPuffed()
  {
    int width = 64;
    int height = 64;
    
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    ModelRenderer clothingBody = new ModelRenderer(this, 38, 0);
    clothingBody.addBox(0.0F, 0.0F, 0.0F, 8, 12, 5);
    clothingBody.setRotationPoint(-4.0F, 0.0F, -2.5F);
    clothingBody.setTextureSize(64, 64);
    setRotation(clothingBody, 0.0F, 0.0F, 0.0F);
    this.bipedBody.addChild(clothingBody);
    
    ModelRenderer clothingArmLeft = new ModelRenderer(this, 0, 30);
    clothingArmLeft.addBox(0.0F, 0.0F, 0.0F, 5, 12, 5);
    clothingArmLeft.setRotationPoint(-1.5F, -1.5F, -2.5F);
    clothingArmLeft.setTextureSize(64, 64);
    clothingArmLeft.mirror = true;
    setRotation(clothingArmLeft, 0.0F, 0.0F, 0.0F);
    this.bipedLeftArm.addChild(clothingArmLeft);
    
    ModelRenderer clothingArmRight = new ModelRenderer(this, 20, 30);
    clothingArmRight.addBox(0.0F, 0.0F, 0.0F, 5, 12, 5);
    
    clothingArmRight.setTextureSize(64, 64);
    setRotation(clothingArmRight, 0.0F, 0.0F, 0.0F);
    this.bipedRightArm.addChild(clothingArmRight);
    
    ModelRenderer clothingLegRight = new ModelRenderer(this, 20, 47);
    clothingLegRight.addBox(0.0F, 0.0F, 0.0F, 5, 12, 5);
    
    clothingLegRight.setTextureSize(64, 64);
    setRotation(clothingLegRight, 0.0F, 0.0F, 0.0F);
    this.bipedRightLeg.addChild(clothingLegRight);
    
    ModelRenderer clothingLegLeft = new ModelRenderer(this, 0, 47);
    clothingLegLeft.addBox(0.0F, 0.0F, 0.0F, 5, 12, 5);
    
    clothingLegLeft.setTextureSize(64, 64);
    setRotation(clothingLegLeft, 0.0F, 0.0F, 0.0F);
    this.bipedLeftLeg.addChild(clothingLegLeft);
  }
  

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setShoesShowing(boolean show) {}
  
  public void setLegsShowing(boolean show) {}
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\client\model\ClothingCraftModelClothingPuffed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */