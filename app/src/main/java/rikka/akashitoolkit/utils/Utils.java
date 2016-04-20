package rikka.akashitoolkit.utils;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Rikka on 2016/3/8.
 */
public class Utils {
    public static File writeStreamToCacheFile(Context context, InputStream inputStream, String name) {
        String FilePath = context.getCacheDir().getAbsolutePath() + name;

        return writeStreamToFile(inputStream, FilePath);
    }

    public static File writeStreamToFile(InputStream inputStream, String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
        }
        try {
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(path);

            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean isNightMode(Resources resources) {
        return ((resources.getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_YES) > 0);
    }

    public static void colorAnimation(int colorFrom, int colorTo, int duration, ValueAnimator.AnimatorUpdateListener listener) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(duration);
        colorAnimation.addUpdateListener(listener);
        colorAnimation.start();
    }

    public static String md5( String input ) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(String.format("%02x", anArray));
            }
            return sb.toString();
        } catch ( NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getKCWikiFileUrl(String fileName) {
        String md5 = Utils.md5(fileName);
        if (md5 == null) {
            return null;
        }

        String a = md5.substring(0, 1);
        String b = md5.substring(0, 2);

        return String.format("http://kc.6candy.com/commons/%s/%s/%s", a, b, fileName);
    }
}
