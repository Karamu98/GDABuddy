#include <iostream>
#include <string>

class FoodItem
{

private:
	std::string ssName;
	float fKCal;
	float fFats;
	float fSaturates;
	float fSugars;
	float fSalts;

public:
	std::string GetName();
	friend std::istream& operator >> (std::istream& in, FoodItem& obj);
	friend std::ostream& operator << (std::ostream& out, const FoodItem& obj);
	float GetKCal();
	float GetFat();
	float GetSaturates();
	float GetSugars();
	float GetSalts();

	void ChangeName(std::string a_ssNewName);
	void ChangeKCal(float a_fNewCal);
	void ChangeFat(float a_fNewFat);
	void ChangeSaturates(float a_fNewSat);
	void ChangeSugars(float a_fNewSugar);
	void ChangeSalts(float a_fNewSalt);
};