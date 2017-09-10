package kittyreverant.clothingcraft.common.crafting;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Items;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemShirt;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemSleeve;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorableShirt;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ClothingCraftRecipeColoredShirt implements IRecipe
{
  private ItemStack output;
  
  public boolean matches(InventoryCrafting ic, World world)
  {
    this.output = null;
    
    boolean violatedRules = false;
    
    boolean foundRowWithStuffYet = false;
    int lastRow = -1;
    int lastColumn = -1;
    
    ItemStack sleeveLeftStack = null;
    ItemStack sleeveRightStack = null;
    ItemStack shirtStack = null;
    
    for (int slot = 0; slot < ic.getSizeInventory(); slot++)
    {
      ItemStack stackInSlot = ic.getStackInSlot(slot);
      
      int row = 0;
      if ((slot >= 3) && (slot <= 5)) row = 1;
      if ((slot >= 6) && (slot <= 8)) { row = 2;
      }
      int column = slot % 3;
      
      if ((lastRow != -1) && (foundRowWithStuffYet) && (lastRow != row) && (stackInSlot != null))
      {
        violatedRules = true;
        

        break;
      }
      
      if (stackInSlot != null) {
        foundRowWithStuffYet = true;
        


        if (!isItemOKForColumn(stackInSlot, column)) {
          violatedRules = true;
          break; }
        if (stackInSlot != null)
        {

          if (column == 0) sleeveLeftStack = stackInSlot;
          if (column == 1) shirtStack = stackInSlot;
          if (column == 2) { sleeveRightStack = stackInSlot;
          }
        }
      }
      

      lastRow = row;
      lastColumn = column;
    }
    


    if (!violatedRules)
    {
      if ((sleeveLeftStack == null) || (shirtStack == null) || (sleeveRightStack == null)) { violatedRules = true;
      }
      if (ClothingCraftItemSleeve.getStackColor(sleeveLeftStack) != ClothingCraftItemSleeve.getStackColor(sleeveRightStack)) {
        violatedRules = true;
      }
    }
    


    if (!violatedRules)
    {
      int sleeveColor = ClothingCraftItemSleeve.getStackColor(sleeveLeftStack);
      int baseColor = ClothingCraftItemShirt.getBaseColor(shirtStack);
      int trimColor = ClothingCraftItemShirt.getTrimColor(shirtStack);
      int trimType = ClothingCraftItemShirt.getTrimType(shirtStack);
      int buttonColor = ClothingCraftItemShirt.getButtonColor(shirtStack);
      
      ItemStack result = new ItemStack(kittyreverant.clothingcraft.ClothingCraft.Clothing.colorableShirt);
      ClothingCraftClothingColorableShirt.setSleeveColor(result, sleeveColor);
      ClothingCraftClothingColorableShirt.setBaseColor(result, baseColor);
      ClothingCraftClothingColorableShirt.setTrimColor(result, trimColor);
      ClothingCraftClothingColorableShirt.setTrimType(result, trimType);
      ClothingCraftClothingColorableShirt.setButtonColor(result, buttonColor);
      
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
  
  private boolean isItemOKForColumn(ItemStack stack, int column) {
    if ((column == 0) || (column == 2)) return (stack != null) && (stack.getItem() == ClothingCraft.Items.sleeve);
    if (column == 1) { return (stack != null) && (stack.getItem() == ClothingCraft.Items.shirt);
    }
    System.err.println("Trying to check for invalid column " + column);
    return false;
  }

@Override
public ItemStack[] getRemainingItems(InventoryCrafting inv) {
	inv.clear();
	return new ItemStack[0];
}


}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\crafting\ClothingCraftRecipeColoredShirt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */