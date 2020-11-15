package Managers;

import Objects.Nuez;
import Objects.Plant;
import Objects.PetaCereza;
import Objects.Peashooter;
import Objects.Sunflower;


public class FactoryPlant {
	public static Plant[] availablePlants = {
		new Nuez(),
		new Peashooter(),
		new PetaCereza(),
		new Sunflower(),
	};
	
	public Plant[] getAvailablePlants() {
		return availablePlants;
	}
	
	public static Plant getPlant(String plantName) {
		
		Plant planta = null;
		Plant copia = null;
		
		for (Plant plant: availablePlants) {
			if (planta == null) 
				planta = plant.parse(plantName);
		}
		
		if (planta == null) {
			System.out.println("Unknown plant");
		}
		
		if (planta != null)
		   copia = (Plant)planta.clone();

		return copia;
	}
	
	
	
	public static String listOfAvailablePlants() {
		
		String stringPlantList = "";
		
		for (Plant plant : availablePlants) {
			stringPlantList = stringPlantList + plant.getName() + ": Cost " + plant.getCoste() + " suncoins " + " Harm: " + plant.getDamage() + "\n";
		}		
		return stringPlantList;
	}
}
