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

public class SerialConnect implements SerialPortEventListener {

	CommPortIdentifier commPortIdentifier;
	CommPort comPort;
	InputStream in;
	BufferedInputStream bin;
	OutputStream out;

	public SerialConnect() {
	}

	public SerialConnect(String portName) throws Exception {
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
		    System.out.println("Receive Low Data:" + ss + "||");
		    
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   break;
		  }

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
		SerialConnect sc = null;
		try {

			// Serial port COM12 //
			sc = new SerialConnect("COM12");
		} catch (NoSuchPortException e) {

			e.printStackTrace();
		}

	}
	
	

}
