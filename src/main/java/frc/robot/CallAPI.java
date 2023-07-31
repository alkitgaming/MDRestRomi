package frc.robot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CallAPI 
{
    public static Order GETOrder(Order destination)
    {
        Order ret;
        try
        {
            URL url = new URL(Constants.GET_ORDER_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            HttpURLConnection.setFollowRedirects(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            connection.getResponseCode();
            String inLine;
            String responseJson = "";
            while ((inLine = in.readLine()) != null) 
                responseJson += inLine;
            in.close();
            System.out.println(responseJson);
            ret = parseLoc(responseJson);
            return ret;
        }
        catch (Exception e) 
        {
            destination = null;
            System.out.println("ERROR!!!!!!!!!" + e.getStackTrace());
            return null;
        }
    }

    public static Order parseLoc(String json) throws Exception
    {
        JSONObject jo = new JSONObject(json);
        int row = jo.getInt("rownum");
        int id = jo.getInt("itemid");
        boolean leftSide = (jo.getString("side") == "a");
        return new Order(row, id, leftSide);
    }
}
