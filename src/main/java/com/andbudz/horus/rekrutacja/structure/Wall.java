package com.andbudz.horus.rekrutacja.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        List<Block> blocksWithAllCompositeBlocksElements = getBlocksWithAllCompositeBlocks(blocks);
        while (blocksWithAllCompositeBlocksElements.iterator().hasNext()) {
            Block block = blocksWithAllCompositeBlocksElements.iterator().next();
            if (block.getColor().equals(color)) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> blocksByMaterial = new ArrayList<>();
        List<Block> blocksWithAllCompositeBlocksElements = getBlocksWithAllCompositeBlocks(blocks);
        while (blocksWithAllCompositeBlocksElements.iterator().hasNext()) {
            Block block = blocksWithAllCompositeBlocksElements.iterator().next();
            if (block.getMaterial().equals(material)) {
                blocksByMaterial.add(block);
            }
        }
        return blocksByMaterial;
    }

    @Override
    public int count() {
        int i = 0;
        for (Block block : blocks) {
            if (isCompositeBlock(block)) {
                i += ((CompositeBlock) block).getBlocks().size();
            } else {
                i++;
            }
        }
        return i;
    }

    private List<Block> getBlocksWithAllCompositeBlocks(List<Block> blocks) {
        List<Block> blocksWithAllCompositeBlocksElements = new ArrayList<>();
        while (blocks.iterator().hasNext()) {
            Block block = blocks.iterator().next();
            if (isCompositeBlock(block)) {
                blocksWithAllCompositeBlocksElements.addAll(((CompositeBlock) block).getBlocks());
            }
        }
        return blocksWithAllCompositeBlocksElements;
    }

    private boolean isCompositeBlock(Block block) {
        return (block instanceof CompositeBlock);
    }
}
