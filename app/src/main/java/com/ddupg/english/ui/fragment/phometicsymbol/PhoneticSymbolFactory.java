package com.ddupg.english.ui.fragment.phometicsymbol;

import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.ddupg.english.R;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

public class PhoneticSymbolFactory {

  public static QMUICommonListItemView listItemView(QMUIGroupListView groupListView, PhoneticSymbolAdapter phoneticSymbol) {
    QMUICommonListItemView itemView = groupListView.createItemView(phoneticSymbol.getName());
    itemView.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
    return itemView;
  }

  public static View.OnClickListener listItemViewListener(final FragmentActivity activity, final PhoneticSymbolAdapter phoneticSymbol, final PhoneticSymbolFragment fragment) {
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        fragment.changePhoneticSymbol(phoneticSymbol);
        activity.getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.name())
            .commit();
      }
    };
  }

}
