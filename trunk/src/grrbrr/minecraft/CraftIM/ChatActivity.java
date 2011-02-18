package grrbrr.minecraft.CraftIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ChatActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.chat);
		ListView lv = (ListView)findViewById(R.id.ChatList);
		lv.setAdapter(createAdapter());
	}

	private ListAdapter createAdapter()
    {
    	// Create some mock data
    	String[] testValues = new String[] {
    			"Grrbrr404: Test Msg",
    			"Grrbrr404: Test Msg",
    			"Grrbrr404: Test Msg long long long long long long long long long long long long long long",
    			"Grrbrr404: Test Msg",
    			"Grrbrr404: Test Msg"
    	};
		
    	ArrayList<String> dataRows = getData();
    	
    	// Create a simple array adapter (of type string) with the test values
    	ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.chat_row, dataRows);
    	 
    	return adapter;
    }

	private ArrayList<String> getData() {
		//String jsonData = "{\"version\":\"1\",\"data\":[{\"time\":\"unixtimestmp\",\"sender\":\"Grrbrr404\",\"message\":\"thisisatestmessage\"},{\"time\":\"unixtimestmp\",\"sender\":\"Grrbrr404\",\"message\":\"thisisanothertestmessage\"},{\"time\":\"unixtimestmp\",\"sender\":\"Grrbrr404\",\"message\":\"thisisatestmessage\"},{\"time\":\"unixtimestmp\",\"sender\":\"Grrbrr404\",\"message\":\"thisisaverylonglonglonglonglonglonglonglonglonglonglonglonglonglongtestmessage\"},{\"time\":\"unixtimestmp\",\"sender\":\"Grrbrr404\",\"message\":\"thisisatestmessage\"}]}";
		String jsonData = getDataFromServer();
		ArrayList<String> dataList = fetchChatRowsFrom(jsonData);
		return dataList;
	}

	private String getDataFromServer() {
		String rawData = "";
		
		try {
		    // Create a URL for the desired page
		    URL url = new URL("http://grrbrr.de/jsondata");

		    // Read all the text returned by the server
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    rawData = in.readLine();
		    in.close();
		    
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		return rawData;
	}

	private ArrayList<String> fetchChatRowsFrom(String srcJsonStr) {
		ArrayList<String> dataList = new ArrayList<String>();
		
		try {
			JSONObject jObj = new JSONObject(srcJsonStr);
			JSONArray jChatData = jObj.getJSONArray("data");
			
			for (int i = 0; i < jChatData.length(); i++) {
				
				String curSender = jChatData.getJSONObject(i).get("sender").toString();
				String curMsg = jChatData.getJSONObject(i).get("message").toString();
				
				dataList.add(curSender + ": " + curMsg);
			}
			
			dataList.add("Done.");
			
		} catch (JSONException e) {
			dataList.add("Failed to fetch chat data.");
			e.printStackTrace();
		}
		
		return dataList;
	}
}
