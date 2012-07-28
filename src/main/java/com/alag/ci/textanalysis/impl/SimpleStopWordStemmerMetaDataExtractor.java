package com.alag.ci.textanalysis.impl;

public class SimpleStopWordStemmerMetaDataExtractor extends
        SimpleStopWordMetaDataExtractor {

    @Override
    protected String normalizeToken(String token) {
        if (acceptToken(token)) {
            token = super.normalizeToken(token);
            if (token.endsWith("s")) {
                int index = token.lastIndexOf("s");
                if (index > 0) {
                    token = token.substring(0, index);
                }
            }
        }
        return token;
    }

}
