#include <string>
#include <iostream>

class User
{
private:
	bool bIsMale;
	std::string ssName;

	float fKCal;
	float fFats;
	float fSaturates;
	float fSugars;
	float fSalts;

public:
	User();

	friend std::istream& operator >> (std::istream& in, User& obj);
	friend std::ostream& operator << (std::ostream& out, const User& obj);

	void Greet();
	void Initialise();
	void Reset();
	void ListGDA();
	void ListGDAPercent();

	void SetKCal(float a_iKCal);
	void SetFats(float a_iFats);
	void SetSaturates(float a_iSaturates);
	void SetSugars(float a_iSugars);
	void SetSalts(float a_iSalts);

	std::string GetName();
	float GetKCal();
	float GetFats();
	float GetSaturates();
	float GetSugars();
	float GetSalts();

	float GetKCalPercent();
	float GetFatsPercent();
	float GetSaturatesPercent();
	float GetSugarsPercent();
	float GetSaltsPercent();
};
