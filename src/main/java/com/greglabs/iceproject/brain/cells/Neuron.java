package com.greglabs.iceproject.brain.cells;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The representation of a neuron.
 * Following a biological diagram the neuron is composed of 4 components:
 *      -Dendrites, collecting information
 *      -Soma, the body of the cell, processes information (not simulated)
 *      -Axon, a long cable like structure passing information through (not simulated)
 *      -Synapses, branched connection sending information to other neuron's dendrites
 * The information flows from the dendrites to the synapses through the components mentioned above
 * We simulate the function of a neuron with its Action Potential
 */
public class Neuron {
    private final List<Dendrite> dendrites = new ArrayList<>();
    private final List<Synapse> synapses = new ArrayList<>();

    private long actionPotential = 0L;

    public void processActionPotential() {
        //todo process information
    }

    public void createDendrite() {
        dendrites.add(new Dendrite());
    }

    public void branchDendrite(int... path) {
        branchDendrite(null, path);
    }

    private void branchDendrite(Dendrite dendrite, int... path) {
        if(path.length > 1) {
            if(dendrite == null && path[0] < dendrites.size()) {
                Dendrite d = dendrites.get(path[0]);
                branchDendrite(d, Arrays.copyOfRange(path, 1, path.length));
            }
            else {
                assert dendrite != null;
                if(path[0] < dendrite.getChildBranchesSize()) {
                    Dendrite d = dendrite.getChildDendrite(path[0]);
                    branchDendrite(d, Arrays.copyOfRange(path, 1, path.length));
                }
            }
        }
        else {
            if(dendrite == null)
                dendrites.get(path[0]).createBranch();
            else
                dendrite.getChildDendrite(path[0]).createBranch();
        }
    }
}
