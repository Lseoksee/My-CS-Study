cl /LD lib/libcul.c /source-charset:utf-8 /execution-charset:euc-kr /Fe"lib/libcul.dll"
cl /I include src/main.c /source-charset:utf-8 /execution-charset:euc-kr /link /LIBPATH:lib libcul.lib