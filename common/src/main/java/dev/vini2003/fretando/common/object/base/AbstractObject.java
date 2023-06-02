package dev.vini2003.fretando.common.object.base;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractObject implements Serializable {
    private UUID id;

    public AbstractObject() {
        this.id = UUID.randomUUID();
    }

    public AbstractObject(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
