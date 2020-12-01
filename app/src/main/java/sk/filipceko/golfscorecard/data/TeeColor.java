package sk.filipceko.golfscorecard.data;

import android.content.res.Resources;
import sk.filipceko.golfscorecard.R;

public enum TeeColor {
    Black("Black",R.string.tee_black_name, R.color.black),
    Blue("Blue", R.string.tee_blue_name, R.color.blue),
    White("White", R.string.tee_white_name, R.color.white),
    Red("Red", R.string.tee_red_name, R.color.red),
    Yellow("Yellow", R.string.tee_yellow_name, R.color.yellow),
    Purple("Purple", R.string.tee_purple_name, R.color.purple),
    Green("Green", R.string.tee_green_name, R.color.green),
    Orange("Orange", R.string.tee_orange_name, R.color.orange);

    private final String colorName;
    private final int uiColorNameCode;
    private final int colorResourceCode;

    TeeColor(String colorName,int uiColorNameCode, int colorResource){
        this.uiColorNameCode = uiColorNameCode;
        this.colorName = colorName;
        this.colorResourceCode = colorResource;
    }

    public static TeeColor parse(String name) {
        if (name == null) return null;
        switch (name) {
            case "Blue":
                return Blue;
            case "White":
                return White;
            case "Red":
                return Red;
            case "Yellow":
                return Yellow;
            case "Purple":
                return Purple;
            case "Green":
                return Green;
            case "Orange":
                return Orange;
            default:
                return Black;
        }
    }

    public String getColorName(){
        return colorName;
    }

    public String getUiColorName(Resources resources) {
        return resources.getString(uiColorNameCode);
    }

    public int getColor(Resources resources) {
        return resources.getColor(colorResourceCode, null);
    }

    /**
     * Parses given color integer into a tee color from the defined list. If unknown,
     * returns black color.
     * @param colorInt color in int form
     * @param resources resources of the app to load defined colors
     * @return TeeColor instance
     */
    public static TeeColor parse(int colorInt, Resources resources){
        if (colorInt == Blue.getColor(resources)) return Blue;
        if (colorInt == White.getColor(resources)) return White;
        if (colorInt == Red.getColor(resources)) return Red;
        if (colorInt == Yellow.getColor(resources)) return Yellow;
        if (colorInt == Purple.getColor(resources)) return Purple;
        if (colorInt == Green.getColor(resources)) return Green;
        if (colorInt == Orange.getColor(resources)) return Orange;
        //If unknown return black
        return Black;
    }

    public static int[] getAllCodes(Resources resources) {
        return new int[]{
                Black.getColor(resources),
                Blue.getColor(resources),
                Yellow.getColor(resources),
                White.getColor(resources),
                Red.getColor(resources),
                Purple.getColor(resources),
                Green.getColor(resources),
                Orange.getColor(resources)
        };
    }

    public boolean secondaryTextColor() {
        return (this == Black || this == Red || this == Purple);
    }
}
