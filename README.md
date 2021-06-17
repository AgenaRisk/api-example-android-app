# AgenaRisk 10 Java API Example Android Studio Project

This example Android Studio project demonstrates how to get AgenaRisk 10 Java API running on an Android device.

## Supported Architectures
* arm64-v8a
* armeabi-v7a

## Example Use Cases
The example code covers use cases:
1. Create a model using the API at runtime and calculate
1. Load a model embedded in the app and calculate

## Usage
See [AgenaRisk 10 Java API](https://github.com/AgenaRisk/api) repository for more details on the API in general.

Note:
* AgenaRisk API is not thread-safe so make sure only one model is worked with at a time
* Power management and battery saver settings will slow down calculation dramatically, so it may be a good idea to warn your user or ask them to disable power management for your app

## Licensing
Please note a valid AgenaRisk license is required.

Replace the placeholder key in class `com.agenarisk.android.example.MainActivity`:
```
AgenaRiskHelper.init("D572AC-78FFC3-451984-D4B4D4-2F5D3A-419E78");
```

## Resources
[JavaDoc](https://agenarisk.github.io/api/)
