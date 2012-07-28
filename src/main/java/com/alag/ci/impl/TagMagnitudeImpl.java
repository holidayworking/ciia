package com.alag.ci.impl;

import com.alag.ci.TagMagnitude;

public class TagMagnitudeImpl implements TagMagnitude {

    private Double magnitude = 0.;
    private Long tagId = null;
    private String tagText = null;

    public TagMagnitudeImpl() {
    }

    public TagMagnitudeImpl(double magnitude, long tagId, String tagText) {
        this.magnitude = magnitude;
        this.tagId = tagId;
        this.tagText = tagText;
    }

    public TagMagnitudeImpl(TagMagnitude tm) {
        this(tm.getMagnitude(),tm.getTagId(),tm.getTagText());
    }

    @Override
    public int compareTo(TagMagnitude o) {
        int retValue = 1;
        if (o != null) {
            retValue = this.magnitude.compareTo(o.getMagnitude());
        }
        return -1 * retValue;
    }

    @Override
    public Long getTagId() {
        return this.tagId;
    }

    @Override
    public String getTagText() {
        return this.tagText;
    }

    @Override
    public double getMagnitude() {
        return magnitude;
    }

    @Override
    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

}
