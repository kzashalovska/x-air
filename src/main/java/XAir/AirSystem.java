package XAir;

import java.util.Date;

public class AirSystem {
    private final long id;
    private final Date date = null;
    private final int sensorId = 0;
    private final int ppm = 0;
    private final int ram = 0;
    private final int temp = 0;
    private final int humidity = 0;
    private final String ssid;

    public AirSystem(long id, String ssid) {
        this.id = id;
        this.ssid = ssid;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getSensorId() {
        return sensorId;
    }

    public int getPpm() {
        return ppm;
    }

    public int getRam() {
        return ram;
    }

    public int getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getSsid() {
        return ssid;
    }
}
