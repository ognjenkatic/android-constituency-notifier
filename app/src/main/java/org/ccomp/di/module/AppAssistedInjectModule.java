package org.ccomp.di.module;

import com.squareup.inject.assisted.AssistedInject;
import com.squareup.inject.assisted.dagger2.AssistedModule;

import dagger.Module;

@Module(includes = {AssistedInject_AppAssistedInjectModule.class})
@AssistedModule
public abstract class AppAssistedInjectModule {
}
