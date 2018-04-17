package boundary;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.wong0903.visitsg.R;


import java.util.ArrayList;
import java.util.List;

import Database.AppDatabase;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainInterface extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchInterface(), "Search");
        adapter.addFragment(new CategoryInterface(), "Categories");
        adapter.addFragment(new SuggestionInterface(), "Suggestion");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        btn_login = findViewById(R.id.btn_login);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new SearchInterface(), "Search");
//        adapter.addFragment(new CategoryInterface(), "Categories");
//        adapter.addFragment(new SuggestionInterface(), "Suggestion");
//        viewPager.setAdapter(adapter);
//    }

     class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        String tabTitles[] = new String[] { "Search", "Categories", "Suggestion" };

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


        public View getTabView(int position) {
            View tab = LayoutInflater.from(MainInterface.this).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tabTitles[position]);
            return tab;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Intent intent1 = new Intent(this, LoginInterface.class);
                startActivity(intent1);
                break;
            default:
                break;
        }

    }


}


//    class RetrieveFeedTask extends AsyncTask<Void, Void, String> //
//        private Exception exception;
//
//        protected void onPreExecute() {
//            progressBar.setVisibility(View.VISIBLE);
//            responseView.setText("");
//        }
//
//        protected String doInBackground(Void... urls) {
//            String attraction = inputText.getText().toString();
//            // Do some validation here
//            try {
//                URL url = new URL(API_URL );
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestProperty("Content-Type","application/json");
//                urlConnection.setRequestProperty("email", "lleong009@e.ntu.edu.sg");
//                urlConnection.setRequestProperty("token", API_KEY);
//                try {
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//                    StringBuilder stringBuilder = new StringBuilder();
//                    String line;
//                    while ((line = bufferedReader.readLine()) != null) {
//                        stringBuilder.append(line);
//                    }
//                    bufferedReader.close();
//                    return stringBuilder.toString();
//                }
//                finally{
//                    urlConnection.disconnect();
//                }
//            }
//            catch(Exception e) {
//                Log.e("ERROR", e.getMessage(), e);
//                return null;
//            }
//        }
//
//        protected void onPostExecute(String response) {
//            if(response == null) {
//                response = "THERE WAS AN ERROR";
//            }
//            progressBar.setVisibility(View.GONE);
//            Log.i("INFO", response);
//            // TODO: check this.exception
//            // TODO: do something with the feed
//            try {
//
////                JSONObject json = new JSONObject(response);
////                responseView.setText(json.toString(1))
//                responseView.setText(retrieveBasicInformation(response));
////                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
////                String requestID = object.getString("requestId");
////                int likelihood = object.getInt("likelihood");
////                JSONArray photos = object.getJSONArray("photos");
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private String retrieveBasicInformation(String jsonString)
//                throws JSONException {
//                final String requestID = "id";
//                final String TITLE = "title";
//
//                JSONObject json = new JSONObject(jsonString);
//                String id = json.getString(requestID);
//                String attraction = json.getString(TITLE);
//
//                String results = attraction + "-----" + id;
//                return results;
//        }
//    }

