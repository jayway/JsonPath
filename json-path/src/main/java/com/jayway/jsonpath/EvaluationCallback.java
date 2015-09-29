/*
 * Copyright 2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jayway.jsonpath;

import com.jayway.jsonpath.internal.Path;

/**
 * A listener that can be registered on a {@link com.jayway.jsonpath.internal.token.TokenStack} that is notified when a
 * result is found for a specific registered path
 */
public interface EvaluationCallback {

    /**
     * Callback invoked when result is found
     * @param path -- the specific path that was triggered
     */
    public void resultFound(Path path) throws Exception;

    /**
     * Callback invoked when the parser leaves the region in which the match
     * was found
     * @param path -- the specific path that was untriggered
     */
    public void resultFoundExit(Path path) throws Exception;
}
