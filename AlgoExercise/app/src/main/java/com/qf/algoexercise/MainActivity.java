package com.qf.algoexercise;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable
{
    private TextView tv;

    private int NUM = 500000;

    private static long countInsert, countMop;

    String strInfo = "";


    private  Handler  handler = new Handler()
{
    @Override
    public void handleMessage(Message msg)
    {
        super.handleMessage(msg);
        tv.setText(strInfo);
        Log.i("xy", "countInsert:" + countInsert + " " + "countMop" + countMop);
    }
};

    int[]  arr = new int[NUM];
    int[]  arr1 = new int[NUM];

    int timeInsertionSort, timeMopSort;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.id_tv);
        initArr();

        new Thread(this).start();


    }

    private void initArr()
    {
        countInsert = 0;
        countMop = 0;
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = arr1[i] = (int)(Math.random() * 1000000);
        }
    }

    /**
     * 选择排序实现
     * @param arr 输入量，整型数组
     */
    public static void insertionSort(int[] arr)
    {
        for (int i = 1; i < arr.length; i++)
        {
            int currentNum = arr[i];
            int k;
            for(k = i - 1; k >= 0 && arr[k] > currentNum; k--)
            {
                arr[k + 1] = arr[k];
                countInsert++;
            }
            arr[k + 1] = currentNum;
        }
    }

    /**
     * 冒泡排序实现
     * @param arr 输入量，整型数组
     */
    public static void mopSort(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = i + 1; j < arr.length; j++)
            {
                if(arr[i] > arr[j])
                {
                    countMop++;
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    @Override
    public void run()
    {
        long start = System.currentTimeMillis();
        insertionSort(arr);
        long end = System.currentTimeMillis();
        timeInsertionSort = (int) (end - start);
        String infoInsertion = "insertionSort:" + arr[0] + " " + arr[1] + " " + arr[2] + "..." + "耗时:" + timeInsertionSort + "\n";
        strInfo += infoInsertion;

        long time0 = System.currentTimeMillis();
        mopSort(arr1);
        long time1 = System.currentTimeMillis();
        timeMopSort = (int) (time1 - time0);
        String infoMop = "mopSort:" + arr1[0] + " " + arr1[1] + " " + arr1[2] + "..." + "耗时:" + timeMopSort + "\n";
        strInfo += infoMop;

        Message mes = Message.obtain();
        handler.sendEmptyMessage(1);
    }
}
