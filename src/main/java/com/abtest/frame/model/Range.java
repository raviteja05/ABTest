package com.abtest.frame.model;

import lombok.Builder;
import lombok.Setter;

@Builder
public class Range {
    private int min;
    private int max;

    public boolean testRange(int number){
        return number>=min&&number<=max;
    }
}
