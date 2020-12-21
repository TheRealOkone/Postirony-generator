#include <iostream>
#include <cstdio>
#include <io.h>
#include <thread>
#include <windows.h>
#include <urlmon.h>
#include <string>
#include <fstream>
#include <algorithm>
#include <filesystem>
#include <vector>
#include <queue>
#include <sstream>
#include <codecvt>
#include <ctime>
#include <sys\types.h> 
#include <sys\stat.h>
#include <random>
#pragma comment (lib, "urlmon.lib")

using namespace std;


void prior() {
	PROCESS_INFORMATION hProc;
	//HANDLE WH = GetForegroundWindow();

	HANDLE curProcess = GetCurrentProcess();

	SetPriorityClass(curProcess, HIGH_PRIORITY_CLASS);

}

bool fnd(vector <int> ss, int a) {
	vector<int>::iterator it = ss.begin();
	for (it; it < ss.end(); it++) {
		if (*it == a) { return 1; }
	}
	return 0;

}

int goh() {
	int i;
	random_device rd;
	mt19937 mt(rd());
	i = mt() % 100000;
	return i;
}

void pinger() {

	bool n = 0;
	string start, st;
	ifstream N0;
	ofstream N1;

	N0.open("KN\\ping.txt");
	while (getline(N0, start)) {
		st += start;
	}
	if (st == "0") {
		n = 1;
	}
	N0.close();

	N1.open("KN\\ping.txt");
	N1 << n;
	N1.close();


}
void del()
{
	SHFILEOPSTRUCT FileOp;

	FileOp.hwnd = NULL;
	FileOp.wFunc = FO_DELETE;
	FileOp.pFrom = "KZ\\*";
	FileOp.pTo = NULL;
	FileOp.fFlags = FOF_NOCONFIRMATION;
	FileOp.lpszProgressTitle = NULL;

	SHFileOperation(&FileOp);
	std::cout << GetLastError() << std::endl;

}


void delk()
{
	SHFILEOPSTRUCT FileOp;

	FileOp.hwnd = NULL;
	FileOp.wFunc = FO_DELETE;
	FileOp.pFrom = "K\\*";
	FileOp.pTo = NULL;
	FileOp.fFlags = FOF_NOCONFIRMATION;
	FileOp.lpszProgressTitle = NULL;

	SHFileOperation(&FileOp);
	std::cout << GetLastError() << std::endl;

}
void delabs() {

	del();
	delk();

}

void ex0() {
	int reint;
	string re, start = "";
	ifstream R;
	R.open("exit.txt");
	while (getline(R, start)) {
		re += start;
	}
	R.close();
	if (re != "") {
		reint = stoi(re);
		if (reint != 0) {

			delabs();

			exit(5);
		}
	}


}

/*
bool ex() {
	bool reload;
	int reint;
	string re, start = "";
	ifstream R;
	R.open("exit.txt");
	while (getline(R, start)) {
		re += start;
	}
	if (re != "") {
		reint = stoi(re);
		if (reint == 0) {
			reload = 0;
		}
		else {
			reload = 1;
			delabs();
			exit(0);
		}
	}
	else {
		reload = 0;
	}
	R.close();
	return reload;

}*/


bool us(vector<string> ssimg, vector<string> sstxt) {
	bool h = 0;
	int i;
	i = 0;
	string s, sr, re, start;
	ifstream O, R, test, test1;
	while (true) {
		i++;
		if (i > 500)
			break;
		test.open("KZ\\image.jpg");
		test1.open("KZ\\request.txt");
		if (!test.is_open() || !test1.is_open()) {
			s = ssimg.back();
			sr = sstxt.back();
			O.open(s);
			if (O.is_open()) {
				O.close();
				rename(s.c_str(), "KZ\\image.jpg");
				rename(sr.c_str(), "KZ\\request.txt");
				h = 1;
			}
			else {
				O.close();

			}
		}
		else {
			break;
		}
		test.close();
		test1.close();

	}
	return h;
}



std::wstring readFile(LPCWSTR filename)
{
	std::wifstream wif(filename);
	wif.imbue(std::locale(std::locale::empty(), new std::codecvt_utf8<wchar_t>));
	std::wstringstream wss;
	wss << wif.rdbuf();
	wif.close();
	return wss.str();
}

