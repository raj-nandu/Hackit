package projects.amey.com.bestread.Volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UploadClass {

    private String url = "https://r6fp6iuvvk.execute-api.us-east-1.amazonaws.com/prod";
    private VolleyApplication volleyApplication;

    public UploadClass(Context context){
        volleyApplication = new VolleyApplication(context);
    }

    public void postRequest(String text){
        JSONObject postparams = new JSONObject();
        try {
            postparams.put("voice", "Joanna");
            postparams.put("text", text);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });

// Adding the request to the queue along with a unique string tag

        volleyApplication.addToRequestQueue(jsonObjReq, "postRequest");
    }

    public void getRequest(){
        JSONObject getparams = new JSONObject();
        JSONArray jsonArray = null;
        try {
            getparams.put("postId", "*");

        }
        catch(Exception e){
            e.printStackTrace();
        }

        CustomJSONArrayRequest customJSONArrayRequest = new CustomJSONArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    System.out.println(response.getJSONObject(0).getString("url"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("postId", "*");


                return params;
            }
        };
        /*JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                url, jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Failure Callback

                    }
                });*/

        volleyApplication.addToRequestQueue(customJSONArrayRequest, "getRequest");
    }

}
