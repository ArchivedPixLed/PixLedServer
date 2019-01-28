package com.pixled.pixledserver.state.base.dto;

import com.pixled.pixledserver.model.ToggleState;
import com.pixled.pixledserver.state.base.State;

public class StateDto {

    private ToggleState toggleState;

    public StateDto(State state) {
        toggleState = state.getToggleState();
    }

    public ToggleState getToggleState() {
        return toggleState;
    }

    public void setToggleState(ToggleState toggleState) {
        this.toggleState = toggleState;
    }
}
