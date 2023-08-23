package com.greglabs.iceproject.brain.cells;

/**
 * This class represents the actions of a cell.
 * It can be active or dormant.
 * When active it can send an activated value.
 */
public abstract class CellActions implements ICellActions {
    private boolean isActive;
    private long activatedValue;

    @Override
    public void awaken() {
        isActive = true;
    }

    @Override
    public void sleep() {
        isActive = false;
    }

    @Override
    public long sendPulse() {
        return isActive ? activatedValue : 0L;
    }

    @Override
    public void preparePulse(long pulseValue) {
        activatedValue = pulseValue;
    }
}
