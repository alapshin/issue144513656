package com.alapshin.issue144513656;

import rx.Observable;

public interface ServerSettingsModel {
    Observable<ServerSettings> getSettings();
}
