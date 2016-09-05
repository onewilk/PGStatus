package it.wilk.pg.view;

import it.wilk.pg.R;
import it.wilk.pg.adapter.MainAdapter;

/**
 * 在线服务器列表Fragment
 * Created by Mr.Wilk on 2016/08/12 0012.
 */
public class OnlineFragment extends BaseFragment {

    public static OnlineFragment newInstance() {
        return new OnlineFragment();
    }

    @Override
    protected void bindEvent() {
        mRecyclerView.setAdapter(new MainAdapter(getActivity(), true));
    }

    @Override
    public void updateTitle(int num) {
        title = String.format(getResources().getString(R.string.online), String.valueOf(num));
    }
}
