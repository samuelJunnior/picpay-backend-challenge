package br.com.samueljunnior.client.integrations;

import br.com.samueljunnior.client.integrations.vo.ResponserVO;
import br.com.samueljunnior.core.feign.FeignDefaultConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "IntegrationsClient", url = "${integrations.base-url}", contextId = "integrations", configuration = FeignDefaultConfiguration.class)
interface IntegrationsClient {

    @GetMapping("/authorize")
    ResponseEntity<ResponserVO> authorization();

    @PostMapping("/notify")
    ResponseEntity<ResponserVO> notification();
}
