package listeners.Grafana;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

public class GrafanaResultSender {


	private static final InfluxDB INFLXUDB = InfluxDBFactory.connect("http://localhost:8084", "admin", "admin");
	private static final String DATABASE = "selenium";

	static{
		INFLXUDB.setDatabase(DATABASE);

	}

	public static void send(final Point point){


		try
		{
			boolean InfluxStatus= INFLXUDB.ping().isGood();

			if (InfluxStatus)
			{
				INFLXUDB.write(point);
			}

		}
		catch(Exception e) {
			//System.out.println("Unable to connect to InfluxDB"+e);
			//e.printStackTrace();
		}


		//System.out.println(InfluxStatus);


	}

}