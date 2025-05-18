package com.avitaliskhakov.multistateviewx;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;

public class MultiStateView extends FrameLayout {

    private View loadingView, emptyView, errorView, contentView;
    private View defaultLoadingView, defaultEmptyView, defaultErrorView;
    private State currentState = State.CONTENT;
    private OnClickListener retryListener;

    public MultiStateView(Context context) {
        super(context);
        initViews();
    }

    public MultiStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        defaultLoadingView = inflater.inflate(R.layout.state_loading, this, false);
        defaultEmptyView = inflater.inflate(R.layout.state_empty, this, false);
        defaultErrorView = inflater.inflate(R.layout.state_error, this, false);

        loadingView = defaultLoadingView;
        emptyView = defaultEmptyView;
        errorView = defaultErrorView;

        addView(loadingView);
        addView(emptyView);
        addView(errorView);

        hookRetryButton(errorView);
        updateVisibility();
    }

    private void hookRetryButton(View errorView) {
        Button retryButton = errorView.findViewById(R.id.btnRetry);
        if (retryButton != null) {
            retryButton.setOnClickListener(v -> {
                if (retryListener != null) retryListener.onClick(v);
            });
        }
    }

    public void setContentView(View view) {
        if (contentView != null) removeView(contentView);
        contentView = view;
        addView(contentView);
        updateVisibility();
    }

    public void setState(State state) {
        currentState = state;
        updateVisibility();
    }

    private void updateVisibility() {
        loadingView.setVisibility(currentState == State.LOADING ? VISIBLE : GONE);
        emptyView.setVisibility(currentState == State.EMPTY ? VISIBLE : GONE);
        errorView.setVisibility(currentState == State.ERROR ? VISIBLE : GONE);
        if (contentView != null) {
            contentView.setVisibility(currentState == State.CONTENT ? VISIBLE : GONE);
        }
    }

    public void setOnRetryClickListener(OnClickListener listener) {
        this.retryListener = listener;
        if (errorView != null) {
            hookRetryButton(errorView);
        }
    }

    public void setCustomView(State state, View customView) {
        switch (state) {
            case LOADING:
                if (loadingView != null) removeView(loadingView);
                loadingView = customView;
                addView(loadingView);
                break;
            case EMPTY:
                if (emptyView != null) removeView(emptyView);
                emptyView = customView;
                addView(emptyView);
                break;
            case ERROR:
                if (errorView != null) removeView(errorView);
                errorView = customView;
                addView(errorView);
                hookRetryButton(errorView);
                break;
            case CONTENT:
                setContentView(customView);
                return;
        }
        updateVisibility();
    }

    public void setCustomLayout(State state, @LayoutRes int layoutId) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId, this, false);
        setCustomView(state, view);
    }

    public void resetToDefault(State state) {
        switch (state) {
            case LOADING:
                if (loadingView != null) removeView(loadingView);
                loadingView = defaultLoadingView;
                addView(loadingView);
                break;
            case EMPTY:
                if (emptyView != null) removeView(emptyView);
                emptyView = defaultEmptyView;
                addView(emptyView);
                break;
            case ERROR:
                if (errorView != null) removeView(errorView);
                errorView = defaultErrorView;
                addView(errorView);
                hookRetryButton(errorView);
                break;
            default:
                return;
        }
        updateVisibility();
    }
}
