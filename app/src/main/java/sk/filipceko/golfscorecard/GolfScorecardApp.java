package sk.filipceko.golfscorecard;

import android.app.Application;
import android.content.ContentProvider;
import android.graphics.drawable.Icon;
import android.os.Build;
import io.realm.*;
import sk.filipceko.golfscorecard.data.Course;

public class GolfScorecardApp extends Application {

    RealmConfiguration realmConfig;
    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     *
     * <p>Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.</p>
     *
     * <p>If you override this method, be sure to call {@code super.onCreate()}.</p>
     *
     * <p class="note">Be aware that direct boot may also affect callback order on
     * Android {@link Build.VERSION_CODES#N} and later devices.
     * Until the user unlocks the device, only direct boot aware components are
     * allowed to run. You should consider that all direct boot unaware
     * components, including such {@link ContentProvider}, are
     * disabled until user unlock happens, especially when component callback
     * order matters.</p>
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        buildRealmConfig();
        Realm.setDefaultConfiguration(realmConfig);
    }

    private void buildRealmConfig(){
        realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(2)
                .migration(new RealmSchemeMigration())
                .build();
    }

    private static class RealmSchemeMigration implements RealmMigration {
        /**
         * This method will be called if a migration is needed. The entire method is wrapped in a
         * write transaction so it is possible to create, update or delete any existing objects
         * without wrapping it in your own transaction.
         *
         * @param realm      the Realm schema on which to perform the migration.
         * @param oldVersion the schema version of the Realm at the start of the migration.
         * @param newVersion the schema version of the Realm after executing the migration.
         */
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();
            if (oldVersion == 0) {
                schema.get("Course").renameField("areal", "resort");
            }
        }
    }
}
