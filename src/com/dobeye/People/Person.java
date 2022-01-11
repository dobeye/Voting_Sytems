package com.dobeye.People;

import com.dobeye.Ideology;

public abstract class Person {

    private final Ideology ideology;
    private final boolean dumb;

    public Person(Ideology ideology) {
        dumb = ideology == null;
        this.ideology = ideology;
    }

    public Person() {
        this(null);
    }

    public Ideology getIdeology() {
        return this.ideology;
    }

    public boolean isDumb() {
        return dumb;
    }
}
