package com.basketball.mybasketball;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;

public class AddTeamDialog extends DialogFragment {

    AddDialogListener mListener;
    ImageButton imageButton;
    String uri;
    int PICK_IMAGE_REQUEST = 1;


    public interface AddDialogListener {
        public void onOk(DialogFragment dialog, String uri);
        public void onCancel(DialogFragment dialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_team, parent, false);
        // Get the layout inflater
        imageButton = view.findViewById(R.id.btnTeamImage);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });

        Button btnOK = view.findViewById(R.id.btDialogOk);
        Button btnCancel = view.findViewById(R.id.btDialogCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddTeamDialog.this.getDialog().dismiss();
                callOK();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTeamDialog.this.getDialog().cancel();
                callCancel();
            }
        });

        return view;
    }

    void getImage(){
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData().toString();
            imageButton.setImageURI(Uri.parse(uri));
        }
    }

    void callOK(){
        mListener.onOk(this, uri);
    }

    void callCancel(){
        mListener.onCancel(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AddDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement AddDialogListener");
        }
    }
}