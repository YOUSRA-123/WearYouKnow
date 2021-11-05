package net.javaguides.springboot.web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.javaguides.springboot.model.Patient;
import net.javaguides.springboot.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@Controller
public class DemoController {
	@Autowired
	FirebaseInitializer db;

	  @GetMapping("/getAllPatients")
	  public ModelAndView passParametersWithModelAndView() {
	      ModelAndView modelAndView = new ModelAndView("dashbord");
	      
	      List<Patient> patList = new ArrayList<Patient>(); CollectionReference
		  patient= db.getFirebase().collection("users"); ApiFuture<QuerySnapshot>
		  querySnapshot= patient.get(); try {
			for(DocumentSnapshot
			  doc:querySnapshot.get().getDocuments()) { 
//				System.out.println(doc.getString("name"));
				Patient pat = doc.toObject(Patient.class);
			  
			  patList.add(pat);
			  
			  }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	      modelAndView.addObject("name", patList);

	      return modelAndView;
	      
	  }
	  

	  
	@PostMapping("/savePatient")
	public String savePatient(@RequestBody Patient patient) {
		CollectionReference employeeCR = db.getFirebase().collection("users");
		employeeCR.document(String.valueOf(patient.getName())).set(patient);
		return patient.getName();
	}

	
}

