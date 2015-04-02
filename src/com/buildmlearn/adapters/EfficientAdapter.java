package com.buildmlearn.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.buildmlearn.design.models.ColorGenerator;
import com.buildmlearn.design.models.TextDrawable;
import com.example.buildmlearntoolkit.R;

public class EfficientAdapter extends BaseAdapter {
	
    private LayoutInflater mInflater;
    private List<String> mDataList;
    private static final int HIGHLIGHT_COLOR = 0x999be6ff;
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder;
   public static int mPosition=-1;
    private Context mContext;
    
    public EfficientAdapter(Context context,List<String>mDataList) {
        mContext=context;
    	mInflater = LayoutInflater.from(context);
        this.mDataList=mDataList;
        mDrawableBuilder=TextDrawable.builder().round();
        
    }

    public int getCount() {
        return mDataList.size();
    }
    
    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.checkIcon = (ImageView) convertView.findViewById(R.id.check_icon);
            holder.view=convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.title.setText(mDataList.get(position));
        //holder.info.setText(mDataList.get(position).getInfo());
        try{TextDrawable drawable = mDrawableBuilder.build(String.valueOf(mDataList.get(position).charAt(0)).toUpperCase(), mColorGenerator.getColor(mDataList.get(position)));
        holder.image.setImageDrawable(drawable);
        holder.view.setBackgroundColor(Color.TRANSPARENT);
        }catch(Exception e)
        {
        	
        }
     // provide support for selected state
        //updateCheckedState(holder,position);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when the image is clicked, update the selected state
              
                updateCheckedState(holder,position);
            }
        });
       // holder.view.setBackgroundColor(HIGHLIGHT_COLOR);
        
        //Glide.with(mContext).load(mDataList.get(position).getIconUrl()).centerCrop().placeholder(R.drawable.flickr).crossFade().into(holder.image);
/*        mImageLoader.get(mDataList.get(position).getIconUrl(), 
        							ImageLoader.getImageListener(holder.image,R.drawable.flickr, android.R.drawable.ic_dialog_alert),
        							//Specify width & height of the bitmap to be scaled down when the image is downloaded.
        							50,50);
  */
        return convertView;
    }
    
    class ViewHolder {
        TextView title;
       
        ImageView image,checkIcon;
        View view;
        
    }	
    private void updateCheckedState(ViewHolder holder,int position) {
        if (position!=mPosition) {
            holder.image.setImageDrawable(mDrawableBuilder.build(" ", 0xff616161));
            holder.view.setBackgroundColor(HIGHLIGHT_COLOR);
            holder.checkIcon.setVisibility(View.VISIBLE);
            mPosition=position;
            
        }
        else {
            TextDrawable drawable = mDrawableBuilder.build(String.valueOf(mDataList.get(position).charAt(0)), mColorGenerator.getColor(mDataList.get(position)));
            holder.image.setImageDrawable(drawable);
            holder.view.setBackgroundColor(Color.TRANSPARENT);
            holder.checkIcon.setVisibility(View.GONE);
        }
    }

    private static class ListData {

        private String data;

        private boolean isChecked;

        public ListData(String data) {
            this.data = data;
        }

        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }
    }
}
    
	