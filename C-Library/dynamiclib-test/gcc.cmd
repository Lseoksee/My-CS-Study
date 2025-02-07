gcc -shared -o ./lib/libcul.dll ./lib/libcul.c -fexec-charset=euc-kr
gcc src/main.c -I./include -L./lib  -lcul -o main.exe -fexec-charset=euc-kr