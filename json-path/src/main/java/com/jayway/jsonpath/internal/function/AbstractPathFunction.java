package com.jayway.jsonpath.internal.function;

import com.jayway.jsonpath.internal.PathRef;
import com.jayway.jsonpath.internal.function.latebinding.JsonLateBindingValue;
import com.jayway.jsonpath.internal.function.latebinding.PathLateBindingValue;
import com.jayway.jsonpath.internal.function.latebinding.StringLateBindingValue;
import com.jayway.jsonpath.internal.path.EvaluationContextImpl;

import java.util.List;

public abstract class AbstractPathFunction implements PathFunction {

    @Override
    public void evaluateParameters(String currentPath, PathRef parent, Object model, List<Parameter> functionParams, EvaluationContextImpl ctx) {
        if (null != functionParams) {
            for (Parameter param : functionParams) {
                if (!param.hasEvaluated()) {
                    switch (param.getType()) {
                        case PATH:
                            param.setLateBinding(new PathLateBindingValue(param.getPath(), "$".equals(currentPath) ? model : ctx.rootDocument(), ctx.configuration()));
                            param.setEvaluated(!"$".equals(currentPath));
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
