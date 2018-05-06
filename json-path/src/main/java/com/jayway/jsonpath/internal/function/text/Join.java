package com.jayway.jsonpath.internal.function.text;

import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.Utils;
import com.jayway.jsonpath.internal.function.Parameter;
import com.jayway.jsonpath.internal.function.PathFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the join of a JSONArray Object
 *
 * Created by fbrissi on 6/26/15.
 */
public class Join implements PathFunction {

    @Override
    public Object invoke(String currentPath, PathRef parent, Object model, EvaluationContext ctx, List<Parameter> parameters) {
        List valuesToJoin = new ArrayList();
        int count = 0;
        if(ctx.configuration().jsonProvider().isArray(model)){
            Parameter paramPath = Parameter.toPathJoin(parameters);
            Iterable<?> objects = ctx.configuration().jsonProvider().toIterable(model);
            for (Object obj : objects) {
                if (paramPath != null) {
                    valuesToJoin.add(Parameter.toConvertJoinValue(obj, count, ctx, paramPath));
                } else {
                    valuesToJoin.add(obj);
                }
                count++;
            }
        }
        if (count != 0) {
            return Utils.join(Parameter.toDelimiterValue(ctx, parameters), valuesToJoin);
        }
        throw new JsonPathException("Join function attempted to calculate value using empty array");
    }

}
