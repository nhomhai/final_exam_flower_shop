package com.flowershop.flowershop.Object;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class getFlower extends Application {
    static List<Flower> flowers;
    public static void Add(Flower flower){
        if(flowers == null)
            flowers = new ArrayList<>();
        flowers.add(flower);
    }
    public static List<Flower> Get(){
        if(flowers == null)
            flowers = new ArrayList<>();
        return flowers;
    }
}
