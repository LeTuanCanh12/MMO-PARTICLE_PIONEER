package FE;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import BE.ExcelReader;
import BE.Particel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUIX {
	static String filePath = " ";
	static Particel tool = new Particel();

	public static void main(String[] args) {
		// Tạo một JFrame để chứa giao diện
		JFrame frame = new JFrame("GPT on Twitter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Tạo JTabbedPane để chứa hai tab "Search" và "Newfeed"
		JTabbedPane tabbedPane = new JTabbedPane();

		// Tạo panel cho tab "Search"
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(5, 2));

		JTextField key = new JTextField();

		// Tạo các thành phần cho panel "Search"
		JLabel importLabel = new JLabel("Import file excel:");
		JButton importButton = new JButton("Browse");

		// Xử lý sự kiện khi người dùng nhấn nút "Browse"
		importButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Tạo JFileChooser
				JFileChooser fileChooser = new JFileChooser();

				// Chỉ cho phép chọn file có phần mở rộng là .xlsx
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx");
				fileChooser.setFileFilter(filter);

				// Hiển thị hộp thoại chọn file
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					// Lấy đường dẫn của file được chọn
					File selectedFile = fileChooser.getSelectedFile();
					filePath = selectedFile.getAbsolutePath();

				}
			}
		});

		// Thêm các thành phần vào panel "Search"
		searchPanel.add(importLabel);
		searchPanel.add(importButton);
		// theem the thoi gian
		searchPanel.add(new JLabel("Time:"));
		JTextField textTime = new JTextField();
		searchPanel.add(textTime);

		searchPanel.add(new JLabel("Address:"));
		JTextField textBonus = new JTextField();
		searchPanel.add(textBonus);

		JButton check = new JButton("Kiểm tra tiến độ");

		searchPanel.add(check);

		JTextArea outPutTotalSearch = new JTextArea();
		searchPanel.add(outPutTotalSearch);

		check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
					try {
						tool.action(textBonus.getText());
					} catch (Exception e1) {
						// TODO: handle exception
						System.out.println(e1);
						tool.reset();
						
					}
				

			}
		});
		searchPanel.add(btnStart);

		JButton btnStop = new JButton("Stop");
		searchPanel.add(btnStop);

		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				System.exit(0);

			}
		});

		// Thêm panel "Search" vào JTabbedPane
		tabbedPane.addTab("List", searchPanel);

		// Tạo panel cho tab "onlyone"
		JPanel newfeedPanel = new JPanel();
		newfeedPanel.setLayout(new GridLayout(5, 2));

		// Thêm các thành phần vào panel "Newfeed"
		newfeedPanel.add(new JLabel("Link:"));
		JTextField quantity = new JTextField();
		newfeedPanel.add(quantity);

		newfeedPanel.add(new JLabel("Time:"));
		JTextField timeInput = new JTextField();
		newfeedPanel.add(timeInput);

		newfeedPanel.add(new JLabel("Text:"));
		JTextField textInput = new JTextField();
		newfeedPanel.add(textInput);

		JButton checkSum = new JButton("Kiểm tra tiến độ");
		newfeedPanel.add(checkSum);

		JTextArea outPutnewfeed = new JTextArea();
		newfeedPanel.add(outPutnewfeed);

		// Thêm panel "Newfeed" vào JTabbedPane
		tabbedPane.addTab("Only One", newfeedPanel);

		// Thêm JTabbedPane vào JFrame
		frame.add(tabbedPane);

		// Thiết lập kích thước và hiển thị JFrame
		frame.setSize(400, 300);
		frame.setVisible(true);
	}

}