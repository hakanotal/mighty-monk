package org.world;

import org.graphics.Graphics;

public class Boundary {
	public float x1;
	public float y1;
	public float x2;
	public float y2;
	public float len_sq;

	public Boundary(float a1, float b1, float a2, float b2) {
		x1 = a1;
		y1 = b1;
		x2 = a2;
		y2 = b2;
		len_sq = (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
	}

	public void render() {
		Graphics.setColor(1, 0, 1, 1);
		Graphics.DrawLine(x1, y1, x2, y2);
		Graphics.setColor(1, 1, 1, 1);
	}

	public int getDistance(float x, float y) {
		float A = x - this.x1;
		float B = y - this.y1;
		float C = this.x2 - this.x1;
		float D = this.y2 - this.y1;

		float dot = A * C + B * D;

		float param = -1;
		if (this.len_sq != 0) // in case of 0 length line
			param = dot / this.len_sq;

		float xx, yy;

		if (param < 0) {
			xx = this.x1;
			yy = this.y1;
		} else if (param > 1) {
			xx = this.x2;
			yy = this.y2;
		} else {
			xx = this.x1 + param * C;
			yy = this.y1 + param * D;
		}
		
		
		float dx = x - xx;
		float dy = y - yy;
		
		if(Math.sqrt(dx * dx + dy * dy) < 0.3f && dx == 0) return -1;
		else if (Math.sqrt(dx * dx + dy * dy) < 0.3f) return 1;
		else return 0;	
	}
}
