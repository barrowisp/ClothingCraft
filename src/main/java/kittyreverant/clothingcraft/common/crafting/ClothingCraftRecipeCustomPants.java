package kittyreverant.clothingcraft.common.crafting;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Clothing;
import kittyreverant.clothingcraft.ClothingCraft.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ClothingCraftRecipeCustomPants implements IRecipe
{
  private ItemStack output;
  
  public boolean matches(InventoryCrafting ic, World world)
  {
    this.output = null;
    
    boolean violatedRules = false;
    

    String fabricColor = "";
    
    for (int slot = 0; slot < ic.getSizeInventory(); slot++) {
      ItemStack stackInSlot = ic.getStackInSlot(slot);
      
      if ((isItemOK(stackInSlot, slot)) && (stackInSlot != null))
      {

        if (slot == 0) {
          if ((stackInSlot.getTagCompound() != null) && (stackInSlot.getTagCompound().hasKey("color"))) {
            fabricColor = stackInSlot.getTagCompound().getString("color");
          }
        } else {
          String thisFabricColor = "---";
          if ((stackInSlot.getTagCompound() != null) && (stackInSlot.getTagCompound().hasKey("color"))) {
            thisFabricColor = stackInSlot.getTagCompound().getString("color");
          }
          
          if (!thisFabricColor.equals(fabricColor)) violatedRules = true;
        }
      }
      else if (!isItemOK(stackInSlot, slot))
      {
        violatedRules = true;
      }
    }
    

    if (!violatedRules) {
      ItemStack result = new ItemStack(ClothingCraft.Clothing.colorablePants);
      int color = kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(fabricColor);
      kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorablePants.setPantsColor(result, color);
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
    if ((slot == 4) || (slot == 7)) {
      return stack == null;
    }
    return (stack != null) && (stack.getItem() == ClothingCraft.Items.fabric);
  }



@Override
public ItemStack[] getRemainingItems(InventoryCrafting inv) {
	inv.clear();
	return new ItemStack[0];
}
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\crafting\ClothingCraftRecipeCustomPants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */