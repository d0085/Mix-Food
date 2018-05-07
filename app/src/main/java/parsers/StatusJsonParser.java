package parsers;

import org.json.JSONException;
import org.json.JSONObject;

import model.DBStatus;

/**
 * Created by denny on 29/04/18.
 */

public class StatusJsonParser {
    static DBStatus status = new DBStatus();
    public static DBStatus parseFeed(String content){
        try {

            JSONObject jsonObj = new JSONObject(content);

            status.setStatus(jsonObj.getBoolean("status"));
            status.setDescription(jsonObj.getString("description"));

            return status;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
