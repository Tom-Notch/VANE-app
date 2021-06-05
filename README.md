# VANE app client

## Introduction

This is the client side of a fitness exercise coaching application. for demo purpose, it has to run on an **Android** mobile phone with a server on the **same LAN/WIFI** (a laptop's hotspot is recommended).

Find the pre-built apk [here](https://github.com/TomNotchYmk/VANE-app/tree/main/app/build/outputs/apk/debug).

Core section of the project is [here](https://github.com/TomNotchYmk/VANE-app/blob/main/app/src/main/java/com/example/vane0427/TrainingActivity.java).

---

## Running environment

Android 10+

Developed on: Windows 10, [Android Studio 4.2](https://developer.android.com/studio)

* You are welcomed to import the project using Android Studio and install the app to your mobile phone via wired connection and [debug](https://developer.android.com/studio/debug#startdebug).

---

## Get started

1. Check that the mobile phone has connected to the same LAN/WIFI with server.
    * If a laptop hotspot is used and the IP address of the laptop is **192.168.137.1** (hopspot's default gateway), jump to the next step.
    * If not, then manually set the IP address of the server in the code [here](https://github.com/TomNotchYmk/VANE-app/blob/main/app/src/main/java/com/example/vane0427/TrainingActivity.java#L50) like this:
    `private static final String TCP_SERVER_IP = "your server's IP address";`
    And then **build** the project.

1. Install the app through pre-built [apk](https://github.com/TomNotchYmk/VANE-app/tree/main/app/build/outputs/apk/debug) or Android Studio.

1. Manually allow all permission of the app in System Settings.

1. Properly place your mobile phone so that the front-facing camera can capture your whole body when you are doing the exercise.

1. Turn on the speaker of the phone, and run the server before using the app.

1. Account registration and information collection can be skipped.

---

## Developer

* [Shawn](https://github.com/XuYinzhe) (UI)
* [Tom Notch](https://github.com/TomNotchYmk) (backend)

## Credit

[VXG RTSP library](https://github.com/VideoExpertsGroup/VXG.Media.SDK.Android)
