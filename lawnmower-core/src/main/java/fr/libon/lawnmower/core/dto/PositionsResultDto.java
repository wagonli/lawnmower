package fr.libon.lawnmower.core.dto;

import fr.libon.lawnmower.core.domain.Position;

import java.util.List;

public class PositionsResultDto {

    private List<Position> positions;

    public PositionsResultDto() {
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
