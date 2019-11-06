package uoft.csc207.fishtank;

import android.graphics.Canvas;

public abstract class FishTankItem {

  FishTankManager fishtankmanager;

  /** This item's first coordinate. */
  int x;
  /** This item's second coordinate. */
  int y;

  /** the original coordinate where store the object. */
  int original_x;

  int original_y;

  /**
   * Set this item's location.
   *
   * @param a the first coordinate.
   * @param b the second coordinate.
   */
  void setLocation(int a, int b) {
    // set x to a
    x = a;
    original_x = a;
    // set y to b
    y = b;
    original_y = b;
  }

  /**
   * Draws the given string in the given graphics context at at the given cursor location.
   *
   * @param canvas the graphics context in which to draw the string.
   * @param s the string to draw.
   * @param x the x-coordinate of the string's cursor location.
   * @param y the y-coordinate of the string's cursor location.
   */
  void drawString(Canvas canvas, String s, int x, int y) {}

  /**
   * Draws this fish tank item.
   *
   * @param canvas the graphics context in which to draw this item.
   */
  public void draw(Canvas canvas) {}
}
