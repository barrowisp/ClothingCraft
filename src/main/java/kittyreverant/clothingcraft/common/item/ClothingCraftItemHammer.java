package kittyreverant.clothingcraft.common.item;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import kittyreverant.clothingcraft.ClothingCraft;
import kittyreverant.clothingcraft.ClothingCraft.Items;

public class ClothingCraftItemHammer extends net.minecraft.item.Item
{
	private final String name = "itemHammer";
  public ClothingCraftItemHammer()
  {

    setUnlocalizedName(name);
    setRegistryName(name);
    setCreativeTab(ClothingCraft.Items.tab);
    setContainerItem(this);
    //setTextureName("clothingcraft:clothingCraft_itemHammer");
    setMaxStackSize(1);
    

    this.bFull3D = true;
  }
  public void registerItemModel() {
	  ClothingCraft.proxy.registerItemRenderer(this, 0, name);
	}
}


/* Location:              D:\Users\Lesyriad\Desktop\[1.7.10] ClothingCraft 1.2.1-deobf.jar!\kittyreverant\clothingcraft\common\item\ClothingCraftItemHammer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */