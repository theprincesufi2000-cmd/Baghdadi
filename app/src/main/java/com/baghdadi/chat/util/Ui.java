package com.baghdadi.chat.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public final class Ui {
    private Ui() {}
    public static int dp(Context c,float v){return (int)(v*c.getResources().getDisplayMetrics().density+0.5f);}
    public static TextView text(Context c,String s,float size,int color){TextView v=new TextView(c);v.setText(s);v.setTextSize(size);v.setTextColor(color);v.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);return v;}
    public static GradientDrawable bg(int color,float r){GradientDrawable g=new GradientDrawable();g.setColor(color);g.setCornerRadius(dpStatic(r));return g;}
    private static float dpStatic(float v){return v*android.content.res.Resources.getSystem().getDisplayMetrics().density;}
    public static GradientDrawable gradient(){GradientDrawable g=new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{Color.rgb(124,58,237),Color.rgb(8,145,178)});g.setCornerRadius(dpStatic(16));return g;}
    public static Button button(Context c,String label){Button b=new Button(c);b.setText(label);b.setTextColor(Color.WHITE);b.setAllCaps(false);b.setTextSize(14);b.setPadding(dp(c,8),0,dp(c,8),0);b.setMinHeight(dp(c,44));return b;}
    public static void margin(View v,int l,int t,int r,int b,Context c){if(v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){ViewGroup.MarginLayoutParams p=(ViewGroup.MarginLayoutParams)v.getLayoutParams();p.setMargins(dp(c,l),dp(c,t),dp(c,r),dp(c,b));v.setLayoutParams(p);}}
}
