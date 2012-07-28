package com.alag.ci.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alag.ci.MetaDataVector;
import com.alag.ci.TagMagnitude;

public class MetaDataVectorImpl implements MetaDataVector {

    private static final double PRECISION = 0.0001;
    private List<TagMagnitude> tagMagnitudeList = null;
    private Map<Long, TagMagnitude> tagMagnitudeMap = null;

    public MetaDataVectorImpl(List<TagMagnitude> tagMagnitudeList) {
        this.tagMagnitudeList = tagMagnitudeList;
        this.tagMagnitudeMap = new HashMap<Long, TagMagnitude>();
        for (TagMagnitude tm : this.tagMagnitudeList) {
            this.tagMagnitudeMap.put(tm.getTagId(), tm);
        }
        normalize(this.tagMagnitudeList);
        Collections.sort(this.tagMagnitudeList);
    }

    protected List<TagMagnitude> normalize(List<TagMagnitude> tmList) {
        if (tmList.size() > 0) {
            double sumSqd = 0.;
            for (TagMagnitude tm : tmList) {
                sumSqd += tm.getMagnitude() * tm.getMagnitude();
            }
            if (Math.abs(sumSqd - 1.) > PRECISION) {
                double factor ;
                if (sumSqd < PRECISION) {
                    factor = 1./ Math.sqrt(tmList.size());
                    for (TagMagnitude tm : tmList) {
                        tm.setMagnitude(factor);
                    }
                } else {
                    factor = 1./ Math.sqrt(sumSqd);
                    for (TagMagnitude tm : tmList) {
                        tm.setMagnitude(tm.getMagnitude() * factor);
                    }
                }
            }
        }
        return tmList;
    }

    @Override
    public List<TagMagnitude> getTagMetaDataMagnitude() {
        return this.tagMagnitudeList;
    }

    @Override
    public MetaDataVector add(MetaDataVector other) {
        return add(other,1);
    }

    public MetaDataVector add(MetaDataVector other, double otherScale) {
        List<TagMagnitude> otherList = other.getTagMetaDataMagnitude();
        Map<Long, TagMagnitude> mergeMap = new HashMap<Long, TagMagnitude>();
        for (TagMagnitude tm: otherList) {
            TagMagnitudeImpl newTm = new TagMagnitudeImpl(tm);
            newTm.setMagnitude(tm.getMagnitude() * otherScale);
            mergeMap.put(newTm.getTagId(), newTm);
        }

        for (Long tagId: this.tagMagnitudeMap.keySet()) {
            TagMagnitude thisTm = this.tagMagnitudeMap.get(tagId);
            TagMagnitude otherTm = mergeMap.get(tagId);
            if (otherTm == null) {
                otherTm = new TagMagnitudeImpl(thisTm);
                mergeMap.put(tagId, otherTm);
            } else {
                otherTm.setMagnitude(otherTm.getMagnitude()+ thisTm.getMagnitude());
            }
        }

        List<TagMagnitude> resultList = new ArrayList<TagMagnitude>(mergeMap.values());
        return new MetaDataVectorImpl(resultList);
    }

}
