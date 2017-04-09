package fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.client.project.R;
import com.client.project.databinding.HomeFragmentBinding;

/**
 * Created by YYBJ on 2017/4/9.
 */

public class HomeFragment extends Fragment {

    private HomeFragmentBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.setContentView(getActivity(),R.layout.home_fragment);
        return mBinding.getRoot();
    }
}
