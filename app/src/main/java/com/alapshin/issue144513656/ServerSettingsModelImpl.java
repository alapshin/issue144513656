package com.alapshin.issue144513656;

import java.util.List;

import rx.Observable;
import rx.functions.Func2;

public class ServerSettingsModelImpl implements ServerSettingsModel {
    private final UserModel userModel;
    private final LocaleModel localeModel;

    public ServerSettingsModelImpl(UserModel userModel,
                                   LocaleModel localeModel) {
        this.userModel = userModel;
        this.localeModel = localeModel;
    }

    @Override
    public Observable<ServerSettings> getSettings() {
        return Observable.combineLatest(
                userModel.changes(),
                localeModel.getAvailableLocales(),
                (user, locales) -> {
                    // Code below causes lint task to hang or slows it down so much that task takes 7+ minutes
                    ServerSettings.Builder builder = ServerSettings.builder()
                            .avatar(user.avatarUrl())
                            .city(user.city())
                            .email(user.email())
                            .admin(user.roles().contains(Role.ADMIN))
                            .coach(user.isCoach() == Boolean.TRUE)
                            .editor(user.roles().contains(Role.EDITOR))
                            .expert(user.isExpert() == Boolean.TRUE)
                            .guest(user.membership() == Membership.GUEST)
                            .hasPassword(user.hasPassword() != null ? user.hasPassword() : false)
                            .hidePassword(user.hideChangePassword() != null ? user.hideChangePassword() : false)
                            .hideSocial(user.hideSocialButtons() != null ? user.hideSocialButtons() : false)
                            .locale(user.locale())
                            .availableLocales(locales);
                    return builder.build();
                    // Replacing code above with code that does nothing solves it and lets task to complete in seconds
//                    return ServerSettings.builder().build();
                }
                // Replacing lambda with explicit anonymous class also solves problem and lets task to complete in seconds
//                new Func2<User, List<String>, ServerSettings>() {
//                    @Override
//                    public ServerSettings call(User user, List<String> locales) {
//                        ServerSettings.Builder builder = ServerSettings.builder()
//                                .avatar(user.avatarUrl())
//                                .city(user.city())
//                                .email(user.email())
//                                .admin(user.roles().contains(Role.ADMIN))
//                                .coach(user.isCoach() == Boolean.TRUE)
//                                .editor(user.roles().contains(Role.EDITOR))
//                                .expert(user.isExpert() == Boolean.TRUE)
//                                .guest(user.membership() == Membership.GUEST)
//                                .hasPassword(user.hasPassword() != null ? user.hasPassword() : false)
//                                .hidePassword(user.hideChangePassword() != null ? user.hideChangePassword() : false)
//                                .hideSocial(user.hideSocialButtons() != null ? user.hideSocialButtons() : false)
//                                .locale(user.locale())
//                                .availableLocales(locales);
//                        return builder.build();
//                    }
//                }
        ).distinctUntilChanged();
    }
}
