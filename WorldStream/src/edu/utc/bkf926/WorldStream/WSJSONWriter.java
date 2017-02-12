package edu.utc.bkf926.WorldStream;

import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.World;
import org.bukkit.Bukkit;

public class WSJSONWriter {
	
	private String world;
	private FileOutputStream stream;
	
	public WSJSONWriter(String worldName){
		world=worldName;
		try {
			stream = new FileOutputStream("plugins/WorldStream/"+world+".json");
			Bukkit.getLogger().info("[DEBUG] Created JSON writer for "+world);
		} catch (IOException e){
			Bukkit.getLogger().severe("ERROR: Failed to access file for world "+worldName+"! Streaming will be disabled! Please correct this issue and restart your server.");
		}
		
	}
	
	public void open(){
		try {
			stream = new FileOutputStream("plugins/WorldStream/"+world+".json");
			Bukkit.getLogger().info("[DEBUG] Opened JSON writer for "+world);
		} catch (IOException e){
			Bukkit.getLogger().severe("ERROR: Failed to access file for world "+world+"! Streaming will be disabled! Please correct this issue and restart your server.");
		}
	}
	
	public void writeBlock(WSBlock block) throws IOException{
		//Write a single block
		//Creates a string to match the JSON format that Unity is expecting, then writes it to the file
		if (block.getBlockID().equals("AIR")) return; //Filters out empty (air) blocks. TODO Add this to the block-culling code when fully implemented.
		String blockText = "{ \n\"blocks\" : [ \n{ \n\"type\": " + block.getBlockID() + 
				",\n \"position\": { \"x\"" + block.x + ", \"y\"" + block.y + ", \"z\"" + block.z
				+ "},\n }\n ]\n }\n";
		stream.write(blockText.getBytes());
	}
	
	public void writeChunk(WSChunk chunk) throws IOException{
		//Write a chunk (16x16x256)
		while (chunk.hasNextBlock())
		{
			writeBlock(chunk.nextBlock());
		}
	}
	
	public void close(){
		try {
			stream.close();
			Bukkit.getLogger().info("[DEBUG] Closed JSON writer for "+world);
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the world associated with this JSON Writer.
	 * @return
	 */
	public World getWorld(){
		return Bukkit.getServer().getWorld(world);
	}
	
}
