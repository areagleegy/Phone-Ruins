# Phone Ruins — v0.1.0

[![Android Debug APK](https://github.com/areagleegy/Phone-Ruins/actions/workflows/android-apk.yml/badge.svg)](https://github.com/areagleegy/Phone-Ruins/actions/workflows/android-apk.yml) · [View build runs](https://github.com/areagleegy/Phone-Ruins/actions)

**Turn your everyday phone routine into a retro adventure.**

Phone Ruins is an experimental, privacy-first Android game inspired by classic Atari-era adventures. Explore a mysterious pixel-art world with rooms, keys, locked passages, and simple challenges.

## 🎮 Vision

- **Explore a retro world:** Discover connected rooms and hidden objects.
- **Progress at your pace:** Future focus sessions may unlock rewards.
- **A changing world:** Future versions may connect optional phone signals to game events.
- **Privacy first:** The initial prototype does not request sensitive phone permissions.
- **No punishment:** The game should encourage exploration, never shame or penalize users for everyday behavior.

## 🚧 Current Status

**Early prototype — v0.1.0.** This version provides an initial Android app, a retro-inspired game screen, room navigation, a key/door state, local preference saving, and a GitHub Actions workflow intended to build a debug APK. Features on the roadmap are not implemented unless explicitly listed as current scope.

## 📱 Requirements

- Android 12 or later
- JDK 17
- Android SDK platform 35
- Gradle 8.9 (the workflow installs it)

## 🛠️ Technology

- Kotlin
- Jetpack Compose and Canvas
- Gradle
- GitHub Actions

## 🔐 Privacy

The prototype does not request Usage Access, Notification Access, location, activity-recognition, or network permissions. Future integrations must be optional, clearly explained, and designed to process only the minimum necessary data.

## 🗺️ Roadmap

- [x] Initial Android project structure
- [x] Retro-inspired game screen
- [x] Five-room indicator and basic room navigation
- [x] Key/door state and local preference saving
- [x] Initial GitHub Actions debug-APK workflow
- [ ] Verify successful CI build
- [ ] Improve player movement and collision mechanics
- [ ] Add richer room layouts, enemies, and exploration objectives
- [ ] Add a focus-session reward system
- [ ] Add optional, privacy-conscious phone integrations
- [ ] Add world-map export using QR codes
- [ ] Publish a tested release APK

## 🏗️ Build and download

The GitHub Actions workflow runs on pushes to `main`, pull requests, and manual dispatch. Open [GitHub Actions](https://github.com/areagleegy/Phone-Ruins/actions), select a **successful** `Android Debug APK` run, and download `Phone-Ruins-debug-apk` from its Artifacts section. The artifact is a ZIP containing the APK.

The artifact is a **debug APK for testing**, not a signed production release. The workflow and APK must be verified before claiming a build is available.

## 🤝 Contributing

Issues, bug reports, ideas, and pull requests are welcome. Please explain permission requirements and privacy implications when proposing phone-integration features.

## 📄 License

No license has been selected yet. A license should be added before inviting reuse or distribution.
