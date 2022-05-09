package assignment9;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

import assignment9.scenes.Bubbles;
import assignment9.scenes.Forest;
import assignment9.scenes.Leaves;
import assignment9.scenes.Poly;
import assignment9.scenes.Sequence;
import assignment9.scenes.ifs.Dragon;
import assignment9.scenes.ifs.Leaf;
import assignment9.scenes.ifs.Tree;
import edu.princeton.cs.introcs.StdDraw;
import support.cse131.ArgsProcessor;

public class SceneComposer {

	public static void drawer (String resp, Map <String, Drawable> storage, List <Drawable> cache, boolean recorder) {
		if (recorder == true) {
			Drawable wantToDraw = (Drawable)(storage.get(resp));
			wantToDraw.draw();
			cache.add(wantToDraw);
		} else {
			Drawable wantToDraw = (Drawable)(storage.get(resp));
			wantToDraw.draw();
		}
	}

	public static void main(String[] args) {

		ArgsProcessor ap = new ArgsProcessor(args);
		
		// Note: Double Buffering is enabled!  
		//       You'll need to call show() to update the screen.
		//       In most cases you'll want to call show() after you've drawn something
		StdDraw.enableDoubleBuffering();
		
		
		//
		// for demo only, remove this code and write your own to do what
		//   is needed for this assignment
//		while(true) {
//			StdDraw.show();
//			StdDraw.pause(10);
//			for (int i=0; i < 10; ++i) {
//				Forest f = new Forest(5);
//				f.draw(); f.draw(); f.draw(); f.draw();
//				Leaves l = new Leaves(5);
//				l.draw(); l.draw();
//			}
//			Bubbles b = new Bubbles(10);
//			b.draw();
//			StdDraw.show();
//			StdDraw.pause(10);
//			String resp = ap.nextString("Again?");
//			if (resp.equals("no")) {
//				break;
//			}
//			else {
//				new Clear().draw();
//			}
//		}
		//
		// end of demo code
		//
		
		Map <String, Drawable> storage = new HashMap<>();
		List <Drawable> cache = new LinkedList<>();
		boolean recorder = false;
		String resp = "";
		
		Drawable b1 = new Bubbles(3);
		storage.put("b1", b1);
		Drawable b2 = new Bubbles(6);
		storage.put("b2", b2);
		Drawable b3 = new Bubbles(9);
		storage.put("b3", b3);
		
		Drawable t1 = new Tree(Math.random()*.3, Math.random()*.3, Math.random());
		storage.put("t1", t1);
		Drawable t2 = new Tree(Math.random()*.3, Math.random()*.3, Math.random());
		storage.put("t2", t2);
		Drawable t3 = new Tree(Math.random()*.3, Math.random()*.3, Math.random());
		storage.put("t3", t3);
		
		Drawable f1 = new Forest(3);
		storage.put("f1", f1);
		Drawable f2 = new Forest(6);
		storage.put("f2", f2);
		Drawable f3 = new Forest(9);
		storage.put("f3", f3);
		
		Drawable d1 = new Dragon(Math.random()*.3, Math.random()*.3, Math.random());
		storage.put("d1", d1);
		Drawable d2 = new Dragon(Math.random()*.3, Math.random()*.3, Math.random());
		storage.put("d2", d2);
		Drawable d3 = new Dragon(Math.random()*.3, Math.random()*.3, Math.random());
		storage.put("d3", d3);
		
		Drawable p1 = new Poly (3);
		storage.put("p1", p1);
		Drawable p2 = new Poly (6);
		storage.put("p2", p2);
		Drawable p3 = new Poly (9);
		storage.put("p3", p3);
		
		Drawable l1 = new Leaf(Math.random()*.3, Math.random()*.3, Math.random());
		storage.put("l1", l1);
		Drawable l2 = new Leaf(Math.random()*.3, Math.random()*.3, Math.random());
		storage.put("l2", l2);
		Drawable l3 = new Leaf(Math.random()*.3, Math.random()*.3, Math.random());
		storage.put("l3", l3);
		
		List <Drawable> initList = new LinkedList<>();
		for (int i=0; i < 10; ++i) {
			Forest f = new Forest(5);
			for (int a = 0; a < 4; a ++) {
				initList.add(f);
			}
			f.draw(); f.draw(); f.draw(); f.draw();
			Leaves l = new Leaves(5);
			for (int b = 0; b < 2; b ++) {
				initList.add(l);
			}
			l.draw(); l.draw();
		}
		Bubbles b = new Bubbles(10);
		for (int i = 0; i < 2; i ++) {
			initList.add(b);
		}
		b.draw();
		StdDraw.show();
		StdDraw.pause(10);
		Drawable init = new Sequence (initList);
		storage.put("init", init);
		
		while(true ) {
			StdDraw.show();
			StdDraw.pause(10);
			if (recorder == false) {
				resp = ap.nextString("Express yourself:");
			}
			if (resp.equals("ron")){
				recorder = true;
			}
			if ( recorder == true) {
				resp = ap.nextString("Express yourself (recording on):");
			}	
			
		    if (resp.equals("roff")){
				String sceneSavor = ap.nextString("What do u want to call this scene?");
		    	recorder = false; 
		    	Drawable saved = new Sequence(cache);
		    	storage.put(sceneSavor, saved);
		    } else if (resp.equals("end")) {
				break;
		    } else if (resp.equals("clear")) {
				StdDraw.clear();
		    } else if (storage.containsKey(resp) == true){
		    	drawer (resp, storage, cache, recorder);
			} else {
				for (String key: storage.keySet()) {
					if (key != "init" && key.length() == 2) {
						System.out.println(key + ": " + storage.get(key).toString() + " " + key.charAt(1));
					} else if (key == "init"){
						System.out.println(key + ": inital");
					} else {
						System.out.println(key + ": your scene " + key);
					}
				}
			}
		}
		
	}

}
