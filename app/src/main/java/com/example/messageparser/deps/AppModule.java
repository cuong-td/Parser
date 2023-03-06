package com.example.messageparser.deps;

import com.example.messageparser.data.RemoteSource;
import com.example.messageparser.data.RemoteSrcImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public final class AppModule {
    @Provides
    @Singleton
    // This should be separate to NetworkModule in scaling up the project
    RemoteSource provideRemoteSource() {
        return new RemoteSrcImpl();
    }
}
