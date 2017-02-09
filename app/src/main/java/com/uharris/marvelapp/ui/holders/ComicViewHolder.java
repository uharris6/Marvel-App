package com.uharris.marvelapp.ui.holders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uharris.genericsimpleradapter.BaseRecyclerViewHolder;
import com.uharris.marvelapp.R;
import com.uharris.marvelapp.data.entities.Comic;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uharris on 2/8/17.
 */

public class ComicViewHolder extends BaseRecyclerViewHolder<Comic> {

    private Context context;

    @BindView(R.id.comic_image)
    ImageView comicImage;
    @BindView(R.id.comic_tile)
    TextView comicTitle;
    @BindView(R.id.comic_price)
    TextView comicPrice;


    public ComicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = itemView.getContext();
    }

    @Override
    protected void configureItem(Comic item) {
        String url = item.getThumbnail().getPath().concat(".").concat(item.getThumbnail()
                .getExtension());
        Picasso.with(context).load(url).into(comicImage);
        comicTitle.setText(item.getTitle());
        if(item.getPrices().size() > 0){
            comicPrice.setText(context.getString(R.string.price, Double.toString(item.getPrices().get(0)
                    .getPrice())));
        }else{
            comicPrice.setText(context.getString(R.string.no_price));
        }
    }
}
