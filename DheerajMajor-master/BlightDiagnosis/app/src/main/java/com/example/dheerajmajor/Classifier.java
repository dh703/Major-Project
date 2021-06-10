package com.example.dheerajmajor;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;

import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;

public class Classifier {
    AssetManager assetManager;
    String modelPath;
    String labelPath;
    private Interpreter INTERPRETER;
    private List LABEL_LIST;
    private final int INPUT_SIZE;
    private final int PIXEL_SIZE;
    private final int IMAGE_MEAN=0;
    private final float IMAGE_STD;
    private final int MAX_RESULTS;
    private final float THRESHOLD;
    public Classifier(AssetManager assetManager,String modelPath,String labelPath,int inputSize) throws IOException {
        this.INPUT_SIZE = inputSize;
        this.PIXEL_SIZE = 3;
        this.IMAGE_STD = 255.0F;
        this.MAX_RESULTS = 3;
        this.THRESHOLD = 0.4F;
        this.INTERPRETER = new Interpreter(this.loadModelFile(assetManager, modelPath));
        this.LABEL_LIST = this.loadLabelList(assetManager, labelPath);
    }


    private final MappedByteBuffer loadModelFile(AssetManager assetManager, String modelPath) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = assetManager.openFd(modelPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intrinsics.checkExpressionValueIsNotNull(assetFileDescriptor, "assetManager.openFd(modelPath)");
        AssetFileDescriptor fileDescriptor = assetFileDescriptor;
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        MappedByteBuffer var10 = null;
        try {
            var10 = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intrinsics.checkExpressionValueIsNotNull(var10, "fileChannel.map(FileChan…rtOffset, declaredLength)");
        return var10;
    }
    private final List loadLabelList(AssetManager assetManager, String labelPath) throws IOException {
        InputStream var10000 = assetManager.open(labelPath);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "assetManager.open(labelPath)");
        InputStream var3 = var10000;
        Charset var4 = Charsets.UTF_8;
        boolean var5 = false;
        boolean var7 = false;
        Reader var6 = (Reader)(new InputStreamReader(var3, var4));
        short var21 = 8192;
        boolean var8 = false;
        Reader $this$useLines$iv = (Reader)(var6 instanceof BufferedReader ? (BufferedReader)var6 : new BufferedReader(var6, var21));
        int $i$f$useLines = 0;
        short var19 = 8192;
        var7 = false;
        Closeable var18 = (Closeable)($this$useLines$iv instanceof BufferedReader ? (BufferedReader)$this$useLines$iv : new BufferedReader($this$useLines$iv, var19));
        boolean var20 = false;
        var7 = false;
        Throwable var22 = (Throwable)null;

        List var24;
        try {
            BufferedReader it$iv = (BufferedReader)var18;
            int var9 = 0;
            Sequence it = TextStreamsKt.lineSequence(it$iv);
            int var11 = 0;
            var24 = SequencesKt.toList(it);
        } catch (Throwable var14) {
            var22 = var14;
            throw var14;
        }

        return var24;
    }
    @NotNull
    public final List recognizeImage(@NotNull Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, this.INPUT_SIZE, this.INPUT_SIZE, false);
        Intrinsics.checkExpressionValueIsNotNull(scaledBitmap, "scaledBitmap");
        ByteBuffer byteBuffer = this.convertBitmapToByteBuffer(scaledBitmap);
        byte var5 = 1;
        float[][] var6 = new float[var5][];

        for(int var7 = 0; var7 < var5; ++var7) {
            int var9 = 0;
            float[] var12 = new float[this.LABEL_LIST.size()];
            var6[var7] = var12;
        }

