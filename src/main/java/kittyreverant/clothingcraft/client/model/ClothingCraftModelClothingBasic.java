package kittyreverant.clothingcraft.client.model;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorablePants;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorableShirt;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorableShoes;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;



@SideOnly(Side.CLIENT)
public class ClothingCraftModelClothingBasic
  extends ClothingCraftModelClothingBiped
{
  private static final ResourceLocation pants = new ResourceLocation("clothingcraft", "textures/clothing/pants.png");
  
  private static final ResourceLocation shirt_overlay_1 = new ResourceLocation("clothingcraft", "textures/clothing/clothing_1.png");
  private static final ResourceLocation shirt_overlay_2 = new ResourceLocation("clothingcraft", "textures/clothing/clothing_2.png");
  private static final ResourceLocation shirt_overlay_3 = new ResourceLocation("clothingcraft", "textures/clothing/clothing_3.png");
  private static final ResourceLocation shirt_overlay_sleeves = new ResourceLocation("clothingcraft", "textures/clothing/clothing_sleeves.png");
  private static final ResourceLocation shirt_overlay_buttons = new ResourceLocation("clothingcraft", "textures/clothing/clothing_buttons.png");
  
  private static final ResourceLocation[] shirtLayers_trimType0 = { shirt_overlay_1, shirt_overlay_2, shirt_overlay_sleeves, shirt_overlay_buttons };
  private static final ResourceLocation[] shirtLayers_trimType1 = { shirt_overlay_1, shirt_overlay_3, shirt_overlay_sleeves, shirt_overlay_buttons };
  
  private static final ResourceLocation shoes_overlay_1 = new ResourceLocation("clothingcraft", "textures/clothing/shoes_1.png");
  private static final ResourceLocation shoes_overlay_2 = new ResourceLocation("clothingcraft", "textures/clothing/shoes_2.png");
  
  private static final ResourceLocation[] shoes_overlays = { shoes_overlay_1, shoes_overlay_2 };
  

  public static final int ID = 2;
  

  ModelRenderer footLeft;
  

  ModelRenderer footRight;
  
  ModelRenderer copyOfLegLeft;
  
  ModelRenderer copyOfLegRight;
  
  public ModelRenderer overlay_body;
  
  public ModelRenderer overlay_armRight;
  
  public ModelRenderer overlay_armLeft;
  
  public ModelRenderer overlay_legRight;
  
  public ModelRenderer overlay_legLeft;
  

  public ClothingCraftModelClothingBasic()
  {
    int WIDTH = 64;
    int HEIGHT = 32;
    
    this.textureWidth = 64;
    this.textureHeight = 32;
    
    this.bipedHeadwear.isHidden = true;
    
    int offy = 10;
    
    this.footLeft = new ModelRenderer(this, 0, 24);
    this.footLeft.addBox(0.0F, 0.0F, 0.0F, 4, 2, 6);
    this.footLeft.setRotationPoint(-2.0F, 10.0F, -5.0F);
    this.footLeft.setTextureSize(64, 32);
    this.footLeft.mirror = true;
    setRotation(this.footLeft, 0.0F, 0.0F, 0.0F);
    
    this.footRight = new ModelRenderer(this, 0, 24);
    this.footRight.addBox(0.0F, 0.0F, 0.0F, 4, 2, 6);
    this.footRight.setRotationPoint(-2.0F, 10.0F, -5.0F);
    this.footRight.setTextureSize(64, 32);
    this.footRight.mirror = true;
    setRotation(this.footRight, 0.0F, 0.0F, 0.0F);
    
    this.copyOfLegRight = new ModelRenderer(this, 0, 0);
    this.copyOfLegRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    this.copyOfLegRight.setRotationPoint(-1.9F, 12.0F, 0.0F);
    
    this.copyOfLegLeft = new ModelRenderer(this, 0, 0);
    this.copyOfLegLeft.mirror = true;
    this.copyOfLegLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    this.copyOfLegLeft.setRotationPoint(1.9F, 12.0F, 0.0F);
    
    this.copyOfLegLeft.addChild(this.footLeft);
    this.copyOfLegRight.addChild(this.footRight);
    
    this.overlay_body = new ModelRenderer(this, 16, 16);
    this.overlay_body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
    this.overlay_body.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.overlay_body.setTextureSize(64, 32);
    
    this.overlay_armRight = new ModelRenderer(this, 40, 16);
    this.overlay_armRight.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
    this.overlay_armRight.setRotationPoint(-5.0F, 2.0F, 0.0F);
    this.overlay_armRight.setTextureSize(64, 32);
    
    this.overlay_armLeft = new ModelRenderer(this, 40, 16);
    this.overlay_armLeft.mirror = true;
    this.overlay_armLeft.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
    this.overlay_armLeft.setRotationPoint(5.0F, 2.0F, 0.0F);
    this.overlay_armLeft.setTextureSize(64, 32);
    
    this.overlay_legRight = new ModelRenderer(this, 0, 16);
    this.overlay_legRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    this.overlay_legRight.setRotationPoint(-1.9F, 12.0F, 0.0F);
    this.overlay_legRight.setTextureSize(64, 32);
    
    this.overlay_legLeft = new ModelRenderer(this, 0, 16);
    this.overlay_legLeft.mirror = true;
    this.overlay_legLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    this.overlay_legLeft.setRotationPoint(1.9F, 12.0F, 0.0F);
    this.overlay_legLeft.setTextureSize(64, 32);
  }
  

@Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    ResourceLocation[] shirtLayers = null;
    
    boolean hasShirt = false;
    int coloringTorsoBase = 0;
    int coloringTorsoTrim = 0;
    int coloringTorsoSleeves = 0;
    int coloringTorsoButtons = 0;
    
    boolean hasPants = false;
    int coloringPants = 0;
    
    boolean hasShoes = false;
    int coloringShoesBase = 0;
    int coloringShoesTrim = 0;
    if ((entity instanceof EntityLivingBase))
    {
      EntityLivingBase living = (EntityLivingBase)entity;
      ItemStack item = living.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
      if(item != null && item.getItem() instanceof ClothingCraftClothingColorablePants)
 	 {
 		 hasPants = true;
 		 ItemStack stack = item;
 		 coloringPants = ClothingCraftClothingColorablePants.getPantsColor(stack);
 	 }
      item = living.getItemStackFromSlot(EntityEquipmentSlot.FEET);
      if(item != null && item.getItem() instanceof ClothingCraftClothingColorableShoes)
 	 {
 		 hasShoes = true;
 	        ItemStack stack = item;
 	        coloringShoesBase = ClothingCraftClothingColorableShoes.getBaseColor(stack);
 	        coloringShoesTrim = ClothingCraftClothingColorableShoes.getTrimColor(stack);
 	 }
      item = living.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
    	 if(item != null && item.getItem() instanceof ClothingCraftClothingColorableShirt)
    	 {
    		 hasShirt = true;
    	        ItemStack stack = item;
    	        ClothingCraftClothingColorableShirt shirt = (ClothingCraftClothingColorableShirt)stack.getItem();
    	        coloringTorsoBase = ClothingCraftClothingColorableShirt.getBaseColor(stack);
    	        coloringTorsoTrim = ClothingCraftClothingColorableShirt.getTrimColor(stack);
    	        coloringTorsoSleeves = ClothingCraftClothingColorableShirt.getSleeveColor(stack);
    	        coloringTorsoButtons = ClothingCraftClothingColorableShirt.getButtonColor(stack);
    	        
    	        if (ClothingCraftClothingColorableShirt.getTrimType(stack) == 0) {
    	          shirtLayers = shirtLayers_trimType0;
    	        } else {
    	          shirtLayers = shirtLayers_trimType1;
    	        }
    	 
    	
     }
     /*
      if ((living.getEquipmentInSlot(3) != null) && ((living.getEquipmentInSlot(3).getItem() instanceof ClothingCraftClothingColorableShirt))) {
        hasShirt = true;
        ItemStack stack = living.getEquipmentInSlot(3);
        ClothingCraftClothingColorableShirt shirt = (ClothingCraftClothingColorableShirt)stack.getItem();
        coloringTorsoBase = ClothingCraftClothingColorableShirt.getBaseColor(stack);
        coloringTorsoTrim = ClothingCraftClothingColorableShirt.getTrimColor(stack);
        coloringTorsoSleeves = ClothingCraftClothingColorableShirt.getSleeveColor(stack);
        coloringTorsoButtons = ClothingCraftClothingColorableShirt.getButtonColor(stack);
        
        if (ClothingCraftClothingColorableShirt.getTrimType(stack) == 0) {
          shirtLayers = shirtLayers_trimType0;
        } else {
          shirtLayers = shirtLayers_trimType1;
        }
      }
      
      if ((living.getEquipmentInSlot(2) != null) && ((living.getEquipmentInSlot(2).getItem() instanceof ClothingCraftClothingColorablePants))) {
        hasPants = true;
        ItemStack stack = living.getEquipmentInSlot(2);
        coloringPants = ClothingCraftClothingColorablePants.getPantsColor(stack);
      }
      
      if ((living.getEquipmentInSlot(1) != null) && ((living.getEquipmentInSlot(1).getItem() instanceof ClothingCraftClothingColorableShoes))) {
        hasShoes = true;
        ItemStack stack = living.getEquipmentInSlot(1);
        coloringShoesBase = ClothingCraftClothingColorableShoes.getBaseColor(stack);
        coloringShoesTrim = ClothingCraftClothingColorableShoes.getTrimColor(stack);
      }*/
    }
    

    super.render(entity, f, f1, f2, f3, f4, f5);
    
    if (hasShoes) {
      renderLayeredTexture(this.copyOfLegLeft, f5, shoes_overlays, new int[] { coloringShoesBase, coloringShoesTrim });
      renderLayeredTexture(this.copyOfLegRight, f5, shoes_overlays, new int[] { coloringShoesBase, coloringShoesTrim });
    }
    
    if (hasShirt) {
      int[] shirtColorArray = { coloringTorsoBase, coloringTorsoTrim, coloringTorsoSleeves, coloringTorsoButtons };
      renderLayeredTexture(this.overlay_body, f5, shirtLayers, shirtColorArray);
      renderLayeredTexture(this.overlay_armLeft, f5, shirtLayers, shirtColorArray, new boolean[] { false, false, true, false });
      renderLayeredTexture(this.overlay_armRight, f5, shirtLayers, shirtColorArray, new boolean[] { false, false, true, false });
    }
    
    if (hasPants) {
      renderNonLayeredTexture(this.overlay_legLeft, f5, pants, coloringPants);
      renderNonLayeredTexture(this.overlay_legRight, f5, pants, coloringPants);
    }
    
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setShoesShowing(boolean show)
  {
    this.footLeft.isHidden = (this.footRight.isHidden = this.copyOfLegLeft.isHidden = this.copyOfLegRight.isHidden = !show ? true : false);
  }
  
  public void setLegsShowing(boolean show)
  {
    this.bipedLeftLeg.isHidden = (this.bipedRightLeg.isHidden);
  }
  
  public void setRotationAngles(float time, float howFar, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity)
  {
    super.setRotationAngles(time, howFar, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entity);
    
    copyRotationAndAngles(this.copyOfLegLeft, this.bipedLeftLeg);
    copyRotationAndAngles(this.copyOfLegRight, this.bipedRightLeg);
    
    copyRotationAndAngles(this.overlay_body, this.bipedBody);
    copyRotationAndAngles(this.overlay_armLeft, this.bipedLeftArm);
    copyRotationAndAngles(this.overlay_armRight, this.bipedRightArm);
    copyRotationAndAngles(this.overlay_legLeft, this.bipedLeftLeg);
    copyRotationAndAngles(this.overlay_legRight, this.bipedRightLeg);
  }
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\client\model\ClothingCraftModelClothingBasic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */