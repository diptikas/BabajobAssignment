package com.diptika.babajob.babajobassignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diptika.babajob.babajobassignment.ParseApplication;
import com.diptika.babajob.babajobassignment.R;
import com.diptika.babajob.babajobassignment.activity.AddNewUserActivity;
import com.diptika.babajob.babajobassignment.db.UserDataBaseHandler;
import com.diptika.babajob.babajobassignment.model.UserInfo;
import com.diptika.babajob.babajobassignment.qrgenerator.Contents;
import com.diptika.babajob.babajobassignment.qrgenerator.QRCodeEncoder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by diptika.s on 15/05/16.
 */
public class UserListAdapter extends BaseAdapter {
    private Context mContext;
    List<UserInfo> userInfoList;
    private ViewHolder holder;
    private int count=0;
    private String day=null;
    private UserDataBaseHandler userDataBaseHandler;
    private ParseApplication parseApplication;

    public UserListAdapter(Activity context, List<UserInfo> userInfoList, String day) {
        mContext = context;
        this.day=day;
        this.userInfoList = userInfoList;
        userDataBaseHandler = new UserDataBaseHandler(mContext);
        parseApplication = new ParseApplication();

    }

    @Override
    public int getCount() {
        return userInfoList.size();
    }

    @Override
    public UserInfo getItem(int position) {
        return userInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_list_item, null, true);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.userId.setText("ID - "+userInfoList.get(position).getUserID());
        holder.userName.setText("Name - "+userInfoList.get(position).getUserName());
        holder.userNum.setText("Contact No. - "+userInfoList.get(position).getUserContactNum());
        holder.userAge.setText("Age -"+userInfoList.get(position).getUserAge());
        holder.userLoc.setText("Location - "+userInfoList.get(position).getUserLocation());
        holder.userBloodgroup.setText("BloodGroup - "+userInfoList.get(position).getUserBloodGroup());
        fetchQrFromServer(userInfoList.get(position), holder.qrCode);

        if(day.equals("day1")){
            holder.presentUserCh.setChecked(userInfoList.get(position).getDay1() ==2);
        } else if(day.equals("day2")){
            holder.presentUserCh.setChecked(userInfoList.get(position).getDay2() ==2);
        }else{
            holder.presentUserCh.setChecked(userInfoList.get(position).getDay3() ==2);
        }

        holder.editUserLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUserDetail(position);
            }
        });

        holder.presentUserCh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String str = "";
                boolean flag = isChecked;
                if(isChecked){
                    count=1;
                    str = "present";
                }else {
                    count=0;
                    str = "absent";
                }
                userDataBaseHandler.updateUserPresent(day, userInfoList.get(position).getUserID(), count);
                Toast.makeText(mContext, "User marked as "+ str +" successfully.", Toast.LENGTH_SHORT).show();

            }
        });
        return convertView;
    }

    private void fetchQrFromServer(UserInfo userInfo, final ImageView qrCode){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("QRCODES");
        query.whereEqualTo("userId",userInfo.getUserID());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects != null && objects.size() > 0) {
                        ParseObject parseObject = objects.get(objects.size() - 1);
                        ParseFile image = (ParseFile) parseObject.get("QRImage");
                        if (image != null) {
                            image.getDataInBackground(new GetDataCallback() {
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        qrCode.setImageBitmap(bmp);
                                    } else {
                                        Log.d("test", "There was a problem downloading the data.");
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });

    }

    private void editUserDetail(int position) {
        int userId=userInfoList.get(position).getUserID();
        Intent editUserIntent=new Intent(mContext, AddNewUserActivity.class);
        editUserIntent.putExtra("UserId",userId);
        mContext.startActivity(editUserIntent);
        notifyDataSetChanged();

    }

    private ViewHolder createViewHolder(View convertView) {
        ViewHolder holder = new ViewHolder();
        holder.userId=(TextView)convertView.findViewById(R.id.user_id);
        holder.userName=(TextView)convertView.findViewById(R.id.user_name);
        holder.userNum=(TextView)convertView.findViewById(R.id.user_contact_num);
        holder.userAge=(TextView)convertView.findViewById(R.id.user_age);
        holder.userLoc=(TextView)convertView.findViewById(R.id.user_location);
        holder.userBloodgroup=(TextView)convertView.findViewById(R.id.user_bloodgroup);
        holder.editUserLayout=(LinearLayout)convertView.findViewById(R.id.edit_user);
        holder.presentUserCh=(CheckBox)convertView.findViewById(R.id.present_user);
        holder.qrCode=(ImageView)convertView.findViewById(R.id.qr_code);
        return holder;
    }


    public class ViewHolder {
    public TextView userId,userName,userNum,userAge,userLoc,userBloodgroup;
    public LinearLayout editUserLayout;
    public CheckBox presentUserCh;
    public ImageView qrCode;
    }

}