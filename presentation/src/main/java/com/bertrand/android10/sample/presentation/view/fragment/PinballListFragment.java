package com.bertrand.android10.sample.presentation.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bertrand.android10.sample.presentation.R;
import com.bertrand.android10.sample.presentation.internal.di.components.PinBallListComponent;
import com.bertrand.android10.sample.presentation.model.UiPinballMatchModel;
import com.bertrand.android10.sample.presentation.presenter.PinballListPresenter;
import com.bertrand.android10.sample.presentation.view.PinballMatchListView;
import com.bertrand.android10.sample.presentation.view.adapter.PinballListAdapter;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PinballListFragment extends BaseFragment implements PinballMatchListView {
    @Inject
    PinballListPresenter presenter;

    @Inject
    PinballListAdapter pinballListAdapter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
//    @BindView(R.id.rl_progress) RelativeLayout rl_progress;
//    @BindView(R.id.rl_retry) RelativeLayout rl_retry;
//    @BindView(R.id.bt_retry) Button bt_retry;

    private Unbinder unbinder;

    public PinballListFragment() {
        setRetainInstance(true);
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(PinBallListComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        setupRecyclerView();
        if (savedInstanceState == null) {
            this.loadPinballMatchList();
        }
    }

    @Override public void onResume() {
        super.onResume();
        this.presenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.presenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
        unbinder.unbind();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override public void showPinballMatches(Collection<UiPinballMatchModel> pinballMatchModelCollection) {
        if (pinballMatchModelCollection != null) {
            this.pinballListAdapter.setPinballMatches(pinballMatchModelCollection);
        } else {
            this.pinballListAdapter.setPinballMatches(new ArrayList<>());
        }
    }

    @Override
    public boolean isReady() {
        return isAdded();
    }

    private void setupRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(pinballListAdapter);
    }

    private void loadPinballMatchList() {
        this.presenter.initialize();
    }
}
