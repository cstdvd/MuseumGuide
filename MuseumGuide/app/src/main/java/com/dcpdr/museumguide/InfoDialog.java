package com.dcpdr.museumguide;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    private NavigableItem pic;

    public InfoDialog(Activity a, NavigableItem pic) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.pic = pic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.dialog_info, null);
        TextView name = (TextView)convertView.findViewById(R.id.name);
        TextView description = (TextView)convertView.findViewById(R.id.description);
        ImageView image = (ImageView) convertView.findViewById(R.id.picture);

        name.setText(pic.getName());
        description.setText(pic.getDescription());
        String picName = pic.getName();
        picName = picName.replace(" ","");
        picName = picName.toLowerCase();
        int resId = c.getResources().getIdentifier(picName, "drawable",
                Parameters.PACKAGE_NAME);
        image.setImageResource(resId);

        setContentView(convertView);

    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.btn_yes:
                c.finish();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }*/
        dismiss();
    }
}
