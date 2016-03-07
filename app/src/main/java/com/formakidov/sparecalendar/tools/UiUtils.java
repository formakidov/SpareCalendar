package com.formakidov.sparecalendar.tools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.formakidov.sparecalendar.Application;
import com.formakidov.sparecalendar.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UiUtils {

    public static void showToast(final Context context, final String text) {
        ((Activity) context).runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                TextView msg = (TextView) toast.getView().findViewById(android.R.id.message);
                msg.setGravity(Gravity.CENTER);
                toast.show();
            }
        });
    }

    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }

    public static Snackbar showSnack(Activity activity, String text) {
        final Snackbar snack = Snackbar.make(activity.findViewById(R.id.coordinator_layout), text, Snackbar.LENGTH_LONG);
        TextView msg = (TextView) snack.getView().findViewById(R.id.snackbar_text);
        msg.setMinHeight(activity.getResources().getDimensionPixelOffset(R.dimen.height_48));
        msg.setGravity(Gravity.CENTER);
        msg.setMaxLines(5);

        activity.runOnUiThread(new Runnable() {
            public void run() {
                snack.show();
            }
        });

        return snack;
    }

    public static Snackbar showSnack(Activity activity, int resId) {
        return showSnack(activity, activity.getString(resId));
    }

    public static void showPopup(Context context, View v, int menuRes, OnMenuItemClickListener listener) {
        PopupMenu popupMenu = new PopupMenu(context, v);

        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("mPopup")) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        popupMenu.inflate(menuRes);
        popupMenu.setOnMenuItemClickListener(listener);
        popupMenu.show();
    }

    public static AutoCompleteTextView getTitleEditor(LinearLayout title, String name, int hint) {
        AutoCompleteTextView editorTitle = (AutoCompleteTextView) title.findViewById(R.id.title_name);
        editorTitle.setText(name);
        editorTitle.setSelection(editorTitle.getText().length());
        editorTitle.setHint(hint);
        editorTitle.setHorizontallyScrolling(false);
        editorTitle.setMaxLines(3);
        return editorTitle;
    }

    private static Resources getResources() {
        return Application.getContext().getResources();
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getToolbarHeight(Context context) {
        int toolbarHeight = 0;
        if (!Tools.isPreLollipop()) {
            TypedValue tv = new TypedValue();
            if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                toolbarHeight = TypedValue.complexToDimensionPixelOffset(tv.data, getResources().getDisplayMetrics());
            }
        } else {
            toolbarHeight = getResources().getDimensionPixelOffset(R.dimen.toolbar_height);
        }
        return toolbarHeight;
    }

}