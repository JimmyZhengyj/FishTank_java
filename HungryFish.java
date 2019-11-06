package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class HungryFish extends Fish {

  /** Constructs a new hungry fish. */
  public HungryFish(FishTankManager fishtankmanager) {
    super(fishtankmanager);
    appearance = "><MEHUNGRY>";
  }
}
