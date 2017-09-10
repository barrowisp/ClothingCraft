package kittyreverant.clothingcraft.client;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.PrintStream;

import kittyreverant.clothingcraft.client.model.ClothingCraftModelClothingBasic;
import kittyreverant.clothingcraft.client.model.ClothingCraftModelClothingBiped;
import kittyreverant.clothingcraft.client.model.ClothingCraftModelClothingHat;
import kittyreverant.clothingcraft.client.model.ClothingCraftModelClothingPuffed;
import kittyreverant.clothingcraft.common.ClothingCraftCommonProxy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

@SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
public class ClothingCraftClientProxy extends ClothingCraftCommonProxy
{
  private static final ClothingCraftModelClothingPuffed modelClothingPuffed = new ClothingCraftModelClothingPuffed();
  private static final ClothingCraftModelClothingBasic modelClothingBasic = new ClothingCraftModelClothingBasic();
  private static final ClothingCraftModelClothingHat modelClothingHat = new ClothingCraftModelClothingHat();
  

  public ModelBiped getArmorModel(int id)
  {
    if (id == 1) return modelClothingPuffed;
    if (id == 2) return modelClothingBasic;
    if (id == 3) { return modelClothingHat;
    }
    System.err.println("Unknown armor model ID: " + id);
    return null;
  }
  

  public ModelBiped fixModel(ModelBiped model, EntityEquipmentSlot armorSlot, EntityLivingBase living)
  {
    ClothingCraftModelClothingBiped modelCast = (ClothingCraftModelClothingBiped)model;
    
    if (modelCast != null) {
      modelCast.bipedHead.showModel = (armorSlot == EntityEquipmentSlot.HEAD);
      modelCast.bipedHeadwear.showModel = (armorSlot == EntityEquipmentSlot.HEAD);
      modelCast.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST);
      modelCast.bipedRightArm.showModel = (armorSlot == EntityEquipmentSlot.CHEST);
      modelCast.bipedLeftArm.showModel = (armorSlot == EntityEquipmentSlot.CHEST);
      modelCast.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS);
      modelCast.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS);
      modelCast.setShoesShowing(armorSlot == EntityEquipmentSlot.FEET);
      modelCast.setLegsShowing(armorSlot == EntityEquipmentSlot.LEGS);
      
      modelCast.isSneak = living.isSneaking();
      modelCast.isRiding = living.isRiding();
      modelCast.isChild = living.isChild();
      modelCast.rightArmPose = ModelBiped.ArmPose.ITEM;//(living.getEquipmentInSlot(0) != null ? 1 : 0);
      
      //if ((living instanceof EntityPlayer)) { modelCast.aimedBow = (((EntityPlayer)living).getItemInUseCount() > 2);
      //}
    }
    
    return modelCast;
  }
  
  @Override
  public void registerItemRenderer(Item item, int meta, String id) {
  	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("clothingcraft:" + id, "inventory"));
  }
  
  @Override
  public boolean isDedicatedServer() {return false;}
}
