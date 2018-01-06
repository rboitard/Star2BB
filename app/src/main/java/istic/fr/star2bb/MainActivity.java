package istic.fr.star2bb;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import istic.fr.star2bb.Fragment.ArretFragment;
import istic.fr.star2bb.Fragment.LigneFragment;
import istic.fr.star2bb.contract.StarContract;

public class MainActivity extends Activity implements LigneFragment.LigneFragmentCallback{

    private FragmentManager fragmentManager;

    private String Tag = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = this.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentLayout,LigneFragment.newInstance()).commit();
        //ligneFragement = (LigneFragment) fragmentManager.findFragmentById(R.id.fragmentLigne);
    }

    @Override
    public void SelectionLigneClicked(int idBus, int dir) {
        fragmentManager.beginTransaction().replace(R.id.fragmentLayout,ArretFragment.newInstance(idBus, dir)).commit();
    }
}
