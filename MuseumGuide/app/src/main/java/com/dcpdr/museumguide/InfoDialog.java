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
// Customized dialog that shows information about the picture close to you

public class InfoDialog extends Dialog {

    public Activity c;
    public Dialog d;
    public Button ok;
    private NavigableItem pic;

    public InfoDialog(Activity a, NavigableItem pic) {
        super(a);
        this.c = a;
        this.pic = pic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.dialog_info, null);
        setCancelable(false);
        TextView name = (TextView)convertView.findViewById(R.id.name);
        TextView description = (TextView)convertView.findViewById(R.id.description);
        ImageView image = (ImageView) convertView.findViewById(R.id.picture);

        // "pic" is a NavigableItem and it contains all the information about the picture
        name.setText(pic.getName());
        description.setText(pic.getDescription());
        // The picture's name will be modified in order to find the appropriate image
        String picName = pic.getName();
        picName = picName.replace(" ","");
        picName = picName.toLowerCase();
        int resId = c.getResources().getIdentifier(picName, "drawable",
                Parameters.PACKAGE_NAME);
        image.setImageResource(resId);

        setContentView(convertView);

        ok = (Button) findViewById(R.id.button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
