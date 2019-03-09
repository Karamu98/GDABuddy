#include "../include/Item.h"

std::string FoodItem::GetName()
{
	return ssName;
}

float FoodItem::GetKCal()
{
	return fKCal;
}

float FoodItem::GetFat()
{
	return fFats;
}

float FoodItem::GetSaturates()
{
	return fSaturates;
}

float FoodItem::GetSugars()
{
	return fSugars;
}

float FoodItem::GetSalts()
{
	return fSalts;
}

void FoodItem::ChangeName(std::string a_ssNewName)
{
	ssName = a_ssNewName;
}

void FoodItem::ChangeKCal(float a_iNewCal)
{
	fKCal = a_iNewCal;
}

void FoodItem::ChangeFat(float a_iNewFat)
{
	fFats = a_iNewFat;
}

void FoodItem::ChangeSaturates(float a_iNewSat)
{
	fSaturates = a_iNewSat;
}

void FoodItem::ChangeSugars(float a_iNewSugar)
{
	fSugars = a_iNewSugar;
}

void FoodItem::ChangeSalts(float a_iNewSalt)
{
	fSalts = a_iNewSalt;
}

std::istream & operator >> (std::istream & in, FoodItem & obj)
{
	in >> obj.ssName;
	in >> obj.fKCal;
	in >> obj.fFats;
	in >> obj.fSaturates;
	in >> obj.fSugars;
	in >> obj.fSalts;
	return in;
}

std::ostream & operator<<(std::ostream & out, const FoodItem & obj)
{
	out << obj.ssName << std::endl;
	out << obj.fKCal << std::endl;
	out << obj.fFats << std::endl;
	out << obj.fSaturates << std::endl;
	out << obj.fSugars << std::endl;
	out << obj.fSalts << std::endl;
	return out;
}
