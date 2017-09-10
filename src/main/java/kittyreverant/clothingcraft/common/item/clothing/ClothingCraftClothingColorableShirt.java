package kittyreverant.clothingcraft.common.item.clothing;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Clothing;
import kittyreverant.clothingcraft.common.ClothingCraftCommonProxy;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemShirtLayerPicker;
import net.minecraft.client.model.ModelBiped;
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
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;



public class ClothingCraftClothingColorableShirt
  extends ItemArmor
{
  private final String name = "itemClothingColorableShirt";
  
  public ClothingCraftClothingColorableShirt()
  {
    super(ClothingCraft.Clothing.getClothingArmorMaterial(), 3, EntityEquipmentSlot.CHEST);
    //GameRegistry.register(this, new ResourceLocation(name));
    setUnlocalizedName(name);
    setCreativeTab(ClothingCraft.Clothing.tab);
    setRegistryName(name);
    //setTextureName("clothingcraft:clothingCraft_itemColorableShirt");
    
    //setMaxDamage(0);
    setHasSubtypes(true);
    if (!ClothingCraft.proxy.isDedicatedServer()) {
        this.addPropertyOverride(new ResourceLocation("shirttype"), new ClothingCraftItemShirtLayerPicker());
      }
  }
  public void registerItemModel() {
	  ClothingCraft.proxy.registerItemRenderer(this, 0, name);
	}
  public String getName()
  {
	  return name;
  }
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
  {
    /*if ((stack != null) && (stack.getItem() == this) && (stack.getItemDamage() != stack.getMaxDamage()))
    {
      //stack.setItemDamage(getTrimType(stack));
    }*/
  }
  

  public void onUpdate(ItemStack stack, World world, Entity entity, int something, boolean somethingElse)
  {
    //stack.setItemDamage(getTrimType(stack));
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
  {
    list.add(I18n.translateToLocal("tooltip.customColoring"));
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tab, List list)
  {
    int[] baseColorsToAdd = { 7770319, 5753196, 0 };
    int[] trimColorsToAdd = { 3300024, 2794305, 7546143 };
    int[] trimTypesToAdd = { 0, 1, 2 };
    int[] sleeveColorsToAdd = { 14803686, 11976631, 0 };
    int[] buttonColorsToAdd = { 5395026, 16250871, 13214522 };
    
    for (int i = 0; i < baseColorsToAdd.length; i++) {
      ItemStack stack = new ItemStack(item);
      setBaseColor(stack, baseColorsToAdd[i]);
      setTrimColor(stack, trimColorsToAdd[i]);
      setTrimType(stack, trimTypesToAdd[i]);
      setSleeveColor(stack, sleeveColorsToAdd[i]);
      setButtonColor(stack, buttonColorsToAdd[i]);
      list.add(stack);
    }
  }
  
  public static int getBaseColor(ItemStack stack)
  {
    return getOrCreateNBT(stack).getInteger("baseColor");
  }
  
  public static int getTrimColor(ItemStack stack) {
    return getOrCreateNBT(stack).getInteger("trimColor");
  }
  
  public static int getTrimType(ItemStack stack) {
    return getOrCreateNBT(stack).getInteger("trimType");
  }
  
  public static int getSleeveColor(ItemStack stack) {
    NBTTagCompound nbt = getOrCreateNBT(stack);
    
    if (!nbt.hasKey("sleeveColor")) nbt.setInteger("sleeveColor", nbt.getInteger("baseColor"));
    return nbt.getInteger("sleeveColor");
  }
  
  public static int getButtonColor(ItemStack stack) {
    return getOrCreateNBT(stack).getInteger("buttonColor");
  }
  
  public static void setBaseColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("baseColor", color);
  }
  
  public static void setTrimColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("trimColor", color);
  }
  
  public static void setTrimType(ItemStack stack, int type) {
    getOrCreateNBT(stack).setInteger("trimType", type);
    //stack.setItemDamage(getTrimType(stack));
  }
  
  public static void setSleeveColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("sleeveColor", color);
  }
  
  public static void setButtonColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("buttonColor", color);
  }
  
  private static NBTTagCompound getOrCreateNBT(ItemStack stack) {
    NBTTagCompound nbt = stack.getTagCompound();
    
    if (nbt == null) {
      nbt = new NBTTagCompound();
      stack.setTagCompound(nbt);
    }
    
    return nbt;
  }
  

  @SideOnly(Side.CLIENT)
  public int getColorFromItemStack(ItemStack stack, int renderPass)
  {
    if (renderPass == 1) {
      return getTrimColor(stack);
    }
    
    if (renderPass == 2) {
      return getSleeveColor(stack);
    }
    
    if (renderPass == 3) {
      return getButtonColor(stack);
    }
    
    return getBaseColor(stack);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean requiresMultipleRenderPasses()
  {
    return true;
  }
  
  public int getRenderPasses(int metadata)
  {
    return 4;
  }
  
  /*@SideOnly(Side.CLIENT)
  public IIcon getIconFromDamageForRenderPass(int metadata, int renderPass)
  {
    if (renderPass == 1) {
      return getStackIcon(metadata);
    }
    
    if (renderPass == 2) {
      return overlaySleevesIcon;
    }
    
    if (renderPass == 3) {
      return overlayButtonsIcon;
    }
    
    return baseIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister register)
  {
    baseIcon = register.registerIcon(getIconString() + "_base");
    overlayDotsIcon = register.registerIcon(getIconString() + "_overlay_dots");
    overlayStripesIcon = register.registerIcon(getIconString() + "_overlay_stripes");
    overlaySleevesIcon = register.registerIcon(getIconString() + "_overlay_sleeves");
    overlayButtonsIcon = register.registerIcon(getIconString() + "_overlay_buttons");
  }
  
  @SideOnly(Side.CLIENT)
  private IIcon getStackIcon(int metadata)
  {
    return metadata == 0 ? overlayDotsIcon : overlayStripesIcon;
  }*/
  







  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "clothingcraft:textures/clothing/clothing_1.png";
  }
  
  @Override
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
  {
    if (FMLCommonHandler.instance().getSide() != Side.CLIENT) { return null;
    }
    if ((itemStack != null) && (itemStack.getItem() == this)) {
      ModelBiped model = ClothingCraft.proxy.getArmorModel(2);
      return ClothingCraft.proxy.fixModel(model, armorSlot, entityLiving);
    }
    

    return null;
  }
}
