package com.example.nbateamsactivity.testTables;




import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "TestPlayerTable")
public class TestPlayerTable extends Model {

    @Column(name="p_id")
    public int p_id;

    @Column(name="teamId")
    public int teamId;

    @Column(name="firstName")
    public String firstName;

    @Column(name="lastName")
    public String lastName;

    @Column(name="position")
    public String position;

    @Column(name="number")
    public int number;

    public TestPlayerTable() {
        super();
    }

    public TestPlayerTable( int p_id, int teamId,String firstName,String lastName,String position,int number) {
        super();

        this.p_id = p_id;
        this.teamId = teamId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position =position;
        this.number = number;
    }
}
