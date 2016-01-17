/*
 *   Copyright 2016 Guillermo Orellana Ruiz
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package es.guillermoorellana.travisforandroid.data;

import es.guillermoorellana.travisforandroid.api.entity.ApiBuild;
import es.guillermoorellana.travisforandroid.api.entity.ApiRepo;
import es.guillermoorellana.travisforandroid.model.Build;
import es.guillermoorellana.travisforandroid.model.BuildBuilder;
import es.guillermoorellana.travisforandroid.model.Repo;
import es.guillermoorellana.travisforandroid.model.RepoBuilder;

public final class ApiAdapter {
    private ApiAdapter() {
        // util class
    }

    public static Repo fromApi(ApiRepo apiRepo) {
        return new RepoBuilder()
                .setId(apiRepo.getId())
                .setSlug(apiRepo.getSlug())
                .setActive(apiRepo.isActive())
                .setDescription(apiRepo.getDescription())
                .setLastBuildId(apiRepo.getLastBuildId())
                .setLastBuildState(apiRepo.getLastBuildState())
                .setLastBuildDuration(apiRepo.getLastBuildDuration())
                .setLastBuildLanguage(apiRepo.getLastBuildLanguage())
                .setLastBuildNumber(apiRepo.getLastBuildNumber())
                .setLastBuildStartedAt(apiRepo.getLastBuildStartedAt().getMillis())
                .setLastBuildFinishedAt(apiRepo.getLastBuildFinishedAt().getMillis())
                .setGithubLanguage(apiRepo.getGithubLanguage())
                .createRepo();
    }

    public static Build fromApi(ApiBuild apiBuild) {
        return new BuildBuilder().setCommitId(apiBuild.getCommitId())
                .setDuration(apiBuild.getDuration())
                .setFinishedAt(apiBuild.getFinishedAt().getMillis())
                .setId(apiBuild.getId())
                .setNumber(apiBuild.getNumber())
                .setPullRequest(apiBuild.isPullRequest())
                .setPullRequestNumber(apiBuild.getPullRequestNumber())
                .setPullRequestTitle(apiBuild.getPullRequestTitle())
                .setRepositoryId(apiBuild.getRepositoryId())
                .setStartedAt(apiBuild.getStartedAt().getMillis())
                .setState(apiBuild.getState())
                .createBuild();
    }
}
