package com.linxz.core.ui.recycle;

import java.util.ArrayList;

/**
 * @author linxz
 */


public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    public String getJsonData() {
        return mJsonData;
    }

    public void clearData(){
        ENTITIES.clear();
    }
}