__int64 FileSize64(const char* szFileName)
{
	struct __stat64 fileStat;
	int err = _stat64(szFileName, &fileStat);
	if (0 != err) return 0;
	return fileStat.st_size;
}
string conw(wstring a) {

	char alf[] = { 'а','б','в','г','д','е','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я' };
	wchar_t alf0[] = { L'а',L'б',L'в',L'г',L'д',L'е',L'ж',L'з',L'и',L'й',L'к',L'л',L'м',L'н',L'о',L'п',L'р',L'с',L'т',L'у',L'ф',L'х',L'ц',L'ч',L'ш',L'щ',L'ъ',L'ы',L'ь',L'э',L'ю',L'я' };
	wchar_t alf1[] = { L'А',L'Б',L'В',L'Г',L'Д',L'Е',L'Ж',L'З',L'И',L'Й',L'К',L'Л',L'М',L'Н',L'О',L'П',L'Р',L'С',L'Т',L'У',L'Ф',L'Х',L'Ц',L'Ч',L'Ш',L'Щ',L'Ъ',L'Ы',L'Ь',L'Э',L'Ю',L'Я' };
	string b = "";
	for (int i = 0; i < a.size(); i++) {
		for (int j = 0; j < 32; j++) {
			//if (!(towlower(a[i]) >= L'а' && towlower(a[i]) <= L'я')) { break; }
			if (a[i] == alf0[j] || a[i] == alf1[j]) { b += alf[j]; break; }
		}
	}
	return b;
}
vector <string>& gen(vector <string>& z)
{
	vector<wstring> cons;
	int iii, ijk, iiio;
	while (cons.size() < 30) {
		iii = goh();
		iiio = goh();
		int jjj = 1;
		bool rd = 0;
		wstring buf0, buf00 = L"";
		string askline = "", askline0 = "";
		wstring fr = L"https://www.litmir.me/br/?b=";
		wstring frl = fr + to_wstring(iii);
		wstring ud = L"K//text" + to_wstring(iii) + L".txt";
		string dele = "K//text" + to_string(iii) + ".txt";


		LPCWSTR frl0 = frl.c_str();
		LPCWSTR ud0 = ud.c_str();
		HRESULT h = URLDownloadToFileW(0, frl0, ud0, 0, 0);
		wstring kekek;
		kekek = readFile(ud0);
		remove(dele.c_str());
		for (ijk = 2; ijk < kekek.size(); ijk++) {
			if (kekek[ijk] == '<' || (kekek[ijk] - 'a' >= 0 && kekek[ijk] < 'z')) {
				rd = 0;
			}
			if (kekek[ijk - 2] == '<' && kekek[ijk - 1] == 'p' && kekek[ijk] == '>') {
				rd = 1;
				ijk++;
			}
			if (kekek[ijk] == '<' && kekek[ijk + 1] == '/' && kekek[ijk + 2] == 'p' && kekek[ijk + 3] == '>') {
				rd = 0;
			}

			if (rd) {
				if (kekek[ijk] == ' ' || kekek[ijk] == '.' || kekek[ijk] == '-') {
					if (buf00.size() < 15) {
						cons.push_back(buf00);
					}
					buf00 = L"";
				}
				else {
					if (!(kekek[ijk] >= 'a' && kekek[ijk] <= 'z') && !(kekek[ijk] >= '0' && kekek[ijk] <= '9'))
						buf00 += towlower(kekek[ijk]);
				}
			}
		}

	}
	ijk = 0;
	while (z[0].size() < 1 || z[1].size() < 1 || z[2].size() < 1) {
		z[0] = conw(cons[(iiio + ijk) % cons.size()]);
		z[1] = conw(cons[(iiio + 1 + ijk) % cons.size()]);
		z[2] = conw(cons[(iiio + 2 + ijk) % cons.size()]);
		ijk += 3;
	}

	return z;
}
vector <string>& u1(vector <string>& b) {
	vector <string> z = { "","","" };
	gen(z);
	b[0] = z[0];
	b[2] = z[1];
	b[4] = z[2];
	char alf[] = { 'а','б','в','г','д','е','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я' };
	string alf0[] = { "%d0%b0","%d0%b1","%d0%b2","%d0%b3","%d0%b4","%d0%b5","%d0%b6","%d0%b7","%d0%b8","%d0%b9","%d0%ba","%d0%bb","%d0%bc","%d0%bd","%d0%be","%d0%bf","%d1%80","%d1%81","%d1%82","%d1%83","%d1%84","%d1%85","%d1%86","%d1%87","%d1%88","%d1%89","%d1%8a","%d1%8b","%d1%8c","%d1%8d","%d1%8e","%d1%8f" };
	for (int qwe = 0; qwe < b[0].size(); qwe++) {
		for (int qwer = 0; qwer < 32; qwer++) {
			if (b[0][qwe] == alf[qwer]) { b[1] += alf0[qwer]; break; }
		}
	}
	for (int qwe = 0; qwe < b[2].size(); qwe++) {
		for (int qwer = 0; qwer < 32; qwer++) {
			if (b[2][qwe] == alf[qwer]) { b[3] += alf0[qwer]; break; }
		}
	}
	for (int qwe = 0; qwe < b[4].size(); qwe++) {
		for (int qwer = 0; qwer < 32; qwer++) {
			if (b[4][qwe] == alf[qwer]) { b[5] += alf0[qwer]; break; }
		}
	}
	return b;
}

