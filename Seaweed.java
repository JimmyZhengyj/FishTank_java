package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Seaweed extends FishTankItem {
  private Paint paintText = new Paint();

  /** The number of weed segments. */
  int l;

  /** Indicates whether the bottom segment is leaning right. */
  boolean leanRight;

  /** My colour. Ah,the vagaries of British vs. US spelling. */
  Color colour;

  /**
   * Constructs a new seaweed item at the specified cursor location (x,y),l segments tall.
   *
   * @param x the x coordinate of the bubble's cursor location.
   * @param y the y coordinate of the bubble's cursor location.
   * @param l the number of segments this seaweed is tall.
   */
  public Seaweed(int l, FishTankManager fishtankmanager) {
    this.l = l;
    paintText.setTextSize(36);
    paintText.setColor(Color.GREEN);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    this.fishtankmanager = fishtankmanager;
  }

  @Override
  void drawString(Canvas canvas, String s, int x, int y) {
    canvas.drawText(s, y * FishTankView.charWidth, x * FishTankView.charHeight, paintText);
  }

  /**
   * Draws this fish tank item. Looks lovely waving in the current, doesn't it?
   *
   * @param canvas the graphics context in which to draw this item.
   */
  public void draw(Canvas canvas) {

    // WWhich way does the first segment lean?
    boolean lR = leanRight;

    for (int i = 0;
        i < l;
        i++) { // Draw a "/" seaweed segment: even numbered and leaning to the right.
      if (i % 2 == 0) {
        if (lR) {
          drawString(canvas, "/", -i + x, y);
        } else {
          drawString(canvas, "\\", -i + x, y);
        }
      } else { // Draw a "/" seaweed segment: odd numbered and leaning to the right.
        if (lR) {
          drawString(canvas, "\\", -i + x, y);
        } else {
          drawString(canvas, "/", -i + x, y);
        }
      }
    }
  }

  /** Causes this item to take its turn in the fish-tank simulation. */
  void wave() {
    leanRight = !leanRight;
  }
}
