package ru.foobarbaz.neuralnetwork.som.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(propOrder = { "data" })
@XmlRootElement(name = "DataSet")
public class DataSet {
    private List<Vehicle> data;

    @XmlAttribute(name = "data", required = true)
    public void setData(List<Vehicle> data) {
        this.data = data;
    }

    public List<Vehicle> getData() {
        return data;
    }
}
