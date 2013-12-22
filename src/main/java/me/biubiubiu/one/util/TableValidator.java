package me.biubiubiu.one.util;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TableValidator {

    private TableLayout mTableView;

    public TableValidator(TableLayout table) {
        mTableView = table;
    }

    public boolean validate() {
        for (int i = 0; i < mTableView.getChildCount(); i++) {
            ViewGroup row = (ViewGroup)mTableView.getChildAt(i);
            if (row.getChildCount() < 2) {
                throw new RuntimeException("child count must excceed two");
            }

            String title = ((TextView)row.getChildAt(0)).getText().toString();
            String message = title + "不能为空";

            View fieldView = row.getChildAt(1);
            if (fieldView instanceof TextView) {
                String field = ((TextView)fieldView).getText().toString();
                if (TextUtils.isEmpty(field)) {
                    Toast.makeText(mTableView.getContext(),
                                   message, Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        }

        return true;
    }
}