//string* u2(int i) {
//	string* b;
//	b = new string[2];
//	string* c;
//	c = new string[2];
//	c = gen();
//	b[0] = c[1];
//	char alf[] = { 'а','б','в','г','д','е','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я' };
//	string alf0[] = { "%d0%b0","%d0%b1","%d0%b2","%d0%b3","%d0%b4","%d0%b5","%d0%b6","%d0%b7","%d0%b8","%d0%b9","%d0%ba","%d0%bb","%d0%bc","%d0%bd","%d0%be","%d0%bf","%d1%80","%d1%81","%d1%82","%d1%83","%d1%84","%d1%85","%d1%86","%d1%87","%d1%88","%d1%89","%d1%8a","%d1%8b","%d1%8c","%d1%8d","%d1%8e","%d1%8f" };
//	for (int qwe = 0; qwe < b[0].size(); qwe++) {
//		for (int qwer = 0; qwer < 32; qwer++) {
//			if (b[0][qwe] == alf[qwer]) { b[1] += alf0[qwer]; }
//		}
//	}
//	return b;
//}


string cp1251_to_utf8(const char* str)
{
	string res;
	WCHAR* ures = NULL;
	char* cres = NULL;

	int result_u = MultiByteToWideChar(1251, 0, str, -1, 0, 0);
	if (result_u != 0)
	{
		ures = new WCHAR[result_u];
		if (MultiByteToWideChar(1251, 0, str, -1, ures, result_u))
		{
			int result_c = WideCharToMultiByte(CP_UTF8, 0, ures, -1, 0, 0, 0, 0);
			if (result_c != 0)
			{
				cres = new char[result_c];
				if (WideCharToMultiByte(CP_UTF8, 0, ures, -1, cres, result_c, 0, 0))
				{
					res = cres;
				}
			}
		}
	}

	delete[] ures;
	delete[] cres;

	return res;
}





