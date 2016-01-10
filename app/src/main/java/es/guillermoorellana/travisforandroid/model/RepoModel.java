/*
 * Copyright 2015 Guillermo Orellana Ruiz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.guillermoorellana.travisforandroid.model;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import es.guillermoorellana.travisforandroid.api.TravisRestApi;
import es.guillermoorellana.travisforandroid.api.entity.ApiRepo;
import rx.Single;

public class RepoModel {
    @NonNull
    private final TravisRestApi mTravisRestApi;

    @Inject
    public RepoModel(@NonNull TravisRestApi travisRestApi) {
        mTravisRestApi = travisRestApi;
    }

    public Single<List<ApiRepo>> getRepos() {
        return mTravisRestApi.repos();
    }
}
