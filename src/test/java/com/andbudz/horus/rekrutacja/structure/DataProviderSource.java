package com.andbudz.horus.rekrutacja.structure;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DataProviderSource {

    private static final Block bluePlasticBlock;
    private static final Block redBrickBlock;
    private static final Block silverIronBlock;
    private static final CompositeBlock compositeBlockWithTwoElements;
    private static final CompositeBlock compositeBlockWithThreeElements;

    static {
        redBrickBlock = new Slab("Red", "Brick");
        silverIronBlock = new Slab("Silver", "Iron");
        bluePlasticBlock = new Slab("Blue", "Plastic");
        compositeBlockWithTwoElements = new CompositeSlab(Arrays.asList(bluePlasticBlock, redBrickBlock));
        compositeBlockWithThreeElements = new CompositeSlab(Arrays.asList(silverIronBlock, redBrickBlock, bluePlasticBlock));
    }

    @DataProvider
    public static Object[][] countTestWithNoCompositeBlocks() {
        return new Object[][]{
                {new Wall(new ArrayList<>()), 0},
                {new Wall(), 0},
                {new Wall(new ArrayList<>(Arrays.asList(bluePlasticBlock, bluePlasticBlock, bluePlasticBlock, bluePlasticBlock))), 4}
        };
    }

    @DataProvider
    public static Object[][] countTestWithCompositeBlocks() {
        return new Object[][]{
                {new Wall(new ArrayList<>()), 0},
                {new Wall(), 0},
                {new Wall(new ArrayList<>(Arrays.asList(bluePlasticBlock, bluePlasticBlock, bluePlasticBlock, bluePlasticBlock, compositeBlockWithTwoElements))), 6},
                {new Wall(new ArrayList<>(Arrays.asList(compositeBlockWithThreeElements, compositeBlockWithTwoElements))), 5}
        };
    }

    @DataProvider
    public static Object[][] testFindBlockByColor() {
        return new Object[][]{
                {new Wall(new ArrayList<>(Arrays.asList(bluePlasticBlock, bluePlasticBlock, bluePlasticBlock, bluePlasticBlock))), "Blue", Optional.of(bluePlasticBlock)},
                {new Wall(new ArrayList<>(Arrays.asList(compositeBlockWithThreeElements, compositeBlockWithTwoElements))), "silver", Optional.of(silverIronBlock)},
                {new Wall(new ArrayList<>(Arrays.asList(compositeBlockWithThreeElements, compositeBlockWithTwoElements))), "green", Optional.empty()},
                {new Wall(new ArrayList<>(Arrays.asList(compositeBlockWithThreeElements, compositeBlockWithTwoElements))), null, Optional.empty()},
                {new Wall(), null, Optional.empty()}

        };
    }

    @DataProvider
    public static Object[][] testFindBlockByMaterial() {
        return new Object[][]{
                {new Wall(new ArrayList<>(Arrays.asList(bluePlasticBlock, bluePlasticBlock, bluePlasticBlock, bluePlasticBlock))), "Plastic", new ArrayList<>(Arrays.asList(bluePlasticBlock, bluePlasticBlock, bluePlasticBlock, bluePlasticBlock))},
                {new Wall(new ArrayList<>(Arrays.asList(bluePlasticBlock, redBrickBlock, bluePlasticBlock, redBrickBlock))), "Plastic", new ArrayList<>(Arrays.asList(bluePlasticBlock, bluePlasticBlock))},
                {new Wall(new ArrayList<>(Arrays.asList(compositeBlockWithThreeElements, compositeBlockWithTwoElements))), "Brick", new ArrayList<>(Arrays.asList(redBrickBlock, redBrickBlock))},
                {new Wall(new ArrayList<>(Arrays.asList(compositeBlockWithThreeElements, compositeBlockWithTwoElements))), null, new ArrayList<>()},
                {new Wall(), null, new ArrayList<>()}


        };
    }

    @DataProvider
    public static Object[][] testFindBlockByColorIfBlocksIsNull() {
        return new Object[][]{
        };
    }

    private static class Slab implements Block {
        String color;
        String material;

        public Slab(String color, String material) {
            this.color = color;
            this.material = material;
        }

        public Slab() {
        }

        @Override
        public String getColor() {
            return color;
        }

        @Override
        public String getMaterial() {
            return material;
        }
    }

    private static class CompositeSlab implements CompositeBlock {
        List<Block> blocks;

        public CompositeSlab(List<Block> blocks) {
            this.blocks = blocks;
        }

        @Override
        public String getColor() {
            return blocks.get(0).getColor();
        }

        @Override
        public String getMaterial() {
            return blocks.get(0).getMaterial();
        }

        @Override
        public List<Block> getBlocks() {
            return blocks;
        }
    }
}
