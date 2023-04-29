package net.javaguides.springboot.Factory;

import net.javaguides.springboot.model.Fund;

public class FundFactory {
	// factory method to create Fund object based on fund type

	public Fund createFund(String fundType) {
        if (fundType.equalsIgnoreCase("Fund")){
            Fund temp = new Fund();
		    return temp;
        }
        else{
            throw new IllegalArgumentException("Invalid product type: " + fundType);
        }
        
		
		
	}
}

