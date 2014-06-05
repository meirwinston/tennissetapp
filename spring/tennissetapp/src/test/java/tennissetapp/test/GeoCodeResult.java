package tennissetapp.test;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.tennissetapp.persistence.entities.Address;


@JsonIgnoreProperties(ignoreUnknown=true)
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
		
		public void populateForm(Address f){
			f.setLatitude(geometry.location.lat);
			f.setLongitude(geometry.location.lng);
//			f.addressTypes = types.toArray(new String[]{});

			for(GeoCodeResult.AddressComponent c : address_components){
				if(c.types.contains("administrative_area_level_1")){
					f.setAdministrativeAreaLevel1(c.long_name);
					f.setAdministrativeAreaLevel1ShortName(c.short_name);
				}
				else if(c.types.contains("administrative_area_level_2")){
					f.setAdministrativeAreaLevel2(c.long_name);
					f.setAdministrativeAreaLevel2ShortName(c.short_name);
				}
				else if(c.types.contains("street_number")){
					f.setStreetNumber(c.long_name);
				}
				else if(c.types.contains("route")){
					f.setRoute(c.long_name);
					f.setRouteShortName(c.short_name);
				}
				else if(c.types.contains("locality")){
					f.setLocality(c.long_name);
					f.setLocalityShortName(c.short_name);
				}
				else if(c.types.contains("country")){
					f.setCountry(c.long_name);
					f.setCountryShortName(c.short_name);
				}
				else if(c.types.contains("postal_code")){
					f.setPostalCode(c.long_name);
				}
				else if(c.types.contains("sublocality")){
					f.setSublocality(c.long_name);
					f.setSublocalityShortName(c.short_name);
				}
				else if(c.types.contains("political")){
					f.setPolitical(c.long_name);
					f.setPoliticalShortName(c.short_name);
				}
				else if(c.types.contains("neighborhood")){
					f.setNeighborhood(c.long_name);
					f.setNeighborhoodShortName(c.short_name);
				}
			}
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

/*
{
"results" : [
   {
      "address_components" : [
         {
            "long_name" : "1600",
            "short_name" : "1600",
            "types" : [ "street_number" ]
         },
         {
            "long_name" : "Amphitheatre Parkway",
            "short_name" : "Amphitheatre Pkwy",
            "types" : [ "route" ]
         },
         {
            "long_name" : "Mountain View",
            "short_name" : "Mountain View",
            "types" : [ "locality", "political" ]
         },
         {
            "long_name" : "Santa Clara",
            "short_name" : "Santa Clara",
            "types" : [ "administrative_area_level_2", "political" ]
         },
         {
            "long_name" : "California",
            "short_name" : "CA",
            "types" : [ "administrative_area_level_1", "political" ]
         },
         {
            "long_name" : "United States",
            "short_name" : "US",
            "types" : [ "country", "political" ]
         },
         {
            "long_name" : "94043",
            "short_name" : "94043",
            "types" : [ "postal_code" ]
         }
      ],
      "formatted_address" : "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA",
      "geometry" : {
         "location" : {
            "lat" : 37.4219988,
            "lng" : -122.083954
         },
         "location_type" : "ROOFTOP",
         "viewport" : {
            "northeast" : {
               "lat" : 37.42334778029149,
               "lng" : -122.0826050197085
            },
            "southwest" : {
               "lat" : 37.42064981970849,
               "lng" : -122.0853029802915
            }
         }
      },
      "types" : [ "street_address" ]
   }
],
"status" : "OK"
}

//--

 
 {
      "address_components" : [
         {
            "long_name" : "1600",
            "short_name" : "1600",
            "types" : [ "street_number" ]
         },
         {
            "long_name" : "Amphitheatre Parkway",
            "short_name" : "Amphitheatre Pkwy",
            "types" : [ "route" ]
         },
         {
            "long_name" : "Mountain View",
            "short_name" : "Mountain View",
            "types" : [ "locality", "political" ]
         },
         {
            "long_name" : "Santa Clara",
            "short_name" : "Santa Clara",
            "types" : [ "administrative_area_level_2", "political" ]
         },
         {
            "long_name" : "California",
            "short_name" : "CA",
            "types" : [ "administrative_area_level_1", "political" ]
         },
         {
            "long_name" : "United States",
            "short_name" : "US",
            "types" : [ "country", "political" ]
         },
         {
            "long_name" : "94043",
            "short_name" : "94043",
            "types" : [ "postal_code" ]
         }
      ],
      "formatted_address" : "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA",
      "geometry" : {
         "location" : {
            "lat" : 37.4219988,
            "lng" : -122.083954
         },
         "location_type" : "ROOFTOP",
         "viewport" : {
            "northeast" : {
               "lat" : 37.42334778029149,
               "lng" : -122.0826050197085
            },
            "southwest" : {
               "lat" : 37.42064981970849,
               "lng" : -122.0853029802915
            }
         }
      },
      "types" : [ "street_address" ]
   }
 */
