# Baghdadi Native Java - Build Notes

## Resource build failure fixed

The Android resource merger fails when extensionless files exist under `app/src/main/res`, for example:

- `app/src/main/res/drawable/a`
- `app/src/main/res/values/a`

Android resource files must have supported extensions. The CI workflow now runs `tools/clean-android-resources.sh` before Gradle. It removes accidental extensionless files and validates `res/values` and `res/drawable` before the build.

## CI

The workflow uses JDK 17 and Gradle 8.9 with Android Gradle Plugin 8.7.3.

The Node 20 deprecation message is unrelated to this Gradle resource error; the Android build does not depend on Node.
