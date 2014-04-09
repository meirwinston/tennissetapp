package com.tennissetapp.args;

import java.util.Date;
import java.util.List;

public class CreateTennisCenterArgs extends AddressArgs{
	public String tennisCenterName;
	public List<Integer> numberOfOutdoorCourts; //each count corresponds to outdoor surface type
	public List<Integer> numberOfIndoorCourts;
	public List<String> indoorSurface;
	public List<String> outdoorSurface;
	public String phoneNumber;
	public String website;
	public Date createdOn;
	
}
