package com.eokwingster.responsor;

import java.util.List;

public interface Modifier {
    List<Class<? extends Responsor>> getRootResponsors();
}
