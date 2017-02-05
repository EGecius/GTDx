package com.egecius.gtdx;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egecius.gtdx.datatypes.TodoTask;

import java.util.ArrayList;
import java.util.List;

final class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	@NonNull private List<TodoTask> list = new ArrayList<>();

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
		if (holder instanceof MyViewHolder) {
			String titleText = list.get(position).getTitle();
			((MyViewHolder) holder).title.setText(titleText);
		}
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	void updateList(@NonNull final List<TodoTask> list) {
		this.list.clear();
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	private class MyViewHolder extends RecyclerView.ViewHolder {

		TextView title;

		MyViewHolder(final View itemView) {
			super(itemView);
			title = (TextView) itemView.findViewById(R.id.title);
		}
	}
}
