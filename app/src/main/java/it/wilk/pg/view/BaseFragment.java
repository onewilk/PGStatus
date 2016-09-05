package it.wilk.pg.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.wilk.pg.R;
import it.wilk.pg.adapter.MainAdapter;

/**
 * Fragment基类
 * Created by Mr.Wilk on 2016/08/12 0012.
 */
public class BaseFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    protected String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        bindEvent();
    }

    protected void bindEvent() {
        mRecyclerView.setAdapter(new MainAdapter(getActivity(), true));
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void notifyDataSetChanged() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    protected void updateTitle(int num) {
        title = String.valueOf(num);
    }
}
