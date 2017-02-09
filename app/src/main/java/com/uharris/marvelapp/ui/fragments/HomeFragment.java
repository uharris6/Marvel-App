package com.uharris.marvelapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.uharris.genericsimpleradapter.BaseRecyclerViewAdapter;
import com.uharris.marvelapp.R;
import com.uharris.marvelapp.data.entities.Comic;
import com.uharris.marvelapp.data.entities.Data;
import com.uharris.marvelapp.data.routing.MarvelRouting;
import com.uharris.marvelapp.data.sections.home.HomeContract;
import com.uharris.marvelapp.data.sections.home.HomePresenter;
import com.uharris.marvelapp.ui.holders.ComicViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeContract.View,
        BaseRecyclerViewAdapter.Listener<Comic>,  SearchView
                .OnQueryTextListener {


    @BindView(R.id.comicsRecyclerView)
    RecyclerView comicsRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    RelativeLayout container;

    HomeContract.ActionListener presenter;

    int offset = 0;

    BaseRecyclerViewAdapter<Comic, ComicViewHolder> adapter;
    private MenuItem searchMenuItem;
    private SearchView mSearchView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        init();

        showDialog(true);
        presenter = new HomePresenter(this, new MarvelRouting());
        presenter.getComics(offset);
        return view;
    }

    private void init() {
        comicsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BaseRecyclerViewAdapter<Comic, ComicViewHolder>(getContext()) {
            @Override
            protected ComicViewHolder onCreateItemView(LayoutInflater inflater, ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_item,
                        parent, false);
                return new ComicViewHolder(view);
            }
        };

        adapter.setOnItemClickListener(this);
        comicsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showDialog(boolean show) {
        if(show){
            progressBar.setVisibility(View.VISIBLE);
            comicsRecyclerView.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            comicsRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Data data) {
        if(data.getCount() > 0){
            showDialog(false);
            adapter.setAll(data.getComics());
        }
    }

    @Override
    public void onClickItem(Comic comic, View v, int position) {
        presenter.comicDetail(getActivity(), comic.getId());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query.equals("")) {
            return false;
        } else {
            showDialog(true);
            mSearchView.clearFocus();
            presenter.searchComics(offset, query);
            return true;
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
