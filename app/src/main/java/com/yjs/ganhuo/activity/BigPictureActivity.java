package com.yjs.ganhuo.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yjs.ganhuo.R;
import com.yjs.ganhuo.base.BaseActivity;
import com.yjs.ganhuo.base.IPresenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.yjs.ganhuo.GanHuoApplication.mContext;

/**
 * Created by yangjingsong on 17/2/15.
 */

public class BigPictureActivity extends BaseActivity {
    @Bind(R.id.ivDetail)
    ImageView ivDetail;
    String url;

    Bitmap bitmap;

    final MediaScannerConnection msc = new MediaScannerConnection(this, new MediaScannerConnection.MediaScannerConnectionClient() {

        public void onMediaScannerConnected() {
            msc.scanFile("/sdcard/image.jpg", "image/jpeg");
        }

        public void onScanCompleted(String path, Uri uri) {
            msc.disconnect();
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.activity_big_picture;
    }

    @Override
    protected void initEventAndData() {
        url = getIntent().getStringExtra("url");
        Glide.with(this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ivDetail.setImageBitmap(resource);
                bitmap = resource;
            }
        });

    }

    @Override
    protected IPresenter getmPresent() {
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.picture_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:

                //MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"meizi",System.currentTimeMillis()+"");
                SaveBitmapTask task = new SaveBitmapTask(this);
                //task.execute(bitmap);

                Observable.create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        try {
                            saveImageToExternal(System.currentTimeMillis()+"",bitmap,BigPictureActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        subscriber.onNext(subscriber);

                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Object>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(BigPictureActivity.this,"图片保存失败",Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNext(Object bitmap) {
                                Toast.makeText(BigPictureActivity.this,"图片已保存",Toast.LENGTH_SHORT).show();

                            }
                        });

//                Observable.just(bitmap)
//                        .map(new Func1<Bitmap, Bitmap>() {
//                            @Override
//                            public Bitmap call(Bitmap bitmap) {
//                                try {
//                                    saveImageToExternal(System.currentTimeMillis()+"",bitmap,BigPictureActivity.this);
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                                return null;
//                            }
//                        })
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<Bitmap>() {
//                            @Override
//                            public void onCompleted() {
//                                Toast.makeText(BigPictureActivity.this,"图片已保存",Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Toast.makeText(BigPictureActivity.this,"图片保存失败",Toast.LENGTH_SHORT).show();
//
//                            }
//
//                            @Override
//                            public void onNext(Bitmap bitmap) {
//
//
//                            }
//                        })
//                        ;


                break;
            case R.id.share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "分享一个好看的妹纸给你" + " " + url);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "分享到"));

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bitmap!=null){
            bitmap.recycle();
            bitmap = null;
        }

    }


    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    class SaveBitmapTask extends AsyncTask<Bitmap,Void,Void>{

        WeakReference<Context> weakReference;

        public SaveBitmapTask(Context context){

            weakReference = new WeakReference<Context>(context);
        }

        @Override
        protected Void doInBackground(Bitmap... bitmaps) {
            if(weakReference.get()!=null){
                try {
                    saveImageToExternal(System.currentTimeMillis()+"",bitmaps[0],weakReference.get());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(weakReference.get()!=null){
                Context context = weakReference.get();
                Toast.makeText(context,"图片已保存",Toast.LENGTH_SHORT).show();
            }

        }

        public void saveImageToExternal(String imgName, Bitmap bm, Context context) throws IOException {
//Create Path to save Image
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "ganhuo"); //Creates app specific folder
            path.mkdirs();
            File imageFile = new File(path, imgName + ".png"); // Imagename.png
            FileOutputStream out = new FileOutputStream(imageFile);
            try {
                bm.compress(Bitmap.CompressFormat.PNG, 100, out); // Compress Image
                out.flush();
                out.close();

                // Tell the media scanner about the new file so that it is
                // immediately available to the user.
                MediaScannerConnection.scanFile(context, new String[]{imageFile.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
            } catch (Exception e) {
                throw new IOException();
            }
        }
    }


    public void saveImageToExternal(String imgName, Bitmap bm, Context context) throws IOException {
//Create Path to save Image
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "ganhuo"); //Creates app specific folder
        path.mkdirs();
        File imageFile = new File(path, imgName + ".png"); // Imagename.png
        FileOutputStream out = new FileOutputStream(imageFile);
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, out); // Compress Image
            out.flush();
            out.close();

            // Tell the media scanner about the new file so that it is
            // immediately available to the user.
            MediaScannerConnection.scanFile(context, new String[]{imageFile.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                    Log.i("ExternalStorage", "Scanned " + path + ":");
                    Log.i("ExternalStorage", "-> uri=" + uri);
                }
            });
        } catch (Exception e) {
            throw new IOException();
        }
    }




}
