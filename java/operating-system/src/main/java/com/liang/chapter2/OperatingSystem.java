package com.liang.chapter2;

import java.io.*;
import java.util.ArrayList;


public class OperatingSystem {

    private final ArrayList<Integer> imgByteToWrite = new ArrayList<Integer>();

    private void readKernelFromFile(String fileName) {
        File file = new File(fileName);
        InputStream in;

        try {
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                imgByteToWrite.add(tempbyte);
            }
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }

        imgByteToWrite.add(0x55);
        imgByteToWrite.add(0xaa);
        imgByteToWrite.add(0xf0);
        imgByteToWrite.add(0xff);
        imgByteToWrite.add(0xff);

    }

    public OperatingSystem() {

        readKernelFromFile("D:\\workspace\\personal\\1\\lyh-os\\asm\\boot\\chapter2\\boot.bin");

        int len = 0x168000;
        int curSize = imgByteToWrite.size();
        for (int l = 0; l < len - curSize; l++) {
            imgByteToWrite.add(0);
        }

    }

    public void makeFllopy()   {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("img/system_c2.img"));
            for (Integer integer : imgByteToWrite) {
                out.writeByte(integer.byteValue());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        OperatingSystem op = new OperatingSystem();
        op.makeFllopy();
    }
}
