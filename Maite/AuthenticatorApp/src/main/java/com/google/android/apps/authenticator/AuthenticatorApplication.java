/*
 * Copyright 2011 Google Inc. All Rights Reserved.
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

package com.google.android.apps.authenticator;

//import com.google.android.apps.authenticator.database.Database;
//import com.google.android.apps.authenticator.entities.PinEntities;
import com.google.android.apps.authenticator.database.Database;
import com.google.android.apps.authenticator.entities.PinEntities;
import com.google.android.apps.authenticator.testability.DependencyInjector;
import com.google.android.apps.authenticator2.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
//import com.j256.ormlite.android.apptools.OpenHelperManager;
//import com.j256.ormlite.dao.Dao;

import android.app.Application;

import java.sql.SQLException;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Authenticator application which is one of the first things instantiated when our process starts.
 * At the moment the only reason for the existence of this class is to initialize
 * {@link DependencyInjector} with the application context so that the class can (later) instantiate
 * the various objects it owns.
 *
 * Also restrict UNIX file permissions on application's persistent data directory to owner
 * (this app's UID) only.
 *
 * @author klyubin@google.com (Alex Klyubin)
 */
public class AuthenticatorApplication extends Application {

  private static AuthenticatorApplication mInstance;

  private Database mDatabaseHelper = null;
  private Dao<PinEntities, Integer> mPinDao = null;

  private Retrofit restAdapter;

  @Override
  public void onCreate() {
    super.onCreate();

    mInstance=this;
  //Comentado por Sgranda  mDatabaseHelper = new Database(this);

    // Try to restrict data dir file permissions to owner (this app's UID) only. This mitigates the
    // security vulnerability where SQLite database transaction journals are world-readable.
    // NOTE: This also prevents all files in the data dir from being world-accessible, which is fine
    // because this application does not need world-accessible files.
    try {
      FileUtilities.restrictAccessToOwnerOnly(
          getApplicationContext().getApplicationInfo().dataDir);
    } catch (Throwable e) {
      // Ignore this exception and don't log anything to avoid attracting attention to this fix
    }
    //Setear variable retrofit
    restAdapter = retrofitOTP();

    // During test runs the injector may have been configured already. Thus we take care to avoid
    // overwriting any existing configuration here.
    DependencyInjector.configureForProductionIfNotConfigured(getApplicationContext());
  }

  /**
   * Return Application
   * @return
   */

  public static AuthenticatorApplication getApplication() {
    return mInstance;
  }

  public Dao<PinEntities, Integer> getmPinDao() throws SQLException {
    if (mPinDao == null)
      mPinDao = mDatabaseHelper.getDao(PinEntities.class);
    return mPinDao;
  }

  private Retrofit retrofitOTP(){
    Retrofit client=new Retrofit.Builder()
            .baseUrl(getString(R.string.server_localhost))
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    return client;
  }

  public Retrofit getRestAdapter(){
    return this.restAdapter;
  }

  @Override
  public void onTerminate() {
    DependencyInjector.close();
    if (mDatabaseHelper != null) {
      OpenHelperManager.releaseHelper();
      mDatabaseHelper = null;
    }
    super.onTerminate();
  }

}
