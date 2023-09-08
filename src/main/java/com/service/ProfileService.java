package com.service;

import com.dto.ProfileDTO;
import com.entity.Profile;
import com.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void register(Profile profile) {
        profile.setPassword(getPasswordHash(profile.getPassword()));
        profileRepository.insert(profile);
    }

    public boolean canRegister(String login) {
        Profile profile = profileRepository.getByLogin(login);
        return !isProfileExists(profile);
    }

    public ProfileDTO login(String login, String password) {
        Profile profile = profileRepository.getByLogin(login);
        if (isProfileExists(profile) && isPasswordsEquals(password, profile)) {
            return mapToProfileDTO(profile);
        }
        return null;
    }

    private boolean isPasswordsEquals(String password, Profile profile) {
        return profile.getPassword().equals(getPasswordHash(password));
    }

    private boolean isProfileExists(Profile profile) {
        return profile != null;
    }

    private ProfileDTO mapToProfileDTO(Profile profile) {
        return new ProfileDTO(
                profile.getId(), profile.getLogin());
    }

    private String getPasswordHash(String password) {
        return String.valueOf(password.hashCode());
    }
}
