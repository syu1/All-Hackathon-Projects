import java.util.*;

public class Optimization{
	private final static int suggestedProtein = 50;	// suggested daily protein intakes
	private final static int suggestedCarbs = 300;	// suggested daily carbohydrate intakes
	private final static int suggestedCalories = 2000;	// suggested daily calorie intakes
	private final static int suggestedSodium = 2400;	// suggested daily sodium intakes
	private final static int suggestedFat = 65;	// suggested daily fat intakes
	
	public static void main(String[] args){
		
		FoodDatabase database = new FoodDatabase();
		List<FoodItem> foodDataBase = database.createBase();
		
		List<FoodItem> foodItems = new ArrayList<FoodItem>(foodDataBase); // copy of ArrayList to alter
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Cost maximum:  ");
		double costMax = scanner.nextDouble();

		int i = 0;
		while( i < foodItems.size()){
			if(foodItems.get(i).getCost() > costMax){
				foodItems.remove(i);
				//System.out.println("removed i: " + i);
			}else{
				i++;
			}
		}
		
		
		System.out.print("\nProtein - High (2), Low (1), n/a (0): ");
		int proteinChoice = scanner.nextInt();
		
		System.out.print("Carbs - High (2), Low (1), n/a (0): ");
		int carbChoice = scanner.nextInt();
		
		System.out.print("Calorie - High (2), Low (1), n/a (0): ");
		int calorieChoice = scanner.nextInt();
		
		System.out.print("Sodium - High (2), Low (1), n/a (0): ");
		int sodiumChoice = scanner.nextInt();
		
		System.out.print("Fat - High (2), Low (1), n/a (0): ");
		int fatChoice = scanner.nextInt();
		
		System.out.println();
		
		List<FoodItem> returnArray = suggest(foodItems, costMax, proteinChoice, carbChoice, calorieChoice, sodiumChoice, fatChoice);
		
		System.out.println("Top 3 Suggestions: ");
		int j = 0;
		
		for(FoodItem item : returnArray){
			
			if(j == 3)
				break;
			
			
			System.out.println("Name: " + item.getName());
			System.out.println("Price: " + item.getCost());
			System.out.println("Protein Percent: " + item.getProteinPercent());
			System.out.println("Carb Percent: " + item.getCarbPercent());
			System.out.println("Calorie Percent: " + item.getCalPercent());
	
			double compositeScore = item.getCompScore();
			System.out.println("Composite Score: " + compositeScore);
			
			System.out.println();
			j++;
		}
		
	}
	
	public static ArrayList<FoodItem> suggest(List<FoodItem> foodItems, double costMax, int proteinChoice, int carbChoice, int calorieChoice, int sodiumChoice, int fatChoice){
		List<FoodItem> returnArray = new ArrayList<FoodItem>();
		
		// Set percentages for the composite score
		for(FoodItem food : foodItems){
			
			double proteinPercent = (double) food.getProtein() / suggestedProtein;
			food.setProteinPercent(proteinPercent);
			
			double carbPercent = (double) food.getCarbs() / suggestedCarbs;
			food.setCarbPercent(carbPercent);
			
			double caloriePercent = (double) food.getCalories() / suggestedCalories;
			food.setCalPercent(caloriePercent);
			
			double sodiumPercent = (double) food.getSodium() / suggestedSodium;
			food.setSodiumPercent(sodiumPercent);
			
			double fatPercent = (double) food.getFat() / suggestedFat;
			food.setFatPercent(fatPercent);
	
		}
		
		
		// If the user wants all 5 to be HIGH
		if(proteinChoice == 2 && carbChoice == 2 && calorieChoice == 2 && sodiumChoice == 2 && fatChoice == 2){
			
			Collections.sort(foodItems, new Comparator<FoodItem>() {
		        @Override public int compare(FoodItem f1, FoodItem f2) {
		        	
		        	double percent2 = ( f2.getProteinPercent() + f2.getCarbPercent() + f2.getCalPercent() );
		        	double percent1 = ( f1.getProteinPercent() + f1.getCarbPercent() + f1.getCalPercent() );
		        	
		        	f2.setCompScore(percent2);
		        	f1.setCompScore(percent1);
		        	
		            return Double.compare( percent2, percent1 ); // Descending
		        }

		    });	
			
			returnArray = new ArrayList<FoodItem>(foodItems);
			
		} // end if(proteinChoice == 2 && carbChoice == 2 && calorieChoice == 2 && sodiumChoice == 2 && fatChoice == 2)
		
		// If the user wants all 5 to be LOW
		else if(proteinChoice == 1 && carbChoice == 1 && calorieChoice == 1 && sodiumChoice == 1 && fatChoice == 1){
					
			Collections.sort(foodItems, new Comparator<FoodItem>() {
				@Override public int compare(FoodItem f1, FoodItem f2) {
				        	
					double percent1 = ( f1.getProteinPercent() + f1.getCarbPercent() + f1.getCalPercent() );
					double percent2 = ( f2.getProteinPercent() + f2.getCarbPercent() + f2.getCalPercent() );
					
					f1.setCompScore(percent1);
					f2.setCompScore(percent2);
				        	
					return Double.compare( percent1, percent2 ); // Ascending
				}

			});	
					
			returnArray = new ArrayList<FoodItem>(foodItems);
					
		} // end else if(proteinChoice == 1 && carbChoice == 1 && calorieChoice == 1 && sodiumChoice == 1 && fatChoice == 1)
		
		else{
			
			for(FoodItem item : foodItems){
				ArrayList<Double> highOptions = new ArrayList<Double>();
				ArrayList<Double> lowOptions = new ArrayList<Double>();
				ArrayList<Double> noOptions = new ArrayList<Double>();
				double highSum = 0.0;
				double lowSum = 0.0;
				
				switchOptions(proteinChoice, item.getProteinPercent(), highOptions, lowOptions, noOptions);
				switchOptions(carbChoice, item.getCarbPercent(), highOptions, lowOptions, noOptions);
				switchOptions(calorieChoice, item.getCalPercent(), highOptions, lowOptions, noOptions);
				switchOptions(sodiumChoice, item.getSodiumPercent(), highOptions, lowOptions, noOptions);
				switchOptions(fatChoice, item.getFatPercent(), highOptions, lowOptions, noOptions);
				
				for(Double option : highOptions){
					highSum += option;
				}
				
				for(Double option : lowOptions){
					lowSum += option;
				}
				
				item.setCompScore(highSum - lowSum);
				
						
			} // end for(FoodItem item : foodItems)
			
			Collections.sort(foodItems, new Comparator<FoodItem>() {
				@Override public int compare(FoodItem f1, FoodItem f2) {
				        	
					double percent2 = ( f2.getCompScore() );
					double percent1 = ( f1.getCompScore() );
				        	
					return Double.compare( percent2, percent1 ); // Descending
				}

			});	
					
			returnArray = new ArrayList<FoodItem>(foodItems);
			
		
		} // end else
		
		return (ArrayList<FoodItem>) returnArray;
	} // end suggest()
	
	public static void switchOptions(int option, double percent, ArrayList<Double> high, ArrayList<Double> low, ArrayList<Double> none){
		
		switch(option){
			case 0:
				none.add(percent);
				break;
			case 1:
				low.add(percent);
				break;
			case 2:
				high.add(percent);
				break;
		}
		
	
	}
	
	
}
