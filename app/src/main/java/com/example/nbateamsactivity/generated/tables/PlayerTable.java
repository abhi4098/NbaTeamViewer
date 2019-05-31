
package com.example.nbateamsactivity.generated.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "PlayerTable")
public class PlayerTable extends Model {

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

    public PlayerTable() {
        super();
    }

    public PlayerTable( int p_id, int teamId,String firstName,String lastName,String position,int number) {
        super();

        this.p_id = p_id;
        this.teamId = teamId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position =position;
        this.number = number;
    }
}
