package bbr.bbrapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BbrApiGatewayApplication {

    /**
     * Local host ports for each service
     */
    private static final String FRONTEND_HOST = "**.192.168.73.125";

    private static final String COMPANY_SERVICE = "http://localhost:7002";
    private static final String INVOICE_SERVICE = "http://localhost:7003";
    private static final String DOCUMENT_SERVICE = "http://localhost:7004";
    private static final String AUTH_SERVICE = "http://localhost:7005";


    public static void main(String[] args) {
        SpringApplication.run(BbrApiGatewayApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	/*@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/send-email")
						.uri(EMAIL_SERVICE))
				.build();
	}*/

}
