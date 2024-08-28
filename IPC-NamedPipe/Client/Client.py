import win32file

# 파이프 이름 (소켓에서 주소값 같은거)
PIPE_NAME = r"\\.\pipe\Test_pipe"

# 서버 파이프 연결
try:
    handle = win32file.CreateFile(
        PIPE_NAME,
        win32file.GENERIC_READ | win32file.GENERIC_WRITE,
        0, 
        None, 
        win32file.OPEN_EXISTING, 
        0, 
        None
    )
except Exception as e:
    # 서버 실행 안하면
    raise Exception("서버에 연결할 수 없음")

print(f"서버에 연결완료")

# 서버에게 메시지 전송
message = "잘 되나요?"
win32file.WriteFile(handle, message.encode('utf-8'))

# 서버 응답 수신 (서버에서 보낸 메시지 없으며 여기서 흐름 중단)
response = win32file.ReadFile(handle, 64*1024)
serverMessge: bytes =response[1]
print(f"[서버]: {(serverMessge.decode("utf-8"))}")

if 'handle' in locals():
    win32file.CloseHandle(handle)
    print("파이프 종료")