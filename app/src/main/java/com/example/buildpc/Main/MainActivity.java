package com.example.buildpc.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildpc.ActivityListItem.RecommendPCActivity;
import com.example.buildpc.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private CardView stbuild, recommendPC, about, setting;
    TextView textView_Name_User;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainActivity.this);
        logoutDialog.setMessage("Đăng xuất?");
        logoutDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        logoutDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        logoutDialog.setCancelable(false);
        logoutDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        Bundle bundle = getIntent().getExtras();

        //Set toolbar như ActionBar
//        setSupportActionBar(toolbar);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_while_24dp);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_username);
        navUsername.setText(bundle.getString("UserName", "Sai key"));

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Đặt mục như đã chọn để tô sáng
                        menuItem.setChecked(true);
                        // Đóng Drawer lại khi item đc chạm vào
                        mDrawerLayout.closeDrawers();
                        // Nội dung sự kiện
                        switch (menuItem.getItemId()) {
                            case R.id.nav_build:
                                startActivity(new Intent(MainActivity.this, MainBuild.class));
                                break;
                            case R.id.nav_setting:
                                commingsoonDialog();
                                break;
                            case R.id.nav_about:
                                startActivity(new Intent(MainActivity.this, ActivityAbout.class));
                                break;
                            case R.id.nav_logout:
                                AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainActivity.this);
                                logoutDialog.setMessage("Đăng xuất?");
                                logoutDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                                logoutDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                logoutDialog.setCancelable(false);
                                logoutDialog.show();
                                break;
                        }


                        return true;
                    }
                });
//        Set các button
        stbuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainBuild.class));
            }
        });
        recommendPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, RecommendPCActivity.class);
               startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAbout.class);
                startActivity(intent);
            }
        });
        
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commingsoonDialog();
            }
        });

        //Bắt sự kiện khi tương tác DrawerLayout
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }
    //Bắt sự kiện khi bấm nút home của menu sẽ kéo DrawerLayout ra
    @SuppressLint("WrongConstant")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Ánh xạ
    private void Anhxa() {
//        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        stbuild = findViewById(R.id.stbuild);
        recommendPC = findViewById(R.id.recommendPC);
        about = findViewById(R.id.about);
        setting = findViewById(R.id.setting);
        textView_Name_User = findViewById(R.id.nav_username);
    }

    private void commingsoonDialog(){
        AlertDialog.Builder comingsoonDialog = new AlertDialog.Builder(MainActivity.this);
        comingsoonDialog.setMessage("Chức năng sẽ được update trong phiên bản tiếp theo!");
        comingsoonDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        comingsoonDialog.setCancelable(false);
        comingsoonDialog.show();
    }
}



