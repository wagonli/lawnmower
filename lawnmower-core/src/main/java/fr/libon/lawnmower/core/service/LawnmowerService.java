package fr.libon.lawnmower.core.service;

import fr.libon.lawnmower.core.dto.LawnmowerDto;
import fr.libon.lawnmower.core.dto.PositionsResultDto;

public interface LawnmowerService {
    PositionsResultDto getPositionsResultDto(LawnmowerDto lawnmowerDto);
}
