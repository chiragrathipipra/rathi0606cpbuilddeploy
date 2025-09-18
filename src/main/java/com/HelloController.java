
package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class HelloController {
	@GetMapping("/welcome")
	public String welcome() {
		return "Code is deployed in Code Deploy Again";
	}

	// Endpoint to show only the state name as a heading
	@GetMapping(value = "/state/{stateName}", produces = MediaType.TEXT_HTML_VALUE)
	public void getStateMap(@PathVariable String stateName, HttpServletResponse response) throws IOException {
		String html = "<!DOCTYPE html>" +
				"<html lang=\"en\">" +
				"<head>" +
				"    <meta charset=\"UTF-8\">" +
				"    <title>India State Name</title>" +
				"</head>" +
				"<body>" +
				"    <h2>" + stateName + "</h2>" +
				"</body>" +
				"</html>";
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		response.getWriter().write(html);
	}
}
