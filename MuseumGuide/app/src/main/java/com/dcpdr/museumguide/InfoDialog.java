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

        Button ok = (Button) findViewById(R.id.button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
