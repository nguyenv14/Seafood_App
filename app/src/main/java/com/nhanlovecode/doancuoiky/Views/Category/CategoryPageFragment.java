package com.nhanlovecode.doancuoiky.Views.Category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhanlovecode.doancuoiky.Adapters.CategoryGroupAdapter;
import com.nhanlovecode.doancuoiky.Models.Category;
import com.nhanlovecode.doancuoiky.R;

import java.util.List;

public class CategoryPageFragment extends Fragment implements CategoryProductMVPView{

    LinearLayout loContent;
    RelativeLayout loProgressBar;
    RecyclerView recyclerViewCategoryProduct;

    CategoryProductPresenter categoryProductPresenter;
    CategoryGroupAdapter categoryGroupAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_page, container, false);
        categoryProductPresenter = new CategoryProductPresenter(getActivity(),this);
        ititUI(view);
        getData();
        return view;
    }

    private void getData() {
        categoryProductPresenter.getDataCategoryProduct();
    }

    private void ititUI(View view) {
        recyclerViewCategoryProduct = view.findViewById(R.id.recyclerViewCategoryGroup);
        loContent = view.findViewById(R.id.loContent);
        loProgressBar = view.findViewById(R.id.loProgressBar);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewCategoryProduct.setLayoutManager(layoutManager);
        recyclerViewCategoryProduct.setHasFixedSize(true);
    }
    @Override
    public void getDataCategoryListSuccess(List<Category> categoryList) {
        categoryGroupAdapter = new CategoryGroupAdapter(getActivity(), categoryList);
        recyclerViewCategoryProduct.setAdapter(categoryGroupAdapter);
        loProgressBar.setVisibility(View.GONE);
        loContent.setVisibility(View.VISIBLE);
    }
}