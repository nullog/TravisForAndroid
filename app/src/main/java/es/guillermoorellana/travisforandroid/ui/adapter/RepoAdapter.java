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

package es.guillermoorellana.travisforandroid.ui.adapter;

import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.raizlabs.android.dbflow.config.FlowManager;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.guillermoorellana.travisforandroid.R;
import es.guillermoorellana.travisforandroid.model.Repo;

public class RepoAdapter extends CursorRecyclerAdapter<RepoAdapter.RepoViewHolder> {

    public RepoAdapter(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void onBindViewHolderCursor(RepoViewHolder holder, Cursor cursor) {
        Repo repo = FlowManager.getModelAdapter(Repo.class).loadFromCursor(cursor);
        holder.bind(repo);
    }

    @Override
    protected RepoViewHolder createViewHolder(View view) {
        return new RepoViewHolder(view);
    }

    protected int getItemLayout() {
        return R.layout.item_repo;
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {
        private static final PeriodFormatter FORMATTER = PeriodFormat.wordBased(Locale.getDefault());

        @Bind(R.id.buildNumber) TextView buildNumber;
        @Bind(R.id.duration) TextView duration;
        @Bind(R.id.finishedAgo) TextView finishedAgo;
        @Bind(R.id.repo) TextView repoName;
        @Bind(R.id.status) View status;

        public RepoViewHolder(View repoView) {
            super(repoView);
            ButterKnife.bind(this, repoView);
        }

        public void bind(@NonNull Repo repo) {
            buildNumber.setText(repo.getLastBuildNumber());
            duration.setText(durationText(repo.getLastBuildDuration()));
            finishedAgo.setText(finishedText(repo));
            repoName.setText(repo.getSlug());
            status.setBackgroundColor(colorForRepo(repo));
        }

        @NonNull
        private static String finishedText(@NonNull Repo repo) {
            String verb;
            if (repo.isActive()) {
                verb = "Started: %s ago";
            } else {
                verb = "Finished: %s ago";
            }
            Period period = new Period(repo.getLastBuildStartedAt(), repo.getLastBuildFinishedAt()).withMillis(0);
            if (period.getMinutes() == 0) {
                return String.format(verb, "less than a minute");
            }
            return String.format(verb, FORMATTER.print(period));
        }

        @ColorInt
        private static int colorForRepo(@NonNull Repo repo) {
            if (repo.getLastBuildState().equals("passed")) {
                return Color.GREEN;
            }
            if (repo.getLastBuildNumber() == null) {
                return Color.GRAY;
            }
            if (repo.isActive()) {
                return Color.YELLOW;
            }
            return Color.RED;
        }

        @NonNull
        private static String durationText(long lastBuildDuration) {
            if (lastBuildDuration == 0) {
                return "Currently running";
            }
            return String.valueOf(lastBuildDuration);
        }
    }
}
