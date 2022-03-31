package fr.libon.lawnmower.core.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class LawnmowerDto {

    @NotNull
    private Dimension dimensions;

    @NotEmpty
    private List<MowerDto> mowers;

    public LawnmowerDto() {
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    public List<MowerDto> getMowers() {
        if (mowers == null) {
            mowers = new ArrayList<>();
        }
        return mowers;
    }

    public void setMowers(List<MowerDto> mowers) {
        this.mowers = mowers;
    }

    public static class Dimension {

        private int width;
        private int height;

        public Dimension() {
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
