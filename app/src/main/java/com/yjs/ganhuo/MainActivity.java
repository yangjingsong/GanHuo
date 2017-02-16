package com.yjs.ganhuo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;


import com.yjs.ganhuo.fragment.ganhuo.GanhuoFragment;
import com.yjs.ganhuo.fragment.jiandan.JokeFragment;
import com.yjs.ganhuo.fragment.picture.PictureFragment;
import com.yjs.ganhuo.fragment.tabFragment.TabFragmentView;
import com.yjs.ganhuo.fragment.zhihu.ZhihuFragment;
import com.yjs.ganhuo.fragment.tabFragment.TabViewPagerFragment;
import com.yjs.ganhuo.view.changeSkin.ChangeModeController;
import com.yjs.ganhuo.view.changeSkin.ChangeModeHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nav_view)
    NavigationView navView;
    ZhihuFragment zhihuFragment;
    TabViewPagerFragment tabViewPagerFragment;
    PictureFragment pictureFragment;
    JokeFragment jokeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ChangeModeController.getInstance().init(this,R.attr.class).setTheme(this,R.style.DayTheme,R.style.NightTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        zhihuFragment = new ZhihuFragment();
        tabViewPagerFragment = new TabViewPagerFragment();
        replaceFragment(zhihuFragment);
        Menu menu = navView.getMenu();
        menu.getItem(0).setChecked(true);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        if(ChangeModeHelper.getChangeMode(this) == ChangeModeHelper.MODE_DAY){
            menu.getItem(0).setTitle("夜间模式");
        }else {
            menu.getItem(0).setTitle("日间模式");
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        TypedValue attrTypedValue;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if(ChangeModeHelper.getChangeMode(this) == ChangeModeHelper.MODE_DAY){
                ChangeModeController.changeNight(this, R.style.NightTheme);
                attrTypedValue = ChangeModeController.getAttrTypedValue(this, R.attr.zhihuItemBackground);
                zhihuFragment.mRecyclerView.setBackgroundColor(getResources().getColor(attrTypedValue.resourceId));
                item.setTitle("日间模式");
            }else {
                ChangeModeController.changeDay(this, R.style.DayTheme);
                attrTypedValue = ChangeModeController.getAttrTypedValue(this, R.attr.zhihuItemBackground);
                zhihuFragment.mRecyclerView.setBackgroundColor(getResources().getColor(attrTypedValue.resourceId));
                item.setTitle("夜间模式");
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            replaceFragment(zhihuFragment == null?new ZhihuFragment():zhihuFragment);
        } else if (id == R.id.nav_gallery) {
            toolbar.setTitle("干货");
            replaceFragment(tabViewPagerFragment == null ? new TabViewPagerFragment() : tabViewPagerFragment);

        } else if (id == R.id.nav_slideshow) {
            toolbar.setTitle("美图");
            replaceFragment(pictureFragment == null?new PictureFragment():pictureFragment);

        } else if (id == R.id.joke) {
            toolbar.setTitle("段子");
             replaceFragment(jokeFragment == null?new JokeFragment():jokeFragment);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, fragment);
        fragmentTransaction.commit();
    }

}
