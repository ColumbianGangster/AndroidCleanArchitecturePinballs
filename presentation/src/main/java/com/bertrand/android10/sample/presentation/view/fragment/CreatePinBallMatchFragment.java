package com.bertrand.android10.sample.presentation.view.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bertrand.android10.sample.domain.DomainPinballMatchModel;
import com.bertrand.android10.sample.presentation.R;
import com.bertrand.android10.sample.presentation.internal.di.components.CreatePinballComponent;
import com.bertrand.android10.sample.presentation.presenter.CreatePinballMatchPresenter;
import com.bertrand.android10.sample.presentation.view.CreatePinballView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CreatePinBallMatchFragment extends BaseFragment implements CreatePinballView {

    @BindView(R.id.create_pinball_match_et)
    EditText createPinballMatchEditText;

    @BindView(R.id.create_pinball_match_tv)
    TextView pointsTotal;

    @Inject
    CreatePinballMatchPresenter presenter;

    private Unbinder unbinder;

    public CreatePinBallMatchFragment() {
        setRetainInstance(true);
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CreatePinballComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_create_pinball_match, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
    }

    private void createPinBallMatch() {
        Editable text = createPinballMatchEditText.getText();
        if(text != null) {
            this.presenter.createPinballMatch(text.toString());
        }
    }

    @Override public void onResume() {
        super.onResume();
        this.presenter.resume();
        if(getActivity() != null ) {
            FloatingActionButton fab = getActivity().findViewById(R.id.fab);
            fab.setOnClickListener(v -> createPinBallMatch());
        }
    }

    @Override public void onPause() {
        super.onPause();
        this.presenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override
    public void onPinballMatchCreated(DomainPinballMatchModel domainPinballMatchModel) {
        if(domainPinballMatchModel != null) {
            pointsTotal.setText(Integer.toString(domainPinballMatchModel.getPinballMatchPointsTotal()));
        }
    }

    @Override
    public boolean isReady() {
        return isAdded();
    }
}
