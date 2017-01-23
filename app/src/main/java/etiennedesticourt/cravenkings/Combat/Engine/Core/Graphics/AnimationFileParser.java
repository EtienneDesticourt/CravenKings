package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class AnimationFileParser {
    private int numFramesTotal, numFramesX, numFramesY;
    private int offsetX, offsetY;
    private boolean looping;

    private AnimationFileParser(){
    }

    public static AnimationFileParser parse(InputStream animation)
            throws ParserConfigurationException, IOException, SAXException {
        //Try to parse the xml in the file
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(animation);

        //Get the relevant nodes
        Element root = doc.getDocumentElement();
        Node numFramesTotalNode = root.getElementsByTagName("num_frames_total").item(0);
        Node numFramesXNode     = root.getElementsByTagName("num_frames_x").item(0);
        Node numFramesYNode     = root.getElementsByTagName("num_frames_y").item(0);
        Node offsetXNode     = root.getElementsByTagName("offset_x").item(0);
        Node offsetYNode     = root.getElementsByTagName("offset_y").item(0);
        Node loopingNode        = root.getElementsByTagName("looping").item(0);

        //Parse node values
        int numFramesTotal = Integer.parseInt(numFramesTotalNode.getTextContent());
        int numFramesX = Integer.parseInt(numFramesXNode.getTextContent());
        int numFramesY = Integer.parseInt(numFramesYNode.getTextContent());
        int offsetX = Integer.parseInt(offsetXNode.getTextContent());
        int offsetY = Integer.parseInt(offsetYNode.getTextContent());
        boolean looping = Boolean.parseBoolean(loopingNode.getTextContent());


        AnimationFileParser instance = new AnimationFileParser();
        instance.setNumFramesTotal(numFramesTotal);
        instance.setNumFramesX(numFramesX);
        instance.setNumFramesY(numFramesY);
        instance.setLooping(looping);
        instance.setOffsetX(offsetX);
        instance.setOffsetY(offsetY);

        return instance;
    }

    public int getNumFramesTotal() {
        return numFramesTotal;
    }

    public void setNumFramesTotal(int numFramesTotal) {
        this.numFramesTotal = numFramesTotal;
    }

    public int getNumFramesX() {
        return numFramesX;
    }

    public void setNumFramesX(int numFramesX) {
        this.numFramesX = numFramesX;
    }

    public int getNumFramesY() {
        return numFramesY;
    }

    public void setNumFramesY(int numFramesY) {
        this.numFramesY = numFramesY;
    }

    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetX(int offset) {
        this.offsetX = offset;
    }

    public void setOffsetY(int offset) {
        this.offsetY = offset;
    }
}
