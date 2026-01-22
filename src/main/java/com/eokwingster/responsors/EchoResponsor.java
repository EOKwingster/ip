package com.eokwingster.responsors;

import java.util.List;

public class EchoResponsor implements Responsor {
    @Override
    public List<String> response(String input) {
        return List.of(input);
    }
}
