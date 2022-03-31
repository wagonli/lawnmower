package fr.libon.lawnmower.api.controller;

import fr.libon.lawnmower.core.dto.LawnmowerDto;
import fr.libon.lawnmower.core.dto.PositionsResultDto;
import fr.libon.lawnmower.core.service.LawnmowerService;
import fr.libon.lawnmower.core.service.impl.LawnmowerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mower")
@Validated
public class LawnmowerController {

    private final LawnmowerService lawnmowerService;

    public LawnmowerController() {
        this.lawnmowerService = new LawnmowerServiceImpl();
    }

    @PostMapping("/process")
    public ResponseEntity<PositionsResultDto> processMowers(@RequestBody LawnmowerDto lawnmowerDto) {
        return new ResponseEntity<>(lawnmowerService.getPositionsResultDto(lawnmowerDto), HttpStatus.OK);
    }
}
