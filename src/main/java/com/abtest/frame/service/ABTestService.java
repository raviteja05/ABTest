package com.abtest.frame.service;

import com.abtest.frame.exception.VariantPercentageOutOfBoundsException;
import com.abtest.frame.model.Pair;
import com.abtest.frame.model.Range;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class ABTestService {

    public String getRandomVariant(Map<String,Integer> variants) throws VariantPercentageOutOfBoundsException {
        Random random =new Random();
        String variant=getVariantBasedOnValue(random.nextInt(101),getVariantRangePairList(variants));

        return variant;

    }
    private List<Pair<Range,String>> getVariantRangePairList(Map<String,Integer> variants) throws VariantPercentageOutOfBoundsException {
        List<Pair<Range,String>> variantRangePairList = new ArrayList<>();
        int init=1,temp=0;
        int sum=0;
        for(String variant:variants.keySet()){
            Range range=Range.builder().min(init).max(temp+variants.get(variant)).build();
            variantRangePairList.add(Pair.<Range,String>builder().key(range).value(variant).build());
            init=variants.get(variant);
            temp=temp+variants.get(variant);
            sum+=variants.get(variant);
            if(variants.get(variant)<0){
                throw new VariantPercentageOutOfBoundsException("Variant percentage cannot be negative.");
            }
            if(sum>100){
                throw new VariantPercentageOutOfBoundsException("Sum of variant percentages cannot be greater than 100");
            }

        }
        return variantRangePairList;

    }
    private String getVariantBasedOnValue(int randomNumber, List<Pair<Range, String>> variantRangeMap){
        for(Pair<Range,String> pair:variantRangeMap){
            if(pair.getKey().testRange(randomNumber)){
                return pair.getValue();
            }
        }
        return null;

    }
}
