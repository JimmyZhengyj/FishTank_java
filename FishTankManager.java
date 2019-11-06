package uoft.csc207.fishtank;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class FishTankManager {

  /** All the locations where a fish can be. */
  FishTankItem[][] myLittleFishies;

  /*the list of all fish items */
  ArrayList<FishTankItem> Fishitems;
  /** The width of myLittleFishes. */
  private int gridWidth;
  /** The height of myLittleFishes. */
  private int gridHeight;

  /**
   * Return the width of a row of locations.
   *
   * @return the width of a column of locations.
   */
  int getGridWidth() {
    return gridWidth;
  }

  /**
   * Return the height of a column of locations.
   *
   * @return the height of a column of locations.
   */
  int getGridHeight() {
    return gridHeight;
  }

  /** The fish tank manager on a screen with height rows and width columns. */
  public FishTankManager(int height, int width) {
    gridHeight = height;
    gridWidth = width;
    myLittleFishies = new FishTankItem[height][width];
    Fishitems = new ArrayList();
  }

  void draw(Canvas canvas) {
    //    for (int a = 0; a != gridHeight; a++) {
    //      for (int b = 0; b != gridWidth; b++) {
    //        if (FishTankManager.myLittleFishies[a][b] != null)
    for (FishTankItem item : Fishitems) {
      item.draw(canvas);
    }
  }

  void update() {
    //    for (int a = 0; a != gridHeight; a++) {
    //      for (int b = 0; b != gridWidth; b++) {
    //        if (myLittleFishies[a][b] != null) {
    Object copy_items = Fishitems.clone();
    for (FishTankItem item : (ArrayList<FishTankItem>) copy_items) {
      if (item instanceof HungryFish) {
        ((HungryFish) item).move();
      } else if (item instanceof Seaweed) {
        ((Seaweed) item).wave();
      } else if (item instanceof Fish) {
        ((Fish) item).move();
      } else if (item instanceof Snake) {
        ((Snake) item).move();
      } else if (item instanceof Bubble) {
        Bubble heybub = (Bubble) item;
        heybub.floatUp();
      }
    }
  }

  void createTankItems() {
    myLittleFishies[28][18] = new Fish(this);
    (myLittleFishies[28][18]).setLocation(28, 18);
    myLittleFishies[10][22] = new Fish(this);
    (myLittleFishies[10][22]).setLocation(10, 22);
    myLittleFishies[17][14] = new Fish(this);
    (myLittleFishies[17][14]).setLocation(17, 14);
    myLittleFishies[15][28] = new Fish(this);
    (myLittleFishies[15][28]).setLocation(15, 28);
    myLittleFishies[43][18] = new Fish(this);
    (myLittleFishies[43][18]).setLocation(35, 36);
    myLittleFishies[16][5] = new Fish(this);
    (myLittleFishies[16][5]).setLocation(16, 5);
    myLittleFishies[16][12] = new Fish(this);
    (myLittleFishies[16][12]).setLocation(16, 12);
    myLittleFishies[16][22] = new Fish(this);
    (myLittleFishies[16][22]).setLocation(16, 18);
    myLittleFishies[23][18] = new Fish(this);
    (myLittleFishies[23][18]).setLocation(23, 18);
    myLittleFishies[6][12] = new Fish(this);
    (myLittleFishies[6][12]).setLocation(6, 12);
    myLittleFishies[10][20] = new HungryFish(this);
    (myLittleFishies[10][20]).setLocation(10, 20);
    myLittleFishies[33][4] = new Seaweed(9, this);
    (myLittleFishies[33][4]).setLocation(33, 4);
    myLittleFishies[24][13] = new Seaweed(6, this);
    (myLittleFishies[24][13]).setLocation(24, 13);
    myLittleFishies[42][15] = new Seaweed(12, this);
    (myLittleFishies[42][15]).setLocation(42, 15);
    myLittleFishies[13][20] = new Seaweed(5, this);
    (myLittleFishies[13][20]).setLocation(13, 20);
    myLittleFishies[30][26] = new Snake(this);
    (myLittleFishies[30][26]).setLocation(30, 26);
    for (int a = 0; a != gridHeight; a++) {
      for (int b = 0; b != gridWidth; b++) {
        if (myLittleFishies[a][b] != null) {
          Fishitems.add(myLittleFishies[a][b]);
        }
      }
    }
  }
}
