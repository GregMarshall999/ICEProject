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
    private final List<Branch> dendrites = new ArrayList<>();
    private final List<Branch> synapses = new ArrayList<>();

    private long actionPotential = 0L;

    public void processActionPotential() {
        for(Branch s : synapses) {
            s.sendPulse(actionPotential);
        }
    }

    public void receiveImpulse(long impulse) {
        actionPotential = impulse;
    }

    public void connectToNeuron(Neuron n) {
        Branch synapse = createSynapse();
        Branch dendrite = n.createDendrite();

        synapse.connectToBranch(dendrite);
    }

    private Branch createDendrite() {
        Branch b = new Branch(this);
        dendrites.add(b);
        return b;
    }

    private Branch createSynapse() {
        Branch b = new Branch(this);
        synapses.add(b);
        return b;
    }

    public void branchDendrite(int... path) {
        branchDendrite(dendrites, null, path);
    }

    public void branchSynapse(int... path) {
        branchDendrite(synapses, null, path);
    }

    private void branchDendrite(List<Branch> branches, Branch branch, int... path) {
        if(path.length > 1) {
            if(branch == null && path[0] < branches.size()) {
                Branch b = branches.get(path[0]);
                branchDendrite(branches, b, Arrays.copyOfRange(path, 1, path.length));
            }
            else {
                assert branch != null;
                if(path[0] < branch.getChildBranchesSize()) {
                    Branch b = branch.getChildDendrite(path[0]);
                    branchDendrite(branches, b, Arrays.copyOfRange(path, 1, path.length));
                }
            }
        }
        else {
            if(branch == null)
                branches.get(path[0]).createBranch();
            else
                branch.getChildDendrite(path[0]).createBranch();
        }
    }
}
