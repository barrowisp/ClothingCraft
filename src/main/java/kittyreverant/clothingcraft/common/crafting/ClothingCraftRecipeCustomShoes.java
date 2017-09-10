package kittyreverant.clothingcraft.common.crafting;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Clothing;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric.ColoringUtil;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorableShoes;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ClothingCraftRecipeCustomShoes implements IRecipe
{
  private ItemStack output;
  
  public boolean matches(InventoryCrafting ic, World world)
  {
    this.output = null;
    
    boolean violatedRules = false;
    
    String baseColor = "";
    String trimColor = "";
    
    for (int slot = 0; slot < ic.getSizeInventory(); slot++) {
      ItemStack stackInSlot = ic.getStackInSlot(slot);
      
      if (isItemOK(stackInSlot, slot))
      {
        if (slot == 0)
        {

          if ((stackInSlot.getTagCompound() != null) && (stackInSlot.getTagCompound().hasKey("color"))) {
            baseColor = stackInSlot.getTagCompound().getString("color");
          }
        }
        
        if (slot == 3)
        {

          if ((stackInSlot.getTagCompound() != null) && (stackInSlot.getTagCompound().hasKey("color"))) {
            trimColor = stackInSlot.getTagCompound().getString("color");
          }
        }
        
        if (slot == 2)
        {
          if ((stackInSlot.getTagCompound() != null) && (stackInSlot.getTagCompound().hasKey("color")) && 
            (!stackInSlot.getTagCompound().getString("color").equals(baseColor))) { violatedRules = true;
          }
        }
        
        if (slot == 5)
        {
          if ((stackInSlot.getTagCompound() != null) && (stackInSlot.getTagCompound().hasKey("color")) && 
            (!stackInSlot.getTagCompound().getString("color").equals(trimColor))) violatedRules = true;
        }
      }
      else
      {
        violatedRules = true;
      }
    }
    

    if (!violatedRules) {
      ItemStack result = new ItemStack(ClothingCraft.Clothing.colorableShoes);
      int baseColorInt = ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(baseColor);
      int trimColorInt = ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(trimColor);
      ClothingCraftClothingColorableShoes.setBaseColor(result, baseColorInt);
      ClothingCraftClothingColorableShoes.setTrimColor(result, trimColorInt);
      this.output = result;
      return true;
    }
    
    return false;
  }
  
  public ItemStack getCraftingResult(InventoryCrafting ic)
  {
    return this.output.copy();
  }
  
  public int getRecipeSize()
  {
    return 10;
  }
  
  public ItemStack getRecipeOutput()
  {
    return this.output;
  }
  
  private boolean isItemOK(ItemStack stack, int slot) {
    if ((slot == 6) || (slot == 8))
    {
      return (stack != null) && (stack.getItem() == net.minecraft.init.Items.LEATHER); }
    if ((slot == 1) || (slot == 4) || (slot == 7))
    {
      return stack == null;
    }
    
    return (stack != null) && (stack.getItem() == kittyreverant.clothingcraft.ClothingCraft.Items.fabric);
  }

@Override
public ItemStack[] getRemainingItems(InventoryCrafting inv) {
	inv.clear();
	return new ItemStack[0];
}


}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\crafting\ClothingCraftRecipeCustomShoes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */