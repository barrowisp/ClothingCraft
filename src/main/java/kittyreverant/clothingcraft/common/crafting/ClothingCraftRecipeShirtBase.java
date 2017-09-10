package kittyreverant.clothingcraft.common.crafting;

import java.util.ArrayList;
import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Items;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemButton;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric.ColoringUtil;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ClothingCraftRecipeShirtBase
  implements IRecipe
{
  private ItemStack output;
  
  public boolean matches(InventoryCrafting ic, World world)
  {
    this.output = null;
    
    boolean violatedRules = false;
    



    for (int recipeType = 0; recipeType <= 1; recipeType++)
    {
      NBTTagCompound shirtNBT = new NBTTagCompound();
      


      List<String> fabricColors = new ArrayList();
      
      String baseColor = "";
      String trimColor = "";
      

      String buttonColorUsed = "";
      
      String buttonColorAttempted = "";
      
      for (int slot = 0; slot < ic.getSizeInventory(); slot++) {
        ItemStack stackInSlot = ic.getStackInSlot(slot);
        
        if (isItemOK(stackInSlot, slot)) {
          if ((shouldBaseFabricGoInSlot(slot, recipeType)) || (shouldTrimFabricGoInSlot(slot, recipeType)))
          {
            if ((stackInSlot != null) && (stackInSlot.getTagCompound() != null) && (stackInSlot.getTagCompound().hasKey("color"))) {
              fabricColors.add(stackInSlot.getTagCompound().getString("color"));
              
              if (shouldBaseFabricGoInSlot(slot, recipeType)) {
                baseColor = stackInSlot.getTagCompound().getString("color");
              }
              else {
                trimColor = stackInSlot.getTagCompound().getString("color");
              }
            }
          }
          

          if (slot == 4)
          {
            buttonColorUsed = ClothingCraftItemButton.getButtonColor(stackInSlot);
          }
          
          if (slot == 7)
          {
            buttonColorAttempted = ClothingCraftItemButton.getButtonColor(stackInSlot);
          }
        }
        else {
          violatedRules = true;
        }
      }
      

      if (!violatedRules) {
        boolean areConsistent = true;
        for (int i = 0; i < fabricColors.size(); i++) {
          String color = (String)fabricColors.get(i);
          if (shouldBaseFabricGoInFabricIndex(i, recipeType))
          {
            if (!color.equals(baseColor)) areConsistent = false;
          }
          else if (shouldTrimFabricGoInFabricIndex(i, recipeType))
          {
            if (!color.equals(trimColor)) { areConsistent = false;
            }
          }
        }
        
        if (!buttonColorUsed.equals(buttonColorAttempted)) { areConsistent = false;
        }
        
        if (areConsistent)
        {




          int base = ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(baseColor);
          int trim = ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(trimColor);
          int button = ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(buttonColorUsed);
          
          shirtNBT.setInteger("baseColor", base);
         
          shirtNBT.setInteger("trimColor", trim);
          
          shirtNBT.setInteger("trimType", recipeType);
          shirtNBT.setInteger("buttonColor", button);
          

          this.output = new ItemStack(ClothingCraft.Items.shirt);
          this.output.setTagCompound(shirtNBT);
          //this.output.setItemDamage(recipeType);
          return true;
        }
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
  
  private boolean shouldBaseFabricGoInFabricIndex(int index, int recipe) {
    int slot = 0;
    
    if (index == 0) {
      slot = 0;
    } else if (index == 1) {
      slot = 2;
    } else if (index == 2) {
      slot = 3;
    } else if (index == 3) {
      slot = 5;
    } else if (index == 4) {
      slot = 6;
    } else if (index == 5) {
      slot = 8;
    }
    
    return shouldBaseFabricGoInSlot(slot, recipe);
  }
  
  private boolean shouldTrimFabricGoInFabricIndex(int index, int recipe) {
    int slot = 0;
    
    if (index == 0) {
      slot = 0;
    } else if (index == 1) {
      slot = 2;
    } else if (index == 2) {
      slot = 3;
    } else if (index == 3) {
      slot = 5;
    } else if (index == 4) {
      slot = 6;
    } else if (index == 5) {
      slot = 8;
    }
    
    return shouldTrimFabricGoInSlot(slot, recipe);
  }
  
  private boolean shouldBaseFabricGoInSlot(int slot, int recipe) {
   
    
    if (recipe == 0) {
    	return (slot == 3) || (slot == 5) || (slot == 6) || (slot == 8);
    }
    if(recipe == 1)
    {
    	return (slot == 0) || (slot == 3) || (slot == 6);
    }
    
    return false;
  }
  
  private boolean shouldTrimFabricGoInSlot(int slot, int recipe) {
    if (recipe == 0) {
      return (slot == 0) || (slot == 2);
    }
    
    if (recipe == 1) {
      return (slot == 2) || (slot == 5) || (slot == 8);
    }
    
    return false;
  }
  
  private boolean isItemOK(ItemStack stack, int slot) {
    if ((slot == 4) || (slot == 7))
    {
      return (stack != null) && (stack.getItem() == ClothingCraft.Items.button); }
    if (slot == 1)
    {
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


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\crafting\ClothingCraftRecipeShirtBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */