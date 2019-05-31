package com.example.nbateamsactivity.api;

import com.example.nbateamsactivity.generated.model.Player;
import com.example.nbateamsactivity.generated.model.TeamListDatum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;


public class UserProfileDeserializer implements JsonDeserializer<Player> {

    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new GsonBuilder().create();

        Player otpResponse = gson.fromJson(json, Player.class);

        JsonObject jsonObject = json.getAsJsonObject();


        return otpResponse;
    }
}
