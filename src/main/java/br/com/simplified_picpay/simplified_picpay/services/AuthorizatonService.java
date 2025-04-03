package br.com.simplified_picpay.simplified_picpay.services;

import br.com.simplified_picpay.simplified_picpay.domain.authorization.Authorization;
import br.com.simplified_picpay.simplified_picpay.domain.transaction.Transaction;
import br.com.simplified_picpay.simplified_picpay.exceptions.UnauthorizedTransactionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
public class AuthorizatonService {

    private RestClient restClient;

    public AuthorizatonService(@Value("${picpay.api.authorization.url}") String AUTHORIZATION_URL, RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl(AUTHORIZATION_URL)
                .build();
    }

    public void authorize(Transaction transaction) {

       this.restClient.get()
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        (((request, response) -> {
                            throw new UnauthorizedTransactionException("Unauthorized transaction", HttpStatus.UNAUTHORIZED);
                        }))
                )
                .toEntity(Authorization.class);
    }
}
