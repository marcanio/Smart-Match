package com.example.login;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.testing.http.MockHttpTransport;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTests {
    private static String URL_ = "https://postman-echo.com/post";
    @Mock
    private HttpResponse response;

    @Test
    public void testRequestType() throws IOException {
        // Given
        String jsonMimeType = "text/plain";
        HttpResponse response = Mockito.mock(HttpResponse.class);
        HttpEntity ent = Mockito.mock(HttpEntity.class);
        JsonWebSignature.Header cont = Mockito.mock(JsonWebSignature.Header.class);
        String mimeType = "text/plain";
        when(response.getEntity()).thenReturn(ent);
        //        when(ContentType.getOrDefault(ent).getMimeType()).thenReturn(String.valueOf(ContentType.create(mimeType)));
        assertEquals(ContentType.create(mimeType).getMimeType(), jsonMimeType);
    }

    @Test
    public void testRequestData() throws IOException {
        HttpPost httpPost = Mockito.mock(HttpPost.class);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //params.add(new BasicNameValuePair("username", "John"));
        //params.add(new BasicNameValuePair("password", "pass"));
        //httpPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = Mockito.mock(CloseableHttpResponse.class);
        StatusLine liine = Mockito.mock(StatusLine.class);
        when(response.getStatusLine()).thenReturn(liine);
        when(response.getStatusLine().getStatusCode()).thenReturn(200);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        ;
    }

    @Test
    public void testApiData() throws IOException {
        HttpTransport transport = new MockHttpTransport();
        HttpRequest request = transport.createRequestFactory().buildGetRequest(new GenericUrl("https://e88bf2da-6812-4702-8725-be192a447d6d.mock.pstmn.io"));
        com.google.api.client.http.HttpResponse response = request.execute();
        System.out.println(response);
    }

    @Test
    public void should_return_true_if_the_status_api_works_properly() throws ClientProtocolException, IOException {
        //given:
        HttpClient httpClient = mock(HttpClient.class);
        HttpGet httpGet = mock(HttpGet.class);
        HttpResponse httpResponse = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);
        //and:
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);
        //and:
     /*   StatusApiClient client = new StatusApiClient(httpClient, httpGet);
        //when:
        boolean status = client.getStatus();
        //then:
        Assert.assertTrue(status);*/
    }

    @Test
    public void should_return_false_if_status_api_do_not_respond() throws ClientProtocolException, IOException {
        //given:
        HttpClient httpClient = mock(HttpClient.class);
        HttpGet httpGet = mock(HttpGet.class);
        HttpResponse httpResponse = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);
        when(httpClient.execute(httpGet)).thenThrow(HttpHostConnectException.class);

      /*  StatusApiClient client = new StatusApiClient(httpClient, httpGet);
        //when:
        boolean status = client.getStatus();
        //then:
        Assert.assertFalse(status);*/
    }
}
