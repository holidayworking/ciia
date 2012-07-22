package com.alag.ci.tagcloud.impl;

import java.util.List;

import com.alag.ci.tagcloud.TagCloudElement;

public interface FontSizeComputationStrategy {
    public void computeFontSize(List<TagCloudElement> elements);
}
