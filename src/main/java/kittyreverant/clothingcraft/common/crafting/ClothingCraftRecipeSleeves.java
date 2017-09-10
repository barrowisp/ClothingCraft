package kittyreverant.clothingcraft.common.crafting;

import java.util.ArrayList;
import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Items;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric.ColoringUtil;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;


public class ClothingCraftRecipeSleeves
  implements IRecipe
{
  private ItemStack output;
  
  public boolean matches(InventoryCrafting ic, World world)
  {
    this.output = null;
    
















    for (int recipe = 0; recipe <= 1; recipe++)
    {
      boolean violatedRules = false;
      List<String> fabricColors = new ArrayList();
      
      for (int slot = 0; slot < ic.getSizeInventory(); slot++) {
        ItemStack stackInSlot = ic.getStackInSlot(slot);
        
        if (isItemOK(stackInSlot, slot, recipe))
        {
          if ((stackInSlot != null) && (stackInSlot.getItem() == ClothingCraft.Items.fabric))
          {
            fabricColors.add(getFabricColor(stackInSlot));
          }
        }
        else {
          violatedRules = true;
        }
        
        if (violatedRules) {
          break;
        }
      }
      
      if (!violatedRules) { violatedRules = fabricColors.size() != 6;
      }
      if (!violatedRules)
      {

        String sleeveColor = (String)fabricColors.get(0);
        int sleeveColorInt = ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(sleeveColor);
        

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("color", sleeveColorInt);
        

        ItemStack sleeveStack = new ItemStack(ClothingCraft.Items.sleeve, 2);
        sleeveStack.setTagCompound(nbt);
        
        this.output = sleeveStack;
        return true;
      }
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
  
  private boolean isItemOK(ItemStack stack, int slot, int recipe) {
    if (recipe == 0) {
      if ((slot == 0) || (slot == 1) || (slot == 3) || (slot == 4) || (slot == 6) || (slot == 7)) {
        return (stack != null) && (stack.getItem() == ClothingCraft.Items.fabric);
      }
      
      if ((slot == 2) || (slot == 5) || (slot == 8)) {
        return stack == null;
      }
    }
    
    if (recipe == 1) {
      if ((slot == 1) || (slot == 2) || (slot == 4) || (slot == 5) || (slot == 7) || (slot == 8)) {
        return (stack != null) && (stack.getItem() == ClothingCraft.Items.fabric);
      }
      
      if ((slot == 0) || (slot == 3) || (slot == 6)) {
        return stack == null;
      }
    }
    
    return false;
  }
  
  private String getFabricColor(ItemStack fabric) {
    NBTTagCompound nbt = fabric.getTagCompound();// == null ? (fabric..getTagCompound() = new NBTTagCompound()) : fabric.stackTagCompound;
    return nbt.hasKey("color") ? nbt.getString("color") : "---";
  }
  
  private int getFabricColorInt(ItemStack fabric) {
    String color = getFabricColor(fabric);
    return ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(color);
  }
  
  private String getDebugString(ItemStack stack) {
    return stack != null ? stack.getItem().getClass().getSimpleName() : "empty";
  }



@Override
public ItemStack[] getRemainingItems(InventoryCrafting inv) {
	inv.clear();
	return new ItemStack[0];
}
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\crafting\ClothingCraftRecipeSleeves.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */