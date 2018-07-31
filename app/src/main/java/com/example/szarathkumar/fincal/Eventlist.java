package com.example.szarathkumar.fincal;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAuthIOException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import org.mortbay.jetty.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;



import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Eventlist.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Eventlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Eventlist extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MultiDexApplication mda;
    private String APPLICATION_NAME;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static NetHttpTransport HTTP_TRANSPORT =null;
    private TextView tv;
    private SharedPreferences pref;
    public  MainActivity ma;
    private  Calendar service;
    public static Eventlist el;
    private Context mcontext;
    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved credentials/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH =  "client_secret.json";

    public Context getMcontext() {
        return mcontext;
    }

    public void setMcontext(Context mcontext) {
        this.mcontext = mcontext;
    }

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Eventlist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Eventlist.
     */
    // TODO: Rename and change types and number of parameters
    public static Eventlist newInstance(String param1, String param2) {
        Eventlist fragment = new Eventlist();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_eventlist, container, false);
        setMcontext(getActivity());

        mda = new MultiDexApplication();
            tv = (TextView) view.findViewById(R.id.tvel);
        el = this;

        APPLICATION_NAME = "Fincal";

        Log.d("check", "run: ");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {


                mainback mTask = new mainback() {


                    @Override
                    protected String doInBackground(Void... voids) {     Log.d("check", "do: ");
                    mainback2 mb2 = new mainback2() {
                        @Override
                        protected String doInBackground(Void... voids) {
                            try {
                                final NetHttpTransport HTTP_TRANSPORT = new com.google.api.client.http.javanet.NetHttpTransport();//GoogleNetHttpTransport.newTrustedTransport();
                                //  new com.google.api.client.http.javanet.NetHttpTransport();
                                Log.d("check", "dont: ");
                                service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                                        .setApplicationName(APPLICATION_NAME)
                                        .build();
                                Log.d("check", service.toString());
                                // List the next 10 events from the primary calendar.

                            }
                            catch (Exception exc)
                            {
                                Log.d("error", exc.toString());
                            }


                            return null;
                        }
                    };
                    mb2.execute();




                        return null;
                    };

                    protected void onPostExecute(String message)
                    {
                        try {
                            DateTime now = new DateTime(System.currentTimeMillis());

                            Events events = service.events().list("primary")
                                    .setMaxResults(10)
                                    .setTimeMin(now)
                                    .setOrderBy("startTime")
                                    .setSingleEvents(true)
                                    .execute();
                            List<Event> items = events.getItems();
                            Log.d("check", items.toString());
                            if (items.isEmpty()) {
                                System.out.println("No upcoming events found.");
                            } else {
                                System.out.println("Upcoming events");
                                for (Event event : items) {
                                    DateTime start = event.getStart().getDateTime();
                                    if (start == null) {
                                        start = event.getStart().getDate();
                                    }

                                    Log.d("check",  event.getSummary());
                                    System.out.printf("%s (%s)\n", event.getSummary(), start);
                                }
                            }
                        }
                        catch (Exception ex)
                        {

                        }


                    }
                };
                mTask.execute();
            }
        });
        t.start();







        return view;

    }



    private abstract class mainback extends AsyncTask<Void, Void, String> {



    }
    private abstract class mainback2 extends AsyncTask<Void, Void, String> {



    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


   /* public Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException{

        String db = mcontext.getFilesDir().getAbsolutePath();
        Log.d("path", db);


        InputStream in = mcontext.getAssets().open(CREDENTIALS_FILE_PATH);//new FileInputStream(new File("/data/user/0/com.example.szarathkumar.fincal/files/"+CREDENTIALS_FILE_PATH));//mcontext.getAssets().open(CREDENTIALS_FILE_PATH);
        Log.d("path", db);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(Eventlist.this.getActivity().getFilesDir().getAbsoluteFile(),TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        //AuthorizationCodeInstalledApp ap = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
       // Log.d("check",ap.toString());
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

    }
*/
    private  Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = mcontext.getAssets().open(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(Eventlist.this.getActivity().getFilesDir().getAbsoluteFile(),TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }


}

