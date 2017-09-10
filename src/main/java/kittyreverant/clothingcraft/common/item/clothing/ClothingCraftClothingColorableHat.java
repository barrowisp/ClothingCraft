package kittyreverant.clothingcraft.common.item.clothing;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Clothing;
import kittyreverant.clothingcraft.common.ClothingCraftCommonProxy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.client.renderer.texture.IIconRegister;
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
//import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ClothingCraftClothingColorableHat extends ItemArmor
{
  private final String name = "itemClothingColorableHat";
  
  public ClothingCraftClothingColorableHat()
  {
	  
    super(ClothingCraft.Clothing.getClothingArmorMaterial(), 3, EntityEquipmentSlot.HEAD);
    //GameRegistry.register(this, new ResourceLocation("ClothingCraft" + "_"+name));
    setRegistryName(name);
    setUnlocalizedName(name);
    setCreativeTab(ClothingCraft.Clothing.tab);
    //setTextureName("clothingcraft:clothingCraft_itemColorableHat");
    //@SideOnly(Side.CLIENT)
    
  }
  
  public String getName()
  {
	  return name;
  }
  public void registerItemModel() {
	  ClothingCraft.proxy.registerItemRenderer(this, 0, name);
	}
  @SideOnly(Side.CLIENT)
  public boolean requiresMultipleRenderPasses()
  {
    return true;
  }
  
  public int getRenderPasses(int metadata)
  {
    return 2;
  }
  
  /*@SideOnly(Side.CLIENT)
  public IIcon getIconFromDamageForRenderPass(int metadata, int renderPass)
  {
    if (renderPass == 1) { return overlayIcon;
    }
    return baseIcon;
  }*/
  
  @SideOnly(Side.CLIENT)
  public int getColorFromItemStack(ItemStack stack, int renderPass)
  {
    if (renderPass == 1) { return getTrimColor(stack);
    }
    return getBaseColor(stack);
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
  {
    if ((stack != null) && (stack.getItem() == this)) {
      stack.setItemDamage(0);
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tab, List list)
  {
    int[] baseColorsToAdd = { 2039583, 6462399, 10466239 };
    int[] trimColorsToAdd = { 15263976, 14277081, 10724259 };
    
    for (int i = 0; i < baseColorsToAdd.length; i++) {
      ItemStack stack = new ItemStack(item);
      setBaseColor(stack, baseColorsToAdd[i]);
      setTrimColor(stack, trimColorsToAdd[i]);
      list.add(stack);
    }
  }
  

  /*@SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister register)
  {
    baseIcon = register.registerIcon(getIconString() + "_base");
    overlayIcon = register.registerIcon(getIconString() + "_overlay");
  }*/
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
  {
    list.add(net.minecraft.util.text.translation.I18n.translateToLocal("tooltip.customColoring"));
  }
  
  public static int getBaseColor(ItemStack stack) {
    return getOrCreateNBT(stack).getInteger("baseColor");
  }
  
  public static int getTrimColor(ItemStack stack) {
    return getOrCreateNBT(stack).getInteger("trimColor");
  }
  
  public static void setBaseColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("baseColor", color);
  }
  
  public static void setTrimColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("trimColor", color);
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
      ModelBiped model = ClothingCraft.proxy.getArmorModel(3);
      return ClothingCraft.proxy.fixModel(model, armorSlot, entityLiving);
    }
    
    return null;
  }
}