        float[][] result = (float[][])var6;
        this.INTERPRETER.run(byteBuffer, result);
        return this.getSortedResult(result);
    }

    private final ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * this.INPUT_SIZE * this.INPUT_SIZE * this.PIXEL_SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[this.INPUT_SIZE * this.INPUT_SIZE];
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int pixel = 0;
        int var5 = 0;

        for(int var6 = this.INPUT_SIZE; var5 < var6; ++var5) {
            int var7 = 0;

            for(int var8 = this.INPUT_SIZE; var7 < var8; ++var7) {
                int val = intValues[pixel++];
                byteBuffer.putFloat((float)((val >> 16 & 255) - this.IMAGE_MEAN) / this.IMAGE_STD);
                byteBuffer.putFloat((float)((val >> 8 & 255) - this.IMAGE_MEAN) / this.IMAGE_STD);
                byteBuffer.putFloat((float)((val & 255) - this.IMAGE_MEAN) / this.IMAGE_STD);
            }
        }

        Intrinsics.checkExpressionValueIsNotNull(byteBuffer, "byteBuffer");
        return byteBuffer;
    }
    private final List getSortedResult(float[][] labelProbArray) {
        String var2 = "List Size:(%d, %d, %d)";
        Object[] var3 = new Object[]{((Object[])labelProbArray).length, labelProbArray[0].length, this.LABEL_LIST.size()};
        String var7 = "Classifier";
        boolean var4 = false;
        String var10000 = String.format(var2, Arrays.copyOf(var3, var3.length));
        Intrinsics.checkNotNullExpressionValue(var10000, "java.lang.String.format(this, *args)");
        String var8 = var10000;
        Log.d(var7, var8);
        PriorityQueue pq = new PriorityQueue(this.MAX_RESULTS);
        int i = 0;

        int recognitionsSize;
        for(recognitionsSize = ((Collection)this.LABEL_LIST).size(); i < recognitionsSize; ++i) {
            float confidence = labelProbArray[0][i];
            if (confidence >= this.THRESHOLD) {
                pq.add(new Classifier.Recognition("" + i, this.LABEL_LIST.size() > i ? (String)this.LABEL_LIST.get(i) : "Unknown", confidence));
            }
        }

        String var11 = "pqsize:(%d)";
        Object[] var14 = new Object[]{pq.size()};
        var7 = "Classifier";
        boolean var15 = false;
        var10000 = String.format(var11, Arrays.copyOf(var14, var14.length));
        Intrinsics.checkNotNullExpressionValue(var10000, "java.lang.String.format(this, *args)");
        var8 = var10000;
        Log.d(var7, var8);
        ArrayList recognitions = new ArrayList();
        recognitionsSize = Math.min(pq.size(), this.MAX_RESULTS);
        int var16 = 0;

        for(int var6 = recognitionsSize; var16 < var6; ++var16) {
            recognitions.add(pq.poll());
        }

        return (List)recognitions;
    }

    public static final class Recognition {
        @NotNull
        private String id;
        @NotNull
        private String title;
        private float confidence;

        @NotNull
        public String toString() {
            return "Title = " + this.title + ", Confidence = " + this.confidence + ')';
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        public final void setId(@NotNull String var1) {
            Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
            this.id = var1;
        }

        @NotNull
        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(@NotNull String var1) {
            Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
            this.title = var1;
        }

        public final float getConfidence() {
            return this.confidence;
        }

        public final void setConfidence(float var1) {
            this.confidence = var1;
        }

        public Recognition(@NotNull String id, @NotNull String title, float confidence) {
            Intrinsics.checkParameterIsNotNull(id, "id");
            Intrinsics.checkParameterIsNotNull(title, "title");
            this.id = id;
            this.title = title;
            this.confidence = confidence;
        }

        @NotNull
        public final String component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.title;
        }

        public final float component3() {
            return this.confidence;
        }

        @NotNull
        public final Classifier.Recognition copy(@NotNull String id, @NotNull String title, float confidence) {
            Intrinsics.checkParameterIsNotNull(id, "id");
            Intrinsics.checkParameterIsNotNull(title, "title");
            return new Classifier.Recognition(id, title, confidence);
        }

        // $FF: synthetic method
        public static Classifier.Recognition copy$default(Classifier.Recognition var0, String var1, String var2, float var3, int var4, Object var5) {
            if ((var4 & 1) != 0) {
                var1 = var0.id;
            }

            if ((var4 & 2) != 0) {
                var2 = var0.title;
            }

            if ((var4 & 4) != 0) {
                var3 = var0.confidence;
            }

            return var0.copy(var1, var2, var3);
        }

        public int hashCode() {
            String var10000 = this.id;
            int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
            String var10001 = this.title;
            return (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31 + Float.floatToIntBits(this.confidence);
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof Classifier.Recognition) {
                    Classifier.Recognition var2 = (Classifier.Recognition)var1;
                    if (Intrinsics.areEqual(this.id, var2.id) && Intrinsics.areEqual(this.title, var2.title) && Float.compare(this.confidence, var2.confidence) == 0) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }
}
