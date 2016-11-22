/*
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package quiz.student.result;


import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * An advanced bar chart with a variety of controls.
 *
 * @see javafx.scene.chart.BarChart
 * @see javafx.scene.chart.Chart
 * @see javafx.scene.chart.NumberAxis
 * @see javafx.scene.chart.XYChart
 */
public class ChartAdvancedBar extends Application {

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        root.getChildren().add(createChart());
    }

    protected BarChart<String, Number> createChart() {
        final String[] years = {"Last Month", "Last Quarter", "Last year"};
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis,"",null));
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        // setup chart
        bc.setTitle("Scores");
        xAxis.setLabel("Time");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(years)));
        yAxis.setLabel("Score");
        // add starting data
        XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
        series1.setName("Easy");
        XYChart.Series<String,Number> series2 = new XYChart.Series<String,Number>();
        series2.setName("Medium");
        XYChart.Series<String,Number> series3 = new XYChart.Series<String,Number>();
        series3.setName("Hard");
        // create sample data
        series1.getData().add(new XYChart.Data<String,Number>(years[0], 10));
        series1.getData().add(new XYChart.Data<String,Number>(years[1], 20));
        series1.getData().add(new XYChart.Data<String,Number>(years[2], 30));
        series2.getData().add(new XYChart.Data<String,Number>(years[0], 45));
        series2.getData().add(new XYChart.Data<String,Number>(years[1], 48));
        series2.getData().add(new XYChart.Data<String,Number>(years[2], 95));
        series3.getData().add(new XYChart.Data<String,Number>(years[0], 100));
        series3.getData().add(new XYChart.Data<String,Number>(years[1], 50));
        series3.getData().add(new XYChart.Data<String,Number>(years[2], 20));
        bc.getData().add(series1);
        bc.getData().add(series2);
        bc.getData().add(series3);
        return bc;
    }
    
    protected BarChart<String, Number> passAndFail() {
        final String[] years = {"Last Month", "Last Quarter", "Last year"};
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis,"",null));
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        // setup chart
        bc.setTitle("Scores");
        xAxis.setLabel("Pass/Fail");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(years)));
        yAxis.setLabel("Number of Students");
        // add starting data
        XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
        series1.setName("Pass");
        XYChart.Series<String,Number> series2 = new XYChart.Series<String,Number>();
        series2.setName("Fail");
        
        // create sample data
        series1.getData().add(new XYChart.Data<String,Number>(years[0], 10));
        series1.getData().add(new XYChart.Data<String,Number>(years[1], 20));
        series1.getData().add(new XYChart.Data<String,Number>(years[2], 30));
        series2.getData().add(new XYChart.Data<String,Number>(years[0], 45));
        series2.getData().add(new XYChart.Data<String,Number>(years[1], 48));
        series2.getData().add(new XYChart.Data<String,Number>(years[2], 95));
        
        bc.getData().add(series1);
        bc.getData().add(series2);
        
        return bc;
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
