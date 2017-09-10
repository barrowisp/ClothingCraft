package kittyreverant.clothingcraft.common.crafting;

import java.util.ArrayList;
import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.ConfigurationValues;
//import kittyreverant.clothingcraft.ClothingCraft.Items;
import kittyreverant.clothingcraft.util.ClothingCraftUtilWoolColors;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;



public class ClothingCraftRecipeFabric
  implements IRecipe
{
  private ItemStack output;
  private static List<FabricRecipe> fabricRecipes = new ArrayList();
  
  static { if (ClothingCraft.ConfigurationValues.enableClayFabricCrafting) fabricRecipes.add(new FabricRecipe(Items.CLAY_BALL, -1, "brown"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 0, "black"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 11, "yellow"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 1, "red"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 2, "dark_green"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 10, "green"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 14, "orange"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 5, "violet"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 7, "light_gray"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 8, "gray"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 3, "brown"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 4, "blue"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 9, "pink"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 6, "sea_green"));
    fabricRecipes.add(new FabricRecipe(Items.DYE, 12, "light_blue"));
    fabricRecipes.add(new FabricTransformationRecipe("yellow", "red", "orange"));
    fabricRecipes.add(new FabricTransformationRecipe("white", "black", "gray"));
    fabricRecipes.add(new FabricTransformationRecipe("gray", "black", "dark_gray"));
    fabricRecipes.add(new FabricTransformationRecipe("gray", "white", "light_gray"));
    fabricRecipes.add(new FabricTransformationRecipe("white", "red", "pink"));
    fabricRecipes.add(new FabricTransformationRecipe("pink", "gray", "dark_pink"));
    fabricRecipes.add(new FabricTransformationRecipe("purple", "dark_gray", "royal_violet"));
    fabricRecipes.add(new FabricTransformationRecipe("pink", "red", "crimson"));
    fabricRecipes.add(new FabricTransformationRecipe("blue", "yellow", "turquoise"));
    fabricRecipes.add(new FabricTransformationRecipe("blue", "dark_gray", "dark_blue"));
    fabricRecipes.add(new FabricTransformationRecipe("turquoise", "green", "sea_green"));
    fabricRecipes.add(new FabricTransformationRecipe("green", "white", "light_green"));
    fabricRecipes.add(new FabricTransformationRecipe("gray", "black", "dark_gray"));
    fabricRecipes.add(new FabricTransformationRecipe("brown", "light_gray", "beige"));
    fabricRecipes.add(new FabricTransformationRecipe("beige", "light_gray", "dark_beige"));
    fabricRecipes.add(new FabricTransformationRecipe("violet", "light_gray", "purple"));
    fabricRecipes.add(new FabricTransformationRecipe("yellow", "white", "bright_yellow"));
    fabricRecipes.add(new FabricTransformationRecipe("yellow", "orange", "mustard_yellow"));
  }
  
  public boolean matches(InventoryCrafting ic, World world)
  {
    this.output = null;
    
    if (ClothingCraftCrafter.doesCarpetMatch(ic, world)) { return false;
    }
    int amountOfStacksFound = 0;
    
    ItemStack fabricStack = null;
    ItemStack transformerStack = null;
    
    boolean violatedRules = false;
    for (int slot = 0; slot < ic.getSizeInventory(); slot++)
    {
      ItemStack stackInSlot = ic.getStackInSlot(slot);
      
      if (stackInSlot != null) {
        if (amountOfStacksFound < 2)
        {
          if (fabricStack == null) {
            fabricStack = stackInSlot;
          } else if (transformerStack == null) {
            transformerStack = stackInSlot;
          }
          
          amountOfStacksFound++;
        }
        else {
          violatedRules = true;
        }
      }
    }
    


    if (!violatedRules)
    {
      if ((fabricStack != null) && (transformerStack != null))
      {

        if ((fabricStack.getItem() == Item.getItemFromBlock(Blocks.WOOL)) && (transformerStack.getItem() == Item.getItemFromBlock(Blocks.WOOL)) && (fabricStack.getItemDamage() == 0) && (transformerStack.getItemDamage() == 0))
        {

          ItemStack result = new ItemStack(ClothingCraft.Items.fabric, 4);
          NBTTagCompound nbt = new NBTTagCompound();
          result.setTagCompound(nbt);
          //result.stackTagCompound = nbt;
          nbt.setString("color", "white");
          this.output = result;
          return true; }
        String firstWool;
        String secondWool;
        if ((fabricStack.getItem() == Item.getItemFromBlock(Blocks.WOOL)) && (transformerStack.getItem() == Item.getItemFromBlock(Blocks.WOOL)))
        {

          firstWool = ClothingCraftUtilWoolColors.getWoolColorForMetadata(fabricStack.getItemDamage());
          secondWool = ClothingCraftUtilWoolColors.getWoolColorForMetadata(transformerStack.getItemDamage());
          

          for (FabricRecipe recipe : fabricRecipes)
          {

            if ((recipe instanceof FabricTransformationRecipe))
            {
              FabricTransformationRecipe transformation = (FabricTransformationRecipe)recipe;
              
              boolean doesFabricTypesMatch = false;
              if (transformation.fabricTypeOne.equals(firstWool)) {
                if (transformation.fabricTypeTwo.equals(secondWool)) {
                  doesFabricTypesMatch = true;
                }
              } else if ((transformation.fabricTypeTwo.equals(firstWool)) && 
                (transformation.fabricTypeOne.equals(secondWool))) {
                doesFabricTypesMatch = true;
              }
              

              if (doesFabricTypesMatch) {
                ItemStack result = new ItemStack(ClothingCraft.Items.fabric, 4);
                NBTTagCompound nbt = new NBTTagCompound();
                result.setTagCompound(nbt);
                //result.stackTagCompound = nbt;
                nbt.setString("color", transformation.colorProduced);
                this.output = result;
                return true;

              }
              


            }
            else if (firstWool.equals(secondWool)) {
              ItemStack result = new ItemStack(ClothingCraft.Items.fabric, 4);
              NBTTagCompound nbt = new NBTTagCompound();
              result.setTagCompound(nbt);
              //result.stackTagCompound = nbt;
              nbt.setString("color", firstWool);
              this.output = result;
              return true;
            }
          }
        }
        





        for (FabricRecipe recipe : fabricRecipes)
        {
          ItemStack fabric = fabricStack.getItem() == ClothingCraft.Items.fabric ? fabricStack : transformerStack;
          ItemStack transformer = fabric == fabricStack ? transformerStack : fabricStack;
          
          if (recipe.isItemStackValidTransformer(transformer, fabric))
          {
            String produced = recipe.colorProduced;
            int quantity = (recipe instanceof FabricTransformationRecipe) ? 2 : 1;
            ItemStack result = new ItemStack(ClothingCraft.Items.fabric, quantity);
            NBTTagCompound nbt = new NBTTagCompound();
            result.setTagCompound(nbt);
            //result.stackTagCompound = nbt;
            nbt.setString("color", produced);
            this.output = result;
            return true;
          }
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
  



  public static class FabricRecipe
  {
    private Item requiredTransformer;
    


    private int transformerMetadata;
    


    public String colorProduced;
    

    protected boolean canOnlyDyeWhite;
    


    public FabricRecipe(Item requiredTransformer, int transformerMetadata, String colorProduced)
    {
      this(requiredTransformer, transformerMetadata, colorProduced, true);
    }
    
    public FabricRecipe(Item requiredTransformer, int transformerMetadata, String colorProduced, boolean canOnlyDyeWhite) {
      this.requiredTransformer = requiredTransformer;
      this.transformerMetadata = transformerMetadata;
      this.colorProduced = colorProduced;
      this.canOnlyDyeWhite = canOnlyDyeWhite;
    }
    
    public boolean isItemStackValidTransformer(ItemStack input, ItemStack fabric) {
      if (fabric.getItem() == ClothingCraft.Items.fabric) {
        String fabricsColor = fabric.getTagCompound() != null ? fabric.getTagCompound().getString("color") : "---";
        if ((!this.canOnlyDyeWhite) || ((this.canOnlyDyeWhite) && (fabricsColor.equals("white"))))
        {
          return (input != null) && (input.getItem() == this.requiredTransformer) && ((this.transformerMetadata == -1) || (input.getItemDamage() == this.transformerMetadata));
        }
      }
      
      return false;
    }
  }
  
  public static class FabricTransformationRecipe extends ClothingCraftRecipeFabric.FabricRecipe
  {
    private String fabricTypeOne;
    private String fabricTypeTwo;
    
    public FabricTransformationRecipe(String requiredFabricColor, String modifyingFabricColor, String colorProduced)
    {
      super(ClothingCraft.Items.fabric,-1, colorProduced);
      this.fabricTypeOne = requiredFabricColor;
      this.fabricTypeTwo = modifyingFabricColor;
    }
    
    public boolean isItemStackValidTransformer(ItemStack input, ItemStack fabric)
    {
      if ((input == null) || (fabric == null) || (input.getItem() != ClothingCraft.Items.fabric) || (fabric.getItem() != ClothingCraft.Items.fabric)) {
        return false;
      }
      

      NBTTagCompound inputNBT = input.getTagCompound();
      NBTTagCompound fabricNBT = fabric.getTagCompound();
      
      if ((inputNBT != null) && (fabricNBT != null) && 
        (inputNBT.hasKey("color")) && (fabricNBT.hasKey("color"))) {
        String inputColor = inputNBT.getString("color");
        String fabricColor = fabricNBT.getString("color");
        
        if (inputColor.equals(this.fabricTypeOne))
        {
          if (fabricColor.equals(this.fabricTypeTwo)) {
            return true;
          }
        }
        else if (inputColor.equals(this.fabricTypeTwo))
        {
          if (fabricColor.equals(this.fabricTypeOne)) {
            return true;
          }
        }
      }
      



      return false;
    }
  }
  

  private boolean oneHasDyeOneHasWool(ItemStack one, ItemStack two)
  {
    return (one != null) && (two != null) && (((one.getItem() == Items.DYE) && (two.getItem() == Item.getItemFromBlock(Blocks.WOOL))) || ((two.getItem() == Items.DYE) && (one.getItem() == Item.getItemFromBlock(Blocks.WOOL))));
  }
  

  private ItemStack getWoolStack(ItemStack one, ItemStack two)
  {
    if (oneHasDyeOneHasWool(one, two)) {
      if (one.getItem() == Item.getItemFromBlock(Blocks.WOOL)) return one;
      if (two.getItem() == Item.getItemFromBlock(Blocks.WOOL)) { return two;
      }
    }
    return null;
  }
  
  private ItemStack getDyeStack(ItemStack one, ItemStack two) {
    if (oneHasDyeOneHasWool(one, two)) {
      if (one.getItem() == Items.DYE) return one;
      if (two.getItem() == Items.DYE) { return two;
      }
    }
    return null;
  }



@Override
public ItemStack[] getRemainingItems(InventoryCrafting inv) {
	inv.clear();
	return new ItemStack[0];
}
}

