package com.pixled.pixledserver.state.base;

import com.pixled.pixledserver.model.ToggleState;

import javax.persistence.*;

@MappedSuperclass
public abstract class State {
    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ToggleState toggleState;

    public State() {
        toggleState = ToggleState.OFF;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ToggleState getToggleState() {
        return toggleState;
    }

    public void setToggleState(ToggleState toggleState) {
        this.toggleState = toggleState;
    }
}
