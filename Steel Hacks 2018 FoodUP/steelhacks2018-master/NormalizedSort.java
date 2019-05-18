import java.util.*;
class NormalizedSort {
	// normalizes food values
	// can only take items that are of type 
	// 
	/*
	 * x = input
	 * a = min of scale
	 * b = max of scale 
	 * A = min of data
	 * B = max of data 
	 */
	double a = 0;
	double b = 1;
	double A = Double.MAX_VALUE;
	double B = Double.MIN_VALUE;

	TreeMap<FoodItem, Double> foodList = new TreeMap<FoodItem, Double>();
	boolean highOrLow = true; //high = 1, low = 0

	NormalizedSort(TreeMap<FoodItem, Double> list, int hOrL){ 
		if(hOrL==2){
			highOrLow = true;
		}else if(hOrL== 1){
			highOrLow = false;
		}else{
			System.err.printf("invalid string for high or low");
			System.exit(0);
		}

		for(FoodItem f: list.keySet()){
					findMin(list.get(f));
					findMax(list.get(f));
				
		}
		
		for(FoodItem f: list.keySet()){
				//System.out.printf("%s: %d\n", f.getName(), list.get(f));
				//System.out.printf("Variable name: %d\n", list.get(f).getClass().getName());
				Double rawinput = list.get(f); //error cant cast to
				Double newWeight = NormalizedValue(rawinput);
				foodList.put(f, newWeight);
		}

	}
	NormalizedSort(TreeMap<FoodItem, Double> foodList, int hOrL, int limit){ // for calories
		if(hOrL== 2){
			highOrLow = true;
		}else if(hOrL== 1){
			highOrLow = false;
		}else{
			System.err.printf("invalid int for high or low");
			//System.exit(0);
		}
		for(FoodItem f: foodList.keySet()){
					findMin(foodList.get(f));
					findMax(foodList.get(f));
				
			
		}
		for(FoodItem f: foodList.keySet()){
			if( highOrLow && f.getCalories() >= limit ){
				this.foodList.put(f, NormalizedValue(foodList.get(f)));


			}else if(!highOrLow && f.getCalories() <= limit){
				this.foodList.put(f, NormalizedValue(foodList.get(f)));

			}			
		}
		
	} 	

	NormalizedSort(TreeMap<FoodItem, Double> foodList, double costLimit){ // for cost

		for(FoodItem f: foodList.keySet()){
				findMin(foodList.get(f));
				findMax(foodList.get(f));
				

		}
	
		for(FoodItem f: foodList.keySet()){
		 if(f.getCost() <= costLimit){
				this.foodList.put(f, NormalizedValue(foodList.get(f)));

			}			
		}

	} 

	boolean findMin(double d){ // returns true if min
		if(d< A){
			A = d;
			return true;
		} else{
			return false;
		}
	}

	boolean findMax(double d){ // returns true if max
		if(d> B){
			B = d;
			return true;
		} else{
			return false;
		}
	}

	//x = input value
	Double NormalizedValue(int i){

		double x = (double)i;
		return new Double(((a+(x-A)*(b-a))/(B-A)));
	}

	Double NormalizedValue(double x){
		return new Double(((a+(x-A)*(b-a))/(B-A)));

	}

	Double NormalizedValue(Double x){
		return new Double(((a+(x.doubleValue()-A)*(b-a))/(B-A)));

	}

