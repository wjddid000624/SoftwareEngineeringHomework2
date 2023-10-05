package edu.yonsei.csi3106.homework2;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ElementDeserializer implements JsonDeserializer<Element> {
    @Override
    public Element deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Element.class, new ElementDeserializer())
                .create();

        JsonObject jsonObject = json.getAsJsonObject();

        if(jsonObject.has("title"))
            return gson.fromJson(json, Book.class);
        return gson.fromJson(json, Collection.class);
    }
}
