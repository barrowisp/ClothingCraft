package kittyreverant.clothingcraft.common.item;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ClothingCraftItemFabric extends Item
{
	private final String name = "itemFabric";
  public ClothingCraftItemFabric()
  {
    //setUnlocalizedName("clothingCraft_" + name);
	  setUnlocalizedName(name);
    //GameRegistry.register(this, new ResourceLocation(name));
	  setRegistryName(name);
    setCreativeTab(ClothingCraft.Items.tab);
    //setTextureName("clothingcraft:clothingCraft_itemFabric");
  }
  public String getName()
  {
  	return name;
  }

  public void onUpdate(ItemStack stack, World world, Entity holder, int p_77663_4_, boolean p_77663_5_)
  {
    getStackColor(stack);
  }
  
  @SideOnly(Side.CLIENT)
  public int getColorFromItemStack(ItemStack stack, int something)
  {
    return ColoringUtil.getColorForTypeName(getStackColor(stack));
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tab, List list)
  {
    /*for (int i = 0; i < ColoringUtil.colors.size(); i++) {
      ItemStack add = new ItemStack(item, 1, i);
      setStackColor(add, ((ClothingCraftItemFabric.ColoringUtil.FabricColor)ColoringUtil.colors.get(i)).name);
      list.add(add);
    }*/
	  for (int i = 0; i < ClothingCraftItemFabric.ColoringUtil.colors.size(); i++) {
	      String color = ((ClothingCraftItemFabric.ColoringUtil.FabricColor)ClothingCraftItemFabric.ColoringUtil.colors.get(i)).name;
	      
	      ItemStack stack = new ItemStack(item);
	      NBTTagCompound nbt = new NBTTagCompound();
	      stack.setTagCompound(nbt);
	      //stack.stackTagCompound = nbt;
	      nbt.setString("color", color);
	      list.add(stack);
	    }
  }
  

  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
  {
    String type = getStackColor(stack);
    String key = "tooltip.color." + type + ".name";
    String localize = I18n.translateToLocal(key);
    
    list.add(localize);
  }
  
  private String getStackColor(ItemStack stack)
  {
    boolean hadNBT = stack.getTagCompound() != null;
    NBTTagCompound nbt = stack.getTagCompound();
    		
    		//stack.getTagCompound() == null ? (stack.stackTagCompound = new NBTTagCompound()) : stack.stackTagCompound;
    return hadNBT ? nbt.getString("color") : "unknown";
  }
  public void registerItemModel() {
	  ClothingCraft.proxy.registerItemRenderer(this, 0, name);
	}
  private void setStackColor(ItemStack stack, String color) {
	  
    NBTTagCompound nbt = getOrCreateNBT(stack);//stack.stackTagCompound == null ? (stack.stackTagCompound = new NBTTagCompound()) : stack.stackTagCompound;
    nbt.setString("color", color);
  }
  
  private static NBTTagCompound getOrCreateNBT(ItemStack stack) {
	    if(stack.getTagCompound() == null)
	    	{
	    		stack.setTagCompound(new NBTTagCompound());
	    	
	    	}
	    		return stack.getTagCompound();

	  }







  public static class ColoringUtil
  {
    public static final List<FabricColor> colors = new ArrayList();
    static { addColor("dark_green", 2458919);
      addColor("green", 6276930);
      addColor("light_green", 8175485);
      addColor("sea_green", 4835706);
      
      addColor("blue", 5743577);
      addColor("light_blue", 9356008);
      addColor("dark_blue", 3552107);
      addColor("turquoise", 3393502);
      
      addColor("violet", 7812062);
      addColor("purple", 6104243);
      addColor("royal_violet", 9183170);
      
      addColor("pink", 14590161);
      addColor("dark_pink", 14237142);
      addColor("crimson", 14237108);
      
      addColor("beige", 13218445);
      addColor("dark_beige", 11375976);
      addColor("brown", 5390884);
      
      addColor("yellow", 13946977);
      addColor("bright_yellow", 14933006);
      addColor("mustard_yellow", 16109644);
      addColor("orange", 13733409);
      addColor("red", 10364184);
      
      addColor("white", 16119285);
      addColor("light_gray", 11053224);
      addColor("dark_gray", 5066061);
      addColor("black", 2368548);
    }
    
    private static void addColor(String name, int hex) {
      FabricColor color = new FabricColor();
      color.name = name;
      color.color = hex;
      colors.add(color);
    }
    
    public static int getIDForTypeName(String type) {
      for (int i = 0; i < colors.size(); i++) {
        if (((FabricColor)colors.get(i)).name.equals(type)) { return i;
        }
      }
      


      return -1;
    }
    
    public static int getColorForTypeID(int id) {
      return id != -1 ? ((FabricColor)colors.get(id)).color : -1;
    }
    
    public static int getColorForTypeName(String type) {
      return getColorForTypeID(getIDForTypeName(type));
    }
    
    public static String getTypeForID(int id) {
      return colors.size() > id ? ((FabricColor)colors.get(id)).name : "unknown";
    }
    
    public static class FabricColor
    {
      public String name;
      public int color;
    }
    
   
  }
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\item\ClothingCraftItemFabric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */