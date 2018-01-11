package nyc.c4q.androidtest_unit4final;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by justiceo on 1/9/18.
 */

public class InfoFragment extends Fragment {

    Button more;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.info_fragment, container, false);
        more = v.findViewById(R.id.more_button);
        textView = v.findViewById(R.id.more_textView);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setVisibility(View.VISIBLE);
                more.setVisibility(View.INVISIBLE);
            }
        });
        return v;
    }
}
