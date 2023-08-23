package com.greglabs.iceproject.brain.cells;

import java.util.HashMap;
import java.util.Map;

/**
 * Dendrites are like tree branches.
 * They collect the information from synapses to pass it on to the Soma.
 */
public class Dendrite extends CellActions {
    private Map<Dendrite, Synapse> branches = new HashMap<>();

    public Map<Dendrite, Synapse> getBranches() {
        return branches;
    }

    public void addBranch(Dendrite dendrite, Synapse synapse) {
        branches.put(dendrite, synapse);
    }
}
