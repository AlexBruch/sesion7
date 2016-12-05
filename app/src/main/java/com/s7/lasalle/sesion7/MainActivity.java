package com.s7.lasalle.sesion7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView List;
    private ArrayList<ItemList> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items();
    }

    public void items() {
        List = (RecyclerView) findViewById(R.id.recycler_view);
        List.setHasFixedSize(true);

        itemList = new ArrayList<>();
        itemList.add(new ItemList("WAZAAAA", "wea", "4"));
        itemList.add(new ItemList("WAZAAAAAA", "weaaa", "5"));

        Adapter adapter = new Adapter(itemList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        List.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(List.getContext(), layoutManager.getOrientation());
        List.addItemDecoration(dividerItemDecoration);

        List.setItemAnimator(new DefaultItemAnimator());
        List.setAdapter(adapter);
    }

    public static class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {

        private OnItemClickListener onItemClickListener;
        private ArrayList<ItemList> listaItems;

        public Adapter(ArrayList<ItemList> data) {this.listaItems = data;}

        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(view);

            return viewHolder;
        }

        public void onBindViewHolder(ItemViewHolder holder, int position) {
            final ItemList itemList = listaItems.get(position);
            holder.bindItem(itemList);
        }

        @Override
        public int getItemCount() {
            return listaItems.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView Name;
            private TextView Description;
            private TextView Price;

            public ItemViewHolder(View view) {
                super(view);

                Name = (TextView) view.findViewById(R.id.name);
                Description = (TextView) view.findViewById(R.id.details);
                Price = (TextView) view.findViewById(R.id.price);

                view.setOnClickListener(this);
            }

            public void bindItem(ItemList r) {
                Name.setText(r.getName());
                Description.setText(r.getDetails());
                Price.setText(r.getPrice());
            }

            @Override
            public void onClick(View view) {
                if(onItemClickListener != null) onItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
