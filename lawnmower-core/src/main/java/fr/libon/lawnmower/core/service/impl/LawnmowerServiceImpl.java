package fr.libon.lawnmower.core.service.impl;

import fr.libon.lawnmower.core.domain.Instructions;
import fr.libon.lawnmower.core.domain.Mower;
import fr.libon.lawnmower.core.domain.Orientation;
import fr.libon.lawnmower.core.domain.Position;
import fr.libon.lawnmower.core.dto.LawnmowerDto;
import fr.libon.lawnmower.core.dto.PositionsResultDto;
import fr.libon.lawnmower.core.exception.LawnmowerException;
import fr.libon.lawnmower.core.service.LawnmowerService;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
public class LawnmowerServiceImpl implements LawnmowerService {

    private static final int STEP = 1;

    @Override
    public PositionsResultDto getPositionsResultDto(LawnmowerDto lawnmowerDto) {

        List<Position> positions = lawnmowerDto.getMowers().stream()
                .map(mowerDto -> {
                    Mower mower = mowerDto.toMower();
                    this.moveMower(mower, mowerDto.getInstructions(), lawnmowerDto.getDimensions());
                    return mower;
                }).map(Mower::getPosition)
                .collect(Collectors.toList());
        PositionsResultDto positionsResultDto = new PositionsResultDto();
        positionsResultDto.setPositions(positions);
        return positionsResultDto;

    }

    private void moveMower(Mower mower, String instructions, LawnmowerDto.Dimension dimension) {
        for (char instruction : instructions.toCharArray()) {
            System.out.print(instruction + " ===> ");
            switch (Instructions.valueOf(String.valueOf(instruction))) {
                case A:
                    moveForward(mower, dimension);
                    break;
                case D:
                    rotateClockwise(mower);
                    break;
                case G:
                    rotateCounterClockwise(mower);
                    break;
                default:
                    throw new LawnmowerException("Unexpected Error");
            }
        }
    }

    private void moveForward(Mower mower, LawnmowerDto.Dimension dimension) {
        switch (mower.getPosition().getOrientation()) {
            case N:
                if (mower.getPosition().getY() + STEP < dimension.getHeight()) {
                    mower.getPosition().setY(mower.getPosition().getY() + STEP);
                }
                break;
            case S:
                if (mower.getPosition().getY() - STEP > 0) {
                    mower.getPosition().setY(mower.getPosition().getY() - STEP);
                }
                break;
            case E:
                if (mower.getPosition().getX() + STEP < dimension.getWidth()) {
                    mower.getPosition().setX(mower.getPosition().getX() + STEP);
                }
                break;
            case W:
                if (mower.getPosition().getX() - STEP > 0) {
                    mower.getPosition().setX(mower.getPosition().getX() - STEP);
                }
                break;
            default:
                throw new LawnmowerException("Unexpected Error");
        }
    }

    private void rotateClockwise(Mower mower) {
        switch (mower.getPosition().getOrientation()) {
            case N:
                mower.getPosition().setOrientation(Orientation.E);
                break;
            case S:
                mower.getPosition().setOrientation(Orientation.W);
                break;
            case E:
                mower.getPosition().setOrientation(Orientation.S);
                break;
            case W:
                mower.getPosition().setOrientation(Orientation.N);
                break;
            default:
                throw new LawnmowerException("Unexpected Error");
        }
    }

    private void rotateCounterClockwise(Mower mower) {
        switch (mower.getPosition().getOrientation()) {
            case N:
                mower.getPosition().setOrientation(Orientation.W);
                break;
            case S:
                mower.getPosition().setOrientation(Orientation.E);
                break;
            case E:
                mower.getPosition().setOrientation(Orientation.N);
                break;
            case W:
                mower.getPosition().setOrientation(Orientation.S);
                break;
            default:
                throw new LawnmowerException("Unexpected Error");
        }
    }
}
