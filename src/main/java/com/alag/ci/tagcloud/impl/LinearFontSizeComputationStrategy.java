package com.alag.ci.tagcloud.impl;


public class LinearFontSizeComputationStrategy extends
        FontSizeComputationStrategyImpl {

    public LinearFontSizeComputationStrategy(int numSizes, String prefix) {
        super(numSizes, prefix);
    }

    @Override
    protected double scaleCount(double count) {
        return count;
    }

}
