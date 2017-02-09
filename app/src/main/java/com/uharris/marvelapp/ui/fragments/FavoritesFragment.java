package com.uharris.marvelapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uharris.genericsimpleradapter.BaseRecyclerViewAdapter;
import com.uharris.marvelapp.R;
import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.routing.MarvelRouting;
import com.uharris.marvelapp.data.sections.favorites.FavoritesContract;
import com.uharris.marvelapp.data.sections.favorites.FavoritesPresenter;
import com.uharris.marvelapp.ui.holders.ComicViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements FavoritesContract.View,
        BaseRecyclerViewAdapter.Listener<Comic>{


    @BindView(R.id.favoriteRecyclerView)
    RecyclerView favoriteRecyclerView;
    private Realm realm;
    FavoritesContract.ActionListener presenter;
    BaseRecyclerViewAdapter<Comic, ComicViewHolder> adapter;
    private ArrayList<Comic> comics = new ArrayList<>();

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        presenter = new FavoritesPresenter(realm, this, new MarvelRouting());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);

        setUpRecyclerView();
        presenter.getFavoritesComics();
        return view;
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        realm.close();
        super.onDestroy();
    }

    private void setUpRecyclerView() {
        favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BaseRecyclerViewAdapter<Comic, ComicViewHolder>(getContext()) {
            @Override
            protected ComicViewHolder onCreateItemView(LayoutInflater inflater, ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_item,
                        parent, false);
                return new ComicViewHolder(view);
            }
        };

        adapter.setOnItemClickListener(this);
        favoriteRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onSuccess(RealmResults<Comic> comics) {
        this.comics.clear();
        for (Comic aux : comics) {
            this.comics.add(aux);
        }

        adapter.setAll(comics);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onClickItem(Comic comic, View v, int position) {
        presenter.detailComic(getActivity(), comic);
    }
}
