package istic.fr.star2bb.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import istic.fr.star2bb.R;

/**
 * Created by romai on 05/01/2018.
 */

class SpinnerLigneAdapter extends BaseAdapter
{
    List<String[][]> lignes;
    Context context;

    public SpinnerLigneAdapter(Context context, List<String[][]> lignes)
    {
        this.context=context;
        this.lignes = lignes;
    }
    @Override
    public int getCount()
    {
        return lignes.size();
    }
    @Override
    public Object getItem(int arg0)
    {
        return lignes.get(arg0);
    }
    @Override
    public long getItemId(int arg0)
    {
        return arg0;
    }
    @Override
    public View getView(int pos, View view, ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        TextView txv=(TextView)view.findViewById(android.R.id.text1);
        String [][] strings = (String[][]) getItem(pos);
        txv.setBackgroundColor(Color.parseColor("#"+strings[0][1]));
        txv.setTextSize(20f);
        txv.setText( strings[0][0]);
        return view;
    }

}