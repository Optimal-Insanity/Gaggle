package com.gaggle.snoretrain.gaggle.interactor;

import com.gaggle.snoretrain.gaggle.network.GaggleApi;

import java.lang.reflect.Method;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Snore Train on 4/16/2017.
 */

public class ApiInteractor implements Interactor{

    private String methodName;
    private Class[] paramTypes;
    private Object[] params;
    private ApiInteractor(){
    }
    private void setMethodName(String methodName){
        // THE METHOD NAME PASSED INTO THIS CONSTRUCTOR MUST BE PRESENT IN
        // GaggleApiAdapter IF NOT, IT WILL THROW AN EXCEPTION OR RETURN NULL
        // Get method name needed for this interactor implementation
        // This can be decided as late as runtime.
        this.methodName = methodName;
    }
    private void setArgs(Object... params){
        // THE VALUES PASSED INTO THIS MUST PARALLEL THE CLASS OF PARAMS
        this.params = params;
    }
    private void setParamTypes(Class... paramTypes){
        // THE CLASS PARAMS PASSED INTO THIS FUNCTION MUST BE THE SAME PARAMS
        // NEEDED BY THE FUNCTION CALLED FROM GaggleApiAdapter
        this.paramTypes = paramTypes;
    }
    @Override
    public Observable getApiData() {
        try {
            //Create class type and object of this class type,
            //class is used to find the method,
            //obj used to action this method
            Object obj = GaggleApi.adapter();
            Class<?> cls = obj.getClass();
            Method method;
            Observable observable;
            //get the method to call
            if (paramTypes != null) {
                method = cls.getDeclaredMethod(methodName, paramTypes);
                observable = (Observable) method.invoke(
                        obj, params
                );
            }else {
                method = cls.getDeclaredMethod(methodName);
                observable = (Observable) method.invoke(obj);
            }
            return observable.subscribeOn(Schedulers.newThread())
                    .map(new Func1() {
                        @Override
                        public Object call(Object o) {
                            return o;
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static class Builder {
        ApiInteractor interactor = new ApiInteractor();

        public Builder setAdapterMethod(String method){
            interactor.setMethodName(method);
            return this;
        }
        public Builder setMethodParameters(Object... params){
            interactor.setArgs(params);
            return this;
        }
        public Builder setMethodParameterTypes(Class... types){
            interactor.setParamTypes(types);
            return this;
        }
        public Interactor build(){
            Interactor returnWI = interactor;
            interactor = null;
            return returnWI;
        }

    }
}
