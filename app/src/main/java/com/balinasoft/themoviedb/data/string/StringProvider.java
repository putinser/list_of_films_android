package com.balinasoft.themoviedb.data.string;

import androidx.annotation.StringRes;

public interface StringProvider {

    String getString(@StringRes int id);

    String getString(@StringRes int resId, Object... formatArgs);

    String getQuantityString(int resId, int quantiry, Object... formatArgs);
}
