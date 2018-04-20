package com.example.android.abqunlicensedvehicles;

import android.content.Context;
import android.os.Build;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;



public class StableArrayAdapter extends ArrayAdapter<String> {

    final int INVALID_ID = -1;

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }

        try {
            String item = getItem(position);
            return mIdMap.get(item);
        } catch (IndexOutOfBoundsException e) {
            return position;
        }
    }

    @Override
    public boolean hasStableIds() {
        return android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }

}
