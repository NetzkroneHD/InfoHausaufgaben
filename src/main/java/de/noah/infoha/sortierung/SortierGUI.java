package de.noah.infoha.sortierung;

import de.noah.infoha.extraklassen.AsyncRun;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class SortierGUI extends JFrame {

	private static final long serialVersionUID = 5086770703247533983L;
	
	private static SortierGUI instance;

	private final JTextField textFieldEingeben;
	private final TextArea textArea;
	private final Choice choice;

	private AsyncRun asyncRun;
	
	public static void main(String[] args) {
		final SortierGUI frame = new SortierGUI();
		frame.setVisible(true);
		
	}

	
	public SortierGUI() {
		instance = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 501);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldEingeben = new JTextField();
		textFieldEingeben.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEingeben.setBounds(10, 406, 763, 20);
		contentPane.add(textFieldEingeben);
		textFieldEingeben.setColumns(10);

		JLabel lblZahlenEingeben = new JLabel("Zahlen eingeben");
		lblZahlenEingeben.setHorizontalAlignment(SwingConstants.CENTER);
		lblZahlenEingeben.setBounds(10, 381, 763, 14);
		contentPane.add(lblZahlenEingeben);

		JLabel label = new JLabel("(Format: 1, 8, 3, 7, 1, 9)");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 437, 763, 14);
		contentPane.add(label);
		
		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 43, 763, 214);
		contentPane.add(textArea);
		
		choice = new Choice();
		choice.setBounds(317, 280, 107, 20);
		choice.add("QuickSort");
		choice.add("InsertionSort");
		choice.add("BubbleSort");
		choice.add("SelectionSort");
		contentPane.add(choice);

		JButton btnSortieren = new JButton("Sortieren");
		btnSortieren.addActionListener(e -> {
			try {
				final long beforeSorting = System.currentTimeMillis();
				final String[] rawText = textFieldEingeben.getText().replace(" ", "").replace(".", "").split(",");
				final int[] toSort = new int[rawText.length];
				for(int i = 0; i < rawText.length; i++) {
					try {
						int zahl = Integer.parseInt(rawText[i]);
						toSort[i] = zahl;
					} catch (NumberFormatException e2) {
						System.out.println("Konnte String nicht in int umwandeln: \""+rawText[i]+"\"");
					}
				}

				asyncRun = new AsyncRun(() -> {
					Sort sort;

					switch (choice.getSelectedItem()) {
						case "BubbleSort":
							sort = new BubbleSort();
							break;
						case "InsertionSort":
							sort = new InsertionSort();
							break;
						case "SelectionSort":
							sort = new SelectionSort();
							break;
						case "QuickSort":
							sort = new QuickSort(0, toSort.length-1);
							break;
						default:
							System.out.println("Bitte wähle eine Methode aus.");
							return;
					}

					textArea.setText("Sortiere...");
					sort.sort(toSort);
					final StringBuilder outPut = new StringBuilder();
					for(int i = 0; i < toSort.length; i++) {
						if(i % 20 == 0 && i != 0) {
							outPut.append(toSort[i]).append(", \n");
						} else outPut.append(toSort[i]).append(", ");
					}

					outPut.append("\nSortiert nach ").append(System.currentTimeMillis() - beforeSorting).append("ms.");
					textArea.setText(outPut.toString());
					instance.toFront();

					try {
						asyncRun.stop();
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				});

			} catch (Exception e2) {
				System.out.println("Bitte gib vernünftige Zahlen an. (1, 2, 5, 9, 102, 9)");
			}
		});
		btnSortieren.setBounds(684, 347, 89, 23);
		contentPane.add(btnSortieren);

		JLabel lblAusgabe = new JLabel("Ausgabe:");
		lblAusgabe.setBounds(10, 18, 270, 14);
		contentPane.add(lblAusgabe);

		JButton btnRandom = new JButton("Zufällige Zahlen");
		btnRandom.addActionListener(e -> {
			final Random random = new Random();
			final int[] intArray = new int[random.nextInt(100000)];
			final StringBuilder sb = new StringBuilder();
			for(int i = 0; i < intArray.length; i++) {
				intArray[i] = random.nextInt(1000000);
				sb.append(intArray[i]).append(", ");
			}

			textFieldEingeben.setText(sb.toString());
		});
		btnRandom.setBounds(10, 347, 145, 23);
		contentPane.add(btnRandom);
		
	}

}
