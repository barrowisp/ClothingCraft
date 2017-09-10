package kittyreverant.clothingcraft.unused;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.inventory.EntityEquipmentSlot;
import kittyreverant.clothingcraft.client.model.ClothingCraftModelClothingBiped;

@Deprecated
public abstract class ClothingCraftOldClothingSet
{
  protected boolean hasShirt;
  protected boolean hasPants;
  protected boolean hasShoes;
  private ClothingPiece shirt;
  private ClothingPiece pants;
  private ClothingPiece shoes;
  
  public ClothingCraftOldClothingSet() {
    this.hasShirt = true;
    this.hasPants = true;
    this.hasShoes = true;
  }
  

  public abstract String getClothingTexture();
  

  public abstract ClothingCraftModelClothingBiped getClothingModel();
  

  public abstract String getUnlocalizedName();
  
  public ClothingPiece getShirt()
  {
    return this.shirt == null ? (this.shirt = processClothingPiece(new ClothingPiece(EntityEquipmentSlot.CHEST))) : this.shirt;
  }
  


  public ClothingPiece getPants()
  {
    return this.pants == null ? (this.pants = processClothingPiece(new ClothingPiece(EntityEquipmentSlot.LEGS))) : this.pants;
  }
  


  public ClothingPiece getShoes()
  {
    return this.shoes == null ? (this.shoes = processClothingPiece(new ClothingPiece(EntityEquipmentSlot.FEET))) : this.shoes;
  }
  
  protected void adjustConfigurationSettings() {}
  
  private ClothingPiece processClothingPiece(ClothingPiece piece) {
    piece.setUnlocalizedName(getUnlocalizedName() + getClothingType(piece.armorType));
    //piece.setTextureName("clothingcraft:" + getUnlocalizedName() + getClothingType(piece.armorType));
    piece.setMaxStackSize(1);
    piece.setCreativeTab(kittyreverant.clothingcraft.ClothingCraft.Clothing.tab);
    return piece;
  }
  
  private String getClothingType(EntityEquipmentSlot id) {
    switch (id) {
    case HEAD:  return "Hat";
    case CHEST:  return "Shirt";
    case LEGS:  return "Pants";
    case FEET:  return "Shoes";
	default:
		return "Unknown";
    }
    
    
  }
  
 
  
  public class ClothingPiece extends net.minecraft.item.ItemArmor
  {
    public ClothingPiece(EntityEquipmentSlot type) {
      this(net.minecraft.item.ItemArmor.ArmorMaterial.DIAMOND, type);
    }
    






    public ClothingPiece(net.minecraft.item.ItemArmor.ArmorMaterial material, EntityEquipmentSlot type)
    {
      super(material, 2, type);
    }
    
    public String getArmorTexture(net.minecraft.item.ItemStack stack, net.minecraft.entity.Entity entity, int slot, String type)
    {
      return "clothingcraft:textures/clothing/clothing.png";
    }
    

    public net.minecraft.client.model.ModelBiped getArmorModel(net.minecraft.entity.EntityLivingBase living, net.minecraft.item.ItemStack stack, int armorSlot)
    {
      if ((stack != null) && (stack.getItem() == this)) {
        ClothingCraftModelClothingBiped model = ClothingCraftOldClothingSet.this.getClothingModel();
        
        if (model != null) {
          model.bipedHead.showModel = (armorSlot == 0);
          model.bipedHeadwear.showModel = (armorSlot == 0);
          model.bipedBody.showModel = (armorSlot == 1);
          model.bipedRightArm.showModel = (armorSlot == 1);
          model.bipedLeftArm.showModel = (armorSlot == 1);
          model.bipedRightLeg.showModel = (armorSlot == 2);
          model.bipedLeftLeg.showModel = (armorSlot == 2);
          model.setShoesShowing(armorSlot == 3);
          
          model.isSneak = living.isSneaking();
          model.isRiding = living.isRiding();
          model.isChild = living.isChild();
          model.rightArmPose = (living.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND) != null ? ModelBiped.ArmPose.ITEM : ModelBiped.ArmPose.EMPTY);
          
          //if ((living instanceof net.minecraft.entity.player.EntityPlayer)) { model.aimedBow = (((net.minecraft.entity.player.EntityPlayer)living).getItemInUseCount() > 2);
          //}
        }
        
        return model;
      }
      

      return null;
    }
  }
}


