#ifdef CREATEDLL_EXPORTS
#define CUL_API __declspec(dllexport)
#else
#define CUL_API __declspec(dllimport)
#endif
// MSVC DLL 생성용 해더 
 
CUL_API void a();
CUL_API void b();
CUL_API void c();
