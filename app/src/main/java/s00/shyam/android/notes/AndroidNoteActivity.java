package s00.shyam.android.notes;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import s00.shyam.android.notes.model.Note;
import s00.shyam.android.notes.stub.NoteData;
import s00.shyam.android.notes.viewmodel.NoteViewModel;

public class AndroidNoteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private Subject<Boolean> showProgress = PublishSubject.create();
    private NoteViewModel viewModel;
    private Disposable progressDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         progressDisposable = showProgress
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(x -> ToggleProgressBar());

        viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        LoadRecyclerView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.android_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ToggleProgressBar() {
        ProgressBar progress = findViewById(R.id.loading);
        progress.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() -> progress.setVisibility(View.GONE),5000);
    }

    private void LoadRecyclerView() {
        NoteListRecyclerAdapter adapter = new NoteListRecyclerAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ProgressBar progress = findViewById(R.id.loading);

        mRecyclerView = findViewById(R.id.note_list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public void OnLoadMore(String text) {
                showProgress.onNext(progress.getVisibility() == View.GONE);
            }
        });

        viewModel.getAllNotes().observe(this, notes -> adapter.setNotes(notes));



        ItemTouchHelper.SimpleCallback noteSwipe = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = ((NoteListRecyclerAdapter.ViewHolder)viewHolder).getNotePosition();

                Note deleted = NoteData.getInstance().RemoveNote(pos);
                Snackbar.make(mRecyclerView, String.format("Deleted Note: %s", deleted.getTitle()), Snackbar.LENGTH_LONG).show();

                adapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper recyclerViewTouch = new ItemTouchHelper(noteSwipe);
        recyclerViewTouch.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!progressDisposable.isDisposed())
            progressDisposable.dispose();
    }
}