string* lbr(string req, int counter,string tt) {

	//string b = "kek.txt";
	bool reqw;

	int ik = 0, j = 0;
	//int k = 0;

	//HRESULT h = URLDownloadToFile(0, TEXT(a.c_str()), TEXT(b.c_str()), 0, 0);

	reqw = 1;
	bool sup = 0;


	ifstream F, K;
	ofstream  testre;

	//пути


	//testre.open("testre.txt");



	string fileline = "", buf, bufname, bufnametxt;
	bufname = "KZ\\buf.jpg";
	bufnametxt = "KZ\\buftxt.txt";

	string test = "https://", test0 = "";

	//char x;


	//__int64 fs, fstxt;
	//string Kline = "";



	//string testtxt = "html";
	//string testtxt0 = "popup";
	//string testtxt1 = "funct";
	//string testtxt2 = "image";
	//string testtxt3 = "tag";
	//string testtxt4 = "100%";
	//string testtxt5 = "div";

	ifstream J;



	//	F.open(b.c_str());
	//	while (getline(F, buf)) {
	//		fileline += buf;
	//	}
	//	F.close();
	//buf = "";
	//bool load = 1;
	//int i0;
	//for (ik = 0; ik<1; ik++) {

	//	if ((fileline[i - 1] != '(') && (fileline[i] == test[0]) && (fileline[i + 1] == test[1]) && (fileline[i + 2] == test[2]) && (fileline[i + 3] == test[3]) && (fileline[i + 4] == test[4]) && (fileline[i + 5] == test[5]) && (fileline[i + 6] == test[6]) && (fileline[i + 7] == test[7])) {


			//load = 1;
			//j = i;
			//x = fileline[j];
			//while (fileline[j] != '"') {
			//	x = fileline[j];

			//	test0 += x;
			//	j++;
			//}
			//k++;



			//testre << test0 << endl;
	//ifstream SS;
	//SS.open("C:\\Postironia\\K\\spisline.txt");
	//while (getline(SS, buf)) {
		//test0 = tt;


		J.open("KZ\\buf.jpg");
		if (!J.is_open()) {




			//				if (test0.size() > 40 && test0.size() < 250) {
			//Kline = "";
			HRESULT rero = URLDownloadToFile(0, TEXT(tt.c_str()), TEXT(bufnametxt.c_str()), 0, 0);
			//fstxt = FileSize64(bufnametxt.c_str());
			/*
			K.open(bufnametxt.c_str());

			while (getline(K, buf)) {
				Kline += buf;
			}
			K.close();
			for (i0 = 0; i0 + 5 < Kline.size(); i0++) {
				if (((Kline[i0] == testtxt[0]) && (Kline[i0 + 1] == testtxt[1]) && (Kline[i0 + 2] == testtxt[2]) && (Kline[i0 + 3] == testtxt[3])) || ((Kline[i0] == testtxt0[0]) && (Kline[i0 + 1] == testtxt0[1]) && (Kline[i0 + 2] == testtxt0[2]) && (Kline[i0 + 3] == testtxt0[3]) && (Kline[i0 + 4] == testtxt0[4])) || ((Kline[i0] == testtxt1[0]) && (Kline[i0 + 1] == testtxt1[1]) && (Kline[i0 + 2] == testtxt1[2]) && (Kline[i0 + 3] == testtxt1[3]) && (Kline[i0 + 4] == testtxt1[4])) || ((Kline[i0] == testtxt2[0]) && (Kline[i0 + 1] == testtxt2[1]) && (Kline[i0 + 2] == testtxt2[2]) && (Kline[i0 + 3] == testtxt2[3]) && (Kline[i0 + 4] == testtxt2[4])) || ((Kline[i0] == testtxt3[0]) && (Kline[i0 + 1] == testtxt3[1]) && (Kline[i0 + 2] == testtxt3[2])) || ((Kline[i0] == testtxt4[0]) && (Kline[i0 + 1] == testtxt4[1]) && (Kline[i0 + 2] == testtxt4[2]) && (Kline[i0 + 3] == testtxt4[3])) || ((Kline[i0] == testtxt5[0]) && (Kline[i0 + 1] == testtxt5[1]) && (Kline[i0 + 2] == testtxt5[2]))) {
					load = 0;

					break;
				}
			}*/

			//if (load) {

				URLDownloadToFile(0, TEXT(tt.c_str()), TEXT(bufname.c_str()), 0, 0);


				//fs = FileSize64(bufname.c_str());

			//}
			//		load = 1;

					//buf = "";


//					}


					//test0 = "";

		}
		//else {


		//	sup = 1;
		//	load = 0;
		//}
		J.close();
//	}
	//	}
	//	if (sup) {
	//		ik = 0;
	//		//k = 0;
	//		//break;
	//	}
	//}

	//K.open(bufnametxt.c_str());

	//while (getline(K, buf)) {
	//	Kline += buf;
	//}
	//K.close();
	//for (i0 = 0; i0 + 5 < Kline.size(); i0++) {
	//	if (((Kline[i0] == testtxt[0]) && (Kline[i0 + 1] == testtxt[1]) && (Kline[i0 + 2] == testtxt[2]) && (Kline[i0 + 3] == testtxt[3])) || ((Kline[i0] == testtxt0[0]) && (Kline[i0 + 1] == testtxt0[1]) && (Kline[i0 + 2] == testtxt0[2]) && (Kline[i0 + 3] == testtxt0[3]) && (Kline[i0 + 4] == testtxt0[4])) || ((Kline[i0] == testtxt1[0]) && (Kline[i0 + 1] == testtxt1[1]) && (Kline[i0 + 2] == testtxt1[2]) && (Kline[i0 + 3] == testtxt1[3]) && (Kline[i0 + 4] == testtxt1[4])) || ((Kline[i0] == testtxt2[0]) && (Kline[i0 + 1] == testtxt2[1]) && (Kline[i0 + 2] == testtxt2[2]) && (Kline[i0 + 3] == testtxt2[3]) && (Kline[i0 + 4] == testtxt2[4])) || ((Kline[i0] == testtxt3[0]) && (Kline[i0 + 1] == testtxt3[1]) && (Kline[i0 + 2] == testtxt3[2])) || ((Kline[i0] == testtxt4[0]) && (Kline[i0 + 1] == testtxt4[1]) && (Kline[i0 + 2] == testtxt4[2]) && (Kline[i0 + 3] == testtxt4[3])) || ((Kline[i0] == testtxt5[0]) && (Kline[i0 + 1] == testtxt5[1]) && (Kline[i0 + 2] == testtxt5[2]))) {
	//		remove(bufnametxt.c_str());
	//		remove(bufname.c_str());
	//		reqw = 0;
	//		break;
	//	}
	//}
	string z, zr;
	string hhh = "KZ\\image";
	string hhhr = "KZ\\request";
	while (true) {
		z = hhh + to_string(counter) + ".jpg";

		ifstream S;
		S.open(z);
		if (!S.is_open()) {
			S.close();
			break;
		}
		S.close();
		counter++;
		counter > 500 ? ik = 1 : 0;

	}
	zr = hhhr + to_string(counter) + ".txt";
	rename(bufname.c_str(), z.c_str());
	rename(bufnametxt.c_str(), zr.c_str());
	req = cp1251_to_utf8(req.c_str());


	ofstream KK;
	KK.open(zr);

	KK << req;


	KK.close();

	remove(bufnametxt.c_str());
	string* fg;
	fg = new string[2];
	fg[0] = z;
	fg[1] = zr;
	return fg;

}




