package edu.utc.bkf926.WorldStream;

import java.util.HashMap;

public class WSBlock {

	int x, y, z;
	//Positions of the block relative to the world origin, NOT the chunk.
	
	public WSBlock(int[] xyz, int id){
		setCoordinates(xyz);
		blockType = id;
	}
	
	public WSBlock(int[] xyz, int[] id){
		setCoordinates(xyz);
		blockType = id[0];
		blockSubType = id[1];
	}
	
	private Integer blockType;
	private Integer blockSubType;
	private HashMap<String, Integer> metadata;
	
	public void setCoordinates(int[] xyz){
		x = xyz[0];
		y = xyz[1];
		z = xyz[2];
	}
	
	public int getBlockID(){
		return blockType;
	}
	
	/**
	 * Returns the full block ID as a String, formatted to be written to the JSON.
	 */
	public String getFullBlockIDAsString(){
		if (blockSubType==0){
			return blockType.toString();
		}
		else {
			return blockType.toString() + "_"
					+ blockSubType.toString();
		}
	}
	
}