package assignment2.food;

import assignment2.Caterpillar;

public class Cake extends FoodItem{
	private int energyProvided;
	
	public Cake(int energy) {
		this.energyProvided = energy;
	}
	
	public int getEnergyProvided() {
		return this.energyProvided;
	}
	
	public void accept(Caterpillar c) {
		c.eat(this);
	}

}
