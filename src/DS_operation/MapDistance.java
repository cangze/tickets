package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1620:15
 */
import java.util.HashMap;
import java.util.Map;

public class MapDistance {

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public  String getDistance(double lat1Str, double lng1Str, double lat2Str, double lng2Str) {

        double radLat1 = rad(lat1Str);
        double radLat2 = rad(lat2Str);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1Str) - rad(lng2Str);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance + "";
        distanceStr = distanceStr.
                substring(0, distanceStr.indexOf("."));

        return distanceStr;
    }
}