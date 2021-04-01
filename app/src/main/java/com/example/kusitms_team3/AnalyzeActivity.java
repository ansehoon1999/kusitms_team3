package com.example.kusitms_team3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AnalyzeActivity extends AppCompatActivity {
    private PieChart pieChart;
    private ImageButton imageButton;

    public static ArrayList nameList = new ArrayList(); // 사람들의 이름을 담은 list
    public static ArrayList percentList = new ArrayList();
    public static String mypercent;
    public Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        pieChart = findViewById(R.id.activity_main_piechart);
        imageButton = findViewById(R.id.imageButton2);
        Bundle bundle = getIntent().getExtras();

        nameList = bundle.getStringArrayList("namearray");
        percentList = bundle.getStringArrayList("percentarray");

        setupPieChart();
        loadPieChartData();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), portfolio.class);
                intent.putExtra("namearray", nameList);
                intent.putExtra("percentarray", percentList);
                startActivity(intent);
                finish();

            }
        });

    }
        private void setupPieChart() {
            pieChart.setDrawHoleEnabled(true);
            pieChart.setUsePercentValues(true);
            pieChart.setEntryLabelTextSize(10);
            pieChart.setEntryLabelColor(Color.BLACK);
            pieChart.setHoleRadius(80f);
            pieChart.getDescription().setEnabled(false);


            Legend l = pieChart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);
            l.setEnabled(false);
        }

        private void loadPieChartData() {
            ArrayList<PieEntry> entries = new ArrayList<>();

            for(int i =0 ;i < nameList.size();i ++) {
                int a = (int) percentList.get(i);
                entries.add(new PieEntry( (float) a, nameList.get(i).toString()));

            }

            final int[] MY_COLORS = { Color.rgb(143,123,133), Color.rgb(106,81,94), Color.rgb(131,186,234), Color.rgb(156,167,250), Color.rgb(243,204,211), Color.rgb(231,168,157)
                   };

            ArrayList<Integer> colors = new ArrayList<>();
            for (int color: MY_COLORS) {
                colors.add(color);
            }

            for (int color: ColorTemplate.PASTEL_COLORS) {
                colors.add(color);
            }

            PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
            dataSet.setColors(colors);

            PieData data = new PieData(dataSet);
            data.setDrawValues(true);
            data.setValueFormatter(new PercentFormatter(pieChart));
            data.setValueTextSize(10);
            data.setValueTextColor(Color.BLACK);

            pieChart.setData(data);
            pieChart.invalidate();

            pieChart.animateY(1400, Easing.EaseInOutQuad);
        }
    }