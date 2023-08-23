package com.greglabs.iceproject.brain.cells;

/**
 * We represent the cell actions with these functions.
 * A cell can sleep, awake from sleep, send a pulse, prepare a pulse.
 */
public interface ICellActions {
    void awaken();
    void sleep();
    long sendPulse();
    void preparePulse(long pulseValue);
}
