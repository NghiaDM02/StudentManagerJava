package studentmanager;

import java.io.Serializable;

/**
 * Student
 */
public class Student implements Serializable{

	private String id;
	private String name;
	private Date date;
	private String address;
	private float score;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDate() {
        return date;
    }
	
    public String scoreToString() {
        return "" + score;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
}
