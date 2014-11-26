function pet(age, health, hunger, happiness, discipline) {
	// Health, hunger, happiness, discipline are on a scale of 0-100 where 100
	// is the best value.
	this.age = age;
	this.health = health;
	this.hunger = hunger;
	this.happiness = happiness;
	this.discipline = discipline;
	this.sick = false;
	this.sleeping = false;
	this.overfed = false;
	this.poop = 0;
}

pet.prototype.feed = function(foodAmount) {
	this.hunger += foodAmount;
};

pet.prototype.play = function(happinessAmount) {
	this.happiness += happinessAmount;
};

pet.prototype.scold = function(disciplineAmount) {
	this.discipline += disciplineAmount;
};

pet.prototype.poop = function() {
	this.poop++;
};

pet.prototype.growUp = function() {
	this.age += 1;
};

pet.prototype.sleep = function() {
	this.sleeping = true;
};

pet.prototype.heal = function() {
	this.sick = false;
	this.health += 20;
}

pet.prototype.updateHealth = function() {
	if (this.hunger == 0 || this.poop == 5) {
		this.health = 0;
	} else {
		//TODO: come up with formula to update health based on hunger, happiness and
		// discipline
		if (this.sick) {
			// When a pet is sick, its health is reduced by a flat rate
			this.health -= 20;
		}
	}
};