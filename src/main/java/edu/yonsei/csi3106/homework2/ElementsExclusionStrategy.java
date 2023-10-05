package edu.yonsei.csi3106.homework2;

import com.google.gson.*;

public class ElementsExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes field){
        return field.getDeclaringClass() == Element.class && field.getName().equals("parentCollection");
    }

    @Override
    public boolean shouldSkipClass(Class<?> cla){
        return false;
    }
}
