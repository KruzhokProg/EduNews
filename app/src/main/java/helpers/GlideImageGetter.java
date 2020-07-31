package helpers;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.edunews.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;

public class GlideImageGetter implements Html.ImageGetter {

    private TextView textView = null;

    public GlideImageGetter() {
    }

    public GlideImageGetter(TextView textView) {
        this.textView = textView;
    }

    @Override
    public Drawable getDrawable(String s) {
        BitmapDrawablePlaceHolder drawable = new BitmapDrawablePlaceHolder();
        Picasso.get().load(s).placeholder(R.drawable.ic_notfound_image).into(drawable);
        return drawable;
    }

    private class BitmapDrawablePlaceHolder extends BitmapDrawable implements Target {

        protected Drawable drawable;

        @Override
        public void draw(Canvas canvas) {
            if(drawable != null){
                drawable.draw(canvas);
            }
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            drawable.setBounds(0,0,width, height);
            setBounds(0,0,width,height);
            if(textView != null){
                textView.setText(textView.getText());
            }
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            setDrawable(new BitmapDrawable(Resources.getSystem(), bitmap));
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }
}
