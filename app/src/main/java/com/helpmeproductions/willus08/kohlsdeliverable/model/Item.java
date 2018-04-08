
package com.helpmeproductions.willus08.kohlsdeliverable.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable,Parcelable
{

    @SerializedName("itemId")
    @Expose
    private String itemId;
    @SerializedName("parentItemId")
    @Expose
    private String parentItemId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("msrp")
    @Expose
    private String msrp;
    @SerializedName("salePrice")
    @Expose
    private String salePrice;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("categoryPath")
    @Expose
    private String categoryPath;
    @SerializedName("longDescription")
    @Expose
    private String longDescription;
    @SerializedName("thumbnailImage")
    @Expose
    private String thumbnailImage;
    @SerializedName("mediumImage")
    @Expose
    private String mediumImage;
    @SerializedName("largeImage")
    @Expose
    private String largeImage;
    @SerializedName("productTrackingUrl")
    @Expose
    private String productTrackingUrl;
    @SerializedName("standardShipRate")
    @Expose
    private String standardShipRate;
    @SerializedName("marketplace")
    @Expose
    private String marketplace;
    @SerializedName("modelNumber")
    @Expose
    private String modelNumber;
    @SerializedName("sellerInfo")
    @Expose
    private String sellerInfo;
    @SerializedName("productUrl")
    @Expose
    private String productUrl;
    @SerializedName("customerRating")
    @Expose
    private String customerRating;
    @SerializedName("numReviews")
    @Expose
    private String numReviews;
    @SerializedName("customerRatingImage")
    @Expose
    private String customerRatingImage;
    @SerializedName("categoryNode")
    @Expose
    private String categoryNode;
    @SerializedName("bundle")
    @Expose
    private String bundle;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("addToCartUrl")
    @Expose
    private String addToCartUrl;
    @SerializedName("affiliateAddToCartUrl")
    @Expose
    private String affiliateAddToCartUrl;
    @SerializedName("freeShippingOver50Dollars")
    @Expose
    private String freeShippingOver50Dollars;
    @SerializedName("imageEntities")
    @Expose
    private List<ImageEntity> imageEntities = null;
    @SerializedName("offerType")
    @Expose
    private String offerType;
    @SerializedName("availableOnline")
    @Expose
    private String availableOnline;
    private final static long serialVersionUID = 3143954315364717046L;

    public Item(String itemId, String name, String salePrice, String thumbnailImage, String largeImage, String customerRating, String stock) {
        this.itemId = itemId;
        this.name = name;
        this.salePrice = salePrice;
        this.thumbnailImage = thumbnailImage;
        this.largeImage = largeImage;
        this.customerRating = customerRating;
        this.stock = stock;
    }

    protected Item(Parcel in) {
        itemId = in.readString();
        parentItemId = in.readString();
        name = in.readString();
        msrp = in.readString();
        salePrice = in.readString();
        upc = in.readString();
        categoryPath = in.readString();
        longDescription = in.readString();
        thumbnailImage = in.readString();
        mediumImage = in.readString();
        largeImage = in.readString();
        productTrackingUrl = in.readString();
        standardShipRate = in.readString();
        marketplace = in.readString();
        modelNumber = in.readString();
        sellerInfo = in.readString();
        productUrl = in.readString();
        customerRating = in.readString();
        numReviews = in.readString();
        customerRatingImage = in.readString();
        categoryNode = in.readString();
        bundle = in.readString();
        stock = in.readString();
        addToCartUrl = in.readString();
        affiliateAddToCartUrl = in.readString();
        freeShippingOver50Dollars = in.readString();
        offerType = in.readString();
        availableOnline = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(String parentItemId) {
        this.parentItemId = parentItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsrp() {
        return msrp;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getProductTrackingUrl() {
        return productTrackingUrl;
    }

    public void setProductTrackingUrl(String productTrackingUrl) {
        this.productTrackingUrl = productTrackingUrl;
    }

    public String getStandardShipRate() {
        return standardShipRate;
    }

    public void setStandardShipRate(String standardShipRate) {
        this.standardShipRate = standardShipRate;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(String sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(String customerRating) {
        this.customerRating = customerRating;
    }

    public String getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(String numReviews) {
        this.numReviews = numReviews;
    }

    public String getCustomerRatingImage() {
        return customerRatingImage;
    }

    public void setCustomerRatingImage(String customerRatingImage) {
        this.customerRatingImage = customerRatingImage;
    }

    public String getCategoryNode() {
        return categoryNode;
    }

    public void setCategoryNode(String categoryNode) {
        this.categoryNode = categoryNode;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getAddToCartUrl() {
        return addToCartUrl;
    }

    public void setAddToCartUrl(String addToCartUrl) {
        this.addToCartUrl = addToCartUrl;
    }

    public String getAffiliateAddToCartUrl() {
        return affiliateAddToCartUrl;
    }

    public void setAffiliateAddToCartUrl(String affiliateAddToCartUrl) {
        this.affiliateAddToCartUrl = affiliateAddToCartUrl;
    }

    public String getFreeShippingOver50Dollars() {
        return freeShippingOver50Dollars;
    }

    public void setFreeShippingOver50Dollars(String freeShippingOver50Dollars) {
        this.freeShippingOver50Dollars = freeShippingOver50Dollars;
    }

    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getAvailableOnline() {
        return availableOnline;
    }

    public void setAvailableOnline(String availableOnline) {
        this.availableOnline = availableOnline;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemId);
        dest.writeString(parentItemId);
        dest.writeString(name);
        dest.writeString(msrp);
        dest.writeString(salePrice);
        dest.writeString(upc);
        dest.writeString(categoryPath);
        dest.writeString(longDescription);
        dest.writeString(thumbnailImage);
        dest.writeString(mediumImage);
        dest.writeString(largeImage);
        dest.writeString(productTrackingUrl);
        dest.writeString(standardShipRate);
        dest.writeString(marketplace);
        dest.writeString(modelNumber);
        dest.writeString(sellerInfo);
        dest.writeString(productUrl);
        dest.writeString(customerRating);
        dest.writeString(numReviews);
        dest.writeString(customerRatingImage);
        dest.writeString(categoryNode);
        dest.writeString(bundle);
        dest.writeString(stock);
        dest.writeString(addToCartUrl);
        dest.writeString(affiliateAddToCartUrl);
        dest.writeString(freeShippingOver50Dollars);
        dest.writeString(offerType);
        dest.writeString(availableOnline);
    }
}
