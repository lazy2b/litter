package com.lazylibs.litter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LitterActivity extends Activity {

    ListView listView = null;

    class LitterAdapter extends BaseAdapter {
        List<Litters.Litter> litters = null;
        protected Context mCxt;

        public LitterAdapter(List<Litters.Litter> litters, Context cxt) {
            this.mCxt = cxt;
            this.litters = litters;
        }

        @Override
        public int getCount() {
            return litters.size();
        }

        @Override
        public Object getItem(int position) {
            return litters.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        String siwtchMB(long size) {
            if (size < 0) return "0MB";
            float _speed = size * 1.00f;
            float curSpeed = _speed / (1024 * 1024);
            String curSpeed_str = curSpeed + "";
            if ((curSpeed_str).contains(".")) {
                String curSpeed_str_suffix = curSpeed_str.substring(curSpeed_str.indexOf(".") + 1, curSpeed_str.length());
                String curSpeed_str_prefix = curSpeed_str.substring(0, curSpeed_str.indexOf("."));
                curSpeed_str_suffix = (curSpeed_str_suffix.length() > 2) ? curSpeed_str_suffix.substring(0, 2) : curSpeed_str_suffix;
                String _curSpeed_str = curSpeed_str_prefix + "." + curSpeed_str_suffix;
                return _curSpeed_str + "MB";
            } else {
                return curSpeed_str + "MB";
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Litters.Litter litter = litters.get(position);
            if (convertView == null)
                convertView = LayoutInflater.from(mCxt).inflate(R.layout.list_item, null);
            ImageView iv = (ImageView) convertView.findViewById(R.id.item_image);
            TextView name_tv = (TextView) convertView.findViewById(R.id.app_name);
            TextView version_tv = (TextView) convertView.findViewById(R.id.versionmore);
            LinearLayout layout_one = (LinearLayout) convertView.findViewById(R.id.layout_one);
            LinearLayout update_state_ll = (LinearLayout) convertView.findViewById(R.id.update_state_ll);
            update_state_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        PackageManager pm = mCxt.getPackageManager();
                        if (!litter.getPackageName().equals("")) {
                            Intent intent = pm.getLaunchIntentForPackage(litter.getPackageName());
                            ((Activity) mCxt).startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(mCxt, "服务不能打开", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            TextView download_tv = (TextView) convertView.findViewById(R.id.download_tv);
            download_tv.setBackgroundResource(R.drawable.selector_normal_bg);
            download_tv.setText("打开");
            download_tv.setTextColor(mCxt.getResources().getColorStateList(R.color.selector_black_2_white));
            name_tv.setText(litters.get(position).getAppName());
            version_tv.setText(String.format("%s | %s", litters.get(position).getVersion(), siwtchMB(litters.get(position).getTotalSize())));
            try {
                Bitmap bitmapFromByte = getBitmapFromByte(litters.get(position).getAppImage());
                iv.setImageBitmap(bitmapFromByte);
            } catch (OutOfMemoryError e) {
                System.gc();
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }

    LitterAdapter adapter = null;
    ContentLoadingProgressBar progressBar;
    long start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Litters.glCxt = this;
        start = System.currentTimeMillis();
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.pb_loading);
    }

    @SuppressLint("StaticFieldLeak")
    protected static Context mCxt;

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    private Bitmap getBitmapFromDrawable(@NonNull Drawable drawable) {
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bmp;
    }

    private byte[] getByteFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return out.toByteArray();
    }

    public void loadData() {
        if (mCxt == null) mCxt = this;
        listView = findViewById(R.id.listview);
        new Thread() {
            @Override
            public void run() {
                super.run();
                Litters.glLitters.clear();
                PackageManager manager = mCxt.getPackageManager();
                @SuppressLint("QueryPermissionsNeeded") List<PackageInfo> lists = manager.getInstalledPackages(0);
                for (PackageInfo info : lists) {
                    ApplicationInfo appInfo = info.applicationInfo;
                    Litters.Litter litter = new Litters.Litter();
                    litter.setAppName(appInfo.loadLabel(manager).toString());
                    litter.setPackageName(info.packageName);
                    litter.setTotalSize((int) new File(appInfo.publicSourceDir).length());
                    litter.setVersion(info.versionName);
                    litter.setVersionCode(info.versionCode);
                    litter.setFilePath("");
                    try {
                        BitmapDrawable drawable = (BitmapDrawable) appInfo.loadIcon(manager);
                        Bitmap bmp = drawable.getBitmap();
                        litter.setAppImage(getByteFromBitmap(bmp));
                    } catch (OutOfMemoryError e) {
                        System.gc();
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Bitmap bmp = getBitmapFromDrawable(appInfo.loadIcon(manager));
                        litter.setAppImage(getByteFromBitmap(bmp));
                    }
                    if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != ApplicationInfo.FLAG_SYSTEM) {
                        Litters.glLitters.add(litter);
                    }
                }
                setupdata();
            }
        }.start();
        setupdata();
    }

    @SuppressLint("DefaultLocale")
    private void setupdata() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
        if (adapter == null) {
            adapter = new LitterAdapter(Litters.glLitters, mCxt);
            listView.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
        ((TextView) findViewById(R.id.tv_time)).setText(String.format("%dms", System.currentTimeMillis() - start));
    }

    private Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            } catch (OutOfMemoryError e) {
                System.gc();
                e.printStackTrace();
            }
            return bitmap;
        } else {
            return null;
        }
    }

}