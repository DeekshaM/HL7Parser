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

public class Loop
        implements Iterable<Segment> {

    private static final long serialVersionUID = 1L;
    private Context context;
    private String name;
    private List<Segment> segments = new ArrayList();
    private List<Loop> loops = new ArrayList();
    private Loop parent;
    private int depth;

    public Loop(Context paramContext, String paramString) {
        this.context = paramContext;
        this.name = paramString;
        this.parent = null;
    }

    public Loop addChild(String paramString) {
        Loop localLoop = new Loop(this.context, paramString);
        localLoop.setParent(this);
        this.depth += 1;
        this.loops.add(localLoop);
        return localLoop;
    }

    public void addChild(int paramInt, Loop paramLoop) {
        paramLoop.setParent(this);
        this.depth += 1;
        this.loops.add(paramInt, paramLoop);
    }

    public Segment addSegment() {
        Segment localSegment = new Segment(this.context);
        this.segments.add(localSegment);
        return localSegment;
    }

    public Segment addSegment(String paramString) {
        Segment localSegment = new Segment(this.context);
        String[] arrayOfString = paramString.split("\\" + this.context.getElementSeparator());
        localSegment.addElements(arrayOfString);
        this.segments.add(localSegment);
        return localSegment;
    }

    public void addSegment(Segment paramSegment) {
        this.segments.add(paramSegment);
    }

    public Segment addSegment(int paramInt) {
        Segment localSegment = new Segment(this.context);
        this.segments.add(paramInt, localSegment);
        return localSegment;
    }

    public Segment addSegment(int paramInt, String paramString) {
        Segment localSegment = new Segment(this.context);
        String[] arrayOfString = paramString.split("\\" + this.context.getElementSeparator());
        localSegment.addElements(arrayOfString);
        this.segments.add(paramInt, localSegment);
        return localSegment;
    }

    public void addSegment(int paramInt, Segment paramSegment) {
        this.segments.add(paramInt, paramSegment);
    }

    public Loop addChild(int paramInt, String paramString) {
        Loop localLoop = new Loop(this.context, paramString);
        localLoop.setParent(this);
        this.depth += 1;
        this.loops.add(paramInt, localLoop);
        return localLoop;
    }

    public boolean hasLoop(String paramString) {
        Iterator localIterator = childList().iterator();
        while (localIterator.hasNext()) {
            Loop localLoop = (Loop) localIterator.next();
            if (paramString.equals(localLoop.getName())) {
                return true;
            }
            if (localLoop.hasLoop(paramString)) {
                return true;
            }
        }
        return false;
    }

    public List<Loop> findLoop(String paramString) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = childList().iterator();
        while (localIterator.hasNext()) {
            Loop localLoop = (Loop) localIterator.next();
            if (paramString.equals(localLoop.getName())) {
                localArrayList.add(localLoop);
            }
            List localList = localLoop.findLoop(paramString);
            if (localList.size() > 0) {
                localArrayList.addAll(localList);
            }
        }
        return localArrayList;
    }

    public List<Segment> findSegment(String paramString) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = this.segments.iterator();
        Object localObject;
        while (localIterator.hasNext()) {
            localObject = (Segment) localIterator.next();
            if (paramString.equals(((Segment) localObject).getElement(0))) {
                localArrayList.add(localObject);
            }
        }
        localIterator = childList().iterator();
        while (localIterator.hasNext()) {
            localObject = (Loop) localIterator.next();
            List localList = ((Loop) localObject).findSegment(paramString);
            if (localList.size() > 0) {
                localArrayList.addAll(localList);
            }
        }
        return localArrayList;
    }

    public Context getContext() {
        return this.context;
    }

    public Loop getLoop(int paramInt) {
        return (Loop) this.loops.get(paramInt);
    }

    public List<Loop> getLoops() {
        return this.loops;
    }

    public Loop getParent() {
        return this.parent;
    }

    public Segment getSegment(int paramInt) {
        return (Segment) this.segments.get(paramInt);
    }

    public List<Segment> getSegments() {
        return this.segments;
    }

    public String getName() {
        return this.name;
    }

    public Iterator<Segment> iterator() {
        return this.segments.iterator();
    }

    public Loop removeLoop(int paramInt) {
        return (Loop) this.loops.remove(paramInt);
    }

    public Segment removeSegment(int paramInt) {
        return (Segment) this.segments.remove(paramInt);
    }

    public List<Loop> childList() {
        return this.loops;
    }

    public int size() {
        int i = 0;
        i = this.segments.size();
        Iterator localIterator = childList().iterator();
        while (localIterator.hasNext()) {
            Loop localLoop = (Loop) localIterator.next();
            i += localLoop.size();
        }
        return i;
    }

    public void setContext(Context paramContext) {
        this.context = paramContext;
    }

    public Loop setChild(int paramInt, String paramString) {
        Loop localLoop = new Loop(this.context, paramString);
        localLoop.setParent(this);
        this.depth += 1;
        this.loops.set(paramInt, localLoop);
        return localLoop;
    }

    public void setChild(int paramInt, Loop paramLoop) {
        paramLoop.setParent(this);
        this.depth += 1;
        this.loops.set(paramInt, paramLoop);
    }

    public void setParent(Loop paramLoop) {
        this.parent = paramLoop;
    }

    public Segment setSegment(int paramInt) {
        Segment localSegment = new Segment(this.context);
        this.segments.set(paramInt, localSegment);
        return localSegment;
    }

    public Segment setSegment(int paramInt, String paramString) {
        Segment localSegment = new Segment(this.context);
        String[] arrayOfString = paramString.split("\\" + this.context.getElementSeparator());
        localSegment.addElements(arrayOfString);
        this.segments.set(paramInt, localSegment);
        return localSegment;
    }

    public void setSegment(int paramInt, Segment paramSegment) {
        this.segments.set(paramInt, paramSegment);
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public String toString() {
        return toString(false);
    }

    public String toString(boolean paramBoolean) {
        StringBuilder localStringBuilder = new StringBuilder();
        Iterator localIterator = this.segments.iterator();
        Object localObject;
        while (localIterator.hasNext()) {
            localObject = (Segment) localIterator.next();
            localStringBuilder.append(((Segment) localObject).toString(paramBoolean));
            localStringBuilder.append(this.context.getSegmentSeparator());
        }
        localIterator = childList().iterator();
        while (localIterator.hasNext()) {
            localObject = (Loop) localIterator.next();
            localStringBuilder.append(((Loop) localObject).toString(paramBoolean));
        }
        return localStringBuilder.toString();
    }

    public String toXML() {
        return toXML(false);
    }

    public String toXML(boolean paramBoolean) {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("<LOOP NAME=\"").append(this.name).append("\">");
        Iterator localIterator = this.segments.iterator();
        Object localObject;
        while (localIterator.hasNext()) {
            localObject = (Segment) localIterator.next();
            localStringBuilder.append(((Segment) localObject).toXML(paramBoolean));
        }
        localIterator = childList().iterator();
        while (localIterator.hasNext()) {
            localObject = (Loop) localIterator.next();
            localStringBuilder.append(((Loop) localObject).toXML(paramBoolean));
        }
        localStringBuilder.append("</LOOP>");
        return localStringBuilder.toString();
    }

    public int getDepth() {
        return this.depth;
    }
}
