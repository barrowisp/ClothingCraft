package kittyreverant.clothingcraft.common.crafting;

import java.util.List;

import kittyreverant.clothingcraft.unused.ClothingCraftOldClothingSet;
import kittyreverant.clothingcraft.unused.ClothingCraftOldClothingSet.ClothingPiece;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;


public class ClothingCraftRecipePants
  implements IRecipe
{
  private ItemStack outputStack;
  
  public ClothingCraftRecipePants() { this.outputStack = null; }
  
  private static List<PantsRecipe> recipes = new java.util.ArrayList();
  
  public static void addRecipe(String[] fabricColors, ClothingCraftOldClothingSet.ClothingPiece output) {
    recipes.add(new PantsRecipe(fabricColors, output));
  }
  
  public boolean matches(InventoryCrafting ic, World world)
  {
    this.outputStack = null;
    
    for (PantsRecipe recipe : recipes)
    {
      boolean violatedRules = false;
      recipe.resetCounter();
      
      for (int slot = 0; slot < ic.getSizeInventory(); slot++)
      {
        ItemStack stackInSlot = ic.getStackInSlot(slot);
        
        if (!isItemOK(stackInSlot, slot, recipe)) {
          violatedRules = true;
          break;
        }
      }
      

      if (!violatedRules) {
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
    return 0;
  }
  
  public ItemStack getRecipeOutput()
  {
    return this.outputStack;
  }
  
  private boolean isItemOK(ItemStack stack, int slot, PantsRecipe recipe)
  {
    if ((slot == 4) || (slot == 7))
    {
      return stack == null;
    }
    


    if (stack != null)
    {
      net.minecraft.item.Item item = stack.getItem();
      

      if (item == kittyreverant.clothingcraft.ClothingCraft.Items.fabric)
      {

        String requiredColor = recipe.nextFabricColor();
        


        if (stack.getTagCompound() != null)
        {
          String fabricsColor = stack.getTagCompound().getString("baseColor");
          

          return fabricsColor.equals(requiredColor);
        }
      }
    }
    




    return false;
  }
  
  public static class PantsRecipe {
    public String[] fabricColors;
    public ClothingCraftOldClothingSet.ClothingPiece output;
    private int counter;
    
    public PantsRecipe(String[] fabricColors, ClothingCraftOldClothingSet.ClothingPiece output) {
      this.fabricColors = fabricColors;
      this.output = output;
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


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\crafting\ClothingCraftRecipePants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */