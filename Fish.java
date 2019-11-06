package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/** A fish. */
public class Fish extends FishTankItem {

  /** How this fish appears on the screen. */
  String appearance;

  /** Indicates whether this fish is moving right. */
  private boolean goingRight;

  private Paint paintText = new Paint();

  /** Constructs a new fish. */
  public Fish(FishTankManager fishtankmanager) {
    appearance = "><>";
    paintText.setTextSize(36);
    paintText.setColor(Color.CYAN);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    goingRight = true;
    this.fishtankmanager = fishtankmanager;
  }

  /** Causes this fish to blow a bubble. */
  private void blowBubble() {
    Bubble b = new Bubble(fishtankmanager);
    b.setLocation(x, y);
    System.out.println(y + " " + x);
    fishtankmanager.myLittleFishies[y][x] = b;

    fishtankmanager.Fishitems.add(fishtankmanager.myLittleFishies[y][x]);
  }

  /** Build and initialize this fish's forward and backward appearances. */
  private String reverseAppearance() {
    String reverse = "";
    for (int i = appearance.length() - 1; i >= 0; i--) {
      switch (appearance.charAt(i)) {
        case ')':
          reverse += '(';
          break;
        case '(':
          reverse += ')';
          break;
        case '>':
          reverse += '<';
          break;
        case '<':
          reverse += '>';
          break;
        case '}':
          reverse += '{';
          break;
        case '{':
          reverse += '}';
          break;
        case '[':
          reverse += ']';
          break;
        case ']':
          reverse += '[';
          break;
        default:
          reverse += appearance.charAt(i);
          break;
      }
    }

    return reverse;
  }

  /** Turns this fish around, causing it to reverse direction. */
  private void turnAround() {
    goingRight = !goingRight;
    appearance = reverseAppearance();
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
        x += 1;
      }
    } else {
      if (x - 1 < 0) {
        this.turnAround();
      } else {
        x -= 1;
      }
    }

    // Figure out whether I blow a bubble.
    d = Math.random();
    if (d < 0.1 && fishtankmanager.myLittleFishies[y][x] == null) {
      blowBubble();
    }

    // Figure out whether to move up or down, or neither.
    d = Math.random();
    if (d < 0.1 && y + 1 < fishtankmanager.getGridHeight() - 1) {
      y += 1;
    } else if (d < 0.2 && y - 1 > 0) {
      y -= 1;
    }
  }
}
