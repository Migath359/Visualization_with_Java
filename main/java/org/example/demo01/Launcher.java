package org.example.demo01;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import org.example.demo01.Szemely;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class Launcher {

    private static List<Szemely> szemelyek = new ArrayList<>();
    private static JFreeChart chart;
    private static int[] letszam;

    public static void main(String[] args) {
        String[] nevek = {"David", "Angel", "Mike", "Lisa", "Victor", "Xerxes"};
        List<Integer> ages = new ArrayList<>();
        Random rnd = new Random();
        int min = 6, max = 30;
        letszam = new int[max];
        for(int i = 0; i<50; i++)
        {
            int currIndex = rnd.nextInt(nevek.length);
            String currNev = nevek[currIndex];
            int currKor = rnd.nextInt(min, max);
            if (!ages.contains(currKor))
            {
                ages.add(currKor);
            }
            szemelyek.add(new Szemely(currNev, currKor));
            letszam[currKor-1] += 1;
            System.out.println(currNev + " - " + currKor);
        }
        Collections.sort(ages);

        JFrame frame = new JFrame("Életkor gyakoriság diagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Map<Integer, Integer> gyakorisag = new HashMap<>();

        for (Szemely sz : szemelyek) {
            int kor = sz.getKor();
            gyakorisag.put(kor, gyakorisag.getOrDefault(kor, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<Integer, Integer> entry : gyakorisag.entrySet()) {
            dataset.addValue(entry.getValue(), "Darabszám", entry.getKey());
        }

        chart = ChartFactory.createBarChart(
                "Életkor gyakoriság",
                "Életkor",
                "Fő",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);

        JButton saveButton = new JButton("Mentés PNG-be");
        saveButton.addActionListener(e -> mentesPNG());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private static void mentesPNG() {
        try {
            File file = new File("diagram.png");
            ChartUtils.saveChartAsPNG(file, chart, 800, 600);
            JOptionPane.showMessageDialog(null, "Sikeres mentés: diagram.png");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hiba mentés közben!");
            e.printStackTrace();
        }
    }

}
