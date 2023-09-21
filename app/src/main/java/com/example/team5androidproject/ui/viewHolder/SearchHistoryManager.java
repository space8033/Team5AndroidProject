package com.example.team5androidproject.ui.viewHolder;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchHistoryManager {
    private static final String SEARCH_HISTORY_PREFS = "search_history_prefs";
    private static final String SEARCH_HISTORY_KEY = "search_history_key";

    private final SharedPreferences sharedPreferences;

    public SearchHistoryManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SEARCH_HISTORY_PREFS, Context.MODE_PRIVATE);
    }

    public List<String> getSearchHistory() {
        String historyString = sharedPreferences.getString(SEARCH_HISTORY_KEY, "");
        if (TextUtils.isEmpty(historyString)) {
            return new ArrayList<>();
        } else {
            // 저장된 문자열을 쉼표로 분리하여 리스트로 변환
            String[] historyArray = historyString.split(",");
            return new ArrayList<>(Arrays.asList(historyArray));
        }
    }

    public void saveSearchHistory(List<String> searchHistory) {
        // 리스트를 쉼표로 연결한 문자열로 변환
        String historyString = TextUtils.join(",", searchHistory);

        // 저장
        sharedPreferences.edit().putString(SEARCH_HISTORY_KEY, historyString).apply();
    }
}