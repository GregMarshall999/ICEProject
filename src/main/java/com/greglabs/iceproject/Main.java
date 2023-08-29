package com.greglabs.iceproject;

import com.greglabs.iceproject.brain.cells.Neuron;
import com.greglabs.iceproject.lwjgl.Testing;

public class Main {
    public static void main(String[] args) {
        Neuron n1 = new Neuron();
        Neuron n2 = new Neuron();

        n1.connectToNeuron(n2);
        n1.receiveImpulse(5L);
        n1.processActionPotential();

        System.out.println(n1);

        //new Testing().run();
    }
}