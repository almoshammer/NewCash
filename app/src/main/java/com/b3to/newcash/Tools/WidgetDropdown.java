package com.b3to.newcash.Tools;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.IdRes;

import java.util.ArrayList;

public class WidgetDropdown {

    AutoCompleteTextView dropDown;
    ArrayList<CList> list;
    View view;
    int mPosition = -1;
    public WidgetDropdown(View view, @IdRes int resource_id) {
        this.view = view;
        dropDown = view.findViewById(resource_id);
        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
                dropDown.setError(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPosition = -1;
            }
        });
        dropDown.setOnItemClickListener((parent, view1, position, id) -> {
            mPosition = position;
            dropDown.setError(null);
        });
    }

    public AutoCompleteTextView getDropDown() {
        return dropDown;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setList(ArrayList<CList> list) {
        if (this.list != null) this.list.clear();
        this.list = list;

        ArrayList<String> plst = new ArrayList<>();
        for (CList item : list) {
            plst.add(item.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, plst);
        dropDown.setAdapter(adapter);
    }

    public int getSelectedIndex() {
        return mPosition;
    }

    public long getId(int position) {
        return list.get(position).getId();
    }

    public long getSelectedId() {
        if (mPosition > -1 && mPosition < list.size())
            return list.get(getSelectedIndex()).getId();
        return -1;
    }

    public void setId(long id) {
        if (list == null || list.size() == 0) return;
        for (int pos = 0; pos < list.size(); pos++) {
            if (id == list.get(pos).getId()) {
                mPosition = pos;
                dropDown.setText(list.get(pos).getName(), false);
                break;
            }
        }
    }

    public String getName(int position) {
        return list.get(position).getName();
    }

    public boolean isEmpty() {


        return false;
    }

    public static class CList {
        private long id;

        private String name;

        public CList(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
