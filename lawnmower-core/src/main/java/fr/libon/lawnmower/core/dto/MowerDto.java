package fr.libon.lawnmower.core.dto;

import fr.libon.lawnmower.core.domain.Mower;

import javax.validation.constraints.NotBlank;

public class MowerDto extends Mower {

    @NotBlank(message = "Instructions should not be empty")
    private String instructions;

    public MowerDto() {
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Mower toMower() {
        Mower mower = new Mower();
        mower.setPosition(this.getPosition());
        return mower;
    }
}
