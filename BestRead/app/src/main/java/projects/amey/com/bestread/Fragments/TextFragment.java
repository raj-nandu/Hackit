package projects.amey.com.bestread.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import projects.amey.com.bestread.R;
import projects.amey.com.bestread.Volley.UploadClass;

public class TextFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    public static EditText editText;
    public Button convertButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();


    }

    public TextFragment(){

    }

    public void initialize(){

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_text,container,false);
        editText = view.findViewById(R.id.textView2);
        convertButton = view.findViewById(R.id.button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadClass uploadClass = new UploadClass(getContext());
                uploadClass.postRequest(editText.getText().toString());
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void changeText(String text){
        editText.setText(text);
    }
}
