package com.abtest.frame.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Pair <K,V>{
    private K key;
    private V value;
}
