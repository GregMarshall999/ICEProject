package com.greglabs.iceproject.brain.cells;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a branch like structure of the neuron.
 * It can receive information or send information depending on its configuration.
 * Dendrite mode is to receive only, Synapse mode is to send only
 * It can be parented to another branch.
 * Inversely, it can have multiple children branching off from it.
 * Here we simulate connections and disconnections between branches.
 */
public class Branch {
    private Branch connection;

    private final Neuron neuron;
    private final Branch parentBranch;
    private final List<Branch> childBranches = new ArrayList<>();

    public Branch(Neuron neuron) {
        this.neuron = neuron;
        parentBranch = null;
    }

    public Branch(Branch parentBranch) {
        neuron = null;
        this.parentBranch = parentBranch;
    }

    public void connectToBranch(Branch connection) {
        this.connection = connection;
        connection.connection = this;
    }

    public void sendPulse(long impulse) {
        if(connection.parentBranch != null) {
            connection.parentBranch.sendPulse(impulse);
        }
        else if (connection.neuron != null) {
            connection.neuron.receiveImpulse(impulse);
        }
    }

    public void createBranch() {
        Branch child = new Branch(this);
        childBranches.add(child);
    }

    public int getChildBranchesSize() {
        return childBranches.size();
    }

    public Branch getChildDendrite(int index) {
        return childBranches.get(index);
    }
}
