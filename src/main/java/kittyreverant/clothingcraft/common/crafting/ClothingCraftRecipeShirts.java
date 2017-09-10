package kittyreverant.clothingcraft.common.crafting;

import java.util.ArrayList;
import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Items;
import kittyreverant.clothingcraft.unused.ClothingCraftOldClothingSet;
//import kittyreverant.clothingcraft.unused.ClothingCraftOldClothingSet.ClothingPiece;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@Deprecated
public class ClothingCraftRecipeShirts
  implements IRecipe
{
  private ItemStack outputStack;
  private static final List<ShirtRecipe> shirtRecipes = new ArrayList();
  
  
  
  public boolean matches(InventoryCrafting ic, World world)
  {
    this.outputStack = null;
    
    for (ShirtRecipe recipe : shirtRecipes) {
      boolean violatedRules = false;
      recipe.resetCounter();
      
      for (int i = 0; i < ic.getSizeInventory(); i++)
      {
        ItemStack stackInSlot = ic.getStackInSlot(i);
        
        if (!isItemOKForRecipe(stackInSlot, i, recipe)) {
          violatedRules = true;
          break;
        }
      }
      

      if (!violatedRules)
      {
        this.outputStack = new ItemStack(recipe.output);
        return true;
      }
    }
    

    return false;
  }
  
  public ItemStack getCraftingResult(InventoryCrafting ic)
  {
    return this.outputStack.copy();
  }
  
  public int getRecipeSize()
  {
    return 10;
  }
  
  public ItemStack getRecipeOutput()
  {
    return this.outputStack;
  }
  
  private boolean isItemOKForRecipe(ItemStack stack, int slot, ShirtRecipe recipe) {
    if (stack != null) {
      Item item = stack.getItem();
      int metadata = stack.getItemDamage();
      
      if ((slot == 4) || (slot == 7))
      {
        if (item == ClothingCraft.Items.button)
        {

          return recipe.buttonType == metadata;
        }
        


        return false;
      }
      



      if (item == ClothingCraft.Items.fabric)
      {
        if (stack.getTagCompound() != null)
        {
          String fabricColor = stack.getTagCompound().getString("color");
          

          String correctFabricColor = recipe.nextFabricColor();
          

          return fabricColor.equals(correctFabricColor);
        }
        


        return true;
      }
      


      return false;
    }
    














    return (stack == null) && (slot == 1);
  }
  
  public static class ShirtRecipe {
    public int buttonType;
    public String[] fabricColors;
    public ClothingCraftOldClothingSet.ClothingPiece output;
    private int counter;
    
    public ShirtRecipe(int buttonType, String[] fabricColors, ClothingCraftOldClothingSet.ClothingPiece output) {
      this.buttonType = buttonType;
      this.fabricColors = fabricColors;
      this.output = output;
      resetCounter();
    }
    
    public void resetCounter() {
      this.counter = 0;
    }
    
    public String nextFabricColor() {
      if (this.counter < this.fabricColors.length) {
        String color = this.fabricColors[this.counter];
        this.counter += 1;
        return color;
      }
      throw new RuntimeException("There is no next fabric color!");
    }
  }



@Override
public ItemStack[] getRemainingItems(InventoryCrafting inv) {
	inv.clear();
	return new ItemStack[0];
}
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\crafting\ClothingCraftRecipeShirts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */