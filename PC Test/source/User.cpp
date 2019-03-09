#include "../include/User.h"

User::User()
{
	fKCal = 0;
	fFats = 0;
	fSaturates = 0;
	fSugars = 0;
	fSalts = 0;
}

void User::Greet()
{
	std::cout << "Hey, " << ssName << "! Let's begin!" << std::endl << std::endl;
}

void User::Initialise()
{
	std::string temp;
	std::cout << "What's your name?" << std::endl;
	std::getline(std::cin, temp);
	while (temp == "")
	{
		std::getline(std::cin, temp);
	}
	ssName = temp;
	std::cout << "Are you..." << std::endl << "1. Male" << std::endl << "2. Female" << std::endl;
	std::getline(std::cin, temp);
	while (temp != "Male" && temp != "Female" && temp != "1" && temp != "2" && temp != "")
	{
		std::getline(std::cin, temp);
	}

	if (temp == "Male" || temp == "1")
	{
		bIsMale = true;
	}
	else
	{
		bIsMale = false;
	}
}

void User::Reset()
{
	fKCal = 0;
	fFats = 0;
	fSaturates = 0;
	fSugars = 0;
	fSalts = 0;
}

void User::ListGDA()
{
	system("CLS");
	std::cout << "KCal: " << fKCal << std::endl;
	std::cout << "Fats: " << fFats << std::endl;
	std::cout << "Saturates: " << fSaturates << std::endl;
	std::cout << "Sugars: " << fSugars << std::endl;
	std::cout << "Salts: " << fSalts << std::endl << std::endl;

}

void User::ListGDAPercent()
{
	std::cout << "KCal: " << GetKCalPercent() << "%"<< std::endl;
	std::cout << "Fats: " << GetFatsPercent() << "%" << std::endl;
	std::cout << "Saturates: " << GetSaturatesPercent() << "%" << std::endl;
	std::cout << "Sugars: " << GetSugarsPercent() << "%" << std::endl;
	std::cout << "Salts: " << GetSaltsPercent()  << "%" << std::endl << std::endl;

	std::cout << "How may I help you?\n" << "1. List GDA\n2. Add Item\n3. List Items\n4. Start New Day\n5. Exit" << std::endl;

}

void User::SetKCal(float a_iKCal)
{
	fKCal = a_iKCal;
}

void User::SetFats(float a_iFats)
{
	fFats = a_iFats;
}

void User::SetSaturates(float a_iSaturates)
{
	fSaturates = a_iSaturates;
}

void User::SetSugars(float a_iSugars)
{
	fSugars = a_iSugars;
}

void User::SetSalts(float a_iSalts)
{
	fSalts = a_iSalts;
}

std::string User::GetName()
{
	return ssName;
}

float User::GetKCal()
{
	return fKCal;
}

float User::GetFats()
{
	return fFats;
}

float User::GetSaturates()
{
	return fSaturates;
}

float User::GetSugars()
{
	return fSugars;
}

float User::GetSalts()
{
	return fSalts;
}

float User::GetKCalPercent()
{
	if (bIsMale)
	{
		return ((fKCal / 2500) * 100);
	}
	else
	{
		return ((fKCal / 2000) * 100);
	}
}

float User::GetFatsPercent()
{
	if (bIsMale)
	{
		return ((fFats / 95) * 100);
	}
	else
	{
		return ((fFats / 70) * 100);
	}
}

float User::GetSaturatesPercent()
{
	if (bIsMale)
	{
		return ((fSaturates / 30) * 100);
	} 
	else
	{
		return ((fSaturates / 20) * 100);
	}
}

float User::GetSugarsPercent()
{
	if (bIsMale)
	{
		return ((fSugars / 120) * 100);
	}
	else
	{
		return ((fSugars / 90) * 100);
	}
}

float User::GetSaltsPercent()
{
	return ((fSalts / 6) * 100);
}

std::istream & operator >> (std::istream & in, User & obj)
{
	in >> obj.bIsMale;
	in >> obj.ssName;
	in >> obj.fKCal;
	in >> obj.fFats;
	in >> obj.fSaturates;
	in >> obj.fSugars;
	in >> obj.fSalts;
	return in;
}

std::ostream & operator<<(std::ostream & out, const User & obj)
{
	out << obj.bIsMale << std::endl;
	out << obj.ssName << std::endl;
	out << obj.fKCal << std::endl;
	out << obj.fFats << std::endl;
	out << obj.fSaturates << std::endl;
	out << obj.fSugars << std::endl;
	out << obj.fSalts << std::endl;
	return out;
}
