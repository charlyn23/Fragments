package charlyn23.c4q.nyc.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by charlynbuchanan on 7/22/15.
 */
public class CounterFragment extends Fragment {

    TextView counter;
    Button button;
    int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       //Set view here for the container that will host the fragment
        View view = inflater.inflate(R.layout.counter_fragment, container, false);

        counter = (TextView) view.findViewById(R.id.counter);
        button = (Button) view.findViewById(R.id.button);


        // Everything under the view initialized is added into the view
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            counter.setText(count);
        } else {
            count = 0;
        }

        //Counter logic
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count+= 1;
                counter.setText(String.valueOf(count));

            }
        });

        //return view now that fragment and it's logic has been applied
        return view;
    }
}
