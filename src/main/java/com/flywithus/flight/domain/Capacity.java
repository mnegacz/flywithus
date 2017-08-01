package com.flywithus.flight.domain;

class Capacity {

    private final int capacity;

    private Capacity(int capacity) {
        this.capacity = capacity;
    }

    public int capacity() {
        return capacity;
    }

    static Capacity of(int capacity) {
        return new Capacity(capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Capacity capacity1 = (Capacity) o;

        return capacity == capacity1.capacity;
    }

    @Override
    public int hashCode() {
        return capacity;
    }

}
