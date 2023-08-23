package com.greglabs.iceproject.brain.cells;

import java.util.ArrayList;
import java.util.List;

public class Neuron {
    private boolean isActive;
    private long activatedValue;

    private Axon axon;

    private List<Dendrite> dendrites = new ArrayList<>();
}
