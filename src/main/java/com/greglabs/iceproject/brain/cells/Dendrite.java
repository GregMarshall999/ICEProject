package com.greglabs.iceproject.brain.cells;

import java.util.ArrayList;
import java.util.List;

/**
 * A dendrite is a branch like structure of the neuron.
 * It receives information from a synapse then passes it on to the soma.
 * A dendrite can be parented to another dendrite if it branches of.
 * Inversely, a dendrite can have multiple child dendrites branching of from it.
 * The information is represented as a long value called pulse.
 * Here we simulate connections and disconnections between dendrites and synapses.
 */
public class Dendrite {
    private Synapse synapse;

    private final Dendrite parentBranch;
    private final Soma soma;
    private final List<Dendrite> childBranches = new ArrayList<>();

    private final boolean isBranch;

    private long pulse;

    public Dendrite(Soma soma) {
        parentBranch = null;
        this.soma = soma;
        isBranch = false;
    }

    public Dendrite(Dendrite parentBranch) {
        this.parentBranch = parentBranch;
        soma = null;
        isBranch = true;
    }

    public void connectToSynapse(Synapse synapse) {
        this.synapse = synapse;
    }

    public void branchOut(Synapse synapse) {
        Dendrite child = new Dendrite(this);
        child.connectToSynapse(synapse);
        childBranches.add(child);
    }
}
