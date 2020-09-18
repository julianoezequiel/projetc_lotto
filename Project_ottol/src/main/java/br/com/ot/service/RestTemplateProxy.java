package br.com.ot.service;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateProxy {

	@Value("${proxy.user}")
	private String username;
	@Value("${proxy.password}")
	private String password;
	@Value("${proxy.host}")
	private String proxyUrl;
	@Value("${proxy.port}")
	private Integer port;
	@Value("${proxy.enabled}")
	private Boolean enabled;

	public RestTemplate restTemplate() {

		if (enabled == true) {
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

			credentialsProvider.setCredentials(AuthScope.ANY,
					new NTCredentials(this.username, this.password, null, null));
			HttpClientBuilder clientBuilder = HttpClientBuilder.create();

			clientBuilder.useSystemProperties();
			clientBuilder.setProxy(new HttpHost(this.proxyUrl, this.port));
			clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
			clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());

			CloseableHttpClient client = clientBuilder.build();

			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setHttpClient(client);
			factory.setConnectTimeout(3000);
			factory.setReadTimeout(5000);
			factory.setConnectionRequestTimeout(3000);

			return new RestTemplate(factory);
		} else {
			return new RestTemplate();
		}
	}
}
