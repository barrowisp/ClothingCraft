package kittyreverant.clothingcraft.client.model;

import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorableHat;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ClothingCraftModelClothingHat
  extends ClothingCraftModelClothingBiped
{
  public static final int ID = 3;
  private static final ResourceLocation hat_1 = new ResourceLocation("clothingcraft", "textures/clothing/hat_1.png");
  private static final ResourceLocation hat_2 = new ResourceLocation("clothingcraft", "textures/clothing/hat_2.png");
  private static final ResourceLocation[] hat_array = { hat_1, hat_2 };
  
  ModelRenderer hatBrim;
  ModelRenderer hatTop;
  ModelRenderer copyOfHead;
  
  public ClothingCraftModelClothingHat()
  {
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.hatBrim = new ModelRenderer(this, 0, 50);
    this.hatBrim.addBox(0.0F, 0.0F, 0.0F, 12, 2, 12);
    this.hatBrim.setRotationPoint(-6.0F, -7.0F, -6.0F);
    this.hatBrim.setTextureSize(64, 64);
    this.hatBrim.mirror = true;
    setRotation(this.hatBrim, 0.0F, 0.0F, 0.0F);
    
    this.hatTop = new ModelRenderer(this, 0, 37);
    this.hatTop.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8);
    this.hatTop.setRotationPoint(-4.0F, -11.0F, -4.0F);
    this.hatTop.setTextureSize(64, 64);
    setRotation(this.hatTop, 0.0F, 0.0F, 0.0F);
    
    this.copyOfHead = new ModelRenderer(this, 0, 20);
    this.copyOfHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
    this.copyOfHead.setRotationPoint(0.0F, 0.0F, 0.0F);
    
    this.copyOfHead.addChild(this.hatBrim);
    this.copyOfHead.addChild(this.hatTop);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    boolean hasHat = false;
    int hatBaseColor = 0;
    int hatTrimColor = 0;
    
    if ((entity instanceof EntityLivingBase))
    {
      EntityLivingBase living = (EntityLivingBase)entity;
      ItemStack item = living.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
      if (item.getItem() instanceof ClothingCraftClothingColorableHat) {
        hasHat = true;
        ItemStack hatStack = item;
        hatBaseColor = ClothingCraftClothingColorableHat.getBaseColor(hatStack);
        hatTrimColor = ClothingCraftClothingColorableHat.getTrimColor(hatStack);
      }
    }
    


    this.bipedHead.isHidden = true;
    this.bipedHeadwear.isHidden = true;
    
    copyRotationAndAngles(this.copyOfHead, this.bipedHead);
    super.render(entity, f, f1, f2, f3, f4, f5);
    
    if (hasHat) {
      renderLayeredTexture(this.copyOfHead, f5, hat_array, new int[] { hatBaseColor, hatTrimColor });
    }
    
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
  
  public void setShoesShowing(boolean show) {}
  
  public void setLegsShowing(boolean show) {}
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\client\model\ClothingCraftModelClothingHat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */