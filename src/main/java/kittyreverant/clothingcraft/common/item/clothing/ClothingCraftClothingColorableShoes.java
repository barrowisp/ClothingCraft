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

public class ClothingCraftClothingColorableShoes extends ItemArmor
{
	private final String name = "itemClothingColorableShoes";
  public ClothingCraftClothingColorableShoes()
  {
    super(ClothingCraft.Clothing.getClothingArmorMaterial(), 3, EntityEquipmentSlot.FEET);
    setUnlocalizedName(name);
    //GameRegistry.register(this, new ResourceLocation(name));
    setCreativeTab(ClothingCraft.Clothing.tab);
    setRegistryName(name);
    //setTextureName("clothingcraft:clothingCraft_itemColorableShoes");
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
  public boolean requiresMultipleRenderPasses()
  {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public int getColorFromItemStack(ItemStack stack, int renderPass)
  {
    if (renderPass > 0) {
      return getTrimColor(stack);
    }
    
    return getBaseColor(stack);
  }
  
  /*@SideOnly(Side.CLIENT)
  public IIcon getIconFromDamageForRenderPass(int metadata, int renderPass)
  {
    return renderPass == 1 ? this.overlayIcon : this.baseIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister register)
  {
    this.baseIcon = register.registerIcon(getIconString() + "_base");
    this.overlayIcon = register.registerIcon(getIconString() + "_overlay");
  }*/
  



  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
  {
    list.add(net.minecraft.util.text.translation.I18n.translateToLocal("tooltip.customColoring"));
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs tab, List list)
  {
    int[] baseColorsToAdd = { 1839377, 6176793, 3523764 };
    int[] trimColorsToAdd = { 9514284, 3549206, 13295324 };
    
    for (int i = 0; i < baseColorsToAdd.length; i++) {
      ItemStack stack = new ItemStack(item);
      setBaseColor(stack, baseColorsToAdd[i]);
      setTrimColor(stack, trimColorsToAdd[i]);
      list.add(stack);
    }
  }
  
  public static int getBaseColor(ItemStack stack)
  {
    return getOrCreateNBT(stack).getInteger("baseColor");
  }
  
  public static void setBaseColor(ItemStack stack, int color) {
    getOrCreateNBT(stack).setInteger("baseColor", color);
  }
  
  public static int getTrimColor(ItemStack stack) {
    return getOrCreateNBT(stack).getInteger("trimColor");
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
      ModelBiped model = ClothingCraft.proxy.getArmorModel(2);
      
      return ClothingCraft.proxy.fixModel(model, armorSlot, entityLiving);
    }
    























    return null;
  }
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\item\clothing\ClothingCraftClothingColorableShoes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */