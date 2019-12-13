package com.alapshin.issue144513656;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.io.Serializable;
import java.util.List;

@AutoValue
public abstract class ServerSettings implements Serializable {
    @Nullable
    public abstract String avatar();
    @Nullable
    public abstract String city();
    @Nullable
    public abstract String email();
    public abstract boolean admin();
    public abstract boolean coach();
    public abstract boolean editor();
    public abstract boolean expert();
    public abstract boolean guest();
    public abstract boolean hasPassword();
    public abstract boolean hidePassword();
    public abstract boolean hideSocial();
    public abstract String locale();
    public abstract List<String> availableLocales();

    public static Builder builder() {
        return new AutoValue_ServerSettings.Builder();
    }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder avatar(String avatar);
        public abstract Builder city(String city);
        public abstract Builder email(String email);
        public abstract Builder admin(boolean admin);
        public abstract Builder coach(boolean coach);
        public abstract Builder editor(boolean editor);
        public abstract Builder expert(boolean expert);
        public abstract Builder guest(boolean guest);
        public abstract Builder hasPassword(boolean hasPassword);
        public abstract Builder hidePassword(boolean hidePassword);
        public abstract Builder hideSocial(boolean hideSocial);
        public abstract Builder locale(String locale);
        public abstract Builder availableLocales(List<String> locales);

        @NonNull
        public abstract ServerSettings build();
    }
}