string* zapros() {
	//string mod1 = "https://yandex.ru/images/search?text=", mod2 = "&stype=image&lr=10748&source=wiz", mod2alt = "&from=tabbar";	//константы запроса
	//string mod1alt = "https://yandex.ru/images/search?from=tabbar&text=";
	//string mod1alt2 = "https://www.google.ru/search?q=", mod2alt2 = "&newwindow=1&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjCk5qNmZLoAhVNAxAIHVmkA-kQ_AUoAXoECAwQAw&biw=946&bih=954";
	string mod1alt3 = "https://www.bing.com/images/search?q=", mod2alt3 = "&FORM=HDRSC2";
	//string mod1alt4 = "https://duckduckgo.com/?q=", mod2alt4 = "&iar=images&iax=images&ia=images";
	//string mod1alt5 = "https://images.rambler.ru/search?query=", mod2alt5 = "&utm_source=search&utm_content=search_img&utm_medium=menu&utm_campaign=self_promo";
	//string mod1alt6 = "https://go.mail.ru/search_images?q=", mod2alt6 = " ";

	//bool useyandex = 0;
	//bool usegoogle = 0;
	//bool useduckduckgo = 0;
	//bool userambler = 0;
	//bool useyahoo = 0;


	string a;
	string* ask1, * ask2, * ask3;
	ask1 = new string[2];
	ask2 = new string[2];
	ask3 = new string[3];
	vector <string> buftext = { "","","","","","" };
	u1(buftext);
	ask1[0] = buftext[0];
	ask1[1] = buftext[1];
	ask2[0] = buftext[2];
	ask2[1] = buftext[3];
	ask3[0] = buftext[4];
	ask3[1] = buftext[5];

	//if (useyahoo) {
	//	a = mod1alt6 + ask1[1] + "+" + ask2[1] + mod2alt6;
	//}
	//else {
	//	if (userambler) {
	//		a = mod1alt5 + ask1[1] + "+" + ask2[1] + mod2alt5;
	//	}
	//	else {
	//		if (useduckduckgo) {
	//			a = mod1alt4 + ask1[1] + "+" + ask2[1] + mod2alt4;
	//		}
	//		else {
	//			if (useyandex) {
	//				a = mod1alt + ask1[1] + "%20" + ask2[1];
	//			}
	//			else {
	//				if (usegoogle) {
	//					a = mod1alt2 + ask1[1] + "+" + ask2[1] + mod2alt2;
	//				}
	//				else {
	//					a = mod1alt3 + ask1[1] + "+" + ask2[1] + mod2alt3;
	//				}
	//			}
	//		}
	//	}
	//}

	a = mod1alt3 + ask1[1] + "+" + ask2[1] + "+" + ask3[1] + mod2alt3;

	string req;
	req = ask1[0] + " " + ask2[0] + " " + ask3[0];
	string* retu;
	retu = new string[2];
	retu[0] = a;
	retu[1] = req;
	ex0();
	return retu;
}


