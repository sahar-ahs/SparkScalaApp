package com.tutorial;

import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import javax.swing.text.Segment;
import java.util.ArrayList;
import java.util.List;


public class TestSort {
    public static void main(String[] args) {
        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(new String[]{"AVG_REVENUE"})
                .setOutputCol("features");
     /*   Dataset<Row> dataset = assembler.transform(dataset);

        KMeans kmeans = new KMeans().setK(3).setSeed(1L).setFeaturesCol("features");
        KMeansModel model = kmeans.fit(dataset);*/

      /*  Vector[] v=new Vector[2];
        v[0]=Vector[12,34]
        sortedResult()*/

    }
    public static List<Segment> sortedResult(Vector[] centers) {

        Long counter = 0L;
        List<Double> centroid = new ArrayList<>();
        for (Vector v : centers) {
            double[] values = ((DenseVector) v).values();
            centroid.add(values[0]);
        }
        java.util.Collections.sort(centroid);
        return null;
    }
}