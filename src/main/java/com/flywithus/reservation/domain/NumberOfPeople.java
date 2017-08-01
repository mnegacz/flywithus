package com.flywithus.reservation.domain;

class NumberOfPeople {

    private final int number;

    private NumberOfPeople(int number) {
        this.number = number;
    }

    int number() {
        return number;
    }

    static NumberOfPeople of(int number) {
        return new NumberOfPeople(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberOfPeople that = (NumberOfPeople) o;

        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

}
