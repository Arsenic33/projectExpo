package org.test.core.service;

import org.test.core.Stall;

import java.util.ArrayList;
import java.util.List;

public class StallServiceImpl implements StallService
{
    @Override
    public List<Stall> getStalls() {
        List<Stall> stallList = new ArrayList<>();

        for(int index = 1; index < 10; index++){
            Stall stall = new Stall();
            stall.setTitle("Stall " + index);

            stall.setDescription("This is a stall conducted bu Department of Computer Science & Statistics that related with IOT. You can get a good knowledge about IOT related applications.");
            stallList.add(stall);
        }
        return stallList;
    }
}