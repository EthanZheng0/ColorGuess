package lab9;

import java.awt.Color;

import lab9.implementations.HonestFeedback;
import lab9.providers.ProvidesColor;
import lab9.providers.ProvidesFeedback;
import lab9.providers.ProvidesGuess;
import lab9.providers.ReceivesHistory;
import sedgewick.StdDraw;

public class DrawHistory implements ReceivesHistory{

	private ProvidesColor c;
	private ProvidesGuess g;

	public DrawHistory(ProvidesColor c, ProvidesGuess g) {
		this.c = c;
		this.g = g;
	}

	@Override
	public void sendHistory(History h) {
		
//		System.out.println("The number of moves is " + h.size());
//		System.out.println("The move is " + h.getHistoryAt(h.size()-1));
//		System.out.println("The choice at 0 is " + h.getHistoryAt(h.size()-1).getChoice(0));		
				
		StdDraw.setCanvasSize(400, 750);
		StdDraw.setXscale(0, 96);
		StdDraw.setYscale(-180, 0);
		for(int j=0; j<h.size(); j++) {
			
			ProvidesFeedback pf = new HonestFeedback(g.getGuess());
			int is = pf.numIntersection(h.getHistoryAt(j));
			int sp = pf.numSamePosition(h.getHistoryAt(j));
			
//			System.out.println("intersection: " + is + " same position: " + sp);
			
			for(int i=0; i<4; i++) {
				StdDraw.setPenColor(c.getColorForPeg(h.getHistoryAt(j).getChoice(i)));
				StdDraw.filledCircle(i*15+5, -j*15-5, 5);
			}
			
			int position = 0;
			
			for(int i=0; i<sp; i++) {
				StdDraw.setPenColor(Color.GREEN);
				StdDraw.filledCircle(75-2.5+position*5.5, -j*15-5, 2.5);
				position++;
			}
			
			for(int i=0; i<is-sp; i++) {
				Color LIGHTBLUE = new Color(128, 192, 255);
				StdDraw.setPenColor(LIGHTBLUE);
				StdDraw.filledCircle(75-2.5+position*5.5, -j*15-5, 2.5);
				position++;
			}
			
			for(int i=0; i<4-is; i++) {
				StdDraw.setPenColor(Color.LIGHT_GRAY);
				StdDraw.filledCircle(75-2.5+position*5.5, -j*15-5, 2.5);
				position++;
			}
		
		}
		
	}

}
