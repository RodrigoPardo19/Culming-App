package com.ufro.culmingapp.homework.domain;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class HomeworkInstruction {

    private String instruction;

    public HomeworkInstruction() {
        // Used only for spring
    }

    public HomeworkInstruction(String instruction) throws NullFieldNotPermitted {
        if (isNull(instruction)) {
            throw new NullFieldNotPermitted("Instruction");
        }
        this.instruction = instruction;
    }

    private Boolean isNull(String instruction) {
        if (instruction == null || instruction.isBlank()) {
            return true;
        }
        return false;
    }

    public String getInstruction() {
        return this.instruction;
    }
}
