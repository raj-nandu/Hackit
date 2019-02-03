package projects.amey.com.bestread;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projects.amey.com.bestread.Adapter.PagerAdapter;
import projects.amey.com.bestread.Fragments.CameraFragment;
import projects.amey.com.bestread.Fragments.SpeechFragment;
import projects.amey.com.bestread.Fragments.TextFragment;

public class MainActivity extends AppCompatActivity implements TextFragment.OnFragmentInteractionListener, SpeechFragment.OnFragmentInteractionListener, CameraFragment.OnFragmentInteractionListener {

    public static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        checkPermissions();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Camera"));
        tabLayout.addTab(tabLayout.newTab().setText("Text"));
        tabLayout.addTab(tabLayout.newTab().setText("Speech"));

        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void initialize(){

    }

    public boolean checkPermissions(){
        System.out.println("Build Version "  + Build.VERSION.SDK_INT);

        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }

        List<String> permissionList = new ArrayList<String>(Arrays.asList(
                Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE));


        /*if(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionList.remove(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)){
            permissionList.remove(Manifest.permission.ACCESS_COARSE_LOCATION);
        }*/
        if(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            permissionList.remove(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            permissionList.remove(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        System.out.println("Number of wifi states:" +  permissionList.size());

        if(permissionList.size() > 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_WIFI_STATE)) {
                showExplanation("Permission Needed", "Rationale", permissionList);
            }else {
                requestPermissions(permissionList.toArray(new String[permissionList.size()]), 100);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults != null ) {
                for(int i= 0; i< grantResults.length;i++){
                    if(grantResults[i]!= PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getApplicationContext(),"permission: " + permissions[i] + " is denied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void showExplanation(String title,
                                 String message,
                                 final List<String> permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission);
                    }
                });
        builder.create().show();
    }

    private void requestPermission(List<String> permissionName) {
        ActivityCompat.requestPermissions(this,
                permissionName.toArray(new String[permissionName.size()]), 100);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
