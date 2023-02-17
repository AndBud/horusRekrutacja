package com.andbudz.horus.rekrutacja.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;
    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Wall() {
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        List<Block> blocksWithAllCompositeBlocksElements = getBlocksWithAllCompositeBlocks(blocks);
        for (Block block : blocksWithAllCompositeBlocksElements) {
            if (block.getColor().equalsIgnoreCase(color)) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> blocksByMaterial = new ArrayList<>();
        List<Block> blocksWithAllCompositeBlocksElements = getBlocksWithAllCompositeBlocks(blocks);
        for (Block block : blocksWithAllCompositeBlocksElements) {
            if (block.getMaterial().equalsIgnoreCase(material)) {
                blocksByMaterial.add(block);
            }
        }
        return blocksByMaterial;
    }

    @Override
    public int count() {
        int i = 0;
        if (blocks==null){
            return i;
        }
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
        if(blocks==null){
            return blocksWithAllCompositeBlocksElements;
        }
        for (Block block:blocks) {
            if (isCompositeBlock(block)) {
                blocksWithAllCompositeBlocksElements.addAll(((CompositeBlock) block).getBlocks());
            } else {
                blocksWithAllCompositeBlocksElements.add(block);
            }
        }
        return blocksWithAllCompositeBlocksElements;
    }

    private boolean isCompositeBlock(Block block) {
        return (block instanceof CompositeBlock);
    }
}
