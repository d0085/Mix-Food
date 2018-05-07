package parsers;

import org.json.JSONException;
import org.json.JSONObject;

import model.Pelanggan;

/**
 * Created by denny on 28/04/18.
 */

public class PelangganJsonParser {
    public static Pelanggan parseFeed(String content){
        try {

            JSONObject jsonObj = new JSONObject(content);

            Pelanggan pelanggan = new Pelanggan();

            pelanggan.setUsername(jsonObj.getString("username"));
            pelanggan.setAlamat(jsonObj.getString("alamat"));
            pelanggan.setNama(jsonObj.getString("nama"));
            pelanggan.setDob(jsonObj.getString("dob"));
            pelanggan.setNoHp(jsonObj.getString("no_hp"));
            pelanggan.setPass(jsonObj.getString("pass"));

            return pelanggan;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
