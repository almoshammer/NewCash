package com.b3to.newcash.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.core.content.ContextCompat;

import com.b3to.newcash.R;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class DML {
    public static boolean isEmpty(TextInputEditText txt) {
        boolean err = (txt == null || String.valueOf(txt.getText()).isBlank());
        txt.setError(err ? txt.getContext().getResources().getString(R.string.this_field_cant_be_empty) : null);
        return err;
    }

    public static boolean isEmpty(WidgetDropdown dropdown) {
        boolean err = dropdown == null || dropdown.getPosition() < 0;
        dropdown.getDropDown().setError(err ? dropdown.getDropDown().getContext().getResources().getString(R.string.this_field_cant_be_empty) : null);
        return err;
    }

    public static String toDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_SHORT);
        return format.format(date);
    }

    public static boolean isEmpty(ThemedToggleButtonGroup group) {
        boolean err = group == null || group.getSelectedButtons().size() == 0;
        assert group != null;
        group.setBackgroundColor(err ? toColorInt(R.color.red, group.getContext()) : toColorInt(R.color.white, group.getContext()));
        return err;
    }

    public static void putLong(Context ctx, String key, long value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences("MAIN", Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void putInt(Context ctx, String key, int value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences("MAIN", Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static long getLong(Context ctx, String key) {
        SharedPreferences preferences = ctx.getSharedPreferences("MAIN", Context.MODE_PRIVATE);
        return preferences.getLong(key, 0);
    }

    public static int getInt(Context ctx, String key, int def) {
        SharedPreferences preferences = ctx.getSharedPreferences("MAIN", Context.MODE_PRIVATE);
        return preferences.getInt(key, def);
    }


    public static void AssignValue(TextInputEditText txt, String value) {
        txt.setText(value);
    }

    public static long toTimeStamp(String date, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format);
            Date dt = df.parse(date);
            assert dt != null;
            return new Timestamp(dt.getTime()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getCurrDate() {
        Calendar calendar = Calendar.getInstance();
        final int y = calendar.get(Calendar.YEAR);
        final int m = calendar.get(Calendar.MONTH) + 1;
        final int d = calendar.get(Calendar.DAY_OF_MONTH);
        return String.format("%s-%s-%s", d, m, y);
    }

    @ColorInt
    public static int toColorInt(@ColorRes int res, Context cx) {
        return ContextCompat.getColor(cx, res);
    }

    public enum QUERY_TYPE {
        NONE, INSERT, UPDATE, DELETE
    }

    public static String getRelatedDate(long timestamp, Context c) {
        return TimeAgo.using(timestamp);
    }

    public static String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static String DATE_FORMAT_SHORT = "dd-MM-yyyy";

    @IdRes
    public static int getAttr(Context context, @AttrRes int resId) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(resId, value, true);
        return value.data;
    }
    public static long getTimestamp(int year, int month, int day, int hour, int minute, int millie) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_SHORT);

        try {
            Date date = format.parse(String.format("%s-%s-%s %s:%s:%s", year, month, day, hour, minute, millie));
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
