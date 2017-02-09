package com.uharris.marvelapp.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jimcoven.view.JCropImageView;
import com.squareup.picasso.Picasso;
import com.uharris.marvelapp.R;
import com.uharris.marvelapp.data.entities.Character;
import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.entities.Creator;
import com.uharris.marvelapp.data.entities.Data;
import com.uharris.marvelapp.data.routing.MarvelRouting;
import com.uharris.marvelapp.data.sections.detail.DetailContract;
import com.uharris.marvelapp.data.sections.detail.DetailPresenter;
import com.uharris.marvelapp.util.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    @BindView(R.id.cover)
    JCropImageView cover;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.content_detail)
    NestedScrollView contentDetail;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.pageCount)
    TextView pageCount;
    @BindView(R.id.synopsis)
    TextView synopsis;
    @BindView(R.id.creators)
    TextView creators;
    @BindView(R.id.series)
    TextView series;
    @BindView(R.id.characters)
    TextView characters;
    private int comicId;

    DetailContract.ActionListener presenter;

    Realm realm;

    Comic comic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getExtras() != null) {
            comicId = getIntent().getExtras().getInt(MarvelRouting.COMIC_ID);
        }

        realm = Realm.getDefaultInstance();

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setVisibility(View.GONE);

        presenter = new DetailPresenter(realm, this);
        presenter.getComic(comicId);

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        realm.close();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSucces(Data data) {
        if (data.getComics().size() > 0) {
            comic = data.getComics().get(0);

            String url = comic.getThumbnail().getPath().concat(".").concat(comic.getThumbnail()
                    .getExtension());
            Picasso.with(this).load(url).into(cover);
            collapsingToolbarLayout.setTitle(comic.getTitle());
            collapsingToolbarLayout.setVisibility(View.VISIBLE);

            if (comic.getPrices().size() > 0) {
                price.setText(getString(R.string.price_detail, Double.toString(comic.getPrices().get(0)
                        .getPrice())));
            } else {
                price.setText(getString(R.string.no_price));
            }

            date.setText(getString(R.string.date, DateUtil.getDate(comic.getDate())));
            pageCount.setText(getString(R.string.page_count, Integer.toString(comic.getPageCount())));
            synopsis.setText(comic.getDescription());

            String creatorsTxt = "";
            if (comic.getCreators().getAvailable() > 0) {
                for (Creator creator : comic.getCreators().getItems()) {
                    creatorsTxt = creatorsTxt + creator.getName().concat(" (").
                            concat(creator.getRole()).concat("), ");
                }
            }

            creators.setText(getString(R.string.creators, creatorsTxt));

            String charactersTxt = "";
            if (comic.getCharacters().getAvailable() > 0) {
                for (Character character : comic.getCharacters().getItems()) {
                    charactersTxt = charactersTxt + character.getName().concat(", ");
                }
            }

            if (charactersTxt.equals("")) {
                characters.setVisibility(View.GONE);
            } else {
                characters.setText(getString(R.string.characters, charactersTxt));
            }


            series.setText(getString(R.string.series, comic.getSeries().getName()));

            presenter.getComicRealm(comic.getId());
        }
    }

    @Override
    public void onSuccesSavedComic(Comic comic) {
        if(comic != null){
            Toast.makeText(this, "Comic saved as favorite with success", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccessGetComicRealm(Comic comic) {
        if(comic != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fab.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_on, this.getTheme()));
            } else {
                fab.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_on));
            }
        }
    }

    @Override
    public void onError(String error) {
        Snackbar.make(contentDetail, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(boolean show) {

    }


    @OnClick(R.id.fab)
    public void onClick() {
        presenter.saveComic(comic);
    }
}
