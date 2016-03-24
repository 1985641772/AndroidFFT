package com.qf.algoexercise;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by 王超 on 2016/3/14.
 */
public class MemoryActivity extends Activity implements Runnable
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        for (int k = 0; k < 1000; k++)
        {
            int[] arr = new int[6000000];
        }

    }
}
