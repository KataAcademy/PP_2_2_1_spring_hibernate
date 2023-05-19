package hiber.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "User_id", referencedColumnName = "id")
    private User user;
    @Column(name = "model")
    private String model;
    @Column(name = "serial")
    private int serial;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
    public Car() {
    }

    public Car(String model, int serial) {
        this.model = model;
        this.serial = serial;
    }



    @Override
    public String toString() {
        return "Car{" +
                "user=" + user +
                ", model='" + model + '\'' +
                ", serial=" + serial +
                '}';
    }


}
