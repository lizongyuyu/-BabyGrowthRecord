package com.baby.babygrowthrecord.Circle;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.baby.babygrowthrecord.R;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DynamicGridAdapter extends BaseAdapter {
	private String[] files;

	private LayoutInflater mLayoutInflater;

	public DynamicGridAdapter(String[] files, Context context) {
		this.files = files;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return files == null ? 0 : files.length;
	}

	@Override
	public String getItem(int position) {
		return files[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyGridViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new MyGridViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.activity_circle_gridview_item,
					parent, false);//头像
			
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.album_image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MyGridViewHolder) convertView.getTag();
		}
		String url = getItem(position);
		if (getCount() == 1) {
			viewHolder.imageView.setLayoutParams(new android.widget.AbsListView.LayoutParams(300, 250));
		}

		if (getCount() == 2 ||getCount() == 4) {
			viewHolder.imageView.setLayoutParams(new android.widget.AbsListView.LayoutParams(200, 200));
		}

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.load_27)  //加载中显示图片
				.showImageOnFail(R.drawable.chlid)    //加载失败显示图片
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();

		ImageLoader.getInstance().displayImage(url, viewHolder.imageView,options);

		return convertView;
	}

	private static class MyGridViewHolder {
		ImageView imageView;
	}
}
