package kittyreverant.clothingcraft.common;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class ClothingCraftCommonProxy
{
  public ModelBiped getArmorModel(int id) {
    return null;
  }
  
  public ModelBiped fixModel(ModelBiped model, EntityEquipmentSlot armorSlot, net.minecraft.entity.EntityLivingBase living) {
    return model;
  }
  
  public void registerItemRenderer(Item item, int meta, String id) {
  }
  
 
  public boolean isDedicatedServer() {return true;}
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\ClothingCraftCommonProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */