package com.diegopereira.cartolafc.classificacao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    public String user;
    public String password;

    public Login( String user, String password ) {
        this.user = user;
        this.password = password;
    }
}
