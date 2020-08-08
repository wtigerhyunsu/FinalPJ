package serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import msg.Msg;
import tcpip2.loadCellArduino;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* 2020.04.06
 * 5kg LoadCell �� Arduino ���� �����ִ� ���� ������ SerialLoadCell.java 
 * tcpip2.loadCellArduino �� �����Ͽ� sender �� ���� ������.
 * 
 * */


public class SerialLoadCell {

	// �������� -> public static sender �� ����, clientArduino���� �����ͼ� ����. //

	public static loadCellArduino loadCellArduino;
	
	public SerialLoadCell()

	{
		super();
	}

	public void connect(String portName) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			// Ŭ���� �̸��� �ĺ��ڷ� ����Ͽ� ��Ʈ ����
			CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

			if (commPort instanceof SerialPort) {
				// ��Ʈ ����(��żӵ� ����. �⺻ 9600���� ���)
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				// Input,OutputStream ���� ���� �� ���� // 
				InputStream in = serialPort.getInputStream();
				OutputStream out = serialPort.getOutputStream();

				// �б�, ���� ������ �۵� // 
 				(new Thread(new SerialReader(in))).start();
				// (new Thread(new SerialWriter(out))).start();

			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	/** */
	// �Ƶ��̳� �ø��� ������ ���� //
	public static class SerialReader implements Runnable {
		InputStream in;
		String message = "";

		public SerialReader(InputStream in) {
			this.in = in;
		}

		public void run() {
			while (true) {
				byte[] buffer = new byte[1024];
				int len = -1;
				try {
					while ((len = this.in.read(buffer)) > -1) {
						Thread.sleep(1000);
						message = new String(buffer, 0, len);
						
						/* ���� message �� value : �� �ް� 0 �� �ް� , g �� �޴� ���� 
						 * --> ���� : ���ٿ� �� �������� �Ͽ���. 
						 * buffer �� ���� ���� �� �־� println �̻��
						 */
						System.out.print(message);
						
						String getValue = "";
						for (int i = 0; i < message.length(); i++) {
							if (48 <= message.charAt(i) && message.charAt(i) <= 57) {
								getValue += message.charAt(i);
							}
						}
						// getValue �� ���ڰ��� �����Ͽ� ��´�. // 
						if (getValue != null && getValue != "") {
							System.out.println("------------------------");
							System.out.println("get Only Int Value: " + getValue);
							System.out.println("------------------------");
						}
						// �º� Ip, port �� ���� �õ� // 
							loadCellArduino = new loadCellArduino("70.12.224.75", 9999);
							Msg msg = new Msg("loadCellArduino", "" + getValue, null);
							System.out.println("Sendmsg: " + msg.getId() + " " + msg.getTxt() + " " + msg.getTid());

						// ����� 1�ʿ� �ѹ��� ���� ��������, ������ ���ÿ��� �� �뵵�� ���� �޸��� �ʿ䰡 �ִ�. ( 1�ð� �� �ѹ��� üũ�ص� �����ٰ� ���� ) 
							Thread.sleep(1000);
							tcpip2.loadCellArduino.sender.setMsg(msg);
							new Thread(tcpip2.loadCellArduino.sender).start();
							Thread.sleep(1000);
						// }

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** */
// �ø��� ������ �۽� -- �ʿ�� ��� // 
	public static class SerialWriter implements Runnable {
		OutputStream out;

		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		public void run() {
			try {
				int c = 0;
				while ((c = System.in.read()) > -1) {
					this.out.write(c);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		try {
			// ��ġ�����ڿ��� ��Ʈ��ȣ�� �׻� Ȯ���ؾ� �Ѵ�. COM3 COM4 COM13 COM11 �� // 
			(new SerialLoadCell()).connect("COM3"); // �Է��� ��Ʈ�� ����
			System.out.println("ArduinoPort Connected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}