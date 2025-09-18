
package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class HelloController {
	@GetMapping("/welcome")
	public String welcome() {
		return "Code is deployed in Code Deploy Again";
	}

	// Endpoint to serve the map page
	@GetMapping(value = "/state/{stateName}", produces = MediaType.TEXT_HTML_VALUE)
	public void getStateMap(@PathVariable String stateName, HttpServletResponse response) throws IOException {
		// Serve the static HTML file
		ClassPathResource htmlFile = new ClassPathResource("static/india_map.html");
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		StreamUtils.copy(htmlFile.getInputStream(), response.getOutputStream());
	}
}
