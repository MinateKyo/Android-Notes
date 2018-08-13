package s00.shyam.android.notes;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public abstract class RecyclerViewScrollListener extends OnScrollListener  {

    private Subject<String> scrollSubject = PublishSubject.create();
    private AtomicInteger i;
    protected RecyclerViewScrollListener() {
        i = new AtomicInteger();
        scrollSubject.observeOn(Schedulers.io())
                .sample(100, TimeUnit.MILLISECONDS)
                .map(x -> x)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(x -> OnLoadMore(x));
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dx != dy && dy > 0) {
            scrollSubject.onNext("Y' " + Integer.toString(dy));
        }
    }

    public abstract void OnLoadMore(String something);
}

