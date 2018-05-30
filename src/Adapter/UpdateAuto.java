package Adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName);
		//This function searches the Model for a given OptionSet and sets the name of OptionSet to
		//newName.
	public void updateOptionPrice(String Modelname, String Optionname, float newprice);
		//This function searches the Model for a given OptionSet and Option name, and sets the price to
		//newPrice.
	public void updateOptionName(String autoName, String optionName, String newOptionName);
		//Function to update an option's name, given the automobile name
	public void deleteOption(String autoName, String optionName);
		//Function to delete an option given the automobile name

}
