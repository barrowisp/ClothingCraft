package kittyreverant.clothingcraft.common.item.clothing;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Clothing;
import kittyreverant.clothingcraft.common.ClothingCraftCommonProxy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ClothingCraftClothingColorablePants extends ItemArmor
{
	
	private final String name ="itemClothingColorablePants";
  public ClothingCraftClothingColorablePants()
  {
    super(ClothingCraft.Clothing.getClothingArmorMaterial(), 3, EntityEquipmentSlot.LEGS);
    //GameRegistry.register(this, new ResourceLocation(name));
    setRegistryName(name);
    setUnlocalizedName(name);
    setCreativeTab(ClothingCraft.Clothing.tab);
    //setTextureName("clothingcraft:clothingCraft_itemColorablePants");
  }
  public String getName()
  {
	  return name;
  }
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
  {
    if ((stack != null) && (stack.getItem() == this)) {
      stack.setItemDamage(0);
    }
  }
  public void registerItemModel() {
	  ClothingCraft.proxy.registerItemRenderer(this, 0, name);
	}
  @SideOnly(Side.CLIENT)
  public int getColorFromItemStack(ItemStack stack, int something)
  {
    return getPantsColor(stack);
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tab, List list)
  {
    int[] hexCodesToAdd = { 11375976, 3552107, 2368548, 5390884 };
    
    for (int hex : hexCodesToAdd) {
      ItemStack stack = new ItemStack(item);
      setPantsColor(stack, hex);
      list.add(stack);
    }
  }
  

  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
  {
    list.add(net.minecraft.util.text.translation.I18n.translateToLocal("tooltip.customColoring"));
  }
  
  public static int getPantsColor(ItemStack stack) {
    return getOrCreateNBT(stack).getInteger("baseColor");
  }
  
  public static void setPantsColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("baseColor", color);
  }
  
  private static NBTTagCompound getOrCreateNBT(ItemStack stack) {
	    if(stack.getTagCompound() == null)
	    	{
	    		stack.setTagCompound(new NBTTagCompound());
	    	
	    	}
	    		return stack.getTagCompound();

	  }
  

  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "clothingcraft:textures/clothing/clothing.png";
  }
  
  @Override
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
  {
    if (FMLCommonHandler.instance().getSide() != Side.CLIENT) { return null;
    }
    if ((itemStack != null) && (itemStack.getItem() == this)) {
      ModelBiped model = kittyreverant.clothingcraft.ClothingCraft.proxy.getArmorModel(2);
      return kittyreverant.clothingcraft.ClothingCraft.proxy.fixModel(model, armorSlot, entityLiving);
    }
    























    return null;
  }
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\item\clothing\ClothingCraftClothingColorablePants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */