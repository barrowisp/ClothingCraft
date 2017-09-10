package kittyreverant.clothingcraft.common.item;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Clothing;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class ClothingCraftItemSleeve extends Item
{
	public final String name = "itemSleeve";
  public ClothingCraftItemSleeve()
  {
    setUnlocalizedName(name);
    //setTextureName("clothingcraft:clothingCraft_itemSleeve");
    //GameRegistry.register(this, new ResourceLocation(name));
    setRegistryName(name);
    setCreativeTab(ClothingCraft.Items.tab);
    setMaxStackSize(16);
  }
  
  public String getName()
  {
	  return name;
  }
  public void registerItemModel() {
	  ClothingCraft.proxy.registerItemRenderer(this, 0, name);
	}
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
  {
    list.add(I18n.translateToLocal("tooltip.customColoring"));
  }
  
  @SideOnly(Side.CLIENT)
  public int getColorFromItemStack(ItemStack stack, int renderPass)
  {
    return getStackColor(stack);
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tab, List list)
  {
    int[] colorsToAdd = { 13928239, 3112148, 4248623, 12562539 };
    
    for (int color : colorsToAdd) {
      ItemStack stack = new ItemStack(item);
      setStackColor(stack, color);
      list.add(stack);
    }
  }
  
  public static int getStackColor(ItemStack stack)
  {
    return getOrCreateNBT(stack).getInteger("color");
  }
  
  public static void setStackColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("color", color);
  }
  
  private static NBTTagCompound getOrCreateNBT(ItemStack stack) {
	  if(stack != null)
	  {
	    if(stack.getTagCompound() == null)
	    	{
	    		stack.setTagCompound(new NBTTagCompound());
	    	
	    	}
	    		return stack.getTagCompound();
	  }
	  return new NBTTagCompound();
	  }
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\item\ClothingCraftItemSleeve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */