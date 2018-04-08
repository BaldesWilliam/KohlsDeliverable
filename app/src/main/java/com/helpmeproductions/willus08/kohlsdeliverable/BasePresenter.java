package com.helpmeproductions.willus08.kohlsdeliverable;



public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
}
