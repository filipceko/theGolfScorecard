package sk.filipceko.golfscorecard.data;

import android.content.res.Resources;
import sk.filipceko.golfscorecard.R;

public enum TeeColor {
    Black("Black", R.color.black),
    Blue("Blue", R.color.blue),
    White("White", R.color.white),
    Red("Red", R.color.red),
    Yellow("Yellow", R.color.yellow),
    Purple("Purple", R.color.purple),
    Green("Green", R.color.green),
    Orange("Orange", R.color.orange);

    private final String colorName;
    private final int colorResourceCode;

    TeeColor(String colorName, int colorResource){
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
