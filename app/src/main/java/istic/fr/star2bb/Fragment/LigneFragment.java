package istic.fr.star2bb.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import istic.fr.star2bb.R;
import istic.fr.star2bb.contract.StarContract;

/**
 * Created by romai on 04/01/2018.
 */

public class LigneFragment extends Fragment {


    public interface LigneFragmentCallback {
        void SelectionLigneClicked(int idBus, int dir);
    }

    private LigneFragmentCallback ligneFragmentCallBack;
    private Button buttonTime;
    private Button buttonDate;
    private TimePickerFragment timePickerFragment;
    private DatePickerFragment datePickerFragment;
    private Spinner spinnerLigne;
    private Spinner spinnerDirection;
    private HashMap<String, String> mapLigneDir;
    private HashMap<String, String> mapLigneId;
    private List<String[][]> listLigne;

    private int idBus;
    private int direction;


    public static LigneFragment newInstance() {
        return new LigneFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        timePickerFragment = new TimePickerFragment();
        timePickerFragment.setLigneFragement(this);

        datePickerFragment = new DatePickerFragment();
        datePickerFragment.setLigneFragement(this);

        buttonTime = (Button) getView().findViewById(R.id.buttonTime);

        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerFragment.show(getActivity().getFragmentManager(), "timePicker");
            }
        });

        buttonDate = (Button) getView().findViewById(R.id.buttonDate);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(getActivity().getFragmentManager(), "datePicker");
            }
        });




        String[] projection = new String[]{StarContract.BusRoutes.BusRouteColumns.ROUTE_ID,
                StarContract.BusRoutes.BusRouteColumns.SHORT_NAME,
                StarContract.BusRoutes.BusRouteColumns.LONG_NAME,
                StarContract.BusRoutes.BusRouteColumns.COLOR
                };

        listLigne = new ArrayList<String[][]>();
        mapLigneDir = new HashMap<String, String>();
        mapLigneId = new HashMap<String, String>();

        Cursor cursor = getActivity().getContentResolver().query(Uri.parse("content://fr.istic.starproviderBB/busroute/"), projection, null, null, null);
        while (cursor.moveToNext()) {
            String [][] strings = new String[1][2];
            strings[0][0] = cursor.getString(1);
            strings[0][1] = cursor.getString(3);
            listLigne.add(strings);
            mapLigneDir.put(cursor.getString(1),cursor.getString(2));
            mapLigneId.put(cursor.getString(1),cursor.getString(0));
        }

        spinnerLigne = (Spinner) getView().findViewById(R.id.spinnerLigne);
        spinnerDirection = (Spinner) getView().findViewById(R.id.spinnerDirection);
        addItemOnSpinner(spinnerLigne, listLigne);
        spinnerLigne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String ligne = mapLigneDir.get(((TextView)selectedItemView).getText());
                idBus = Integer.parseInt(mapLigneId.get(((TextView)selectedItemView).getText()));
                List<String> listDir = new ArrayList<String>();
                String[] strings = ligne.split("<>");
                listDir.add(strings[0]);
                listDir.add(strings[strings.length-1]);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listDir);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDirection.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinnerDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                direction = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Button buttonValider = (Button) getView().findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ligneFragmentCallBack != null)
                    ligneFragmentCallBack.SelectionLigneClicked(idBus, direction);
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lignefragment, container, false);
    }

    private void addItemOnSpinner(Spinner spinner, List<String[][]> items){
        spinner.setAdapter(new SpinnerLigneAdapter(getActivity(), items));
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof LigneFragmentCallback)
            ligneFragmentCallBack = (LigneFragmentCallback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ligneFragmentCallBack = null;
    }

    public void updateTime(int hourOfDay, int minute) {
        buttonTime.setText(new String(hourOfDay + " : " + minute));
    }

    public void updateDate(int year, int month, int day) {
        buttonDate.setText(new String(day + " / " + month + " / " + year));
    }
}
