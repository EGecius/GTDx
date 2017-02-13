package com.egecius.gtdx.feature.showtasks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.egecius.gtdx.R;
import com.egecius.gtdx.datatypes.TodoTask;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

final class TasksRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	/** Notifications to clients */
	interface RemoveTaskCallback {
		void onRemoveTaskClicked(@NonNull String taskId);
	}

	@NonNull private List<TodoTask> list = new ArrayList<>();

	@Nullable private RemoveTaskCallback callback;

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

			((MyViewHolder) holder).remove.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View view) {
					onRemoveClicked(position);
				}
			});
		}
	}

	private void onRemoveClicked(final int position) {
		if (callback != null) {
			callback.onRemoveTaskClicked(list.get(position).getId());
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

	public void setRemoveTaskCallback(@NotNull final RemoveTaskCallback callback) {
		this.callback = callback;
	}

	private class MyViewHolder extends RecyclerView.ViewHolder {

		TextView title;
		Button remove;

		MyViewHolder(final View itemView) {
			super(itemView);
			title = (TextView) itemView.findViewById(R.id.title);
			remove = (Button) itemView.findViewById(R.id.removeButton);
		}
	}
}
