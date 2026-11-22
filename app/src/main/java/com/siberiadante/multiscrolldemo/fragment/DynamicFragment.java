package com.siberiadante.multiscrolldemo.fragment;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.siberiadante.multiscrolldemo.R;
import com.siberiadante.multiscrolldemo.adapter.MineDynamicAdapter;
import com.siberiadante.multiscrolldemo.bean.MineDynamicBean;
import com.siberiadante.multiscrolldemo.fragment.base.LazyFragment;
import com.siberiadante.multiscrolldemo.view.NormalDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Created SiberiaDante
 * @Describe锛? * @CreateTime: 2017/12/14
 * @UpDateTime:
 * @Email: 2654828081@qq.com
 * @GitHub: https://github.com/SiberiaDante
 */

public class DynamicFragment extends LazyFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MineDynamicAdapter adapter;

    public static DynamicFragment getInstance() {
        return new DynamicFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void lazyInitView(View view, Bundle savedInstanceState) {
        final List<MineDynamicBean> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            final MineDynamicBean questionBean = new MineDynamicBean();
            questionBean.setContent("浣跨敤NestedScrollView+ViewPager+RecyclerView+SmartRefreshLayout鎵撻€犻叿鐐笅鎷夎宸晥鏋滃苟瑙ｅ喅鍚勭婊戝姩鍐茬獊" + i);
            if (i == 0) {
                questionBean.setType(0);
            } else {
                if (i % 2 == 0) {
                    questionBean.setType(1);
                }
                if (i % 3 == 0) {
                    questionBean.setType(2);
                }
            }
            data.add(questionBean);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.addItemDecoration(new NormalDecoration(ContextCompat.getColor(mActivity, R.color.mainGrayF8), (int) mActivity.getResources().getDimension(R.dimen.one)));

        adapter = new MineDynamicAdapter(mActivity);
        recyclerView.setAdapter(adapter);
        adapter.addAll(data);
        adapter.setNoMore(R.layout.view_no_more);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mActivity, "---position---" + position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                Log.d(TAG, "----onMoreShow");
                adapter.addAll(data);
            }

            @Override
            public void onMoreClick() {

            }
        });
    }
}

