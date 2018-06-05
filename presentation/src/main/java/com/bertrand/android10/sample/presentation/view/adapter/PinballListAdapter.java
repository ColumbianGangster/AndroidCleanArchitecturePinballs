
package com.bertrand.android10.sample.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bertrand.android10.sample.presentation.R;
import com.bertrand.android10.sample.presentation.model.UiPinballMatchModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PinballListAdapter extends RecyclerView.Adapter<PinballListAdapter.PinballMatchViewHolder> {

    private List<UiPinballMatchModel> pinballMatchModels;
    private final LayoutInflater layoutInflater;

    @Inject
    PinballListAdapter(Context context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.pinballMatchModels = Collections.emptyList();
    }

    @Override public int getItemCount() {
        return (this.pinballMatchModels != null) ? this.pinballMatchModels.size() : 0;
    }

    @Override public PinballMatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_pinball_match, parent, false);
        return new PinballMatchViewHolder(view);
    }

    @Override public void onBindViewHolder(PinballMatchViewHolder holder, final int position) {
        final UiPinballMatchModel pinballMatchModel = this.pinballMatchModels.get(position);
        holder.tvPinballMatch.setText(pinballMatchModel.getPinballMatch());
        holder.tvPinballMatchPointTotal.setText(Integer.toString(pinballMatchModel.getPinballMatchPointsTotal()));
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void setPinballMatches(Collection<UiPinballMatchModel> usersCollection) {
        this.pinballMatchModels = (List<UiPinballMatchModel>) usersCollection;
        this.notifyDataSetChanged();
    }

    static class PinballMatchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pinball_match) TextView tvPinballMatch;
        @BindView(R.id.pinball_match_points_total) TextView tvPinballMatchPointTotal;
        PinballMatchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
