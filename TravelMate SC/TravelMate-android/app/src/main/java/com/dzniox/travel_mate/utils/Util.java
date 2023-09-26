package com.dzniox.travel_mate.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;


public class Util {


    public static final String ANDROID = "android";
    public static final String MANUAL = "manual";
    public static final String PREF_NAME = "streamtube";
    public static final String FACEBOOK = "facebook";
    public static final String GOOGLE = "google";
    public static final String SUCCESS = "success";
    public static final String TRUE = "true";
    public static final String CHROME = "chrome";
    public static final int TIMEOUT = 20000;
    public static final int MAX_RETRY = 3;
    public static final float DEFAULT_BACKOFF_MULT = 1f;
    public static final String UNKNOWN = "unknown";
    public static final String POOR = "poor";
    public static final String GOOD = "good";
    public static final String MODERATE = "modrate";
    public static final String EXCELLENT = "excellent";
    public static int GET = 0;
    public static int POST = 1;
    public static int NO_REQUEST = -1;
    public static Dialog progressDialog;

    public static boolean isConnected(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                return info.isConnected() || info.isConnectedOrConnecting();
            }
        }
        return false;
    }

    @ColorInt
    public static int color(Context context, @ColorRes int res) {
        return ContextCompat.getColor(context, res);
    }

    public static BitmapDrawable getRoundedCornerBitmap(Context context, Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth() - 10, bitmap.getHeight() - 10);
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return new BitmapDrawable(context.getResources(), output);
    }


    public class Param {
        public static final String URL = "url";
        public static final String ID = "id";
        public static final String TOKEN = "token";
        public static final String NAME = "name";
        public static final String DOB = "dob";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String MOBILE = "mobile";
        public static final String DESCRIPTION = "description";
        public static final String PICTURE = "picture";
        public static final String COVER = "cover";
        public static final String LOGIN_BY = "login_by";
        public static final String TYPE = "type";
        public static final String DEVICE_TYPE = "device_type";
        public static final String DEVICE_TOKEN = "device_token";
        public static final String SOCIAL_UNIQUE_ID = "social_unique_id";
        public static final String PUSH_STATUS = "status";
        public static final String CARD_ID = "card_id";

        public static final String OLD_PASSWORD = "old_password";
        public static final String PASSWORD_CONFIRMATION = "password_confirmation";

        public static final String SKIP = "skip";
        public static final String AGE = "age";
        public static final String TITLE = "title";
        public static final String PAYMENT_STATUS = "payment_status";
        public static final String AMOUNT = "amount";
        public static final String SUBTITLE = "subtitle";
        public static final String VIEWS = "views";
        public static final String VIDEO_ID = "video_tape_id";
        public static final String USER_ID = "user_id";
        public static final String CHANNEL_ID = "channel_id";
        public static final String PAYMENT_ID = "payment_id";
        public static final String BROWSWER = "browser";
        public static final String FOLLOWER_ID = "follower_id";
        public static final String BLOCKER_ID = "blocker_id";
        public static final String CATEGORY_ID = "category_id";
        public static final String TAG_ID = "tag_id";
        public static final String COUPON_CODE = "coupon_code";
        public static final String CANCEL_REASON = "cancel_reason";
        public static final String LIVE_VIDEO_ID = "live_video_id";

    }

}