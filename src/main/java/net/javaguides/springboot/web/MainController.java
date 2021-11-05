package net.javaguides.springboot.web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.QuerySnapshot;

import net.javaguides.springboot.model.Patient;
import net.javaguides.springboot.service.FirebaseInitializer;

@Controller
public class MainController {
	@Autowired
	FirebaseInitializer db;
	
	@GetMapping("/")
	public String home() {
		return "acceuil";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	
	@GetMapping("/index") 
	  public String index() {
		return "index"; 
		}
	
	
	
	  @PostMapping("/Test") 
	  public ModelAndView Pushpin(@RequestParam("id") String customer) {
	      ModelAndView modelAndView = new ModelAndView("Test");

	      List<GeoPoint> location = new ArrayList<GeoPoint>();
	      GeoPoint lastNum = new GeoPoint(0, 0);
	      List<Patient> patList = new ArrayList<Patient>(); CollectionReference
		  patient= db.getFirebase().collection("users"); ApiFuture<QuerySnapshot>
		  querySnapshot= patient.get(); try {
			for(DocumentSnapshot
			  doc:querySnapshot.get().getDocuments()) {
				Patient pat = doc.toObject(Patient.class);

				if (customer.contentEquals(pat.getName())) {
					location = pat.getPositions();
					lastNum = location.get(location.size() - 1);
				}
			  
			  }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  double latitude = lastNum.getLatitude();
		  double longitude = lastNum.getLongitude();

	      modelAndView.addObject("latitude", latitude);
	      modelAndView.addObject("longitude", longitude);

	      return modelAndView;
		}
	 
	 
	
}
