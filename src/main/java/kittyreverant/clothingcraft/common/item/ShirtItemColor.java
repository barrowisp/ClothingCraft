package kittyreverant.clothingcraft.common.item;

import java.awt.Color;

import scala.Console;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class ShirtItemColor implements IItemColor {
	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex) {
		
			if(stack.getTagCompound() != null)
			{
				switch (tintIndex) {
				case 0:
				{
					//if stack does not return a tagcompound then it is most likely the render for a creative tab
					return stack.getTagCompound().getInteger("baseColor");
						


				}
				
				case 1:
				{
					return stack.getTagCompound().getInteger("trimColor");
				}
				case 2:
				{
					return stack.getTagCompound().getInteger("buttonColor");
				}
				case 3:
				{
					return stack.getTagCompound().getInteger("sleeveColor");
				}
				default: {
					// oops! should never get here.
					return Color.BLACK.getRGB();
				}
				}
			}
		return 0;
	}
}
