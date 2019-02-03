package projects.amey.com.bestread.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import projects.amey.com.bestread.Adapter.RecordListAdapter;
import projects.amey.com.bestread.R;
import projects.amey.com.bestread.Utilities.RecyclerItemTouchListener;

public class SpeechFragment extends Fragment {

    public static ArrayList<String> records = new ArrayList<String>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextFragment.OnFragmentInteractionListener mListener;
    public static RecordListAdapter recordListAdapter = new RecordListAdapter(records);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();


    }

    public SpeechFragment(){

    }

    public void initialize(){
        records.clear();
        File file = new File(Environment.getExternalStorageDirectory(),"/BestRead/Audio/");
        file.mkdirs();

        File files[] = file.listFiles();
        if(files != null) {
            for (int i = 0; i < files.length; i++) {
                records.add(files[i].getName());
            }
            recordListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_speech,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recordListRecycler);
        recyclerView.setAdapter(recordListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(getContext(), recyclerView, new RecyclerItemTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //ImageView imageView = (ImageView) view.findViewById(R.id.action);
                initialize();
                //TODO play the audio file
            }

            //@Override
            public void onLongClick(View view, int position) {
                /*Intent intent = new Intent(getContext(), ResultActivity.class);
                TextView textView = (TextView) view.findViewById(R.id.name);
                intent.putExtra("file",textView.getText().toString());
                startActivity(intent);*/
            }
        }));
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
        if (context instanceof TextFragment.OnFragmentInteractionListener) {
            mListener = (TextFragment.OnFragmentInteractionListener) context;
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
}
