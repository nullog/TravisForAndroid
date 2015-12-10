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

package es.guillermoorellana.travisforandroid.api;

import java.util.List;

import es.guillermoorellana.travisforandroid.api.entity.BuildDetails;
import es.guillermoorellana.travisforandroid.api.entity.BuildHistory;
import es.guillermoorellana.travisforandroid.api.entity.Repo;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Single;

public interface TravisRestApi {
    @GET("repos/")
    Single<List<Repo>> repos();

    @GET("repos/{id}")
    Single<Repo> repo(@Path("id") int id);

    @GET("builds/{id}")
    Single<BuildDetails> build(@Path("id") int id);

    @GET("repos/{user}/{repo}/builds")
    Single<BuildHistory> buildsHistory(@Path("user") String user, @Path("repo") String repo);
}
