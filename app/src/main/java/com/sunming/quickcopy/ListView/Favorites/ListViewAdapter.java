package com.sunming.quickcopy.ListView.Favorites;

/**
 * Created by minkyujo on 2016. 12. 11..
 */

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sunming.quickcopy.Database.MySQLiteHandler;
import com.sunming.quickcopy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends ArrayAdapter<ListViewItem> {

    private List<ListViewItem> items;
    private ArrayList<ListViewItem> itemsArray;
    private LayoutInflater inflater;
    private MySQLiteHandler ms;
    private ClipboardManager clipboardManager;

    public ListViewAdapter(Context context, int textViewResourceId,
                           ArrayList<ListViewItem> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.itemsArray = new ArrayList<ListViewItem>();
        this.itemsArray.addAll(items);
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        clipboardManager = (ClipboardManager)context.getSystemService(context.CLIPBOARD_SERVICE);
        ms = new MySQLiteHandler(context);
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflater.inflate(R.layout.favorites_listview, null);
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
                if(ms.updateMyText(item.getUUID(), item.getTitle(), item.getContents(), 0)){
                    item.setIsFavorites(0);
                    items.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        Button copyBtn = (Button)view.findViewById(R.id.copyContentsBtn);
        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clipboardManager.setText(item.getContents());
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(itemsArray);
        }
        else
        {
            for (ListViewItem wp : itemsArray)
            {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    items.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}