package com.andbudz.horus.rekrutacja.structure;


import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class WallTest {

    @Test(dataProviderClass = DataProviderSource.class, dataProvider = "countTestWithNoCompositeBlocks")
    public void shouldReturnNumberOfBlocksFromListWithNoComposites(Wall wall, int numberOfBlocks) {
        assertEquals(numberOfBlocks, wall.count());
    }

    @Test(dataProviderClass = DataProviderSource.class, dataProvider = "countTestWithCompositeBlocks")
    public void shouldReturnNumberOfBlocksFromListWithComposites(Wall wall, int numberOfBlocks) {
        assertEquals(numberOfBlocks, wall.count());
    }

    @Test(dataProviderClass = DataProviderSource.class, dataProvider = "testFindBlockByColor")
    public void shouldReturnFirstBlockMatchingProvidedColor(Wall wall, String color, Optional<Block> block) {
        assertEquals(wall.findBlockByColor(color), block);
    }

    @Test(dataProviderClass = DataProviderSource.class, dataProvider = "testFindBlockByMaterial")
    public void shouldReturnListOfBlocksWithProvidedMaterial(Wall wall, String material, ArrayList<Block> blocks) {
        assertEquals(wall.findBlocksByMaterial(material), blocks);
    }
}