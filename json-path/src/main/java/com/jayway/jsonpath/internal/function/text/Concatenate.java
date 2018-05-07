package com.jayway.jsonpath.internal.function.text;

import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.function.AbstractPathFunction;
import com.jayway.jsonpath.internal.function.Parameter;

import java.util.List;

/**
 * String function concat - simple takes a list of arguments and/or an array and concatenates them together to form a
 * single string
 *
 */
public class Concatenate extends AbstractPathFunction {
    @Override
    public Object invoke(String currentPath, PathRef parent, Object model, EvaluationContext ctx, List<Parameter> parameters) {
        StringBuffer result = new StringBuffer();
        if(ctx.configuration().jsonProvider().isArray(model)){
            Iterable<?> objects = ctx.configuration().jsonProvider().toIterable(model);
            for (Object obj : objects) {
                if (obj instanceof String) {
                    result.append(obj.toString());
                }
            }
        }
        if (parameters != null) {
            for (String value : Parameter.toList(String.class, ctx, parameters)) {
                result.append(value);
            }
        }
        return result.toString();
    }
}
