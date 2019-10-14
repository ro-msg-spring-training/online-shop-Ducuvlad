package ro.msg.learning.shop.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.dto.Options;
import ro.msg.learning.shop.dto.RouteMatrixRequestDTO;
import ro.msg.learning.shop.exception.RouteMatrixException;

import java.util.List;

public class LocationRestTemplate {
    public String getDistanceData(List<String> locations) {
        /*
         Other details do not interest me , just the distance until the first location sent
         */
        String matrixKey = "c6GchASGWY1KEcjQ9WE9u8pA90MYyGle";
        String matrixUri = "http://www.mapquestapi.com/directions/v2/routematrix?key=";
        String routeMatrixUrl = matrixUri + matrixKey;
        RestTemplate restTemplate = new RestTemplate();
        Options options = new Options(false, true);
        HttpEntity<RouteMatrixRequestDTO> request = new HttpEntity<>(new RouteMatrixRequestDTO(locations, options));
        ResponseEntity<String> response = restTemplate.exchange(routeMatrixUrl, HttpMethod.POST, request, String.class);
        String body = response.getBody();
        if (body == null)
            throw new RouteMatrixException("Route Matrix Error");
        return body.substring(body.indexOf("[") + 1, body.indexOf("]"));
    }
}
