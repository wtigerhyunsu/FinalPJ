package serial;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialReadWrite implements SerialPortEventListener {

	CommPortIdentifier commPortIdentifier;
	CommPort comPort;
	InputStream in;
	BufferedInputStream bin;
	OutputStream out;

	public SerialReadWrite() {
	}

	public SerialReadWrite(String portName) throws Exception {
		commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		// ���� Ȯ�� //
		System.out.println("Identififier Verified");

		connect();
		
		System.out.println("Com Port Connected!");
		new Thread(new SerialWrite()).start();
		System.out.println("Start Can Network..");

	}

	public void connect() throws Exception {
		if (commPortIdentifier.isCurrentlyOwned()) {
			// �̹� ������̸� ���Ұ� //
			System.out.println("Port is already in use.");
		} else {
			// 5000ms ���� ������ ������ Exception //
			comPort = commPortIdentifier.open(this.getClass().getName(), 5000);
			// comPort �� SerialPort �� �ƴϸ� �۾��� �� ����. //
			if (comPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) comPort;
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(921600, // ��żӵ�
						SerialPort.DATABITS_8, // ������ ��Ʈ - CAN ����� B Ÿ�� �����ͱ���
						SerialPort.STOPBITS_1, // stop ��Ʈ
						SerialPort.PARITY_NONE); // �и�Ƽ

				in = serialPort.getInputStream();
				// TCPIP �� Input �� Output Stream �� ����� ���� //
				// BufferedInputStream �� �̿��� �����ϰ� ������ Read //
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();

			} else {
				System.out.println("This port is not a Serial Port.");
			}
		}
	}


	// CAN ������� �����Ͱ� ������ Event �� �߻��Ѵ�. //
	@Override
	public void serialEvent(SerialPortEvent event) {
		  switch (event.getEventType()) {
		  case SerialPortEvent.BI:
		  case SerialPortEvent.OE:
		  case SerialPortEvent.FE:
		  case SerialPortEvent.PE:
		  case SerialPortEvent.CD:
		  case SerialPortEvent.CTS:
		  case SerialPortEvent.DSR:
		  case SerialPortEvent.RI:
		  case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
		   break;
		  case SerialPortEvent.DATA_AVAILABLE:
		   byte[] readBuffer = new byte[128];

		   try {

		    while (bin.available() > 0) {
		     int numBytes = bin.read(readBuffer);
		    }

		    String ss = new String(readBuffer);
		    
		    if(ss.charAt(1)=='W') {
		    	System.out.println("Send Low Data:" + ss + "||");
		    }else {
		    	System.out.println("Receive Low Data:" + ss + "||");
		    }
		    
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   break;
		  }

	}
	
	//  msg �� �����带 ������� ���� // 
	public void send(String msg) {
		new Thread(new SerialWrite(msg)).start();
		
	}
	
	
	// Write �ð��� �����ɸ��ų� ������� �־ Thread�� ���� // 
	class SerialWrite implements Runnable{
		
		
		//CAN �� �����͸� String ���� �ְ�޴´�. //
		String data;
		
		public SerialWrite() {
			// DATA ǥ�ؽ� :G11A9 ���� \r// 
			// ���۹��� 1�� ��ɹ��� 1�� �����͹��� ~�� CheckSum 2�� �� 1��  //  
			// ':' = ���� 'G' = ��� '11' = ������ 'A9' = CheckSum '\r' = ��  //  
			this.data = ":G11A9\r";
		}
		
		public SerialWrite(String msg) {
			this.data = convertData(msg);
		}

		public String convertData(String msg) {
			msg = msg.toUpperCase(); // ��ҹ��� ���� ����
			msg = "W28"+msg; // ��ɾ� W28 
			// W28 00000000 0000000000000000 //
			char c [] = msg.toCharArray();
			int checkSum = 0;
			for(char ch:c) {
				checkSum += ch;
			}
			checkSum = (checkSum & 0xff); // and ���� + 0xff 
			String result = ":";
			result += msg + Integer.toHexString(checkSum).toUpperCase() + "\r";
			
			return result;
		}

		@Override
		public void run() {
			byte[] outData = data.getBytes();
			// Can Network Area �� Data �� ���� // 
			try {
				out.write(outData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		SerialReadWrite sc = null;
		String id = "00000000";
		String data = "0000000000000000";
		String msg = id+data;
		// CAN msg �� 4�ڸ��� ���̵� + 8�ڸ��� ������ �� ���� // 
		try {

			// Serial port COM12 //
			sc = new SerialReadWrite("COM4");
			sc.send(msg);
		} catch (NoSuchPortException e) {

			e.printStackTrace();
		}

	}
	
	

}
