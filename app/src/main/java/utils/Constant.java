package utils;

import com.example.model.CartProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Constant {
    public static final String SELECTED_ITEM = "SelectedProduct";
    public static final String SELECTED_BLOG = "SelectedBlog";
    public static final String SELECTED_HOME_BLOG="SelectedHomeBlog";
    public static final String SELECTED_ORDER = "SelectedOrder";
    public static ArrayList<CartProduct> arrCartProduct;
    public static double PHI_SHIP = 35000;
    public static double PHI_SHIP_NHANH = 45000;
    public static int shipping_method = 0;
    public static DecimalFormat decimalFormat = new DecimalFormat("###,###,### đ");
}
