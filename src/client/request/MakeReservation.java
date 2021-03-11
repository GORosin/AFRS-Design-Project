package client.request;

import com.google.gson.Gson;
import common.models.Reservation;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MakeReservation implements RequestCommand {

    private Reservation reservation;

    /**
     * @param reservation The reservation object that is being added to the system
     */
    public MakeReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Execute the command to add the reservation to the system
     */
    @Override
    public void execute() {
        URL url;
        Gson gson = new Gson();
        try {
            url = new URL("http://localhost:8080/MakeReservationQuery");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    con.getOutputStream());
            out.write(gson.toJson(this.reservation));
            out.close();
            con.getInputStream();

            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undo() {
        URL url;
        Gson gson = new Gson();
        try {
            url = new URL("http://localhost:8080/DeleteReservationQuery");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("DELETE");
            OutputStreamWriter out = new OutputStreamWriter(
                    con.getOutputStream());
            out.write(gson.toJson(this.reservation));
            out.close();
            con.getInputStream();

            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
