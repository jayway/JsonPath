package com.jayway.jsonpath.internal.function.text;

import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.function.Parameter;
import com.jayway.jsonpath.internal.function.PathFunction;

import java.util.List;

/**
 * Provides the join of a JSONArray Object
 *
 * Created by fbrissi on 6/26/15.
 */
public class Join implements PathFunction {

    @Override
    public Object invoke(String currentPath, PathRef parent, Object model, EvaluationContext ctx, List<Parameter> parameters) {
        if(ctx.configuration().jsonProvider().isArray(model)){
            return ctx.configuration().jsonProvider().join(Parameter.toConvertJoinValue(model, ctx, Parameter.toParamJoinValue(ctx, parameters)),
                    Parameter.toDelimiterValue(ctx, parameters));
        } else if(ctx.configuration().jsonProvider().isMap(model)){
            return ctx.configuration().jsonProvider().join(Parameter.toConvertJoinValue(model, ctx, Parameter.toParamJoinValue(ctx, parameters)),
                    Parameter.toDelimiterValue(ctx, parameters));
        }
        return null;
    }

}
