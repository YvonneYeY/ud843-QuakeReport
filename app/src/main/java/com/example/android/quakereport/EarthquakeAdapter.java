package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An {@link EarthquakeAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link Earthquake} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";
    String primaryLocation;
    String locationOffset;
    private  List<Earthquake> earthquakes=new ArrayList<Earthquake>();

    /**
     * Constructs a new {@link EarthquakeAdapter}.
     *
     * @param context of the app
     * @param earthquakes is the list of earthquakes, which is the data source of the adapter
     */
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
            final Earthquake currentEarthquake = (Earthquake) getItem(position);
            String originalLocation = currentEarthquake.getPlace();
            if (originalLocation.contains(LOCATION_SEPARATOR)) {
                String[] parts = originalLocation.split(LOCATION_SEPARATOR);
                locationOffset = parts[0] + LOCATION_SEPARATOR;
                primaryLocation = parts[1];
            } else {
                locationOffset = getContext().getString(R.string.near_the);
                primaryLocation = originalLocation;
            }


            // 根据当前的地震震级获取相应的背景颜色


            // 设置震级圆圈的颜色



            TextView textView1 = (TextView) listItemView.findViewById(R.id.textView1);
            String formattedMagnitude = formatMagnitude(currentEarthquake.getMag());
            textView1.setText(formattedMagnitude);

            GradientDrawable magnitudeCircle = (GradientDrawable) textView1.getBackground();
            int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());
            magnitudeCircle.setColor(magnitudeColor);


            TextView textView2_1 = (TextView) listItemView.findViewById(R.id.textView2_2);
            textView2_1.setText(primaryLocation);

            TextView textView2_2 = (TextView) listItemView.findViewById(R.id.textView2_1);
            textView2_2.setText(locationOffset);

            TextView textView3 = (TextView) listItemView.findViewById(R.id.textView3);
            textView3.setText(String.valueOf(currentEarthquake.getTimed()));

            TextView textView4 = (TextView) listItemView.findViewById(R.id.textView4);
            textView4.setText(String.valueOf(currentEarthquake.getTimeh()));


        return listItemView;

    }
    private int getMagnitudeColor(double magnitude) {
              int magnitudeColorResourceId;
              int magnitudeFloor = (int) Math.floor(magnitude);
              switch (magnitudeFloor) {
                       case 0:
                       case 1:
                                magnitudeColorResourceId = R.color.magnitude1;
                                break;
                       case 2:
                                magnitudeColorResourceId = R.color.magnitude2;
                                break;
                        case 3:
                               magnitudeColorResourceId = R.color.magnitude3;
                                break;
                        case 4:
                                magnitudeColorResourceId = R.color.magnitude4;
                                break;
                        case 5:
                                magnitudeColorResourceId = R.color.magnitude5;
                                break;
                        case 6:
                                magnitudeColorResourceId = R.color.magnitude6;
                                break;
                        case 7:
                                magnitudeColorResourceId = R.color.magnitude7;
                                break;
                       case 8:
                                magnitudeColorResourceId = R.color.magnitude8;
                                break;
                        case 9:
                                magnitudeColorResourceId = R.color.magnitude9;
                                break;
                        default:
                                magnitudeColorResourceId = R.color.magnitude10plus;
                                break;
                    }

                       return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
            }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
