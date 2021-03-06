package rikka.akashitoolkit.staticdata;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rikka.akashitoolkit.R;
import rikka.akashitoolkit.model.EquipType;
import rikka.akashitoolkit.utils.Utils;

/**
 * Created by Rikka on 2016/3/24.
 */
public class EquipTypeList {
    private static final String FILE_NAME = "EquipType.json";

    private static List<EquipType> sList;
    private static Map<String, Integer> sParentList;

    public static synchronized List<EquipType> get(Context context) {
        if (sList == null) {
            sList = new BaseGSONList<EquipType>() {
                @Override
                public void afterRead(List<EquipType> list) {
                    generateParentList(list);
                }
            }.get(context, FILE_NAME, new TypeToken<ArrayList<EquipType>>() {}.getType());
        }
        return sList;
    }

    public static synchronized void clear() {
        sList = null;
    }

    public static synchronized Map<String, Integer> getsParentList(Context context) {
        if (sParentList == null) {
            get(context);
        }
        return sParentList;
    }

    public static EquipType findItemById(Context context, int id) {
        for (EquipType item:
                get(context)) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private static void generateParentList(List<EquipType> list) {
        if (sParentList != null) {
            return;
        }

        sParentList = new LinkedHashMap<>();

        for (EquipType item:
                list) {

            if (sParentList.get(item.getParent()) == null) {
                sParentList.put(item.getParent(), sParentList.size() + 1);
            }
        }
    }

    private static int[] ICON = {
            0,
            R.drawable.system_icon_01_24dp,
            R.drawable.system_icon_02_24dp,
            R.drawable.system_icon_03_24dp,
            R.drawable.system_icon_01_24dp,
            R.drawable.system_icon_05_24dp,
            R.drawable.system_icon_06_24dp,
            R.drawable.system_icon_06_24dp,
            R.drawable.system_icon_06_24dp,
            R.drawable.system_icon_06_24dp,
            R.drawable.system_icon_10_24dp,
            R.drawable.system_icon_11_24dp,
            R.drawable.system_icon_12_24dp,
            R.drawable.system_icon_13_24dp,
            R.drawable.system_icon_14_24dp,
            R.drawable.system_icon_15_24dp,
            R.drawable.system_icon_16_24dp,
            R.drawable.system_icon_17_24dp,
            R.drawable.system_icon_11_24dp,
            R.drawable.system_icon_19_24dp,
            R.drawable.system_icon_20_24dp,
            R.drawable.system_icon_21_24dp,
            R.drawable.system_icon_22_24dp,
            R.drawable.system_icon_23_24dp,
            R.drawable.system_icon_24_24dp,
            R.drawable.system_icon_25_24dp,
            R.drawable.system_icon_26_24dp,
            R.drawable.system_icon_27_24dp,
            R.drawable.system_icon_28_24dp,
            R.drawable.system_icon_29_24dp,
            R.drawable.system_icon_30_24dp,
            R.drawable.system_icon_31_24dp,
            R.drawable.system_icon_32_24dp,
            R.drawable.system_icon_33_24dp,
            R.drawable.system_icon_34_24dp,
            R.drawable.system_icon_25_24dp,
            R.drawable.system_icon_36_24dp,
            R.drawable.system_icon_05_24dp,
            R.drawable.system_icon_05_24dp,
            R.drawable.system_icon_10_24dp,
            R.drawable.system_icon_10_24dp,
            R.drawable.system_icon_11_24dp,
            R.drawable.system_icon_06_24dp,
            R.drawable.system_icon_06_24dp
    };

    private static int[][] COLOR = {
            {
                    0,
                    R.color.material_red_400,
                    R.color.material_red_700,
                    R.color.material_red_700,
                    R.color.material_amber_500, // 5

                    R.color.material_blue_grey_500,
                    R.color.material_green_500,
                    R.color.material_red_400,
                    R.color.material_light_blue_500,
                    R.color.material_amber_500, // 10

                    R.color.material_green_200,
                    R.color.material_orange_500,
                    R.color.material_green_400,
                    R.color.material_red_400,
                    R.color.material_grey_800, // 15

                    R.color.material_green_300,
                    R.color.material_green_300,
                    R.color.material_light_blue_300,
                    R.color.material_light_blue_300,
                    R.color.material_amber_500, // 20

                    R.color.material_lime_700,
                    R.color.material_green_300,
                    R.color.material_cyan_200,
                    R.color.material_deep_purple_300,
                    R.color.material_orange_500, // 25

                    R.color.material_grey_400,
                    R.color.material_brown_300,
                    R.color.material_orange_400,
                    R.color.material_deep_purple_200,
                    R.color.material_brown_300, // 30

                    R.color.material_lime_800,
                    R.color.material_red_500,
                    R.color.material_green_200,
                    R.color.material_green_200,
                    R.color.material_grey_800, // 35

                    R.color.material_teal_200,
                    R.color.material_lime_700,
                    R.color.material_blue_grey_500,
                    R.color.material_blue_grey_500,
                    R.color.material_green_300, // 40

                    R.color.material_green_300,
                    R.color.material_orange_500,
                    R.color.material_light_blue_300,
                    R.color.material_green_500
            },
            {
                    0,
                    R.color.material_red_200,
                    R.color.material_red_200,
                    R.color.material_red_200,
                    R.color.material_amber_200, // 5

                    R.color.material_blue_grey_200,
                    R.color.material_green_200,
                    R.color.material_red_200,
                    R.color.material_light_blue_200,
                    R.color.material_amber_200, // 10

                    R.color.material_green_200,
                    R.color.material_orange_200,
                    R.color.material_green_200,
                    R.color.material_red_200,
                    R.color.material_grey_200,  // 15

                    R.color.material_green_200,
                    R.color.material_green_200,
                    R.color.material_light_blue_200,
                    R.color.material_light_blue_200,
                    R.color.material_amber_200, // 20

                    R.color.material_lime_200,
                    R.color.material_green_200,
                    R.color.material_cyan_200,
                    R.color.material_deep_purple_200,
                    R.color.material_orange_200,    // 25

                    R.color.material_grey_200,
                    R.color.material_brown_200,
                    R.color.material_orange_200,
                    R.color.material_deep_purple_200,
                    R.color.material_brown_200, // 30

                    R.color.material_lime_200,
                    R.color.material_red_200,
                    R.color.material_green_200,
                    R.color.material_green_200,
                    R.color.material_grey_200, // 35

                    R.color.material_teal_200,
                    R.color.material_lime_200,
                    R.color.material_blue_grey_200,
                    R.color.material_blue_grey_200,
                    R.color.material_green_200, // 40

                    R.color.material_green_200,
                    R.color.material_orange_200,
                    R.color.material_light_blue_200,
                    R.color.material_green_200
            }
    };

    /*private static String[] COLOR = {
            "",

            "#f44336",
            "#f44336",
            "#f44336",
            "#ffc107",
            "#3f51b5",

            "#4caf50",
            "#f44336",
            "#2196f3",
            "#ffc107",
            "#8bc34a",

            "#FFD54F",
            "#4caf50",
            "#f44336",
            "#9e9e9e",
            "#4caf50",

            "#4caf50",
            "#03a9f4",
            "#03a9f4",
            "#ffc107",
            "#4caf50",

            "#4caf50",
            "#03a9f4",
            "#673ab7",
            "#ff9800",
            "#9e9e9e",
            "#795548",

            "#ff9800",
            "#673ab7",
            "#795548",
            "#009688",
            "#f44336",

            "#8bc34a",
            "#8bc34a",
            "#9e9e9e",
            "#009688",
            "#009688"
    };*/

    /*public static int getResourceId(Context context, int id) {
        return context.getResources().getIdentifier(String.format("system_icon_%02d", id), "drawable",
                context.getPackageName());
    }*/

    public static void setIntoImageView(ImageView imageView, int id) {
        if (imageView.getTag() != null
                && (int) imageView.getTag() == id) {
            return;
        }

        try {
            imageView.setTag(id);
            imageView.setImageResource(ICON[id]);
            imageView.setColorFilter(ContextCompat.getColor(
                    imageView.getContext(),
                    COLOR[Utils.isNightMode(imageView.getContext().getResources()) ? 1 : 0][id]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
