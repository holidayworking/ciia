package com.alag.ci.textanalysis.impl;

import java.util.HashMap;
import java.util.Map;

public class SimpleStopWordMetaDataExtractor extends SimpleMetaDataExtractor {

    private static final String[] STOP_WORD =
        {"and", "of", "the", "to", "is", "their", "can", "all", ""};
    private Map<String, String> stopWordMap = null;

    public SimpleStopWordMetaDataExtractor() {
        this.stopWordMap = new HashMap<String, String>();
        for (String s : STOP_WORD) {
            this.stopWordMap.put(s, s);
        }
    }

    @Override
    protected boolean acceptToken(String token) {
        return !this.stopWordMap.containsKey(token);
    }

}
