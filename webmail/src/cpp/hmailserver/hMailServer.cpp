//Include JNI.H file which is available with JDK1.3 to tell complier
//Open Tools|Option|directories and include the proper dir. so that
//complier can open it for reading otherwise fatal error will be 
//reported.
#include 
//Allready in the working dir.
#include "hMailServer.h"
#include 
//Normal for the Com Client
#include "..\Java_COM_ATL\Java_COM_ATL.h"
#include 

//Refere JNI API or get the following native method signature from
//HelloCo.H file This is the method called by JavaClient.

JNIEXPORT jobjectArray JNICALL Java_com_ivata_groupware_business_mail_server_HMailServer_getUserAliases
  (JNIEnv *, jobject, jstring, jstring)
{
	 

// Copy the following from the Java_COM_ATL_i.c file
// from the Java_COM_ATL project directory
//This number may be diff. as it generated by VC++ using uuidgen.exe.

	const IID IID_IJcom = {0x665A282D,0x3ECD,0x11D5,{0xAC,0x5C,0xDF,0xF9,0xE8,0x6D,0xD6,0x2D}};
	const CLSID CLSID_Jcom = {0x665A282E,0x3ECD,0x11D5,{0xAC,0x5C,0xDF,0xF9,0xE8,0x6D,0xD6,0x2D}};

// Declare and HRESULT and a pointer to the Java_COM_ATL interface
	HRESULT			hr;
// Interface creadted in Part I
	IJcom		*IJComAtl;

	// Now we will intilize COM
	hr = CoInitialize(0);
	if(SUCCEEDED(hr))
	{
		hr = CoCreateInstance( CLSID_Jcom, NULL, CLSCTX_INPROC_SERVER,
						IID_IJcom, (void**) &IJComAtl);
		
		// If we succeeded then call the AddNumbers method, if it failed
		// then display an appropriate message to the user.
		if(SUCCEEDED(hr))
		{
			long ReturnValue;

			hr = IJComAtl->AddNumbers(5, 7, &ReturnValue);
			cout << "The answer for 5 + 7 is: " << ReturnValue << endl;
			hr = IJComAtl->Release();  
		}
		else
		{
			printf("CoCreateInstance Failed.");
		}
	}
	// Uninitialize COM
	CoUninitialize();

    printf("Hello Com is ok and over!\n");
    return;
}