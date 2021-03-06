package com.s7.lasalle.sesion7;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by alexbruch on 5/12/16.
 */

public class AdapterItem extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<ItemList> info = Collections.emptyList();

    /** ADAPTADOR PER CONTEXT I INFO DE MainActivity **/

    public AdapterItem(Context context, List<ItemList> info) {
        this.context = context;
        this.info = info;
        inflater = LayoutInflater.from(context);
    }

    /** PER OMPLIR EL LAYOUT QUAN TENIM EL VIEWHOLDER **/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    /** ENLLACEM L'INFORMACIÓ **/

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        //Pillem posició
        ItemList current = info.get(position);
        //Enllacem info de la llista
        myHolder.name.setText(current.name);
        myHolder.details.setText(current.details);
        myHolder.price.setText(current.price);
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    /** CONSTRUCTOR PER ELS TEXTVIEW **/

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView name, details, price;

        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            details = (TextView) itemView.findViewById(R.id.details);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
