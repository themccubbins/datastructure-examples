package org.cubbs;

public class Key implements Comparable<Key> {

    public String name;
    public double cost;


    public Key(String name, double value) {
        this.name = name;
        this.cost = value;
    }

    public int compareTo(Key o) {
        if (cost ==o.cost) return name.compareTo(o.name);
        return Double.compare(cost, o.cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if (Double.compare(key.cost, cost) != 0) return false;
        return name != null ? name.equals(key.name) : key.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Key{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
