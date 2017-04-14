package fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.client.project.MyApplication;
import com.client.project.R;
import com.client.project.databinding.LeftSlidingFragmentBinding;

import bean.UseView;
import util.image.ImageLoader;

/**
 * Created by YYBJ on 2017/4/9.
 */

public class SlidingFragment extends Fragment {

    private LeftSlidingFragmentBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.left_sliding_fragment,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        UseView useView = MyApplication.getInstance().getUseView();
        ImageLoader.loadCircle(mBinding.portrait, useView.useIcon);
        mBinding.nickname.setText(useView.nickname);
    }
}