string f1p(string a) {
	string b = "kek0.txt";
	char x;
	string test = "murl";
	int i, j;
	HRESULT h = URLDownloadToFile(0, TEXT(a.c_str()), TEXT(b.c_str()), 0, 0);
	ifstream F;
	string eline, buf, t;
	F.open(b.c_str());
	while (getline(F, buf)) {
		eline += buf;
	}
	F.close();
	//ofstream SP;
	//SP.open("C:\\Postironia\\K\\spisline.txt");
	for (i = 1; i + 7 < eline.size(); i++) {

		if ((eline[i - 1] != '(') && (eline[i] == test[0]) && (eline[i + 1] == test[1]) && (eline[i + 2] == test[2]) && (eline[i + 3] == test[3])) {


			i += 17;
			j = i;
			x = eline[j];
			while (eline[j] != '"' && eline[j] != ')' && !(eline[j] == 'q' && eline[j + 1] == 'u' && eline[j + 2] == 'o' && eline[j + 3] == 't') && eline[j] != '&' && !(eline[j - 4] == '.' && eline[j - 3] == 'j' && eline[j - 2] == 'p' && eline[j - 1] == 'g') && !(eline[j - 5] == '.' && eline[j - 4] == 'j' && eline[j - 3] == 'p' && eline[j - 2] == 'e' && eline[j - 1] == 'g')) {
				x = eline[j];

				t += x;
				j++;
			}




			//SP << t << endl;
			
			//if (eline[j - 3] == '.' && eline[j - 2] == 'j' && eline[j - 1] == 'p' && eline[j] == 'g') { SP << endl; }
			break;
		}
	}
	//SP.close();
	return t;
}

void parse() {
	int counter = 0;
	ofstream R0;
	R0.open("exit.txt");
	R0.close();
	int chsz;
	vector<string> ssimg;
	vector<string> sstxt;
	vector<int> ssisz;
	string* a;
	a = new string[2];
	string* b;
	b = new string[2];
	string tt;
	while (true) {
		counter++;
		a = zapros();

		tt=f1p(a[0]);

		b = lbr(a[1], counter,tt);
		char* bb;
		bb = new char[b[0].size() + 1];
		strcpy_s(bb, b[0].size() + 1, b[0].c_str());
		chsz = FileSize64(bb);
		delete[] bb;
		if (!fnd(ssisz, chsz)) {

			ssimg.push_back(b[0]);
			sstxt.push_back(b[1]);
			ssisz.push_back(chsz);

			if (us(ssimg, sstxt)) {
				ssimg.pop_back();
				sstxt.pop_back();
				ssisz.pop_back();
			}
		}
		else {
			remove(b[0].c_str());
			remove(b[1].c_str());
		}


		pinger();

		ex0();
		if (us(ssimg, sstxt)) {
			ssimg.pop_back();
			sstxt.pop_back();
			ssisz.pop_back();
		}

	}
}





int main() {
	//srand(time(NULL));
	CreateDirectory("K", NULL);
	CreateDirectory("KZ", NULL);
	system("start Postironia.jar");
	system("start konets.exe");
	delabs();
	prior();
	//int i, q, w, e;
	//time_t t;
	//q = goh();
	//Sleep(99);
	//w = goh();
	//Sleep(99);
	//e = goh();
	//t = time(NULL) + q % 10000;
	//i = q % 10000;
	//thread t1(parse, i, &t);
	//Sleep(300);
	//t = time(NULL) + w % 10000;
	//i = w % 10000;
	//thread t2(parse, i, &t);
	//Sleep(300);
	//t = time(NULL) + e % 10000;
	//i = e % 10000;
	//thread t3(parse, i, &t);
	parse();
	//t1.join();
	//t2.join();
	//t3.join();

	delabs();

}