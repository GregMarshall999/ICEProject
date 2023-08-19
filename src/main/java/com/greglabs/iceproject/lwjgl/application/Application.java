package com.greglabs.iceproject.lwjgl.application;

public abstract class Application implements IApplication {
    protected abstract void init();
    protected abstract void loop();
}
