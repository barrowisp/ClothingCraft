package kittyreverant.clothingcraft.common.item;

import java.awt.Color;

import scala.Console;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class ItemColor implements IItemColor {
	@Override
	  public int getColorFromItemstack(ItemStack stack, int tintIndex) {
	    // when rendering, choose the colour multiplier based on the contents
	    // we want layer 0 (the bottle glass) to be unaffected (return white as the multiplier)
	    // layer 1 will change colour depending on the contents.
	    {
	      switch (tintIndex) {
	        case 0:
	        {
	        	//if stack does not return a tagcompound then it is most likely the render for a creative tab
	        	
	        	if(stack.getTagCompound() != null)
	        	{
	          String metadata = stack.getTagCompound().getString("color");
	          //int contentsBits = metadata & 0x03;
	          if(metadata != "")
	          {
	          return ClothingCraftItemFabric.ColoringUtil.getColorForTypeName(metadata);
	          }
	          int meta = stack.getTagCompound().getInteger("color");
	          return meta;
	         // ItemVariants.EnumBottleContents contents = ItemVariants.EnumBottleContents.byMetadata(contentsBits);
	         // return contents.getRenderColour().getRGB();
	        	}
	        	return Color.WHITE.getRGB();
	        }
	        default: {
	          // oops! should never get here.
	          return Color.BLACK.getRGB();
	        }
	      }
	    }
	  }
}
