package ru.foobarbaz.neuralnetwork.som.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType
@XmlRootElement(name = "DataSet")
public class DataSet {
    private List<Vehicle> data;

    @XmlElement(name = "Vehicle", required = true)
    public void setData(List<Vehicle> data) {
        this.data = data;
    }

    public List<Vehicle> getData() {
        return data;
    }
}
