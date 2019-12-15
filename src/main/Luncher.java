package main;

import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import com.google.gson.Gson;

public class Luncher {

	public static void main(String[] args) {
		staticFiles.location("/public");
		port(5000);
		
		HashMap<String,Object> polja=new HashMap<>();
		
	
		
		get("/home",(request,response)->{
			ArrayList<Car> tmp=Data.readFromJson("cars.json");
			polja.put("cars", tmp);
			return new ModelAndView(polja,"index.hbs");
		},new HandlebarsTemplateEngine());
		
		get("/cars",(request,response)->{
			ArrayList<Car> tmp=Data.readFromJson("cars.json");
			polja.put("cars", tmp);
			return new ModelAndView(polja,"cars.hbs");
		},new HandlebarsTemplateEngine());
		
			
		
		get("/api/search",(request,response)->{
			response.type("text/text");
			int godineOd=Integer.parseInt(request.queryParams("godineOd"));
			int godineDo=Integer.parseInt(request.queryParams("godineDo"));
			ArrayList<Car> automobili=new ArrayList<>();
			for(Car element:Data.readFromJson("cars.json")){
				if(element.getGodiste()>=godineOd && element.getGodiste()<godineDo){
					automobili.add(element);
				}
			}

			Gson gson = new Gson();
			return gson.toJson(automobili);
		});
		
		get("/cars/forms/add",(request,response)->{
			return new ModelAndView(polja,"addCar.hbs");
		},new HandlebarsTemplateEngine());
		
		get("/cars/add",(request,response)->{
			ArrayList<Car> tmp=Data.readFromJson("cars.json");
			int id=tmp.size()+1;
			String brend=request.queryParams("brend");
			String model=request.queryParams("model");
			int godiste=Integer.parseInt(request.queryParams("godiste"));
			double cena=Double.parseDouble(request.queryParams("cena"));
			tmp.add(new Car(id,brend,model,godiste,cena));
			Data.writeToJson(tmp, "cars.json");
			polja.put("cars", tmp);
			return new ModelAndView(polja,"index.hbs");
		},new HandlebarsTemplateEngine());
		
		get("/cars/get",(request,response)->{
			ArrayList<Car> tmp=new ArrayList<>();
			for(Car element:Data.readFromJson("cars.json")){
				if(element.getId()==Integer.parseInt(request.queryParams("id"))){
					tmp.add(element);
					break;
				}
			}
			polja.put("cars", tmp);
			return new ModelAndView(polja,"cars.hbs");
		},new HandlebarsTemplateEngine());
		
		
		post("/api/search/sort",(request,response)->{
			response.type("text/text");
			String sort = request.queryParams("sort");
	
				int godineOd=Integer.parseInt(request.queryParams("godineOd"));
				int godineDo=Integer.parseInt(request.queryParams("godineDo"));
				ArrayList<Car> automobili=new ArrayList<>();
				for(Car element:Data.readFromJson("cars.json")){
					if(element.getGodiste()>=godineOd && element.getGodiste()<godineDo){
						automobili.add(element);
					}
				}

		
			
			Car pom = new Car(0, sort,null , 0, 0);
			
			for(int i=0; i<automobili.size()-1; i++) {
				for(int j=i+1; j<automobili.size(); j++) {
					if(sort.equals("asc")) {
						if(automobili.get(i).getGodiste()>automobili.get(j).getGodiste()) {
							pom = automobili.get(i);
							automobili.set(i, automobili.get(j));
							automobili.set(j, pom);
						}
					}else {
						if(automobili.get(i).getGodiste()<automobili.get(j).getGodiste()) {
							pom = automobili.get(i);
							automobili.set(i, automobili.get(j));
							automobili.set(j, pom);
						}
					}
				}
			}
			
			Gson gson = new Gson();
			return gson.toJson(automobili);
		});
		
		get("/change",(request,response)->{
			ArrayList<Car> tmp=Data.readFromJson("cars.json");
			polja.put("cars", tmp);
			return new ModelAndView(polja,"change.hbs");
		},new HandlebarsTemplateEngine());
		
		
		post("/api/ispis/",(request,response)->{
			response.type("text/text");
			int id = Integer.valueOf(request.queryParams("id"));
			Car auto = new Car(id, null,null, id, id);
			for(Car element:Data.readFromJson("cars.json")) {
				if(element.getId() == id) {
					auto = element;
					break;
				}
			}
			Gson gson = new Gson();
			return gson.toJson(auto);
		});
		
		post("/forma/change",(request,response)->{
			int id = Integer.valueOf(request.queryParams("id"));
			int cena = Integer.valueOf(request.queryParams("cena"));
			ArrayList<Car> automobili = Data.readFromJson("cars.json");
			for(Car element:automobili) {
				if(element.getId() == id) {
					element.setCena(cena);
				}
			}
			Data.writeToJson(automobili, "cars.json");
			polja.put("cars",automobili);
			return new ModelAndView(polja, "index.hbs");
		},new HandlebarsTemplateEngine());

	}

}


