package com.gaggle.snoretrain.gaggle.presenter;

import com.gaggle.snoretrain.gaggle.interactor.ApiInteractor;
import com.gaggle.snoretrain.gaggle.interactor.GaggleApplicationView;
import com.gaggle.snoretrain.gaggle.interactor.Interactor;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Snore Train on 4/16/2017.
 */

public class ViewPresenter {
    private GaggleApplicationView view;
    private Interactor interactor;

    public ViewPresenter(GaggleApplicationView view, Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }
    public void getData() {
        try {
            interactor.getApiData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Object data) {
                            view.presentGaggleData(data);
                        }
                    });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
