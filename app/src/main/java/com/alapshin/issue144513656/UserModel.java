package com.alapshin.issue144513656;

import rx.Observable;

interface UserModel {
    Observable<User> changes();
}
