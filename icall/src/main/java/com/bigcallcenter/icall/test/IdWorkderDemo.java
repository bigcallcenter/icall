package com.bigcallcenter.icall.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sohu.idcenter.IdWorker;

public class IdWorkderDemo {
	public static void main(String[] args) {

        final long idepo = System.currentTimeMillis() - 3600 * 1000L;
        IdWorker iw = new IdWorker(1, 1, 0, idepo);
        IdWorker iw2 = new IdWorker(2, 1, 0, idepo);
        for (int i = 0; i < 10; i++) {
            System.out.println(iw.getId() + " -> " + iw2.getId());
        }
        System.out.println(iw);
        System.out.println(iw2);
        long nextId = iw.getId();
        long nextId1 = iw2.getId();
        System.out.println(nextId);
        System.out.println(nextId1);
        long time = iw.getIdTimestamp(nextId);
        long time1 = iw2.getIdTimestamp(nextId1);
        System.out.println(time+" -> "+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(time)));
        System.out.println(time1+" -> "+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(time1)));
    }
}
