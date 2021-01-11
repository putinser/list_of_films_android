package com.balinasoft.themoviedb.data.string;

import android.content.Context;

import androidx.annotation.StringRes;

public class StringProviderImp implements StringProvider {

    private Context context;

    public StringProviderImp(Context context) {
        this.context = context;
    }

    @Override
    public String getString(@StringRes int id) {
        return context.getString(id);
    }

    @Override
    public final String getString(@StringRes int resId, Object... formatArgs) {
        return context.getString(resId, formatArgs);
    }

    @Override
    public String getQuantityString(int resId, int quantiry, Object... formatArgs) {
        return context.getResources().getQuantityString(resId, quantiry, formatArgs);
    }
}
