package com.compy.check.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemNoHeader extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemNoHeader(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top=space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) == 0)
            outRect.top = space;
    }
}
