package com.jayway.jsonpath;

import static org.junit.Assert.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.jayway.jsonpath.internal.Path;
import com.jayway.jsonpath.internal.PathCompiler;
import com.jayway.jsonpath.internal.token.TokenStack;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

public class JacksonTest_Split extends BaseTest implements EvaluationCallback {

    private static final Logger log = LoggerFactory.getLogger(JacksonTest_Split.class);
    private List<Object> results = new ArrayList<Object>();

    @Test
    public void json_Test() throws Exception {
        jsonSplit_Test(JACKSON_CONFIGURATION);
        results.clear();
        jsonSplit_Test(JSON_SMART_CONFIGURATION);
        results.clear();
    }

    private void jsonSplit_Test(Configuration jsonProviderCfg)
        throws JsonParseException, IOException, Exception {

        try {
            String res = "json_opsview1.json";
            InputStream stream = getClass().getClassLoader().getResourceAsStream(res);
            Path path = PathCompiler.compile("$.list[*]");

            TokenStack stack = new TokenStack(jsonProviderCfg);

            JsonFactory factory = new JsonFactory();
            stack.registerPath(path);
            stack.read(factory.createParser(stream), this);
        } finally {
            log.debug("results: " + results.size());
            //assertTrue(results.size() == 96);
        }
    }

    @Override
    public void resultFound(Path path) throws Exception {
        //log.debug(source + ":" + String.valueOf(obj));
        //results.add(obj);
    }

    @Override
    public void resultFoundExit(Path path) throws Exception {
        //log.debug(source + ":" + String.valueOf(obj));
    }
}
