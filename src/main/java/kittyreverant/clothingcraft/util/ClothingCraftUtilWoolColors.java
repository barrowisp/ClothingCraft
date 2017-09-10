package kittyreverant.clothingcraft.util;

public class ClothingCraftUtilWoolColors
{
  public static String getWoolColorForMetadata(int metadata) {
    if ((metadata > 15) || (metadata < 0)) { throw new IllegalArgumentException("The parameter metadata must be the metadata of the wool!");
    }
    if (metadata == 0) {
      return "white";
    }
    
    if (metadata == 1) {
      return "orange";
    }
    
    if (metadata == 2) {
      return "dark_pink";
    }
    
    if (metadata == 3) {
      return "light_blue";
    }
    
    if (metadata == 4) {
      return "yellow";
    }
    
    if (metadata == 5) {
      return "light_green";
    }
    
    if (metadata == 6) {
      return "pink";
    }
    
    if (metadata == 7) {
      return "gray";
    }
    
    if (metadata == 8) {
      return "light_gray";
    }
    
    if (metadata == 9) {
      return "sea_green";
    }
    
    if (metadata == 10) {
      return "purple";
    }
    
    if (metadata == 11) {
      return "blue";
    }
    
    if (metadata == 12) {
      return "brown";
    }
    
    if (metadata == 13) {
      return "green";
    }
    
    if (metadata == 14) {
      return "red";
    }
    
    if (metadata == 15) {
      return "black";
    }
    
    throw new IllegalArgumentException("It seems that there is an error! There was no result for metadata " + metadata + "! This should NOT be happening and is the fault of the programmer!");
  }
  
  public static int getWoolMetadataFromDyeMetadata(int dye)
  {
    if ((dye > 15) || (dye < 0)) { throw new IllegalArgumentException("The parameter metadata must be the metadata of the dye!");
    }
    return dye * -1 + 15;
  }
}


