package helper;

/**
 * Created by wong0903 on 16/4/2018.
 * It's an interface between the category data and Category Interface layout
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.wong0903.visitsg.R;

import java.util.ArrayList;
import java.util.List;

import boundary.ListViewInterface;
import control.CategoryManager;
import entity.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> mCategoryList;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        TextView categoryType;
        NetworkImageView categoryPhoto;

        public CategoryViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            categoryPhoto = (NetworkImageView) v.findViewById(R.id.categoryThumbnail);
            categoryType = (TextView) v.findViewById(R.id.categoryName);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryAdapter(List<Category> categoryList) {
        mCategoryList = categoryList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CategoryViewHolder vh = new CategoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        final Category category = mCategoryList.get(position);
        final CategoryManager categoryManager = new CategoryManager();
        holder.categoryType.setText(category.getType());
        holder.categoryPhoto.setImageUrl(category.getImageURL(),imageLoader);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> matchedURLList;
                matchedURLList = categoryManager.getAttractionsUnderCategory(category.getType());
                Bundle information = new Bundle();
                information.putStringArrayList("matchedURLList", matchedURLList);
                Intent intent = new Intent(v.getContext(), ListViewInterface.class);
                intent.putExtras(information);
                v.getContext().startActivity(intent);
            }
        }).start();
    }
            });
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}