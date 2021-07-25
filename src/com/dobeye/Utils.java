package com.dobeye;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Random;

public class Utils {

    public static int getRandomNumber (int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static double round (double val, int precision) {
        BigDecimal bd = BigDecimal.valueOf(val);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
