package com.jellyfish.jfgnbody.nbody.barneshut;

import com.jellyfish.jfgnbody.nbody.Body;

/**
 *
 * @author thw
 */
public class BarnesHutTree {
    
    /**
     * body or aggregate body stored in this node.
     */
    private Body b;
    
    /**
     * square region that the tree represents.
     */
    private final Quadrant quadrant;
    
    // BarnerHut trees.
    BarnesHutTree NW;
    BarnesHutTree NE;
    BarnesHutTree SW;
    BarnesHutTree SE;

    /**
     * Create and initialize a new bhtree - Initially, all nodes are null and will be filled by recursion,
     * each BHTree represents a quadrant and a body that represents all bodies inside the quadrant.
     * @param q Quadrant
     */
    public BarnesHutTree(final Quadrant q) {
        this.quadrant = q;
        this.b = null;
        this.NW = null;
        this.NE = null;
        this.SW = null;
        this.SE = null;
    }

    /**
     * If all nodes of the BHTree are null, then the quadrant represents a 
     * single body and it is "external".
     * @param t
     * @return true if so.
     */
    public Boolean isExternal(final BarnesHutTree t) {
        return t.NW == null && t.NE == null && t.SW == null && t.SE == null;
    }

    /**
     * We have to populate the tree with bodies,
     * start at the current tree and recursively travel through the branches.
     * @param b
     */
    public void insert(final Body b) {
        
        // If there's not a body there already, put the body there.
        if (this.b == null) {
            this.b = b;
        } else if (!this.isExternal(this)) {
            
            /**
             * If there's already a body there, but it's not an external node,
             * combine the two bodies and figure out which quadrant of the 
             * tree it should be located in - 
             * then recursively update the nodes below it.
             */
            
            this.b = b.add(this.b, b);
            
            final Quadrant nW = this.quadrant.getSubQuadrant(Quadrant.Cardinality.NW);
                    
            if (b.in(nW)) {
                
                if (this.NW == null) this.NW = new BarnesHutTree(nW);
                NW.insert(b);
            } else {
                
                final Quadrant nE = this.quadrant.getSubQuadrant(Quadrant.Cardinality.NE);
                if (b.in(nE)) {
                    if (this.NE == null) this.NE = new BarnesHutTree(nE);
                    NE.insert(b);
                } else {
                    final Quadrant sE = this.quadrant.getSubQuadrant(Quadrant.Cardinality.SE);
                    if (b.in(sE)) {
                        if (this.SE == null) this.SE = new BarnesHutTree(sE);
                        SE.insert(b);
                    } else {
                        Quadrant sW = this.quadrant.getSubQuadrant(Quadrant.Cardinality.SW);
                        if (this.SW == null) this.SW = new BarnesHutTree(sW);
                        SW.insert(b);
                    }
                }
            }
        } else if (this.isExternal(this)) {
            
            /**
             * If the node is external and contains another body, create BHTrees
             * where the bodies should go, update the node, and end (do not do anything recursively).
             */
            
            Body c = this.b;
            final Quadrant nW = this.quadrant.getSubQuadrant(Quadrant.Cardinality.NW);
            if (c.in(nW)) {
                if (this.NW == null) this.NW = new BarnesHutTree(nW);
                NW.insert(c);
            } else {
                final Quadrant nE = this.quadrant.getSubQuadrant(Quadrant.Cardinality.NE);
                if (c.in(nE)) {
                    if (this.NE == null) this.NE = new BarnesHutTree(nE);
                    NE.insert(c);
                } else {
                    final Quadrant sE = this.quadrant.getSubQuadrant(Quadrant.Cardinality.SE);
                    if (c.in(sE)) {
                        if (this.SE == null) this.SE = new BarnesHutTree(sE);
                        SE.insert(c);
                    } else {
                        final Quadrant sW = this.quadrant.getSubQuadrant(Quadrant.Cardinality.SW);
                        if (this.SW == null) this.SW = new BarnesHutTree(sW);
                        SW.insert(c);
                    }
                }
            }
            this.insert(b);
        }
    }
    
    /**
     * Start at the main node of the tree - Then, recursively go each branch
     * Until either we reach an external node or we reach a node that is sufficiently
     * far away that the external nodes would not matter much.
     * @param b Body to update.
     */
    public void updateForce(final Body b) {
        
        if (this.isExternal(this)) {
            if (this.b != b) b.addForce(this.b);
        } else if (this.quadrant.l / (this.b.distanceTo(b)) < 2) {
            b.addForce(this.b);
        } else {
            if (this.NW != null) this.NW.updateForce(b);
            if (this.SW != null) this.SW.updateForce(b);
            if (this.SE != null) this.SE.updateForce(b);
            if (this.NE != null) this.NE.updateForce(b);
        }
    }
    
}
