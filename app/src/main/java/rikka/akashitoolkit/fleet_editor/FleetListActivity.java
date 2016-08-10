package rikka.akashitoolkit.fleet_editor;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayInputStream;
import java.util.List;

import rikka.akashitoolkit.R;
import rikka.akashitoolkit.model.Fleet;
import rikka.akashitoolkit.staticdata.FleetList;
import rikka.akashitoolkit.ui.BaseActivity;
import rikka.akashitoolkit.ui.widget.ItemTouchHelperCallback;
import rikka.akashitoolkit.utils.Utils;

public class FleetListActivity extends BaseActivity {

    private FleetsListAdapter mAdapter;
    private List<Fleet> mFleets;

    private static final String JSON_NAME = "/json/fleets.json";
    private String CACHE_FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.fleets);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fleet fleet = new Fleet();
                fleet.init(view.getContext());
                mAdapter.addItem(0, 0, fleet);
                FleetEditActivity.start(view.getContext(), mFleets.size() - 1);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content_container);
        mAdapter = new FleetsListAdapter();
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, 0, Utils.dpToPx(8));
            }
        });

        /*SimpleItemAnimator animator = (SimpleItemAnimator) recyclerView.getItemAnimator();
        animator.setSupportsChangeAnimations(false);*/

        mFleets = FleetList.get(this);
        mAdapter.setItemList(mFleets);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        CACHE_FILE = getFilesDir().getAbsolutePath() + JSON_NAME;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        Utils.saveStreamToFile(new ByteArrayInputStream(gson.toJson(mFleets).getBytes()),
                CACHE_FILE);
    }
}
