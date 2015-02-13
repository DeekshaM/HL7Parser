/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vmanchala
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cf {

    private String name;
    private String segment;
    private String[] segmentQuals;
    private Integer segmentQualPos;
    private int depth;
    private List<Cf> children = new ArrayList();
    private Cf parent;

    public Cf(String paramString) {
        this.name = paramString;
    }

    public Cf(String paramString1, String paramString2) {
        this.name = paramString1;
        this.segment = paramString2;
    }

    public Cf(String paramString1, String paramString2, String paramString3, Integer paramInteger) {
        this.name = paramString1;
        this.segment = paramString2;
        this.segmentQuals = paramString3.split(",");
        this.segmentQualPos = paramInteger;
    }

    public void addChild(Cf paramCf) {
        this.depth += 1;
        this.children.add(paramCf);
        paramCf.setParent(this);
    }

    public Cf addChild(String paramString1, String paramString2) {
        Cf localCf = new Cf(paramString1, paramString2);
        this.depth += 1;
        this.children.add(localCf);
        localCf.setParent(this);
        return localCf;
    }

    public Cf addChild(String paramString1, String paramString2, String paramString3, Integer paramInteger) {
        Cf localCf = new Cf(paramString1, paramString2, paramString3, paramInteger);
        this.depth += 1;
        this.children.add(localCf);
        localCf.setParent(this);
        return localCf;
    }

    public List<Cf> childList() {
        return this.children;
    }

    public boolean hasChildren() {
        return this.children.size() > 0;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public Cf getParent() {
        return this.parent;
    }

    public String getName() {
        return this.name;
    }

    public String getSegment() {
        return this.segment;
    }

    public String[] getSegmentQuals() {
        return this.segmentQuals;
    }

    public Integer getSegmentQualPos() {
        return this.segmentQualPos;
    }

    public void setParent(Cf paramCf) {
        this.parent = paramCf;
    }

    public void setChildren(List<Cf> paramList) {
        this.children = paramList;
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            Cf localCf = (Cf) localIterator.next();
            this.depth += 1;
            localCf.setParent(this);
        }
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public void setSegment(String paramString) {
        this.segment = paramString;
    }

    public void setSegmentQuals(String[] paramArrayOfString) {
        this.segmentQuals = paramArrayOfString;
    }

    public void setSegmentQualPos(Integer paramInteger) {
        this.segmentQualPos = paramInteger;
    }

    public String toString() {
        StringBuilder dump = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            dump.append("|  ");
        }
        dump.append("+--");
        dump.append(name);
        if (segment != null) {
            dump.append(" - ").append(segment);
        }
        if (segmentQuals != null) {
            dump.append(" - ");
            for (String s : segmentQuals) {
                dump.append(s).append(",");
            }
        }
        if (segmentQualPos != null) {
            dump.append(" - ").append(segmentQualPos);
        }
        dump.append(System.getProperty("line.separator"));
        for (Cf cf : children) {
            dump.append(cf.toString());
        }
        return dump.toString();
    }
}
