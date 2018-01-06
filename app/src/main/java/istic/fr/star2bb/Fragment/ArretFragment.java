package istic.fr.star2bb.Fragment;

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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import istic.fr.star2bb.R;
import istic.fr.star2bb.contract.StarContract;

/**
 * Created by romain on 06/01/2018.
 */

public class ArretFragment extends Fragment {

    private static final String LIGNE = "Ligne";
    private static final String DIRECTION = "Direction";

    private int ligne;
    private int dir;

    private ListView arretView;
    public static ArretFragment newInstance(int idBus, int dir ) {
        ArretFragment fragment = new ArretFragment();
        Bundle args = new Bundle();
        args.putInt(LIGNE, idBus);
        args.putInt(DIRECTION, dir);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.arretfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arretView = (ListView) view.findViewById(R.id.arretView);

        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey(LIGNE)){
                ligne = args.getInt(LIGNE);
            }
            if (args.containsKey(DIRECTION)){
                dir = args.getInt(DIRECTION);
            }

        }

        String[] projection = new String[]{StarContract.Trips.TripColumns._ID,
                StarContract.BusRoutes.BusRouteColumns.SHORT_NAME,
                StarContract.BusRoutes.BusRouteColumns.LONG_NAME,
                StarContract.BusRoutes.BusRouteColumns.COLOR
        };
        String selection = "_ID = ?";
        String[] selectionArguments = new String[] {"101"};
        String sortOrder = null;

        listLigne = new ArrayList<String[][]>();
        mapLigneDir = new HashMap<String, String>();
        mapLigneId = new HashMap<String, String>();
        try {
            String  myType = yourCR.getType(uri);
            Cursor cursor = getActivity().getContentResolver().query(Uri.parse("content://fr.istic.starproviderBB/busroute/"), projection, null, null, null);
            while (cursor.moveToNext()) {
                String [][] strings = new String[1][2];
                strings[0][0] = cursor.getString(1);
                strings[0][1] = cursor.getString(3);
                listLigne.add(strings);
                mapLigneDir.put(cursor.getString(1),cursor.getString(2));
                mapLigneId.put(cursor.getString(1),cursor.getString(0));
            }
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
