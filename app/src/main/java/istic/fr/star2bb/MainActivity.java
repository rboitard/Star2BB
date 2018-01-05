package istic.fr.star2bb;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import istic.fr.star2bb.Fragment.LigneFragment;
import istic.fr.star2bb.contract.StarContract;

public class MainActivity extends Activity{

    private LigneFragment ligneFragement;

    private String Tag = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = this.getFragmentManager();
        ligneFragement = (LigneFragment) fragmentManager.findFragmentById(R.id.fragmentLigne);
    }

    public void testContentProvider()
    {
        Uri uri = Uri.parse("content://fr.istic.starproviderBB/busroute/");
        ContentProviderClient yourCR = getContentResolver().acquireContentProviderClient(uri);

        String[] projection = new String[]{StarContract.BusRoutes.BusRouteColumns._ID,
                StarContract.BusRoutes.BusRouteColumns.SHORT_NAME,
                StarContract.BusRoutes.BusRouteColumns.LONG_NAME,
                StarContract.BusRoutes.BusRouteColumns.DESCRIPTION,
                StarContract.BusRoutes.BusRouteColumns.TYPE,
                StarContract.BusRoutes.BusRouteColumns.COLOR,
                StarContract.BusRoutes.BusRouteColumns.TEXT_COLOR};
        String selection = null;
        String[] selectionArguments = null;
        String sortOrder = null;

        try {
            String  myType = yourCR.getType(uri);
            Log.i(Tag, "Type : "+myType );
            Cursor cursor = getContentResolver().query(Uri.parse("content://fr.istic.starproviderBB/busroute/"), projection, null, null, null);

            while (cursor.moveToNext()) {
                
                Log.i(Tag, "_ID :"+cursor.getString(0)+ "\n Short Name :"+cursor.getString(1) );
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}
