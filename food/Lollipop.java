package assignment2.food;

import assignment2.Caterpillar;

public class Lollipop extends FoodItem{
	
	public void accept(Caterpillar c) {
		c.eat(this);
	}

}
