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
public final class NetworkModule {
    @Provides
    @Singleton
    RemoteSource provideRemoteSource() {
        return new RemoteSrcImpl();
    }
}
