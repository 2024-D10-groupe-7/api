package fr.scrumtogether.scrumtogetherapi.config;

import brevo.ApiClient;
import brevo.auth.ApiKeyAuth;
import brevoApi.ContactsApi;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "brevo")
public class BrevoConfig {
    @Getter(AccessLevel.NONE)
    private String apiKey;
    @Getter(AccessLevel.NONE)
    private String apiKeyFile;

    private long scrumTogetherFolderId;
    private long scrumTogetherUsersListId;
    private long scrumTogetherAdminsListId;

    @Bean
    public ApiClient apiClient(@Qualifier("getApiKey") String key) {
        ApiClient client = brevo.Configuration.getDefaultApiClient();
        ApiKeyAuth auth = (ApiKeyAuth) client.getAuthentication("api-key");
        auth.setApiKey(key);
        return client;
    }

    @Bean
    protected String getApiKey() throws IOException {
        if (apiKey == null || apiKey.isBlank()) {
            apiKey = Files.readString(Path.of(apiKeyFile)).trim();
        }
        System.out.println(apiKey);
        return apiKey;
    }

    @Bean
    public ContactsApi brevoContactsApi(ApiClient apiClient) {
        return new ContactsApi(apiClient);
    }
}
