package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    private Long id;
    @Column (name = "model")
    private String model;
    @Column (name = "series")
    private int series;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
