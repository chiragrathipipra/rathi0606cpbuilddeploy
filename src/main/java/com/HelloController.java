
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

	// Endpoint to serve the map page with dynamic state name
	@GetMapping(value = "/state/{stateName}", produces = MediaType.TEXT_HTML_VALUE)
	public void getStateMap(@PathVariable String stateName, HttpServletResponse response) throws IOException {
		String html = "<!DOCTYPE html>" +
				"<html lang=\"en\">" +
				"<head>" +
				"    <meta charset=\"UTF-8\">" +
				"    <title>India State Map</title>" +
				"    <style>" +
				"        #map { height: 600px; width: 100%; }" +
				"        .highlighted {" +
				"            fill: none !important;" +
				"            stroke: red !important;" +
				"            stroke-width: 3 !important;" +
				"        }" +
				"    </style>" +
				"    <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet/dist/leaflet.css\" />" +
				"</head>" +
				"<body>" +
				"    <h2>India State Map</h2>" +
				"    <div id=\"map\"></div>" +
				"    <script src=\"https://unpkg.com/leaflet/dist/leaflet.js\"></script>" +
				"    <script src=\"https://unpkg.com/axios/dist/axios.min.js\"></script>" +
				"    <script src=\"https://unpkg.com/leaflet-ajax/dist/leaflet.ajax.min.js\"></script>" +
				"    <script>" +
				"        // State name from backend" +
				"        const stateName = '" + stateName.toLowerCase() + "';" +
				"        var map = L.map('map').setView([22.9734, 78.6569], 5);" +
				"        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 18 }).addTo(map);" +
				"        fetch('https://raw.githubusercontent.com/geohacker/india/master/state/india_telengana.geojson')" +
				"            .then(response => response.json())" +
				"            .then(geojson => {" +
				"                L.geoJSON(geojson, {" +
				"                    style: function(feature) {" +
				"                        if (feature.properties && feature.properties.st_nm && feature.properties.st_nm.toLowerCase() === stateName) {" +
				"                            return {color: 'red', weight: 3};" +
				"                        }" +
				"                        return {color: '#3388ff', weight: 1};" +
				"                    }," +
				"                    onEachFeature: function(feature, layer) {" +
				"                        if (feature.properties && feature.properties.st_nm) {" +
				"                            layer.bindPopup(feature.properties.st_nm);" +
				"                        }" +
				"                        if (feature.properties && feature.properties.st_nm && feature.properties.st_nm.toLowerCase() === stateName) {" +
				"                            layer.openPopup();" +
				"                        }" +
				"                    }" +
				"                }).addTo(map);" +
				"            });" +
				"    </script>" +
				"</body>" +
				"</html>";
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		response.getWriter().write(html);
	}
}
