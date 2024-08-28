package com.seok;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;

public class App {
    public static void main(String[] args) throws Exception {
        // 파이프 이름 (소켓에서 주소값 같은거)
        String pipeName = "\\\\.\\pipe\\Test_pipe";

        // 망명 파이프 생성
        WinNT.HANDLE handle = Kernel32.INSTANCE.CreateNamedPipe(
            pipeName,
            WinBase.PIPE_ACCESS_DUPLEX,
            WinBase.PIPE_TYPE_MESSAGE | WinBase.PIPE_READMODE_MESSAGE | WinBase.PIPE_WAIT,
            1,
            65536,
            65536,
            0,
            null
        );

        // 생성 잘됬는지 확인
        if (WinBase.INVALID_HANDLE_VALUE.equals(handle)) {
            System.out.println("파이프 생성 실패");
            return;
        }

        System.out.println("파이프 생성 성공");

        // 여기서 클라이언트 연결을 대기시킴
        boolean connected = Kernel32.INSTANCE.ConnectNamedPipe(handle, null);

        if (!connected) {
            System.out.println("연결 실패");
            Kernel32.INSTANCE.CloseHandle(handle);
            return;
        }

        // 클라이언트한테 전송
        String message = "안녕";
        byte[] encodeMessge = message.getBytes("utf-8");
        Kernel32.INSTANCE.WriteFile(handle, encodeMessge, encodeMessge.length, null, null);
        
        // 클라이언트 데이터 읽기 (클라이언트에서 보낸 데이터 없으면 여기서 흐름 중단)
        byte[] buffer = new byte[1024];
        IntByReference bytesRead = new IntByReference();  //바이트 해석을 위한
        boolean result = Kernel32.INSTANCE.ReadFile(handle, buffer, buffer.length, bytesRead, null);

        if (result) {
            String response = new String(buffer, 0, bytesRead.getValue(), "utf-8");
            System.out.println("[클라이언트]: " + response);
        } else {
            System.out.println("클라이언트꺼 읽지 못함");
        }
        
        
        System.out.println("파이프 종료");
        Kernel32.INSTANCE.CloseHandle(handle);
    }
}
