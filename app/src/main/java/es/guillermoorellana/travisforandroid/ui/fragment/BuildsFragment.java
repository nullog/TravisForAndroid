package es.guillermoorellana.travisforandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.guillermoorellana.travisforandroid.R;
import es.guillermoorellana.travisforandroid.api.entity.Build;
import es.guillermoorellana.travisforandroid.mvp.BaseMvpLceFragment;
import es.guillermoorellana.travisforandroid.ui.presenter.BuildsPresenter;
import es.guillermoorellana.travisforandroid.ui.view.BuildsView;

public class BuildsFragment extends BaseMvpLceFragment<RecyclerView, List<Build>, BuildsView, BuildsPresenter>
        implements BuildsView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_builds, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
// TODO
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public BuildsPresenter createPresenter() {
        return new BuildsPresenter();
    }

    @Override
    public void setData(List<Build> data) {
        // TODO
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        // TODO
    }
}
