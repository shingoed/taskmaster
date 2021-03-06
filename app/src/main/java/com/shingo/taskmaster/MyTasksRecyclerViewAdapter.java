package com.shingo.taskmaster;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.ListTaskmastersQuery;
import com.shingo.taskmaster.TasksFragment.OnListFragmentInteractionListener;
import com.shingo.taskmaster.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTasksRecyclerViewAdapter extends RecyclerView.Adapter<MyTasksRecyclerViewAdapter.ViewHolder> {

    static final String TAG = "shingo.ViewAdapter";
    private List<ListTaskmastersQuery.Item> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTasksRecyclerViewAdapter(List<ListTaskmastersQuery.Item> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tasks, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).title());
        holder.mBodyView.setText(mValues.get(position).description());
//        holder.mStateView.setText(mValues.get(position).

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i = new Intent(v.getContext(),TaskDetail.class);
              i.putExtra("title",holder.mTitleView.getText());
              i.putExtra("state",holder.mStateView.getText());
              i.putExtra("body",holder.mBodyView.getText());
              v.getContext().startActivity(i);
            }
        });
    }

    public void setItems(List<ListTaskmastersQuery.Item> items){
        this.mValues = items;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mBodyView;
        public final TextView mStateView;
        public ListTaskmastersQuery.Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            mBodyView = (TextView) view.findViewById(R.id.body);
            mStateView = (TextView) view.findViewById(R.id.state);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mBodyView.getText() + "'";
        }
    }
}
