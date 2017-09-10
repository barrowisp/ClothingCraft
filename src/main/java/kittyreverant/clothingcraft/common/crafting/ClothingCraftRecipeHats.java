package kittyreverant.clothingcraft.common.crafting;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Items;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric.ColoringUtil;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorableHat;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ClothingCraftRecipeHats implements IRecipe
{
  private ItemStack output;
  
  public boolean matches(InventoryCrafting ic, World world)
  {
    this.output = null;
    
    boolean violatedRules = false;
    
    String attemptedBaseColor = null;
    String attemptedTrimColor = null;
    
    for (int slot = 0; slot < ic.getSizeInventory(); slot++)
    {
      ItemStack stackInSlot = ic.getStackInSlot(slot);
      
      if (shouldItemGoInSlot(stackInSlot, slot))
      {
        if (shouldBaseFabricGoInSlot(slot))
        {

          String fabricColor = getFabricColor(stackInSlot);
          
          if (attemptedBaseColor == null)
          {

            attemptedBaseColor = fabricColor;





          }
          else if (!attemptedBaseColor.equals(fabricColor)) { violatedRules = true;
          }
          
        }
        else if (shouldTrimFabricGoInSlot(slot))
        {

          String fabricColor = getFabricColor(stackInSlot);
          
          if (attemptedTrimColor == null)
          {

            attemptedTrimColor = fabricColor;




          }
          else if (!attemptedTrimColor.equals(fabricColor)) { violatedRules = true;
          }
          
        }
      }
      else {
        violatedRules = true;
      }
      

      if (violatedRules) {
        break;
      }
    }
    if (!violatedRules)
    {


      ItemStack result = new ItemStack(kittyreverant.clothingcraft.ClothingCraft.Clothing.colorableHat);
      
      int baseColor = ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(attemptedBaseColor);
      int trimColor = ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(attemptedTrimColor);
      
      ClothingCraftClothingColorableHat.setBaseColor(result, baseColor);
      ClothingCraftClothingColorableHat.setTrimColor(result, trimColor);
      
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
    return 0;
  }
  
  public ItemStack getRecipeOutput()
  {
    return this.output;
  }
  
  private boolean shouldItemGoInSlot(ItemStack stack, int slot) {
    switch (slot) {
    case 0: 
    case 2: 
    case 3: 
    case 5: 
      return stack == null;
    
    case 1: 
    case 4: 
    case 6: 
    case 7: 
    case 8: 
      return (stack != null) && (stack.getItem() == ClothingCraft.Items.fabric);
    }
    
    return false;
  }
  
  private boolean shouldBaseFabricGoInSlot(int slot) {
    return (slot == 1) || (slot == 6) || (slot == 7) || (slot == 8);
  }
  
  private boolean shouldTrimFabricGoInSlot(int slot) {
    return slot == 4;
  }
  
  private String getFabricColor(ItemStack stack) {
    if (stack == null) return "---ns";
    if (stack.getTagCompound() == null) return "---nt";
    if (!stack.getTagCompound().hasKey("color")) return "---nc";
    if (stack.getItem() != ClothingCraft.Items.fabric) { return "---ni";
    }
    return stack.getTagCompound().getString("color");
  }
  
  private int getFabricColorInt(ItemStack stack) {
    return ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(getFabricColor(stack));
  }
  
  private String getDebugString(ItemStack stack) {
    return stack == null ? "empty" : stack.getItem().getClass().getSimpleName();
  }



@Override
public ItemStack[] getRemainingItems(InventoryCrafting inv) {
	inv.clear();
	return new ItemStack[0];
}
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\crafting\ClothingCraftRecipeHats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */