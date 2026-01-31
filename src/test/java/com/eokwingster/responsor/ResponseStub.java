package com.eokwingster.responsor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.eokwingster.util.DynamicMessage;

public record ResponseStub(List<DynamicMessage> messages) {
    public static Response of(String... messages) {
        List<DynamicMessage> dynamicMessages = new ArrayList<>();
        for (String message : messages) {
            dynamicMessages.add(new DynamicMessage(message));
        }
        return new Response(dynamicMessages, 3, Set.of());
    }
}
