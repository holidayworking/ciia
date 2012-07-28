package com.alag.ci;

import java.util.List;

public interface MetaDataVector {
    public List<TagMagnitude> getTagMetaDataMagnitude();
    public MetaDataVector add(MetaDataVector other);
}
