package com.jayway.jsonpath.internal.function.numeric;

import com.jayway.jsonpath.Function;
import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.PathRef;
import net.minidev.json.JSONArray;

import java.util.Iterator;

/**
 * Defines the pattern for processing numerical values via an abstract implementation that iterates over the collection
 * of JSONArray entities and verifies that each is a numerical value and then passes that along the abstract methods
 *
 *
 * Created by mattg on 6/26/15.
 */
public abstract class AbstractAggregation implements Function {

    /**
     * Defines the next value in the array to the mathmatical function
     *
     * @param value
     *      The numerical value to process next
     */
    protected abstract void next(Number value);

    /**
     * Obtains the value generated via the series of next value calls
     *
     * @return
     *      A numerical answer based on the input value provided
     */
    protected abstract Number getValue();

    @Override
    public Object invoke(String currentPath, PathRef parent, Object model, EvaluationContext ctx) {
        if (model instanceof JSONArray) {
            JSONArray array = (JSONArray)model;
            Double num = 0d;
            Iterator<?> it = array.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof Number) {
                    Number value = (Number) next;
                    next(value);
                }
            }
            return getValue();
        }
        return null;
    }
}