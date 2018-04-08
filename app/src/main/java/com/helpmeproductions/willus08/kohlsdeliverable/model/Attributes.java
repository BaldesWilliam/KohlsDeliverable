
package com.helpmeproductions.willus08.kohlsdeliverable.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("productUrlText")
    @Expose
    private String productUrlText;
    @SerializedName("size")
    @Expose
    private String size;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductUrlText() {
        return productUrlText;
    }

    public void setProductUrlText(String productUrlText) {
        this.productUrlText = productUrlText;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
