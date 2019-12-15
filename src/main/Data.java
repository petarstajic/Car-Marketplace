package main;
import java.util.*;
import java.io.*;

import com.google.gson.*;
import com.google.gson.stream.*;
public class Data {
	public static boolean writeToJson(ArrayList<Car> list,String path){
		try {
			Writer writer=new FileWriter(path);
			Gson gson=new GsonBuilder().create();
			gson.toJson(list,writer);
			writer.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<Car> readFromJson(String path){
		if(!new File(path).exists()){
			return new ArrayList<>();
		}
		try {
			JsonReader reader=new JsonReader(new FileReader(path));
			Gson gson=new GsonBuilder().create();
			Car[] cars=gson.fromJson(reader, Car[].class);
			ArrayList<Car> list=new ArrayList<>();
			for(int i=0;i<cars.length;i++){
				list.add(cars[i]);
			}
			return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
