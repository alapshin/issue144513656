package com.alapshin.issue144513656;

import java.util.List;

import rx.Observable;

interface LocaleModel {
    Observable<List<String>> getAvailableLocales();
}
