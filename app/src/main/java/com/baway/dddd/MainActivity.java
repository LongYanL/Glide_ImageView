package com.baway.dddd;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.baway.mylibrary.R;

import com.baway.mylibrary.okhttp.OkHttpUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Welfare.ResultsBean> list;
    private List<Dog> list1;
    private RecyclerView recyclerView,mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        initData();
        initData1();
    }

    private void initData() {
        //返回对象的接口
        String url = "http://gank.io/api/data/福利/11/2";
        OkHttpUtils.getInstan().getOkHttpClass(url, null, Welfare.class, new OkHttpUtils.ClassCallBack<Welfare>() {


            @Override
            public void success(Welfare welfare) {
                list = welfare.getResults();
                recyclerView.setAdapter(new Welfare_Adapter(MainActivity.this,list));
            }


            @Override
            public void fail(String meg) {
                Toast.makeText(MainActivity.this,"Welfare"+meg,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initData1() {
        //返回集合的接口
        String url1 = "https://www.zhuangbi.info/search?q=dog";
        Type type = new TypeToken<List<Dog>>() {}.getType();
        OkHttpUtils.getInstan().getOkHttpList(url1, null,type, new OkHttpUtils.ListCallBack<Dog>() {

            @Override
            public void success(List<Dog> t) {
                list1=t;
                mRecyclerView.setAdapter(new Dog_Adapter(MainActivity.this,list1));
            }

            @Override
            public void fail(String meg) {

            }
        });

    }


}
