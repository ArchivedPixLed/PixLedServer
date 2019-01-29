package com.pixled.pixledserver.model.state.base.dto;

import com.pixled.pixledserver.core.ToggleState;
import com.pixled.pixledserver.core.state.base.State;

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
