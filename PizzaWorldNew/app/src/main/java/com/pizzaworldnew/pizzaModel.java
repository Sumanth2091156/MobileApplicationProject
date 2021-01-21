package com.pizzaworldnew;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class pizzaModel implements Parcelable{
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;
    @SerializedName("image")
    String image;
    @SerializedName("description")
    String description;

    protected pizzaModel(Parcel in) {
        name = in.readString();
        price = in.readString();
        image = in.readString();
        description = in.readString();

    }

    public static final Parcelable.Creator<pizzaModel> CREATOR = new Parcelable.Creator<pizzaModel>() {
        @Override
        public pizzaModel createFromParcel(Parcel in) {
            return new pizzaModel(in);
        }

        @Override
        public pizzaModel[] newArray(int size) {
            return new pizzaModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(image);
        parcel.writeString(description);

    }


}
