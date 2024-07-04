package br.com.samueljunnior.client.integrations;

import br.com.samueljunnior.client.integrations.vo.StatusEnum;
import br.com.samueljunnior.core.exceptions.IntegrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public record IntegrationsService(IntegrationsClient integrationClient) {

    public boolean authorize() {
        try {
            log.info("Init authorize integration service.");
            final var responser = integrationClient.authorization();

            if (!responser.getStatusCode().is2xxSuccessful()) {
                return false;
            }

            final var body = Objects.requireNonNull(responser.getBody());
            if (body.status().equals(StatusEnum.fail)) {
                return false;
            }

            return body.data().authorization();
        } catch (Exception e) {
            log.error("Authorization error.", e);
            throw new IntegrationException("Authorization Error.");
        }
    }

    public void notification() {
        try {
            log.info("Init notification integration service.");
            integrationClient.notification();
        } catch (Exception e) {
            log.error("Notification error", e);
            throw new IntegrationException("Notification Error");
        }
    }
}
