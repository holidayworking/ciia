package com.alag.ci.tagcloud.impl;

import java.util.Collections;
import java.util.List;

import com.alag.ci.tagcloud.TagCloud;
import com.alag.ci.tagcloud.TagCloudElement;

public class TagCloudImpl implements TagCloud {

    private List<TagCloudElement> elements = null;

    public TagCloudImpl(List<TagCloudElement> elements,
            FontSizeComputationStrategy strategy) {
        this.elements = elements;
        strategy.computeFontSize(this.elements);
        Collections.sort(this.elements);
    }

    @Override
    public List<TagCloudElement> getTagCloudElements() {
        return this.elements;
    }

}
