package com.alag.ci.tagcloud.impl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alag.ci.tagcloud.TagCloud;
import com.alag.ci.tagcloud.TagCloudElement;
import com.alag.ci.tagcloud.VisualizeTagCloudDecorator;

public class HTMLTagCloudDecorator implements VisualizeTagCloudDecorator {

    private static final String HEADER_HTML =
            "<html><head><title>TagCloud</title></head>";
    private static final int NUM_TAGS_IN_LINE = 10;
    private Map<String, String> fontMap = null;

    public HTMLTagCloudDecorator() {
        getFontMap();
    }

    private void getFontMap() {
        this.fontMap = new HashMap<String, String>();
        fontMap.put("font-size: 0", "font-size: 13px");
        fontMap.put("font-size: 1", "font-size: 20px");
        fontMap.put("font-size: 2", "font-size: 24px");
    }

    @Override
    public String decorateTagCloud(TagCloud tagCloud) {
        StringWriter sw = new StringWriter();
        List<TagCloudElement> elements = tagCloud.getTagCloudElements();
        sw.append(HEADER_HTML);
        sw.append("<body><h3>TagClould (" + elements.size() + ")</h3>");
        int count = 0;
        for (TagCloudElement tce : elements) {
            sw.append("&nbsp;<a style=\"" + fontMap.get(tce.getFontSize()) + ";\">");
            sw.append(tce.getTagText() + "</a>&nbsp;");
            if (count++ == NUM_TAGS_IN_LINE) {
                count = 0;
                sw.append("<br />");
            }
        }
        sw.append("<br /></body></html>");
        return sw.toString();
    }

}
