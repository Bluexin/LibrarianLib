package com.teamwizardry.librarianlib.client;

import net.minecraft.util.ResourceLocation;

/**
 * This class represents a section of a {@link Texture}
 */
public class Sprite {

	protected Texture tex;
	protected int u, v, width, height;
	
	public Sprite(Texture tex, int u, int v, int width, int height) {
		this.tex = tex;
		this.u = u;
		this.v = v;
		this.width = width;
		this.height = height;
	}
	
	public Sprite(ResourceLocation loc) {
		this.tex = new Texture(loc, 16, 16);
		this.u = 0;
		this.v = 0;
		this.width = 16;
		this.height = 16;
	}
	
	/**
	 * The minimum U coordinate (0-1)
	 */
	public float minU() {
		return (float)u/(float)tex.getWidth();
	}
	
	/**
	 * The minimum V coordinate (0-1)
	 */
	public float minV() {
		return (float)v/(float)tex.getHeight();
	}
	
	/**
	 * The maximum U coordinate (0-1)
	 */
	public float maxU() {
		return (float)(u+width)/(float)tex.getWidth();
	}
	
	/**
	 * The maximum V coordinate (0-1)
	 */
	public float maxV() {
		return (float)(v+height)/(float)tex.getHeight();
	}

	/**
	 * The {@link Texture} that this sprite is a part of
	 * @return
	 */
	public Texture getTex() {
		return tex;
	}

	/**
	 * The minimum U coordinate in pixels
	 */
	public int getU() {
		return u;
	}

	/**
	 * The minimum V coordinate in pixels
	 */
	public int getV() {
		return v;
	}

	/**
	 * The width in pixels
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * The height in pixels
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Draws the sprite to the screen
	 * @param x The x position to draw at
	 * @param y The y position to draw at
	 */
	public void draw(float x, float y) {
		DrawingUtil.draw(this, x, y, getWidth(), getHeight());
	}
	
	/**
	 * Draws the sprite to the screen with a custom width and height
	 * @param x The x position to draw at
	 * @param y The y position to draw at
	 * @param width The width to draw the sprite
	 * @param height The height to draw the sprite
	 */
	public void draw(float x, float y, int width, int height) {
		DrawingUtil.draw(this, x, y, width, height);
	}
	
	/**
	 * Draws the sprite to the screen with a custom width and height by clipping instead of stretching/squashing
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawClipped(float x, float y, int width, int height) {
		DrawingUtil.drawClipped(this, x, y, width, height);
	}
	
}