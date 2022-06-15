package ir.shariaty.ganjo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class shAdapter extends RecyclerView.Adapter<shAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<shaer> shaerList;
    private List<shaer> shaerListFiltered;
    private shAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView birthdate;
        public TextView deathdate;
        public TextView   birthplace;




        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.txtname);
            birthdate = view.findViewById(R.id.txtbirth);
            deathdate = view.findViewById(R.id.txtdeath);
            birthplace = view.findViewById(R.id.txtbirthplace);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onshaerSelected(shaerListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public shAdapter(Context context, List<shaer> shaerList, shAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.shaerList = shaerList;
        this.shaerListFiltered = shaerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poets_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final shaer poets = shaerListFiltered.get(position);
        holder.name.setText(poets.getName());
        holder.birthdate.setText(poets.getBirthdate());
        holder.deathdate.setText(poets.getDeathdate());
        holder.birthplace.setText(poets.getBirthplace());


    }

    @Override
    public int getItemCount() {
        return shaerListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    shaerListFiltered = shaerList;
                } else {
                    List<shaer> filteredList = new ArrayList<>();
                    for (shaer row : shaerList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getBirthplace().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    shaerListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = shaerListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                shaerListFiltered = (ArrayList<shaer>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface shAdapterListener {
        void onshaerSelected(shaer poet2);


    }
}