	public static void main(String[] args){

		FoodItem burger = new FoodItem("Bob's Burger", 6.5, 26, 0, 271, 440, 18);
		FoodItem crushOrange = new FoodItem("Crush Orange", 1.0, 0, 43, 160, 70, 0);
		FoodItem cookies = new FoodItem("Grandma's Cookies", 3.0, 0, 30, 30, 10, 15);
		FoodItem iceCream = new FoodItem("Ice Cream", 3.50, 2, 13, 120, 45, 7);



		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Cost maximum:  ");
		double costMax = scanner.nextDouble();
		
		System.out.print("\nProtein - High (2), Low (1), n/a (0): ");
		int proteinChoice = scanner.nextInt();
		
		System.out.print("\nCarbs - High (2), Low (1), n/a (0): ");
		int carbChoice = scanner.nextInt();
		
		System.out.print("\nCalorie - High (2), Low (1), n/a (0): ");
		int calorieChoice = scanner.nextInt();
		
		ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();

		foodItems.add(burger);
		foodItems.add(crushOrange);
		foodItems.add(cookies);
		foodItems.add(iceCream);

		TreeMap cost = new TreeMap<FoodItem, Double>();
		TreeMap protein = new TreeMap<FoodItem, Double>();
		TreeMap carbs = new TreeMap<FoodItem, Double>();
		TreeMap calories = new TreeMap<FoodItem, Double>();
		for(FoodItem f: foodItems){
			cost.put(f, f.getCost());
			if(proteinChoice != 0){ 
				//System.out.println(f.getProtein());
				protein.put(f, new Double(f.getProtein())); 
				System.out.printf("food: %s protein: %s\n",f.getName(), (protein.get(f)).toString());
			}
			if(carbChoice != 0 ){ carbs.put(f, new Double(f.getCarbs())); }
			if(calorieChoice != 0 ){ calories.put(f, new Double(f.getCalories())); }
		}
		NormalizedSort weightedCost = new NormalizedSort(cost, costMax);
		NormalizedSort weightedProtein;
		NormalizedSort weightedCarbs;
		NormalizedSort weightedCalories;

		TreeMap<FoodItem, Double> options = weightedCost.foodList;

		//protein
		if(protein.size() !=0){
			System.out.println("protein:");
			weightedProtein = new NormalizedSort(protein, proteinChoice); //problem child
			if(options.size() ==0){
				for(FoodItem f : weightedProtein.foodList.keySet()){
					options.put(f, weightedProtein.foodList.get(f));
				}
			}else{
				for(FoodItem f : weightedProtein.foodList.keySet()){
					options.replace(f, weightedProtein.foodList.get(f) * options.get(f));
				}
			}
		}

		//cost
		if(cost.size() !=0){
			weightedCost = new NormalizedSort(cost, costMax);
			if(options.size() ==0){
				for(FoodItem f : weightedCost.foodList.keySet()){
					options.put(f, weightedCost.foodList.get(f));
				}
			}else{
				for(FoodItem f : weightedCost.foodList.keySet()){
					options.replace(f, weightedCost.foodList.get(f) * options.get(f));
				}
			}
		}

		//carbs
		if(carbs.size() !=0){
			weightedCarbs = new NormalizedSort(carbs, carbChoice);
			if(options.size() ==0){
				for(FoodItem f : weightedCarbs.foodList.keySet()){
					options.put(f, weightedCarbs.foodList.get(f));
				}
			}else{
				for(FoodItem f : weightedCarbs.foodList.keySet()){
					options.replace(f, weightedCarbs.foodList.get(f) * options.get(f));
				}
			}
		}

		//calories
		if(calories.size() !=0){
			weightedCalories = new NormalizedSort(calories, calorieChoice);
			if(options.size() ==0){
				for(FoodItem f : weightedCalories.foodList.keySet()){
					options.put(f, weightedCalories.foodList.get(f));
				}
			}else{
				for(FoodItem f : weightedCalories.foodList.keySet()){
					options.replace(f, weightedCalories.foodList.get(f) * options.get(f));
				}
			}
		}

		for(FoodItem f : options.keySet()){
			System.out.printf("food: %s\t weight: %s \n",f.getName(),options.get(f).toString());
		}

		/*Map results = sortByValues(options);
		System.out.printf("results size:%d\n",results.size());
		for(Object f : results.values()){
			if(f instanceof FoodItem){
				System.out.printf("-");
			}
		}*/
		/*for( Object f : results.keySet()){
			System.out.println(results.get(f));
			System.out.println("|");
		}*/

	}

	//Method for sorting the TreeMap based on values
  public static <K, V extends Comparable<V>> Map<K, V> 
    sortByValues(final Map<K, V> map) {
    Comparator<K> valueComparator = 
             new Comparator<K>() {
      public int compare(K k1, K k2) {
        int compare = 
              map.get(k1).compareTo(map.get(k2));
        if (compare == 0) 
          return 1;
        else 
          return compare;
      }
    };
 
    Map<K, V> sortedByValues = 
      new TreeMap<K, V>(valueComparator);
    sortedByValues.putAll(map);
    return sortedByValues;

	}
}