package it.wilk.pg.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.wilk.pg.PGApplication;
import it.wilk.pg.R;
import it.wilk.pg.parse.WebParser;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    List<BaseFragment> mFragments;
    String[] mTabText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mFragments = new ArrayList<>();
        mFragments.add(OnlineFragment.newInstance());
        mFragments.add(OfflineFragment.newInstance());

        mTabText = new String[]{
                String.format(getResources().getString(R.string.online), String.valueOf(0)),
                String.format(getResources().getString(R.string.offline), String.valueOf(0))};
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public BaseFragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabText[position];
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void updateData() {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            WebParser.getData();
                            subscriber.onNext(null);
                            subscriber.onCompleted();
                        } catch (IOException e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("请求完毕");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        notifyUpdate();
                    }
                });
    }

    private void notifyUpdate() {
        mTabText[0] = String.format(getResources().getString(R.string.online), String.valueOf(PGApplication.mResult.openNum));
        mTabText[1] = String.format(getResources().getString(R.string.offline), String.valueOf(PGApplication.mResult.closeNum));
        mViewPager.getAdapter().notifyDataSetChanged();

        for (int i = 0; i < mFragments.size(); i++) {
            mFragments.get(i).notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }
}
