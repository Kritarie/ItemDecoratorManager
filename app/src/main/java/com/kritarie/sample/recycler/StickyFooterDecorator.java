package com.kritarie.sample.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.kritarie.itemdecoratormanager.decorator.BaseItemDecorator;


/**
 * Created by Sean on 11/3/2015.
 */
public class StickyFooterDecorator extends BaseItemDecorator {

    private Bitmap mDrobble;

    public StickyFooterDecorator(Context context, @DrawableRes int drobble) {
        mDrobble = BitmapFactory.decodeResource(context.getResources(), drobble);
    }

    @Override
    public boolean decoratesView(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView parent) {
        return viewHolder.getAdapterPosition() == parent.getAdapter().getItemCount() - 1;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = mDrobble.getHeight();
    }

    @Override
    public void onDrawUnder(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        // At this point we know the last view is on screen somewhere. Let's figure out where
        int parentHeight = parent.getHeight();
        int drobbleHeight = mDrobble.getHeight();
        int viewBottom = holder.itemView.getBottom();

        if (viewBottom + drobbleHeight > parentHeight) {
            // View is sliding off, draw footer relative to view
            canvas.drawBitmap(mDrobble, parent.getLeft(), viewBottom, null);
        } else {
            // View is above (few items in RecyclerView), draw pinned to bottom
            canvas.drawBitmap(mDrobble, parent.getLeft(), parentHeight - drobbleHeight, null);
        }

        if (parent.getAdapter() == null || parent.getAdapter().getItemCount() == 0) {
            int parentBottom = parent.getBottom();
            canvas.drawBitmap(mDrobble, parent.getLeft(), parentBottom - drobbleHeight, null);
        }
    }
}
