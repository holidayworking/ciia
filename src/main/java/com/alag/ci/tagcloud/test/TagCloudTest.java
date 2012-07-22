package com.alag.ci.tagcloud.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alag.ci.tagcloud.TagCloud;
import com.alag.ci.tagcloud.TagCloudElement;
import com.alag.ci.tagcloud.VisualizeTagCloudDecorator;
import com.alag.ci.tagcloud.impl.FontSizeComputationStrategy;
import com.alag.ci.tagcloud.impl.HTMLTagCloudDecorator;
import com.alag.ci.tagcloud.impl.LinearFontSizeComputationStrategy;
import com.alag.ci.tagcloud.impl.LogFontSizeComputationStrategy;
import com.alag.ci.tagcloud.impl.TagCloudElementImpl;
import com.alag.ci.tagcloud.impl.TagCloudImpl;

public class TagCloudTest {

    public static void main(String[] args) throws IOException {
        String firstString = "binary";
        int numSizes = 3;
        String fontPrefix = "font-size: ";

        List<TagCloudElement> list = new ArrayList<TagCloudElement>();
        list.add(new TagCloudElementImpl("tagging", 1));
        list.add(new TagCloudElementImpl("schema", 3));
        list.add(new TagCloudElementImpl("denormalized", 1));
        list.add(new TagCloudElementImpl("database", 2));
        list.add(new TagCloudElementImpl(firstString, 1));
        list.add(new TagCloudElementImpl("normalized", 1));

        FontSizeComputationStrategy strategy =
                new LinearFontSizeComputationStrategy(numSizes, fontPrefix);
        TagCloud cloudLinear = new TagCloudImpl(list, strategy);
        System.out.println(cloudLinear);

        strategy = new LogFontSizeComputationStrategy(numSizes, fontPrefix);
        TagCloud cloudLog = new TagCloudImpl(list, strategy);
        System.out.println(cloudLog);

        String fileName = "testTagCloudChap3.html";
        writeToFile(fileName, cloudLinear);
    }

    private static void writeToFile(String fileName, TagCloud cloud) throws IOException {
        BufferedWriter out =  new BufferedWriter(new FileWriter(fileName));
        VisualizeTagCloudDecorator decorator = new HTMLTagCloudDecorator();
        out.write(decorator.decorateTagCloud(cloud));
        out.close();
    }

}
