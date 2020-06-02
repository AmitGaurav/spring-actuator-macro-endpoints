package com.macro.actuatorservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BVAController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/hello-world")
	@ResponseBody
	public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping(method = RequestMethod.POST, value="/raiseEvent/sumOfNumbers")
	@ResponseBody
	  public Parameters raiseEvent(@RequestBody Parameters parameters) {
		  System.out.println("In raiseEvent");
		  return eventService.raiseEvent(parameters);
		}

}
