package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/** A bubble. */
public class Bubble extends FishTankItem {

  /** How this bubble appears on the screen. */
  private String appearance;

  private Paint paintText = new Paint();

  /** Constructs a new bubble at the specified cursor location (x, y). */
  public Bubble(FishTankManager fishtankmanager) {
    // Get a nice-looking grey for the bubble
    paintText.setTextSize(36);
    paintText.setColor(Color.LTGRAY);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    // start off with . as the appearance
    appearance = ".";
    this.fishtankmanager = fishtankmanager;
  }

  @Override
  void drawString(Canvas canvas, String s, int x, int y) {
    canvas.drawText(s, y * FishTankView.charWidth, x * FishTankView.charHeight, paintText);
  }

  /**
   * Draws this fish tank item.
   *
   * @param canvas the graphics context in which to draw this item.
   */
  public void draw(Canvas canvas) {
    drawString(canvas, appearance, y, x);
  }

  /** cause the bubble to float up */
  void floatUp() {
    double d = Math.random();
    if (d < 0.33) {
      if (y - 1 > 0) {
        y--; // Move upwards.
        change_appearance();
      } else {
        this.delete_bubble();
      }
    } else if (d < 0.66) {
      if (y - 1 >= 0 && x + 1 <= fishtankmanager.getGridWidth() - 1) {
        y--; // Move upwards.
        x += 1; // right
        change_appearance();
      } else {
        delete_bubble();
      }
    } else {
      if (y - 1 > 0 && x - 1 > 0) {
        y--; // Move upwards.
        x -= 1; // left
        change_appearance();
      } else {
        this.delete_bubble();
      }
    }
  }

  /** change the appearance of bubble from "." to "o" and "o" to "O" */
  private void change_appearance() {
    // Figure out whether to grow, if at all.
    double d = Math.random();
    // Occasionally change a . to a o or a o to a O
    if (d < 0.05) {
      // If the appearance is a ., change it to an o
      if (appearance.equals(".")) appearance = "o";
      // If the appearance is an o, change it to a O
      else if (appearance.equals("o")) appearance = "O";
    }
  }

  /** delete the bubble if it is out of the boundary */
  private void delete_bubble() {
    fishtankmanager.Fishitems.remove(fishtankmanager.myLittleFishies[original_y][original_x]);
    fishtankmanager.myLittleFishies[original_y][original_x] = null;
  }
}
