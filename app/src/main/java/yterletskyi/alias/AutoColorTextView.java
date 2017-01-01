package yterletskyi.alias;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.renderscript.Double2;
import android.util.AttributeSet;
import android.widget.TextView;

import kotlin.NotImplementedError;

/**
 * Created by yterletskyi on 01.01.17.
 */

public class AutoColorTextView extends TextView {

    private static final int TEXT_COLOR_POSITIVE = 0xFF4CAF50;
    private static final int TEXT_COLOR_NEGATIVE = 0xFFF44336;
    private static final int TEXT_COLOR_NEUTRAL = Color.BLACK;

    public AutoColorTextView(Context context) {
        super(context);
    }

    public AutoColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setTextColor(int color) {
        // intentionally left blank to prevent setting color
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        double number;
        try {
            String textStr = String.valueOf(text);
            number = Double.parseDouble(textStr);
            if (number > 0) {
                super.setTextColor(TEXT_COLOR_POSITIVE);
            } else if (number < 0) {
                super.setTextColor(TEXT_COLOR_NEGATIVE);
            } else {
                super.setTextColor(TEXT_COLOR_NEUTRAL);
            }
        } catch (NumberFormatException e) {
            super.setTextColor(TEXT_COLOR_NEUTRAL);
        }

        super.setText(text, type);

    }


}
