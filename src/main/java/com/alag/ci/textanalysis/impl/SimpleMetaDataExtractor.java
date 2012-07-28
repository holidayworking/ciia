package com.alag.ci.textanalysis.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.alag.ci.MetaDataVector;
import com.alag.ci.TagMagnitude;
import com.alag.ci.impl.MetaDataVectorImpl;
import com.alag.ci.impl.TagMagnitudeImpl;
import com.alag.ci.textanalysis.MetaDataExtractor;

public class SimpleMetaDataExtractor implements MetaDataExtractor {

    private Map<String, Long> idMap = null;
    private Long currentId = null;

    public SimpleMetaDataExtractor() {
        this.idMap = new HashMap<String, Long>();
        this.currentId = new Long(0);
    }

    @Override
    public MetaDataVector extractMetaData(String title, String body) {
        MetaDataVector titleMDV = getMetaDataVector(title);
        MetaDataVector bodyMDV = getMetaDataVector(body);
        return titleMDV.add(bodyMDV);
    }

    private MetaDataVector getMetaDataVector(String text) {
        Map<String, Integer> keywordMap = new HashMap<String, Integer>();
        StringTokenizer st = new StringTokenizer(text);
        while (st.hasMoreTokens()) {
            String token = normalizeToken(st.nextToken());
            if (acceptToken(token)) {
                Integer count = keywordMap.get(token);
                if (count == null) {
                    count = new Integer(0);
                }
                count++;
                keywordMap.put(token, count);
            }
        }
        MetaDataVector mdv = createMetaDataVector(keywordMap);
        return mdv;
    }

    protected MetaDataVector createMetaDataVector(Map<String, Integer> keywordMap) {
        List<TagMagnitude> tmList = new ArrayList<TagMagnitude>();
        for (String tagName: keywordMap.keySet()) {
            TagMagnitude tm = new TagMagnitudeImpl(
                    keywordMap.get(tagName),getTokenId(tagName),tagName);
            tmList.add(tm);
        }
        MetaDataVector mdv =  new MetaDataVectorImpl(tmList);
        return mdv;
    }

    protected String normalizeToken(String token) {
        String normalizedToken = token.toLowerCase().trim();
        if (normalizedToken.endsWith(".") || normalizedToken.endsWith(",")) {
            int size = normalizedToken.length();
            normalizedToken = normalizedToken.substring(0, size - 1);
        }
        return normalizedToken;
    }

    protected boolean acceptToken(String token) {
        return false;
    }

    private Long getTokenId(String token) {
        Long id = this.idMap.get(token);
        if (id == null) {
            id = this.currentId++;
            this.idMap.put(token, id);
        }
        return id;
    }

}
