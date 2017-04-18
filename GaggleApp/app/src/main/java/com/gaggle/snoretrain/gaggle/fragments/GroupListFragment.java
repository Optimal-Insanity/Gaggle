package com.gaggle.snoretrain.gaggle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.GroupRVAdapter;
import com.gaggle.snoretrain.gaggle.interactor.ApiInteractor;
import com.gaggle.snoretrain.gaggle.interactor.GaggleApplicationView;
import com.gaggle.snoretrain.gaggle.interactor.Interactor;
import com.gaggle.snoretrain.gaggle.listener.IGroupCallbackListener;
import com.gaggle.snoretrain.gaggle.models.GroupListModel;
import com.gaggle.snoretrain.gaggle.models.GroupModel;
import com.gaggle.snoretrain.gaggle.models.UserModel;
import com.gaggle.snoretrain.gaggle.presenter.ViewPresenter;
import com.gaggle.snoretrain.gaggle.services.GetGroupTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class GroupListFragment extends Fragment implements GaggleApplicationView<GroupListModel>{
    @BindView(R.id.fragment_recycler_view)
    RecyclerView groupRecycler;
    private UserModel user;
    public GroupListFragment(){

    }
    public void setUser(UserModel u){

        user = u;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //get context of view within activity and set to fragment recycler view layout
        View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        //bind list of views that need binding
        ButterKnife.bind(this, root);
        LinearLayoutManager groupRVLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(groupRecycler.getContext(),
                groupRVLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.vp_margin, getContext().getTheme()));
        groupRecycler.addItemDecoration(dividerItemDecoration);
        groupRecycler.setLayoutManager(groupRVLayoutManager);
        /*IGroupCallbackListener groupCallbackListener = new IGroupCallbackListener() {
            @Override
            public void onSearchCallback(GroupListModel groupModels) {
                //Create new adapter of GroupRVAdapter type and set the RV adapter to it
                GroupRVAdapter groupRVAdapter = new GroupRVAdapter(groupModels);
                groupRecycler.setAdapter(groupRVAdapter);

                //get the layout manager for this activity and make RV use it
                LinearLayoutManager groupRVLayoutManager = new LinearLayoutManager(getActivity());
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(groupRecycler.getContext(),
                        groupRVLayoutManager.getOrientation());
                dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.vp_margin, getContext().getTheme()));
                groupRecycler.addItemDecoration(dividerItemDecoration);
                groupRecycler.setLayoutManager(groupRVLayoutManager);
            }
        };
        GetGroupTask getGroupTask = new GetGroupTask(groupCallbackListener, user.getUserId());
        getGroupTask.execute();*/
        Interactor interactor = new ApiInteractor.Builder()
                .setAdapterMethod("getGroups")
                .setMethodParameters(null)
                .setMethodParameterTypes(null)
                .build();
        ViewPresenter presenter = new ViewPresenter(this, interactor);
        presenter.getData();
        return root;
    }

    @Override
    public void presentGaggleData(GroupListModel data) {
        GroupRVAdapter groupRVAdapter = new GroupRVAdapter(data);
        groupRecycler.setAdapter(groupRVAdapter);
    }
}
