package com.sunming.quickcopy.ListView;

/**
 * Created by minkyujo on 2016. 12. 11..
 */

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sunming.quickcopy.AllListTab;
import com.sunming.quickcopy.Database.MySQLiteHandler;
import com.sunming.quickcopy.R;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<ListViewItem> {

    private List<ListViewItem> items;
    private LayoutInflater inflater;
    private MySQLiteHandler ms;

    public ListViewAdapter(Context context, int textViewResourceId,
                           List<ListViewItem> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ms = new MySQLiteHandler(context);
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflater.inflate(R.layout.listview, null);
        }

        // 현재 position 의 내용을 View 로 작성하여 리턴
        final ListViewItem item = (ListViewItem) items.get(position);

        if (item != null) {

            TextView titleText = (TextView) view.findViewById(R.id.listViewText);
            titleText.setTypeface(Typeface.DEFAULT_BOLD);
            titleText.setText(item.getTitle());

        }

        Button deleteBtn = (Button)view.findViewById(R.id.deleteListBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ms.delete(item.getUUID());
            }
        });
//
//        Button copyBtn = (Button)view.findViewById(R.id.copyContentsBtn);
//        copyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainActivity)MainActivity.mContext).copyClipboard(item.getContents());
//            }
//        });

        return view;
    }

}