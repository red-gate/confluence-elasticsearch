package com.redgate.confluenceSearch.elasticSearch;

import com.sun.jersey.api.client.Client;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.stream.Collectors;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class RestSearchService {

    private String m_ElasticSearchIndexUrl = "http://localhost:9200/search-local-confluence/_search/";

    @GET
    @Path("search")
    public Response getSearchResults(@QueryParam("query") String query) throws IOException {

        String jsonQuery = getResourceFileAsString("query-template.json").replace("##query##", query);

        Result results = Client.create()
                .resource(m_ElasticSearchIndexUrl)
                .type("application/json")
                .post(Result.class, jsonQuery);

        return Response.ok(results.hits.hits).build();
    }

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    String getResourceFileAsString(String fileName) throws IOException {
        //ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}

@XmlRootElement()
class Result {
    public ResultInner1 hits;
}

class ResultInner1 {
    public int total;
    public float max_score;
    public ResultInner2[] hits;
}

class ResultInner2 {
    public float _score;
    public ResultItem _source;
    public ResultHighlight highlight;
}

class ResultItem {
    public Date lastModified;
    public String title;
    public String id;
    public String content;
    public String[] labels;
    public Date created;
    public String url;
    public String spaceKey;
}

class ResultHighlight {
    public String content;
    public String title;
    public String url;
}