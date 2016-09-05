package it.wilk.pg.view;

import it.wilk.pg.R;
import it.wilk.pg.adapter.MainAdapter;

/**
 * 离线服务器列表Fragment
 * Created by Mr.Wilk on 2016/08/12 0012.
 */
public class OfflineFragment extends BaseFragment {

    public static OfflineFragment newInstance() {
        return new OfflineFragment();
    }

    @Override
    protected void bindEvent() {
        mRecyclerView.setAdapter(new MainAdapter(getActivity(),false));
    }

    @Override
    public void updateTitle(int num) {
        title = String.format(getResources().getString(R.string.offline), String.valueOf(num));
    }
}
