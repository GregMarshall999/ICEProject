package com.greglabs.iceproject.brain.cells;

import java.util.ArrayList;
import java.util.List;

/**
 * A dendrite is a branch like structure of the neuron.
 * It receives information from a synapse then passes it on to the soma.
 * A dendrite can be parented to another dendrite if it branches off.
 * Inversely, a dendrite can have multiple child dendrites branching off from it.
 * Here we simulate connections and disconnections between dendrites and synapses.
 */
public class Dendrite {
    private Synapse synapse;

    private final Dendrite parentBranch;
    private final List<Dendrite> childBranches = new ArrayList<>();

    public Dendrite() {
        parentBranch = null;
    }

    public Dendrite(Dendrite parentBranch) {
        this.parentBranch = parentBranch;
    }

    public void connectToSynapse(Synapse synapse) {
        this.synapse = synapse;
    }

    public void createBranch() {
        Dendrite child = new Dendrite(this);
        childBranches.add(child);
    }

    public int getChildBranchesSize() {
        return childBranches.size();
    }

    public Dendrite getChildDendrite(int index) {
        return childBranches.get(index);
    }
}
