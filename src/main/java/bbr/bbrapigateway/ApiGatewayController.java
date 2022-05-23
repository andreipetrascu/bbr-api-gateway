package bbr.bbrapigateway;


import bbr.bbrapigateway.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class ApiGatewayController {

    @Autowired
    private RestTemplate restTemplate;

   // private static final String EMAIL_SERVICE = "http://localhost:7001";
    private static final String EMAIL_SERVICE = "http://bbr-email-service.azurewebsites.net";
    private static final String EMAIL_SERVICE_SEND_EMAIL = EMAIL_SERVICE + "/send-email";
    private static final String EMAIL_SERVICE_SEND_SECURITY_CODE = EMAIL_SERVICE + "/send-security-code";
    private static final String EMAIL_SERVICE_VERIFY_SECURITY_CODE = EMAIL_SERVICE + "/verify-security-code";
    private static final String EMAIL_SERVICE_HELLO = EMAIL_SERVICE + "/hello";

    //private static final String COMPANY_SERVICE = "http://localhost:7002";
    private static final String COMPANY_SERVICE = "https://bbr-company-service.azurewebsites.net";
    private static final String COMPANY_SERVICE_COMPANIES = COMPANY_SERVICE + "/companies/";

    //private static final String INVOICE_SERVICE = "http://localhost:7003";
    private static final String INVOICE_SERVICE = "https://bbr-invoice-service.azurewebsites.net";
    private static final String INVOICE_SERVICE_INVOICES = INVOICE_SERVICE + "/invoices/";

    //private static final String DOCUMENT_SERVICE = "http://localhost:7004";
    private static final String DOCUMENT_SERVICE = "https://bbr-document-service.herokuapp.com";


    private static final Logger logger = LoggerFactory.getLogger(ApiGatewayController.class);

    @GetMapping(path = "/hello")
    public Response hello() {
        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                EMAIL_SERVICE_HELLO,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /hello]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController /hello]: success");
        return re.getBody();
    }

    /* DOCUMENT SERVICE */

    @PostMapping(path = "/get-pdf")
    public ResponseEntity<byte[]> getPDF(@RequestBody Object body) {
        HttpEntity requestEntity = new HttpEntity(body, null);
        ResponseEntity<byte[]> re = restTemplate.exchange(
                DOCUMENT_SERVICE + "/get-pdf",
                HttpMethod.POST,
                requestEntity,
                byte[].class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /getPDF]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController]: /getPDF");
        return re;
    }


    /* INVOICE SERVICE */

    @GetMapping(path = "/invoices")
    public Response findAllInvoices() {
        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                INVOICE_SERVICE_INVOICES,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /findAllInvoices]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController /findAllInvoices]: success");
        return re.getBody();
    }

    @PostMapping(path = "/invoices")
    public Response addInvoice(@RequestBody Object body) {
        HttpEntity requestEntity = new HttpEntity(body, null);
        ResponseEntity<Response> re = restTemplate.exchange(
                INVOICE_SERVICE_INVOICES,
                HttpMethod.POST,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /addInvoice]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController]: /addInvoice");
        return re.getBody();
    }

    @PutMapping(path = "/invoices")
    public Response editInvoice(@RequestBody Object body) {
        HttpEntity requestEntity = new HttpEntity(body, null);
        ResponseEntity<Response> re = restTemplate.exchange(
                INVOICE_SERVICE_INVOICES,
                HttpMethod.PUT,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /editInvoice]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController]: /editInvoice");
        return re.getBody();
    }

    @PostMapping(path = "/generate-pdf/{invoiceId}")
    public Response generatePdf(@PathVariable Integer invoiceId) {
        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                INVOICE_SERVICE + "/generate-pdf/" + invoiceId,
                HttpMethod.POST,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /generatePdf]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController]: /generatePdf");
        return re.getBody();
    }

    @GetMapping(path = "/codes")
    public Response findAllCodes() {
        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                INVOICE_SERVICE + "/codes",
                HttpMethod.GET,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /findAllCodes]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController /findAllCodes]: success");
        return re.getBody();
    }

    @PutMapping(path = "/codes")
    public Response updateCodes(@RequestBody Object body) {
        HttpEntity requestEntity = new HttpEntity(body, null);
        ResponseEntity<Response> re = restTemplate.exchange(
                INVOICE_SERVICE + "/codes",
                HttpMethod.PUT,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /updateCodes]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController]: /updateCodes");
        return re.getBody();
    }

    @GetMapping(path = "/items/{invoiceId}")
    public Response findAllItemsByInvoiceId(@PathVariable Integer invoiceId) {
        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                INVOICE_SERVICE + "/items/" + invoiceId,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /findAllItemsByInvoiceId]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController /findAllItemsByInvoiceId]: success");
        return re.getBody();
    }

    @GetMapping(path = "/storno/{invoiceId}")
    public Response storno(@PathVariable Integer invoiceId) {
        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                INVOICE_SERVICE + "/storno/" + invoiceId,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /storno]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController /storno]: success");
        return re.getBody();
    }


    /* COMPANY SERVICE */

    @GetMapping(path = "/companies")
    public Response findAllCompanies() {
        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                COMPANY_SERVICE_COMPANIES,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /findAllCompanies]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController /findAllCompanies]: success");
        return re.getBody();
    }

    @GetMapping(path = "/companies/{companyId}")
    public Response findCompnayById(@PathVariable Integer companyId) {
        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                COMPANY_SERVICE_COMPANIES + companyId,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /findCompnayById]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController /findCompnayById]: success");
        return re.getBody();
    }

    @PostMapping(path = "/companies")
    public Response addCompany(@RequestBody Object body) {
        HttpEntity requestEntity = new HttpEntity(body, null);
        ResponseEntity<Response> re = restTemplate.exchange(
                COMPANY_SERVICE_COMPANIES,
                HttpMethod.POST,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /addCompany]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController]: /addCompany");
        return re.getBody();
    }

    @PutMapping(path = "/companies")
    public Response updateCompany(@RequestBody Object body) {
        HttpEntity requestEntity = new HttpEntity(body, null);
        ResponseEntity<Response> re = restTemplate.exchange(
                COMPANY_SERVICE_COMPANIES,
                HttpMethod.PUT,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /updateCompany]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController]: /updateCompany");
        return re.getBody();
    }

    @DeleteMapping(path = "/companies/{companyId}")
    public Response deleteCompany(@PathVariable Integer companyId) {
        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                COMPANY_SERVICE_COMPANIES + companyId,
                HttpMethod.DELETE,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[ApiGatewayController /deleteCompany]: Get routes error, response code: {}", re.getStatusCodeValue());
        }
        logger.info("[ApiGatewayController]: /deleteCompany");
        return re.getBody();
    }


    /* EMAIL SERVICE */

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

    @PostMapping(path = "/send-security-code")
    public Response sendSecurityCode(@RequestBody Object body) {
        HttpEntity requestEntity = new HttpEntity(body, null);
        ResponseEntity<Response> re = restTemplate.exchange(
                EMAIL_SERVICE_SEND_SECURITY_CODE,
                HttpMethod.POST,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[sendSecurityCode] error: " + re.getStatusCodeValue());
        }
        logger.info("[sendSecurityCode]: success");
        return re.getBody();
    }

    @PostMapping(path = "/verify-security-code")
    public Response verifySecurityCode(@RequestBody Object body) {
        HttpEntity requestEntity = new HttpEntity(body, null);
        ResponseEntity<Response> re = restTemplate.exchange(
                EMAIL_SERVICE_VERIFY_SECURITY_CODE,
                HttpMethod.POST,
                requestEntity,
                Response.class);
        if (re.getStatusCode() != HttpStatus.OK) {
            logger.error("[verifySecurityCode] error: " + re.getStatusCodeValue());
        }
        logger.info("[verifySecurityCode]: success");
        return re.getBody();
    }


}
