package com.greglabs.iceproject;

import com.greglabs.iceproject.brain.cells.Neuron;
import com.greglabs.iceproject.lwjgl.Testing;

public class Main {
    public static void main(String[] args) {
        Neuron n = new Neuron();

        n.createDendrite();
        n.createDendrite();
        n.createDendrite();

        n.branchDendrite(0);
        n.branchDendrite(0);
        n.branchDendrite(1);
        n.branchDendrite(2);

        n.branchDendrite(0, 0);
        n.branchDendrite(0, 1);

        System.out.println(n);

        //new Testing().run();
    }
}