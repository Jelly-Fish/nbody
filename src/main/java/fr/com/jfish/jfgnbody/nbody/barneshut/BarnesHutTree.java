package fr.com.jfish.jfgnbody.nbody.barneshut;

import fr.com.jfish.jfgnbody.nbody.entities.Body;

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
    private final BHTCube bhtcube;
    
    // BarnerHut 3D trees.
    BarnesHutTree NWly;
    BarnesHutTree NEly;
    BarnesHutTree SWly;
    BarnesHutTree SEly;
    BarnesHutTree NWhy;
    BarnesHutTree NEhy;
    BarnesHutTree SWhy;
    BarnesHutTree SEhy;   
    
    /**
     * Create and initialize a new bhtree - Initially, all nodes are null and will be filled by recursion,
     * each BHTree represents a bhtcube and a body that represents all bodies inside the bhtcube.
     * @param bhtc BHTCube
     */
    public BarnesHutTree(final BHTCube bhtc) {
        this.bhtcube = bhtc;
        this.b = null;
        this.NWly = null;
        this.NEly = null;
        this.SWly = null;
        this.SEly = null;
        this.NWhy = null;
        this.NEhy = null;
        this.SWhy = null;
        this.SEhy = null;
    }

    /**
     * If all nodes of the BHTree are null, then the bhtcube represents a 
     * single body and it is "external".
     * @param t
     * @return true if so.
     */
    public Boolean isExternal(final BarnesHutTree t) {
        return t.NWly == null && t.NEly == null && t.SWly == null && t.SEly == null &&
            t.NWhy == null && t.NEhy == null && t.SWhy == null && t.SEhy == null;
    }
    
    /**
     * We have to populate the tree with bodies,
     * start at the current tree and recursively travel through the branches.
     * 3Dv.
     * Barnes–Hut simulation :
     * @param body body.
     */
    public void insert(final Body body) {
        
        Body c = null;
        boolean external = false;
        
        // If there's not a body there already, put the body there.
        if (this.b == null) {
            this.b = body;
        } else {
            
            external = this.isExternal(this);
            
            if (external) {
                
                /**
                 * If there's already a body there, but it's not an external node,
                 * combine the two bodies and figure out which bhtcube of the 
                 * tree it should be located in - then recursively update the 
                 * nodes below it.
                 */                
                this.b = body.add(this.b, body);
                
            } else {
                
                /**
                 * If the node is external and contains another body, create BHTrees
                 * where the bodies should go, update the node, and end (do not do anything recursively).
                 */
                c = this.b;
                
            }
            
            final BHTCube nWly = this.bhtcube.getSubBHTCube(BHTCube.Cardinality.NW_LOW_Y);                    
            if (body.in(nWly)) {                
                if (this.NWly == null) this.NWly = new BarnesHutTree(nWly);
                NWly.insert(external ? c : body);
            } else {                
                final BHTCube nEly = this.bhtcube.getSubBHTCube(BHTCube.Cardinality.NE_LOW_Y);
                if (body.in(nEly)) {
                    if (this.NEly == null) this.NEly = new BarnesHutTree(nEly);
                    NEly.insert(external ? c : body);
                } else {
                    final BHTCube sEly = this.bhtcube.getSubBHTCube(BHTCube.Cardinality.SE_LOW_Y);
                    if (body.in(sEly)) {
                        if (this.SEly == null) this.SEly = new BarnesHutTree(sEly);
                        SEly.insert(external ? c : body);
                    } else {
                        final BHTCube sWly = this.bhtcube.getSubBHTCube(BHTCube.Cardinality.SW_LOW_Y);                        
                        if (body.in(sWly)) {
                            if (this.SWly == null) this.SWly = new BarnesHutTree(sWly);
                            SWly.insert(external ? c : body);
                        } else {
                            final BHTCube nWhy = this.bhtcube.getSubBHTCube(BHTCube.Cardinality.NW_HIGH_Y);
                            if (body.in(nWhy)) {
                                if (this.NWhy == null) this.NWhy = new BarnesHutTree(nWhy);
                                NWhy.insert(external ? c : body);
                            } else {
                                final BHTCube nEhy = this.bhtcube.getSubBHTCube(BHTCube.Cardinality.NE_HIGH_Y);
                                if (body.in(nEhy)) {
                                    if (this.NEhy == null) this.NEhy = new BarnesHutTree(nEhy);
                                    NEhy.insert(external ? c : body);
                                } else {
                                    final BHTCube sEhy = this.bhtcube.getSubBHTCube(BHTCube.Cardinality.SE_HIGH_Y);
                                    if (body.in(sEhy)) {
                                        if (this.SEhy == null) this.SEhy = new BarnesHutTree(sEhy);
                                        SEhy.insert(external ? c : body);
                                    } else {
                                        final BHTCube sWhy = this.bhtcube.getSubBHTCube(BHTCube.Cardinality.SW_HIGH_Y);
                                        if (this.SWhy == null) this.SWhy = new BarnesHutTree(sWhy);
                                        SWhy.insert(external ? c : body);
                                    }
                                }
                            }
                        }
                    }
                }
            }            
            
            if (external) this.insert(body);
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
        } else if (this.bhtcube.l / (this.b.distanceTo(b)) < 2) {
            b.addForce(this.b);
        } else {
            if (this.NWly != null) this.NWly.updateForce(b);
            if (this.SWly != null) this.SWly.updateForce(b);
            if (this.SEly != null) this.SEly.updateForce(b);
            if (this.NEly != null) this.NEly.updateForce(b);            
            if (this.NWhy != null) this.NWhy.updateForce(b);
            if (this.SWhy != null) this.SWhy.updateForce(b);
            if (this.SEhy != null) this.SEhy.updateForce(b);
            if (this.NEhy != null) this.NEhy.updateForce(b);
        }
    }

    /**
     * @param b Body to check for collision with this.b.
     */
    public void checkCollision(final Body b) {
        
        if (this.isExternal(this)) {
            if (this.b != b) b.checkCollision(this.b);
        } else if (this.bhtcube.l / (this.b.distanceTo(b)) < 2) {
            b.checkCollision(this.b);
        }
    }
    
}
