package com.b3to.newcash.Tools;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class Widgets {
    public static void setImageTextThump(ImageView img, String text) {
        String[] texts = text.split(" ", 2);
        String letters = (texts.length > 0 ? texts[0].charAt(0) : "") + "" + (texts.length > 1 ? texts[1].charAt(0) : "");
        //begin set image
        //--init color
        //end set image
        ColorGenerator generator = ColorGenerator.MATERIAL;
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .useFont(Typeface.DEFAULT)
                .fontSize(30)
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound(letters.toUpperCase(), generator.getColor(text));
        img.setImageDrawable(drawable);
    }

    public interface ILongPress {
        void longPress(View view);
    }

    public interface IFragmentListener {
        boolean isSelectable();

        void click(int pos, Object obj);

        void longClick(int pos);

        void clickOptions(int pos);

        void onSelect(View view, int pos);
    }

    public interface IExecute {
        void exec(boolean result);
    }
}
