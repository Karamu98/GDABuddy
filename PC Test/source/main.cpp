#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "../include/Item.h"
#include "../include/User.h"

void AddItem(std::vector<FoodItem> &a_vList, User &a_User);
void ListItems(std::vector<FoodItem> &a_vList);
void DisplayOptions();
void Reset(std::vector<FoodItem> &a_vList, User &a_User);

int iItemAmount;

int main()
{
	// Start

	std::string ssInput;
	// Check for Operator
	User Operator; // Stores session data

	std::fstream UserSettings; // Creates file stream 
	UserSettings.open("UserSettings", std::ios::in); // Opens "UserSettings"

	if (UserSettings.is_open()) // If the file can be opened
	{
		if (UserSettings >> Operator) // ... And can be read
		{
			UserSettings >> Operator; // Load the user profile
		}
		else
		{
			std::cout << "File Corrupt :(" << std::endl; // It's corrupt :(
		}
		UserSettings.close(); // Closes the file
	}
	else
	{
		// First time launch
		Operator.Initialise(); // Creates Object of user
	}

	// Check for food
	std::vector<FoodItem> FoodList;

	std::fstream FoodListSaved;
	FoodListSaved.open("FoodList", std::ios::in);

	if (FoodListSaved.is_open())
	{
		if (FoodListSaved >> iItemAmount)
		{
			for (int i = 0; i < iItemAmount; i++)
			{
				FoodItem temp;
				FoodListSaved >> temp;
				FoodList.push_back(temp);
			}
		}
		else
		{
			std::cout << "File Corrupt :(";
		}
		FoodListSaved.close();
	}
	else
	{
		iItemAmount = 0;
		// First time launch
	}

	system("CLS");
	Operator.Greet();
	DisplayOptions();

	// Main Loop
	while (ssInput != "5")
	{
		while (ssInput != "1" && ssInput != "2" && ssInput != "3" && ssInput != "4" && ssInput != "")
		{
			std::getline(std::cin, ssInput);
		}
		if (ssInput == "1")
		{
			Operator.ListGDA();
			std::cout << std::endl;
			Operator.ListGDAPercent();
		}
		else if (ssInput == "2")
		{
			AddItem(FoodList, Operator);
		}
		else if (ssInput == "3")
		{
			ListItems(FoodList);
		}
		else if( ssInput == "4")
		{
			Reset(FoodList, Operator);
		}
		else if( ssInput == "")
		{
			
		}
		else
		{
			return 1;
		}
		std::getline(std::cin, ssInput);
	}
	// Exit

	UserSettings.open("UserSettings", std::ios::out);
	UserSettings << Operator;
	UserSettings.close();

	FoodListSaved.open("FoodList", std::ios::out);
	FoodListSaved << iItemAmount << std::endl;
	for (int i = 0; i < iItemAmount; i++)
	{
		FoodListSaved << FoodList.at(i);
	}
	FoodListSaved.close();

	return 0;
}



void AddItem(std::vector<FoodItem> &a_vList, User &a_User)
{
	system("CLS");
	float fTempNum;
	std::string tempName;

	FoodItem currentItem;

	
	// Name
	std::cout << "Please enter your foods name." << std::endl;
	std::getline(std::cin, tempName);
	while (tempName == "")
	{
		std::getline(std::cin, tempName);
	}
	currentItem.ChangeName(tempName);

	// KCal
	std::cout << "Please enter the KCal amount" << std::endl;
	std::cin >> fTempNum;
	while (!std::cin)
	{
		std::cin.clear();
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

		std::cin >> fTempNum;
	}
	currentItem.ChangeKCal(fTempNum);
	a_User.SetKCal((a_User.GetKCal() + fTempNum));
	

	// Fat
	std::cout << "Please enter the Fat amount" << std::endl;
	std::cin >> fTempNum;
	while (!std::cin)
	{
		std::cin.clear();
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

		std::cin >> fTempNum;
	}
	currentItem.ChangeFat(fTempNum);
	a_User.SetFats((a_User.GetFats() + fTempNum));


	// Saturates
	std::cout << "Please enter the Saturates amount" << std::endl;
	std::cin >> fTempNum;
	while (!std::cin)
	{
		std::cin.clear();
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

		std::cin >> fTempNum;
	}
	currentItem.ChangeSaturates(fTempNum);
	a_User.SetSaturates((a_User.GetSaturates() + fTempNum));


	// Sugar
	std::cout << "Please enter the Sugar amount" << std::endl;
	std::cin >> fTempNum;
	while (!std::cin)
	{
		std::cin.clear();
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

		std::cin >> fTempNum;
	}
	currentItem.ChangeSugars(fTempNum);
	a_User.SetSugars((a_User.GetSugars() + fTempNum));


	// Salt
	std::cout << "Please enter the Salt amount" << std::endl;
	std::cin >> fTempNum;
	while (!std::cin)
	{
		std::cin.clear();
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

		std::cin >> fTempNum;
	}
	currentItem.ChangeSalts(fTempNum);
	a_User.SetSalts((a_User.GetSalts() + fTempNum));
	
	std::cout << std::endl;
	a_vList.push_back(currentItem); // Adds Item to list
	iItemAmount++;
	// End of Object creation

	system("CLS");
	std::cout << "Added!" << std::endl;

	DisplayOptions();
}

void ListItems(std::vector<FoodItem> &a_vList)
{
	system("CLS");
	if (iItemAmount != 0)
	{
		for (int i = 0; i < iItemAmount; i++)
		{
			std::cout << "Name: " << a_vList.at(i).GetName() << std::endl;
			std::cout << "KCal: " << a_vList.at(i).GetKCal() << std::endl;
			std::cout << "Fats: " << a_vList.at(i).GetFat() << std::endl;
			std::cout << "Saturates: " << a_vList.at(i).GetSaturates() << std::endl;
			std::cout << "Sugars: " << a_vList.at(i).GetSugars() << std::endl;
			std::cout << "Salts: " << a_vList.at(i).GetSalts() << std::endl << std::endl;
		}
	}
	DisplayOptions();
}

void DisplayOptions()
{
	std::cout << "How may I help you?\n" << "1. List GDA\n2. Add Item\n3. List Items\n4. Start New Day\n5. Exit" << std::endl;
}

void Reset(std::vector<FoodItem>& a_vList, User& a_User)
{
	a_User.Reset();
	a_vList.clear();
	iItemAmount = 0;
	system("CLS");
	DisplayOptions();
}


