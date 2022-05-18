package bbr.bbrapigateway;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ApiGatewayController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String EMAIL_SERVICE = "http://localhost:7001";
    private static final String EMAIL_SERVICE_SEND_EMAIL = EMAIL_SERVICE + "/send-email";

    private static final Logger logger = LoggerFactory.getLogger(ApiGatewayController.class);


    @PostMapping(path = "/send-email")
    public Response sendEmail(@RequestBody Object body) {
        HttpEntity requestEntity = new HttpEntity(body, null);
        ResponseEntity<Response> re = restTemplate.exchange(
                EMAIL_SERVICE_SEND_EMAIL,
                HttpMethod.POST,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController]: /send-email");
        return re.getBody();
    }

}
