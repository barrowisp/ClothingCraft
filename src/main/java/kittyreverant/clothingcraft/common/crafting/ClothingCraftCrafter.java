package kittyreverant.clothingcraft.common.crafting;

import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class ClothingCraftCrafter
{
  private static ShapedRecipes[] carpetRecipes;
  
  public static void addRecipes()
  {
    ItemStack hammerStack = new ItemStack(kittyreverant.clothingcraft.ClothingCraft.Items.hammer);
    ItemStack cobbleStack = new ItemStack(Blocks.COBBLESTONE);
    ItemStack stickStack = new ItemStack(Items.STICK);
    //IRecipe test;
    GameRegistry.addShapedRecipe(hammerStack, new Object[] { "ccc", "ccc", " s ", Character.valueOf('c'), cobbleStack, Character.valueOf('s'), stickStack });
    //GameRegistry.addRecipe(hammerStack, );
    


    addSpecialRecipe("buttons", new ClothingCraftRecipeButton());
    
    addSpecialRecipe("hats", new ClothingCraftRecipeHats());
    addSpecialRecipe("shirtbase", new ClothingCraftRecipeShirtBase());
    addSpecialRecipe("sleeves", new ClothingCraftRecipeSleeves());
    addSpecialRecipe("coloredshirts", new ClothingCraftRecipeColoredShirt());
    addSpecialRecipe("custompants", new ClothingCraftRecipeCustomPants());
    addSpecialRecipe("customshoes", new ClothingCraftRecipeCustomShoes());
    addSpecialRecipe("fabric", new ClothingCraftRecipeFabric());
    
    carpetRecipes = new ShapedRecipes[16];
    //CraftingManager manager = ;
    for (int i = 0; i < 16; i++) {
     GameRegistry.addShapedRecipe(new ItemStack(Blocks.CARPET, 3, i), new Object[] { "##", Character.valueOf('#'), new ItemStack(Blocks.WOOL, 1, i) });
    }
  }
  
  private static void addSpecialRecipe(String name, IRecipe recipe)
  {
    RecipeSorter.register("clothingcraft:" + name, recipe.getClass(), RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
    //ForgeRegistries.RECIPES.register(recipe);
    GameRegistry.addRecipe(recipe);
  }
  
  public static boolean doesCarpetMatch(InventoryCrafting ic, World world) {
    for (ShapedRecipes recipe : carpetRecipes) {
    	if(recipe != null)
    	{
      if (recipe.matches(ic, world)) { return true;
      }
    	}
    }
    return false;
  }
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\crafting\ClothingCraftCrafter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */