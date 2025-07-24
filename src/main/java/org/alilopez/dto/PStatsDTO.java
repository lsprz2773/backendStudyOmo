package org.alilopez.dto;

import java.util.ArrayList;
import java.util.List;

public class PStatsDTO {
    ArrayList<String> labels = new ArrayList<>();
    ArrayList<Float> data = new ArrayList<Float>();

    public PStatsDTO(ArrayList<String> labels, ArrayList<Float> data) {
        this.labels = labels;
        this.data = data;
    }
}
