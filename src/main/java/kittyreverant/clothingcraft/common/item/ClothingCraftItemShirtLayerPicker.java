package kittyreverant.clothingcraft.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ClothingCraftItemShirtLayerPicker implements IItemPropertyGetter{

	@Override
	public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
		if (worldIn == null && entityIn != null)  {
	        worldIn = entityIn.worldObj;
	      }

	      if (entityIn == null || worldIn == null)
	    	  return 0.0f;
	      
	      NBTTagCompound tag = stack.getTagCompound();
	      
	      if(tag!=null)
	      {
	    	 int trim = tag.getInteger("trimType");
	    	 if(trim == 0)
	    		 return 0.1f;
	    	 else if(trim == 1)
	    		 return 0.2f;
	    	
	    	  
	      }
		return 0.0f;
	}
	
	

}
