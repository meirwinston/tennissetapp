package tennissetapp.test;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class GeoCodeResult {
	public List<Result> results;
	public String status;
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class Result{
		public List<AddressComponent> address_components;
		public String formatted_address;
		public Geometry geometry;
		public List<String> types;
		@Override
		public String toString() {
			return formatted_address;
		}
		
	}
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class AddressComponent{
		public String long_name;
		public String short_name;
		public List<String> types;
	}

	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class Geometry{
		public Location location;
		public String location_type;
		public NorthEastSouthWest viewport;
		public NorthEastSouthWest bounds;
		
		@JsonIgnoreProperties(ignoreUnknown=true)
		public static class Location{
			public double lat;
			public double lng;
		}
		
		@JsonIgnoreProperties(ignoreUnknown=true)
		public static class NorthEastSouthWest{
			public Location northeast;
			public Location southwest;
			
		}
		
	}

	@Override
	public String toString() {
		System.out.println("");
		return "GeoCodeResult [results=" + results + ", status=" + status + "]";
	}
	
	
}