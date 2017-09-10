package kittyreverant.clothingcraft.common.item;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class ClothingCraftItemButton
  extends Item implements IItemColor
{
	private final String name = "itemButton";
  public ClothingCraftItemButton()
  {
    setUnlocalizedName(name);//Reference.MODID
    setRegistryName(name);
    //GameRegistry.register(this, new ResourceLocation(name));
    setCreativeTab(ClothingCraft.Items.tab);
    //setTextureName("clothingcraft:clothingCraft_itemButton");
    setMaxStackSize(16);
    //ItemColors ic = Minecraft.getMinecraft().getItemColors();
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
  {
    list.add(I18n.translateToLocal("tooltip.color." + getButtonColor(stack) + ".name"));
  }
  

  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tab, List list)
  {
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
  public int getColorFromItemStack(ItemStack stack, int renderPass)
  {
    return ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(getButtonColor(stack));
  }
  
  public static String getButtonColor(ItemStack stack) {
    return getOrCreateNBT(stack).getString("color");
  }
  
  public static void setButtonColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("color", color);
  }
  public void registerItemModel() {
	  ClothingCraft.proxy.registerItemRenderer(this, 0, name);
	}
  private static NBTTagCompound getOrCreateNBT(ItemStack stack) {
	    if(stack.getTagCompound() == null)
	    	{
	    		stack.setTagCompound(new NBTTagCompound());
	    	
	    	}
	    		return stack.getTagCompound();

	  }
  
  public String getName()
  {
	  return name;
  }

@Override
public int getColorFromItemstack(ItemStack stack, int tintIndex) {
	int i = stack.getMetadata();
	return 0;
}
}


