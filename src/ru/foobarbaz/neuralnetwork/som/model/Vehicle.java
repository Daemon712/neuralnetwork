package ru.foobarbaz.neuralnetwork.som.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import static ru.foobarbaz.neuralnetwork.common.NormalizeHelper.*;

@XmlType(propOrder = {"name", "cost", "enginePower", "engineVolume" , "mass", "capacity"} )
@XmlRootElement(name = "Vehicle")
public class Vehicle {
    private static final int MIN_COST = 20000, MAX_COST = 20000000;
    private static final float MIN_POWER = 3, MAX_POWER = 1200;
    private static final float MIN_VOLUME = 0.05f, MAX_VOLUME = 20000000;
    private static final int MIN_MASS = 70, MAX_MASS = 40000;
    private static final int MIN_CAPACITY = 1, MAX_CAPACITY = 200;

    private String name;
    private int cost;
    private float enginePower;
    private float engineVolume;
    private int mass;
    private int capacity;

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name", required = true)
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Empty name is not allowed");
        }
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    @XmlAttribute(name = "cost", required = true)
    public void setCost(int cost) {
        assertNormal(MIN_COST, cost, MAX_COST);
        this.cost = cost;
    }

    public float getEnginePower() {
        return enginePower;
    }

    @XmlAttribute(name = "engine_power", required = true)
    public void setEnginePower(float enginePower) {
        assertNormal(MIN_POWER, enginePower, MAX_POWER);
        this.enginePower = enginePower;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    @XmlAttribute(name = "engine_volume", required = true)
    public void setEngineVolume(float engineVolume) {
        assertNormal(MIN_VOLUME, engineVolume, MAX_VOLUME);
        this.engineVolume = engineVolume;
    }

    public int getMass() {
        return mass;
    }

    @XmlAttribute(name = "mass", required = true)
    public void setMass(int mass) {
        assertNormal(MIN_MASS, mass, MAX_MASS);
        this.mass = mass;
    }

    public int getCapacity() {
        return capacity;
    }

    @XmlAttribute(name = "capacity", required = true)
    public void setCapacity(int capacity) {
        assertNormal(MIN_CAPACITY, capacity, MAX_CAPACITY);
        this.capacity = capacity;
    }

    public double[] getNormalizedData(){
        return new double[]{
                normalize(MIN_COST, cost, MAX_COST),
                normalize(MIN_POWER, enginePower, MAX_POWER),
                normalize(MIN_VOLUME, engineVolume, MAX_VOLUME),
                normalize(MIN_MASS, mass, MAX_MASS),
                normalize(MIN_CAPACITY, capacity, MAX_CAPACITY),
        };
    }
}
