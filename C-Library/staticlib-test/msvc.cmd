cl /c lib/libcul.c /source-charset:utf-8 /execution-charset:euc-kr  /Fo:lib/libcul.obj
lib /out:lib/libcul.lib lib/libcul.obj
cl /I include src/main.c /source-charset:utf-8 /execution-charset:euc-kr /link /LIBPATH:lib libcul.lib