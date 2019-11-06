package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Snake extends FishTankItem {
  /** How this fish appears on the screen. */
  private String appearance;

  /** Indicates whether this fish is moving right. */
  private boolean goingRight;

  private Paint paintText = new Paint();

  /** Constructs a new snake. */
  public Snake(FishTankManager fishtankmanager) {
    appearance = "~~~~";
    paintText.setTextSize(36);
    paintText.setColor(Color.CYAN);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    goingRight = true;
    this.fishtankmanager = fishtankmanager;
  }

  /** Turns this snake around, causing it to reverse direction. */
  private void turnAround() {
    goingRight = !goingRight;
  }

  @Override
  void drawString(Canvas canvas, String s, int x, int y) {
    canvas.drawText(s, y * FishTankView.charWidth, x * FishTankView.charHeight, paintText);
  }

  /**
   * Draws this fish tank item.
   *
   * @param canvas the canvas on which to draw this item.
   */
  public void draw(Canvas canvas) {
    drawString(canvas, appearance, y, x);
  }

  /** Causes this item to take its turn in the fish-tank simulation. */
  void move() {

    // Figure out whether I turn around.
    double d = Math.random();
    if (d < 0.1) {
      turnAround();
    }

    // Move one spot to the right or left in the direction I'm going. If I bump into a wall,
    // turn around.
    if (goingRight) {
      if (x + 1 > fishtankmanager.getGridWidth() - 1) {
        this.turnAround();
      } else {
        eatSeaweed();
        x += 1;
      }
    } else {
      if (x - 1 < 0) {
        this.turnAround();
      } else {
        eatSeaweed();
        x -= 1;
      }
    }

    // Figure out whether to move up or down, or neither.
    d = Math.random();
    if (d < 0.1 && y + 1 < fishtankmanager.getGridHeight() - 1) {
      eatSeaweed();
      y += 1;
    } else if (d < 0.2 && y - 1 > fishtankmanager.getGridHeight() / 2) {
      eatSeaweed();
      y -= 1;
    }
  }

  // the snake will eat the seaweed that is above him, and it becomes longer
  private void eatSeaweed() {
    for (int b = y; b != fishtankmanager.getGridHeight(); b++) {
      if (fishtankmanager.myLittleFishies[b][x] instanceof Seaweed) {
        Seaweed tempweed = (Seaweed) fishtankmanager.myLittleFishies[b][x];
        if (b - tempweed.l <= y) {
          this.appearance = this.appearance + "~";
          tempweed.l = b - y - 1;
        }
      }
    }
  }
}
