package com.jayway.jsonpath.internal.function.text;

import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.function.Parameter;
import com.jayway.jsonpath.internal.function.PathFunction;
import com.jayway.jsonpath.internal.path.EvaluationContextImpl;
import com.jayway.jsonpath.internal.path.FunctionPathToken;
import com.jayway.jsonpath.internal.path.PathToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the join of a JSONArray Object
 *
 * Created by fbrissi on 6/26/15.
 */
public class Join implements PathFunction {

    @Override
    public Object invoke(PathToken next, String currentPath, PathRef parent, Object model, EvaluationContext ctx, List<Parameter> parameters) {
        if(ctx.configuration().jsonProvider().isArray(model)){
            if (next instanceof FunctionPathToken) {
                List array = new ArrayList();
                for (Object o : ctx.configuration().jsonProvider().toIterable(model)) {
                    next.evaluate(currentPath, parent, o, (EvaluationContextImpl) ctx);
                    array.add(ctx.getValue());
                }
                return ctx.configuration().jsonProvider().join(Parameter.toConvertJoinValue(array, ctx, Parameter.toParamJoinValue(parameters)),
                        Parameter.toDelimiterValue(ctx, parameters));
            } else {
                return ctx.configuration().jsonProvider().join(Parameter.toConvertJoinValue(model, ctx, Parameter.toParamJoinValue(parameters)),
                        Parameter.toDelimiterValue(ctx, parameters));
            }
        }
        return null;
    }

}
