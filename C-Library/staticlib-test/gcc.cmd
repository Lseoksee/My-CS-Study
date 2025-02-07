gcc -c lib/libcul.c -o lib/libcul.o -fexec-charset=euc-kr
ar rcs lib/libcul.a lib/libcul.o
gcc src/main.c -fexec-charset=euc-kr -I./include -L./lib  -lcul -o main.exe