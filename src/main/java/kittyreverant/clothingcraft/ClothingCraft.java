package kittyreverant.clothingcraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.Color;
import java.util.Random;

import kittyreverant.clothingcraft.common.ClothingCraftCommonProxy;
import kittyreverant.clothingcraft.common.crafting.ClothingCraftCrafter;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemButton;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemFabric;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemHammer;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemShirt;
import kittyreverant.clothingcraft.common.item.ClothingCraftItemSleeve;
import kittyreverant.clothingcraft.common.item.ItemColor;
import kittyreverant.clothingcraft.common.item.ShirtItemColor;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorableHat;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorablePants;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorableShirt;
import kittyreverant.clothingcraft.common.item.clothing.ClothingCraftClothingColorableShoes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;




@Mod(modid="ClothingCraft", name="ClothingCraft", version="1.2.1")
public class ClothingCraft
{
  @Mod.Instance("ClothingCraft")
  public static ClothingCraft instance;
  @SidedProxy(serverSide="kittyreverant.clothingcraft.common.ClothingCraftCommonProxy", clientSide="kittyreverant.clothingcraft.client.ClothingCraftClientProxy")
  public static ClothingCraftCommonProxy proxy;
  
  public static class Items
  {
    public static final CreativeTabs tab = new CreativeTabs("clothingCraftItems")
    {
      public Item getTabIconItem() {
        return ClothingCraft.Items.fabric;
      }
    };
    
    public static Item fabric;
    
    public static Item button;
    public static Item sleeve;
    public static Item shirt;
    public static Item hammer;
    
    public static void instantiate()
    {
      fabric = new ClothingCraftItemFabric();
      button = new ClothingCraftItemButton();
      sleeve = new ClothingCraftItemSleeve();
      shirt = new ClothingCraftItemShirt();
      hammer = new ClothingCraftItemHammer();
    }
    
    public static void register() {
      //GameRegistry.registerItem(fabric, "itemFabric");
    	ForgeRegistries.ITEMS.register(fabric);
    	((ClothingCraftItemFabric) fabric).registerItemModel();
    	
    	ForgeRegistries.ITEMS.register(button);
    	((ClothingCraftItemButton) button).registerItemModel();
    	//Minecraft.getMinecraft().getItemColors().registerItemColorHandler((IItemColor)button, null);
    	
    	ForgeRegistries.ITEMS.register(sleeve);
    	((ClothingCraftItemSleeve) sleeve).registerItemModel();
    	ForgeRegistries.ITEMS.register(shirt);
    	//GameRegistry.register(shirt);
    	((ClothingCraftItemShirt) shirt).registerItemModel();
    	ForgeRegistries.ITEMS.register(hammer);
    	((ClothingCraftItemHammer) hammer).registerItemModel();
    }
  }
  
  public static class Clothing
  {
    private static final ItemArmor.ArmorMaterial CLOTHING_MATERIAL_ARMOR = EnumHelper.addArmorMaterial("CLOTHING_MATERIAL_ARMOR", null, 15, new int[] { 1, 3, 2, 1 }, 12, null, 0);
    private static final ItemArmor.ArmorMaterial CLOTHING_MATERIAL_NO_ARMOR = EnumHelper.addArmorMaterial("CLOTHING_MATERIAL_NO_ARMOR", "clothing_1", 15, new int[] { 0, 0, 0, 0 }, 12, null, 0);
    
    public static final CreativeTabs tab = new CreativeTabs("clothingCraftClothing") {
      private ItemStack iconStack;
      private final Random random = new Random();
      
      public Item getTabIconItem()
      {
        return ClothingCraft.Clothing.colorableShirt;
      }
      
      @SideOnly(Side.CLIENT)
      public ItemStack getIconItemStack()
      {
        if (this.iconStack == null) {
          this.iconStack = new ItemStack(ClothingCraft.Clothing.colorableShirt);
          ClothingCraftClothingColorableShirt.setBaseColor(this.iconStack, getRandomHex(this.random));
          ClothingCraftClothingColorableShirt.setTrimColor(this.iconStack, getRandomHex(this.random));
          ClothingCraftClothingColorableShirt.setTrimType(this.iconStack, this.random.nextInt(2));
          ClothingCraftClothingColorableShirt.setSleeveColor(this.iconStack, getRandomHex(this.random));
        }
        
        return this.iconStack;
      }
      
      private int getRandomHex(Random random) {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)).getRGB();
      }
    };
    
    public static ClothingCraftClothingColorableShirt colorableShirt;
    public static ClothingCraftClothingColorablePants colorablePants;
    public static ClothingCraftClothingColorableShoes colorableShoes;
    public static ClothingCraftClothingColorableHat colorableHat;
    
    public static void instantiate()
    {
      colorableShirt = new ClothingCraftClothingColorableShirt();
      colorablePants = new ClothingCraftClothingColorablePants();
      colorableShoes = new ClothingCraftClothingColorableShoes();
      colorableHat = new ClothingCraftClothingColorableHat();
    }
    
    public static void register() {
    	ForgeRegistries.ITEMS.register(colorableShirt);
    	((ClothingCraftClothingColorableShirt) colorableShirt).registerItemModel();
    	ForgeRegistries.ITEMS.register(colorablePants);
    	((ClothingCraftClothingColorablePants) colorablePants).registerItemModel();
    	ForgeRegistries.ITEMS.register(colorableShoes);
    	((ClothingCraftClothingColorableShoes) colorableShoes).registerItemModel();
    	ForgeRegistries.ITEMS.register(colorableHat);
    	((ClothingCraftClothingColorableHat) colorableHat).registerItemModel();
    	//ModelLoader.setCustomModelResourceLocation(colorableHat,0,new ModelResourceLocation("ClothingCraft:itemClothingColorableHat","inventory"));
    	
    }
    
    public static ItemArmor.ArmorMaterial getClothingArmorMaterial() {
      return ClothingCraft.ConfigurationValues.clothingHasArmorRating ? CLOTHING_MATERIAL_ARMOR : CLOTHING_MATERIAL_NO_ARMOR;
    }
  }
  

  public static class ConfigurationValues
  {
    public static boolean clothingHasArmorRating = false;
    

    public static boolean enableClayFabricCrafting = true;
  }
  

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    Configuration configuration = new Configuration(event.getSuggestedConfigurationFile());
    configuration.load();
    
    ConfigurationValues.clothingHasArmorRating = configuration.getBoolean("clothingHasArmorRating", "misc", false, "true/false. Whether clothing should give you armor points.");
    ConfigurationValues.enableClayFabricCrafting = configuration.getBoolean("enableClayFabricCrafting", "misc", true, "true/false. Whether you can craft brown fabric with clay items because cocoa beans and jungles are somewhat hard to find these days.");
    
    configuration.save();
    

    Clothing.instantiate();
    Items.instantiate();
    
    Clothing.register();
    Items.register();
    
    ClothingCraftCrafter.addRecipes();
  }
  
  @Mod.EventHandler
  public void init(FMLInitializationEvent event) {}
  
  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent event) {
	  Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColor(),Items.button);
	  Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColor(),Items.fabric);
	  Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ShirtItemColor(),Items.shirt);
	  Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColor(),Items.sleeve);
	  Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ShirtItemColor(),Clothing.colorableShirt);
	  Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ShirtItemColor(),Clothing.colorableHat);
	  Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ShirtItemColor(),Clothing.colorablePants);
	  Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ShirtItemColor(),Clothing.colorableShoes);
  }
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\ClothingCraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */