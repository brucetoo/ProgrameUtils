package com.yunyou.tounahao.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class BitmapUtil {

	/**
	 * 缩放图片
	 * 
	 * @param bitmap
	 * @param f
	 * @return
	 */
	public static Bitmap zoom(Bitmap bitmap, float zf) {
		Matrix matrix = new Matrix();
		matrix.postScale(zf, zf);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}

	/**
	 * 缩放图片,倍数缩放
	 * 
	 * @param bitmap
	 * @param f
	 * @return
	 */
	public static Bitmap zoom(Bitmap bitmap, float wf, float hf) {
		Matrix matrix = new Matrix();
		matrix.postScale(wf, hf);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}

	/**
	 * 缩放图片,倍数缩放
	 * 
	 * @param bitmap
	 * @param f
	 * @return
	 */
	public static Bitmap zoom(Bitmap bitmap, int w, int h) {
		Matrix matrix = new Matrix();
		if (bitmap.getWidth() < bitmap.getHeight()) {
			matrix.setRotate(-90);
		}
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}

	/**
	 * 图片圆角处理
	 * 
	 * @param bitmap
	 * @param roundPX
	 * @return
	 */
	public static Bitmap getRCB(Bitmap bitmap, float roundPX) {
		// RCB means
		// Rounded
		// Corner Bitmap
		Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(dstbmp);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return dstbmp;
	}

	/**
	 * 将字节数组转为Bitmap
	 * 
	 * @param in
	 * @param opts
	 * @return
	 */
	public static Bitmap getBitmapFromByte(byte[] bytes, BitmapFactory.Options opts) {
		if (bytes != null)
			if (opts != null)
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return null;
	}

	/**
	 * 将输入流转为Bitmap
	 * 
	 * @param in
	 * @param opts
	 * @return
	 */
	public static Bitmap getBitmapFromStream(InputStream in, BitmapFactory.Options opts) {
		byte[] bytes = null;
		try {
			bytes = IoUtil.readStreamToByteArray(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (bytes != null)
			if (opts != null)
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return null;
	}

	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			left = 0;
			bottom = width;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(4);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);

		return output;
	}

	/** 保存方法 */
	public static File saveBitmap(String path, Bitmap bm) {
		File f = new File(path);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	/**
	 * 生成缩略图
	 * 
	 * @param inputStream
	 *            数据
	 * @param size
	 *            大小 50K 50*1024
	 * @return
	 * @throws IOException
	 */
	public static Bitmap getSamllerBitmap(InputStream inputStream, long size) {
		Bitmap bitmap = null;
		try {
			byte[] data = IoUtil.readStreamToByteArray(inputStream);
			bitmap = getSamllerBitmap(data, size);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 生成缩略图
	 * 
	 * @param data
	 *            数据
	 * @param size
	 * @return
	 */
	public static Bitmap getSamllerBitmap(byte[] data, long size) {
		Bitmap bitmap;
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 创建文件流
		// 把inpuStream转换成byte[]

		// --------------------压缩图片
		// 能够读入像素数组
		options.inJustDecodeBounds = false;
		//
		// For example, inSampleSize == 4 returns an image that is 1/4 the
		// width/height of the original, and 1/16 the number of pixels.
		// 缩小位数
		// 50K 4 9 16 25
		if (data.length > size * 25) {
			options.inSampleSize = 5;
		} else if (data.length > size * 16) {
			options.inSampleSize = 4;
		} else if (data.length > size * 9) {
			options.inSampleSize = 3;
		} else if (data.length > size * 4) {
			options.inSampleSize = 2;
		} else {
			options.inSampleSize = 1;
		}
		// options.inSampleSize = 2;// 实际图片变成原来的1/4
		bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
		return bitmap;
	}

	/**
	 * 从本地读取位图
	 * 
	 * @param urlStr
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Bitmap readBitmapFromFile(final String urlStr) throws FileNotFoundException, IOException {
		String path = FileUtil.getSaveDir() + FileUtil.getFileName(urlStr);
		// // 创建文件对象
		File file = new File(path);
		if (!file.exists())
			return null;
		// 创建文件流
		FileInputStream inputStream = new FileInputStream(file);//
		// 把inpuStream转换成byte[]
		byte[] data = IoUtil.readStreamToByteArray(inputStream);
		// 返回Bitmap对象
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		return bitmap;
	}

	/**
	 * 存储Bitmap文件
	 * 
	 * @param urlStr
	 * @param bitmap
	 * @throws FileNotFoundException
	 */
	public static void saveBitmapAsFile(final String urlStr, Bitmap bitmap) {
		try {
			// 确认保存目录
			FileUtil.checkSaveDir();
			// 创建何存文件
			File file = FileUtil.checkOutputFile(FileUtil.getFileName(urlStr));
			// 创建输出流
			OutputStream output = new FileOutputStream(file);
			// 选择在sd卡目录 给生一张压缩图
			bitmap.compress(CompressFormat.JPEG, 80, output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Drawable转化为Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {

		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;
	}

}
