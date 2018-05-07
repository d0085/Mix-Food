package dbhelper;

import android.os.AsyncTask;
import android.view.View;

import id.ac.unsyiah.android.mixfood.HttpManager;
import model.DBStatus;
import model.Pelanggan;
import parsers.PelangganJsonParser;
import parsers.StatusJsonParser;

/**
 * Created by denny on 29/04/18.
 */

public class DBHelperPelanggan {
    DBStatus status;

    public boolean addPelanggan(String uri, String params){
        MyTask task = new MyTask();
        task.execute(uri, params);

        if (status.isStatusTrue()) {
            return true;
        }
        return false;
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String konten = HttpManager.getData(params[0], params[1]);
            return konten;
        }

        @Override
        protected void onPostExecute(String result) {
            status = StatusJsonParser.parseFeed(result);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //updateDisplay(values[0]);
        }
    }
}
