package com.example.dheerajmajor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public class MainActivity extends AppCompatActivity {
    private Classifier mClassifier;
    private Bitmap mBitmap;
    private final int mCameraRequestCode=0;
    private final int mGalleryRequestCode = 2;
    private final int mInputSize = 224;
    private final String mModelPath = "plant_disease_model.tflite";
    private final String mLabelPath = "plant_labels.txt";
    private final String mSamplePath = "soybean.JPG";
    Closeable closeable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);
        final ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new BlankFragment(), "CLASSIFY");
        adapter.addFrag(new BlankFragment2(), "HISTORY");
        viewPager.setAdapter(adapter);
    }

        /*AssetManager assetManager = this.getAssets();
        Intrinsics.checkExpressionValueIsNotNull(assetManager, "assets");
        try {
            this.mClassifier = new Classifier(assetManager, this.mModelPath, this.mLabelPath, this.mInputSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Resources resources = this.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        try {
            closeable = (Closeable)resources.getAssets().open(this.mSamplePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean var3 = false;
        boolean var4 = false;
        Throwable var11 = (Throwable)null;
        InputStream it = (InputStream)closeable;
        int var6 = 0;
        Bitmap var10001 = BitmapFactory.decodeStream(it);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "BitmapFactory.decodeStream(it)");
        this.mBitmap = var10001;
        var10001 = this.mBitmap;
        if (var10001 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBitmap");
        }
        var10001 = Bitmap.createScaledBitmap(var10001, this.mInputSize, this.mInputSize, true);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "Bitmap.createScaledBitma…utSize, mInputSize, true)");
        this.mBitmap = var10001;
        ImageView var13 = (ImageView)this.findViewById(R.id.mPhotoImageView);
        var10001 = this.mBitmap;
        if (var10001 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBitmap");
        }

        var13.setImageBitmap(var10001);
        Unit var12 = Unit.INSTANCE;
        ((Button)this.findViewById(R.id.mCameraButton)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent callCameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                MainActivity.this.startActivityForResult(callCameraIntent, MainActivity.this.mCameraRequestCode);
            }
        }));
        ((Button)this.findViewById(R.id.mGalleryButton)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent callGalleryIntent = new Intent("android.intent.action.PICK");
                callGalleryIntent.setType("image/*");
                MainActivity.this.startActivityForResult(callGalleryIntent, MainActivity.this.mGalleryRequestCode);
            }
        }));
        ((Button)this.findViewById(R.id.mDetectButton)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Classifier.Recognition results = (Classifier.Recognition) CollectionsKt.firstOrNull(MainActivity.access$getMClassifier$p(MainActivity.this).recognizeImage(MainActivity.access$getMBitmap$p(MainActivity.this)));
                TextView var10000 = (TextView)MainActivity.this.findViewById(R.id.mResultTextView);
                Intrinsics.checkExpressionValueIsNotNull(var10000, "mResultTextView");
                var10000.setText((CharSequence)((results != null ? results.getTitle() : "No suitable disease found") + "\n Accuracy:" + (results != null ? results.getConfidence() : "0%")));
            }
        }));*/
    }
   /* protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ImageView var9;
        Bitmap var13;
        Bitmap var10002;
        if (requestCode == this.mCameraRequestCode) {
            if (resultCode == -1 && data != null) {
                Bundle var10001 = data.getExtras();
                if (var10001 == null) {
                    Intrinsics.throwNpe();
                }

                Object var11 = var10001.get("data");
                if (var11 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.Bitmap");
                }

                this.mBitmap = (Bitmap)var11;
                var10002 = this.mBitmap;
                if (var10002 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBitmap");
                }

                this.mBitmap = this.scaleImage(var10002);
                Context var10000 = (Context)this;
                StringBuilder var12 = (new StringBuilder()).append("Image crop to: w= ");
                var10002 = this.mBitmap;
                if (var10002 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBitmap");
                }

                var12 = var12.append(var10002.getWidth()).append(" h= ");
                var10002 = this.mBitmap;
                if (var10002 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBitmap");
                }

                Toast toast = Toast.makeText(var10000, (CharSequence)var12.append(var10002.getHeight()).toString(), Toast.LENGTH_SHORT);
                toast.setGravity(80, 0, 20);
                toast.show();
                var9 = (ImageView)this.findViewById(R.id.mPhotoImageView);
                var13 = this.mBitmap;
                if (var13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBitmap");
                }

                var9.setImageBitmap(var13);
                TextView var10 = (TextView)this.findViewById(R.id.mResultTextView);
                Intrinsics.checkExpressionValueIsNotNull(var10, "mResultTextView");
                var10.setText((CharSequence)"Your photo image set now.");
            } else {
                Toast.makeText((Context)this, (CharSequence)"Camera cancel..", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == this.mGalleryRequestCode) {
            if (data != null) {
                Uri uri = data.getData();

                try {
                    var13 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    Intrinsics.checkExpressionValueIsNotNull(var13, "MediaStore.Images.Media.…his.contentResolver, uri)");
                    this.mBitmap = var13;
                } catch (IOException var7) {
                    var7.printStackTrace();
                }

                String var5 = "Success!!!";
                boolean var6 = false;
                System.out.println(var5);
                var10002 = this.mBitmap;
                if (var10002 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBitmap");
                }

                this.mBitmap = this.scaleImage(var10002);
                var9 = (ImageView)this.findViewById(R.id.mPhotoImageView);
                var13 = this.mBitmap;
                if (var13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBitmap");
                }

                var9.setImageBitmap(var13);
            }
        } else {
            Toast.makeText(this, "Unrecognized", Toast.LENGTH_SHORT).show();
        }

    }

    @NotNull
    public final Bitmap scaleImage(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            Intrinsics.throwNpe();
        }

        int orignalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        float scaleWidth = (float)this.mInputSize / (float)orignalWidth;
        float scaleHeight = (float)this.mInputSize / (float)originalHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap var10000 = Bitmap.createBitmap(bitmap, 0, 0, orignalWidth, originalHeight, matrix, true);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "Bitmap.createBitmap(bitm…inalHeight, matrix, true)");
        return var10000;
    }

    // $FF: synthetic method
    public static final Classifier access$getMClassifier$p(MainActivity $this) {
        Classifier var10000 = $this.mClassifier;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClassifier");
        }

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setMClassifier$p(MainActivity $this, Classifier var1) {
        $this.mClassifier = var1;
    }

    // $FF: synthetic method
    public static final Bitmap access$getMBitmap$p(MainActivity $this) {
        Bitmap var10000 = $this.mBitmap;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBitmap");
        }

        return var10000;
    }

    public static final void access$setMBitmap$p(MainActivity $this, Bitmap var1) {
        $this.mBitmap = var1;
    }
*/
