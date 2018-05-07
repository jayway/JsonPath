package com.jayway.jsonpath.internal.function.text;

import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.internal.EvaluationContext;
import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.Utils;
import com.jayway.jsonpath.internal.function.AbstractPathFunction;
import com.jayway.jsonpath.internal.function.Parameter;
import com.jayway.jsonpath.internal.function.latebinding.JsonLateBindingValue;
import com.jayway.jsonpath.internal.function.latebinding.PathArrayLateBindingValue;
import com.jayway.jsonpath.internal.function.latebinding.StringLateBindingValue;
import com.jayway.jsonpath.internal.path.EvaluationContextImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the join of a JSONArray Object
 *
 * Created by fbrissi on 6/26/15.
 */
public class Join extends AbstractPathFunction {

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

    @Override
    public void evaluateParameters(String currentPath, PathRef parent, Object model, List<Parameter> functionParams, EvaluationContextImpl ctx) {
        if (null != functionParams) {
            for (Parameter param : functionParams) {
                if (!param.hasEvaluated()) {
                    switch (param.getType()) {
                        case PATH:
                            param.setLateBinding(new PathArrayLateBindingValue(param.getPath(), model, ctx.configuration()));
                            param.setEvaluated(true);
                            break;
                        case JSON:
                            param.setLateBinding(new JsonLateBindingValue(ctx.configuration().jsonProvider(), param));
                            param.setEvaluated(true);
                            break;
                        case STRING:
                            param.setLateBinding(new StringLateBindingValue(param));
                            param.setEvaluated(true);
                            break;
                    }
                }
            }
        }
    }
}
