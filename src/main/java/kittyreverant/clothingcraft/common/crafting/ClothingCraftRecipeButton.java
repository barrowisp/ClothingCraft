package kittyreverant.clothingcraft.common.crafting;

import java.util.ArrayList;
import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemHammer;
//import kittyreverant.clothingcraft.ClothingCraft.Items;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;




public class ClothingCraftRecipeButton
  implements IRecipe
{
  private ItemStack output;
  private static List<ButtonRecipe> buttonRecipes = new ArrayList();
  
  static { buttonRecipes.add(new ButtonRecipe(Items.DYE, 0, "black"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 11, "yellow"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 1, "red"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 2, "dark_green"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 10, "green"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 14, "orange"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 5, "violet"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 7, "light_gray"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 8, "gray"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 3, "brown"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 4, "blue"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 9, "pink"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 6, "sea_green"));
    buttonRecipes.add(new ButtonRecipe(Items.DYE, 12, "light_blue"));
    buttonRecipes.add(new ButtonTransformationRecipe("yellow", "red", "orange"));
    buttonRecipes.add(new ButtonTransformationRecipe("white", "black", "gray"));
    buttonRecipes.add(new ButtonTransformationRecipe("gray", "black", "dark_gray"));
    buttonRecipes.add(new ButtonTransformationRecipe("gray", "white", "light_gray"));
    buttonRecipes.add(new ButtonTransformationRecipe("white", "red", "pink"));
    buttonRecipes.add(new ButtonTransformationRecipe("pink", "gray", "dark_pink"));
    buttonRecipes.add(new ButtonTransformationRecipe("purple", "dark_gray", "royal_violet"));
    buttonRecipes.add(new ButtonTransformationRecipe("pink", "red", "crimson"));
    buttonRecipes.add(new ButtonTransformationRecipe("blue", "yellow", "turquoise"));
    buttonRecipes.add(new ButtonTransformationRecipe("blue", "dark_gray", "dark_blue"));
    buttonRecipes.add(new ButtonTransformationRecipe("turquoise", "green", "sea_green"));
    buttonRecipes.add(new ButtonTransformationRecipe("green", "white", "light_green"));
    buttonRecipes.add(new ButtonTransformationRecipe("gray", "black", "dark_gray"));
    buttonRecipes.add(new ButtonTransformationRecipe("brown", "light_gray", "beige"));
    buttonRecipes.add(new ButtonTransformationRecipe("beige", "light_gray", "dark_beige"));
    buttonRecipes.add(new ButtonTransformationRecipe("violet", "light_gray", "purple"));
    buttonRecipes.add(new ButtonTransformationRecipe("yellow", "white", "bright_yellow"));
    buttonRecipes.add(new ButtonTransformationRecipe("yellow", "orange", "mustard_yellow"));
  }
  

  public boolean matches(InventoryCrafting ic, World world)
  {
    this.output = null;
    
    int amountOfStacksFound = 0;
    

    ItemStack firstStack = null;
    ItemStack secondStack = null;
    ItemStack thirdStack = null;
    
    boolean violatedRules = false;
    for (int slot = 0; slot < ic.getSizeInventory(); slot++)
    {
      ItemStack stackInSlot = ic.getStackInSlot(slot);
      
      if (stackInSlot != null) {
        if (amountOfStacksFound < 3)
        {
          if (firstStack == null) {
            firstStack = stackInSlot;
          } else if (secondStack == null) {
            secondStack = stackInSlot;
          } else if (thirdStack == null) {
            thirdStack = stackInSlot;
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
      if ((firstStack != null) && (secondStack != null))
      {



        if (((firstStack == null) && (secondStack != null) && (thirdStack != null)) || ((firstStack != null) && (secondStack == null) && (thirdStack != null)) || ((firstStack != null) && (secondStack != null) && (thirdStack == null)))
        {


          ItemStack notNullOne = secondStack;
          ItemStack notNullTwo = thirdStack;
          

          if ((firstStack != null) && (secondStack == null) && (thirdStack != null))
          {
            notNullOne = firstStack;
            notNullTwo = thirdStack;
          }
          
          if ((firstStack != null) && (secondStack != null) && (thirdStack == null))
          {
            notNullOne = firstStack;
            notNullTwo = secondStack;
          }
          
          if ((notNullOne != null) && (notNullTwo != null))
          {


            Object[] findIronAndHammerResult = getItemStacksOfType(notNullOne, notNullTwo, Items.IRON_INGOT, ClothingCraft.Items.hammer);
            boolean hasHammerAndIron = ((Boolean)findIronAndHammerResult[0]).booleanValue();
            if (hasHammerAndIron)
            {
              ItemStack ironStack = (ItemStack)findIronAndHammerResult[1];
              ItemStack hammerStack = (ItemStack)findIronAndHammerResult[2];
              
              ItemStack result = new ItemStack(ClothingCraft.Items.button, 1);
              NBTTagCompound nbt = new NBTTagCompound();
              result.setTagCompound(nbt);
              //result.stackTagCompound = nbt;
              nbt.setString("color", "white");
              this.output = result;
              return true;
            }
          }
        }
        






        for (ButtonRecipe recipe : buttonRecipes)
        {
          Object[] getTransformerButtonHammerResults = getItemStacksOfType(firstStack, secondStack, thirdStack, ClothingCraft.Items.button, ClothingCraft.Items.hammer, null);
          boolean hasButtonHammerAndTransformer = ((Boolean)getTransformerButtonHammerResults[0]).booleanValue();
          ItemStack buttonStack = (ItemStack)getTransformerButtonHammerResults[1];
          ItemStack hammerStack = (ItemStack)getTransformerButtonHammerResults[2];
          ItemStack transformerStack = (ItemStack)getTransformerButtonHammerResults[3];
          
          if ((hasButtonHammerAndTransformer) && 
            (recipe.isItemStackValidTransformer(transformerStack, buttonStack)))
          {
            String produced = recipe.colorProduced;
            int quantity = (recipe instanceof ButtonTransformationRecipe) ? 2 : 1;
            ItemStack result = new ItemStack(ClothingCraft.Items.button, quantity);
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
  



  public static class ButtonRecipe
  {
    private Item requiredTransformer;
    


    private int transformerMetadata;
    


    public String colorProduced;
    

    protected boolean canOnlyDyeWhite;
    


    public ButtonRecipe(Item requiredTransformer, int transformerMetadata, String colorProduced)
    {
      this(requiredTransformer, transformerMetadata, colorProduced, true);
    }
    
    public ButtonRecipe(Item requiredTransformer, int transformerMetadata, String colorProduced, boolean canOnlyDyeWhite) {
      this.requiredTransformer = requiredTransformer;
      this.transformerMetadata = transformerMetadata;
      this.colorProduced = colorProduced;
      this.canOnlyDyeWhite = canOnlyDyeWhite;
    }
    
    public boolean isItemStackValidTransformer(ItemStack input, ItemStack button) {
      if (button.getItem() == ClothingCraft.Items.button) {
        String buttonsColor = button.getTagCompound() != null ? button.getTagCompound().getString("color") : "---";
        if ((!this.canOnlyDyeWhite) || ((this.canOnlyDyeWhite) && (buttonsColor.equals("white"))))
        {
          return (input != null) && (input.getItem() == this.requiredTransformer) && ((this.transformerMetadata == -1) || (input.getItemDamage() == this.transformerMetadata));
        }
      }
      
      return false;
    }
  }
  
  public static class ButtonTransformationRecipe extends ClothingCraftRecipeButton.ButtonRecipe
  {
    private String buttonTypeOne;
    private String buttonTypeTwo;
    
    public ButtonTransformationRecipe(String requiredButtonColor, String modifyingButtonColor, String colorProduced)
    {
      super(ClothingCraft.Items.button,-1, colorProduced);
      this.buttonTypeOne = requiredButtonColor;
      this.buttonTypeTwo = modifyingButtonColor;
    }
    
    public boolean isItemStackValidTransformer(ItemStack input, ItemStack button)
    {
      if ((input == null) || (button == null) || (input.getItem() != ClothingCraft.Items.button) || (button.getItem() != ClothingCraft.Items.button)) {
        return false;
      }
      

      NBTTagCompound inputNBT = input.getTagCompound();
      NBTTagCompound buttonNBT = button.getTagCompound();
      
      if ((inputNBT != null) && (buttonNBT != null) && 
        (inputNBT.hasKey("color")) && (buttonNBT.hasKey("color"))) {
        String inputColor = inputNBT.getString("color");
        String buttonColor = buttonNBT.getString("color");
        
        if (inputColor.equals(this.buttonTypeOne))
        {
          if (buttonColor.equals(this.buttonTypeTwo)) {
            return true;
          }
        }
        else if (inputColor.equals(this.buttonTypeTwo))
        {
          if (buttonColor.equals(this.buttonTypeOne)) {
            return true;
          }
        }
      }
      



      return false;
    }
  }
  





  private Object[] getItemStacksOfType(ItemStack one, ItemStack two, Item typeOne, Item typeTwo)
  {
    if ((one == null) || (two == null)) return new Object[] { Boolean.valueOf(false), null, null };
    if ((one.getItem() == typeOne) && (two.getItem() == typeTwo)) return new Object[] { Boolean.valueOf(true), one, two };
    if ((one.getItem() == typeTwo) && (two.getItem() == typeOne)) { return new Object[] { Boolean.valueOf(true), two, one };
    }
    return new Object[] { Boolean.valueOf(false), null, null };
  }
  







  private Object[] getItemStacksOfType(ItemStack one, ItemStack two, ItemStack three, Item typeOne, Item typeTwo, Item typeThree)
  {
    if ((one == null) || (two == null) || (three == null)) { return new Object[] { Boolean.valueOf(false), null, null, null };
    }
    








    if ((one.getItem() == typeOne) || (typeOne == null)) {
      if (((two.getItem() == typeTwo) || (typeTwo == null)) && (
        (three.getItem() == typeThree) || (typeThree == null))) {
        return new Object[] { Boolean.valueOf(true), one, two, three };
      }
      

      if (((two.getItem() == typeThree) || (typeThree == null)) && (
        (three.getItem() == typeTwo) || (typeTwo == null))) {
        return new Object[] { Boolean.valueOf(true), one, three, two };
      }
    }
    

    if ((one.getItem() == typeTwo) || (typeTwo == null)) {
      if (((two.getItem() == typeOne) || (typeOne == null)) && (
        (three.getItem() == typeThree) || (typeThree == null))) {
        return new Object[] { Boolean.valueOf(true), two, one, three };
      }
      

      if (((two.getItem() == typeThree) || (typeThree == null)) && (
        (three.getItem() == typeOne) || (typeOne == null))) {
        return new Object[] { Boolean.valueOf(true), two, three, one };
      }
    }
    

    if ((one.getItem() == typeThree) || (typeThree == null)) {
      if (((two.getItem() == typeOne) || (typeOne == null)) && (
        (three.getItem() == typeTwo) || (typeTwo == null))) {
        return new Object[] { Boolean.valueOf(true), three, one, two };
      }
      

      if (((two.getItem() == typeTwo) || (typeTwo == null)) && (
        (three.getItem() == typeOne) || (typeOne == null))) {
        return new Object[] { Boolean.valueOf(true), three, two, one };
      }
    }
    

    return new Object[] { Boolean.valueOf(false), null, null, null };
  }

@Override
public ItemStack[] getRemainingItems(InventoryCrafting inv) {
	for(int i = 0; i < inv.getSizeInventory(); ++i)
	{
		if(!(inv.getStackInSlot(i).getItem() instanceof ClothingCraftItemHammer))
		{
			inv.removeStackFromSlot(i);
		}
	}
	return new ItemStack[0];
}


}

