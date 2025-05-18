package com.avitaliskhakov.multistateviewx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class MultiStateNavigator {

    public static void startActivity(Context context, Class<?> targetActivity, TransitionType type) {
        Intent intent = new Intent(context, targetActivity);
        context.startActivity(intent);

        if (context instanceof Activity) {
            applyEnterTransition((Activity) context, type);
        }
    }

    private static void applyEnterTransition(Activity activity, TransitionType type) {
        switch (type) {
            case SLIDE:
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case FADE:
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case NONE:
            default:
                break;
        }
    }

    public static void finishWithTransition(Activity activity, TransitionType type) {
        activity.finish();
        switch (type) {
            case SLIDE:
                activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case FADE:
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case NONE:
            default:
                break;
        }
    }
}
