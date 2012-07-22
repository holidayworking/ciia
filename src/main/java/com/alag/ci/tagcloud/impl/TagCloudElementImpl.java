package com.alag.ci.tagcloud.impl;

import com.alag.ci.tagcloud.TagCloudElement;

public class TagCloudElementImpl implements TagCloudElement {

    private String fontSize = null;
    private Double weight = null;
    private String tagText = null;

    public TagCloudElementImpl(String tagText, double tagCount) {
        this.tagText = tagText;
        this.weight = tagCount;
    }

    @Override
    public int compareTo(TagCloudElement o) {
        return this.tagText.compareTo(o.getTagText());
    }

    @Override
    public String getTagText() {
        return this.tagText;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getFontSize() {
        return this.fontSize;
    }

    @Override
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

}
