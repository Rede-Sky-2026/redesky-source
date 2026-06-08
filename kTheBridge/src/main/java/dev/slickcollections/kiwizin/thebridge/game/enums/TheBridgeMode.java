package dev.slickcollections.kiwizin.thebridge.game.enums;

public enum TheBridgeMode {
  SOLO("1v1", 1),
  DUPLA("2v2", 2);
  
  private static final TheBridgeMode[] VALUES = values();
  private final int size;
  private final String name;
  
  TheBridgeMode(String name, int size) {
    this.name = name;
    this.size = size;
  }
  
  public static TheBridgeMode fromName(String name) {
    for (TheBridgeMode mode : VALUES) {
      if (name.equalsIgnoreCase(mode.name())) {
        return mode;
      }
    }
    
    return null;
  }
  
  public int getSize() {
    return this.size;
  }
  
  public String getName() {
    return this.name;
  }
}