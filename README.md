# SIP2 Android Core

Universal Android SIP library based on PJSIP that provides core SIP functionality for React Native, Flutter, and native Android applications.

## Overview

This library extracts the core SIP business logic from the original `react-native-sip2` project into a reusable Android library. It contains all the essential SIP functionality including account management, call handling, audio/video support, and event broadcasting.

## Architecture

The library is designed to be used by different platform wrappers:

- **React Native**: `react-native-sip2` - Thin wrapper for React Native
- **Flutter**: `flutter_sip2` - Thin wrapper for Flutter  
- **Native Android**: Direct integration

## Features

- ✅ SIP account creation and management
- ✅ Account registration and deregistration
- ✅ Incoming and outgoing calls
- ✅ Call control (answer, hangup, hold, mute, etc.)
- ✅ Audio/video support
- ✅ DTMF support
- ✅ Call transfer and redirect
- ✅ Event broadcasting
- ✅ Background service support
- ✅ Wake lock management
- ✅ Audio routing (speaker, earpiece)
- ✅ Codec configuration
- ✅ STUN/TURN support
- ✅ Echo cancellation
- ✅ Cross-platform compatibility

## Project Structure

```
sip2-android-core/
├── android/
│   └── src/main/
│       ├── java/
│       │   ├── org/pjsip/pjsua2/     # JNI wrappers for PJSIP
│       │   └── org/telon/sip2/       # Universal SIP business logic
│       │       ├── dto/              # Data Transfer Objects
│       │       ├── utils/            # Utility classes
│       │       ├── PjSipService.java # Main SIP service
│       │       ├── PjSipAccount.java # Account management
│       │       ├── PjSipCall.java    # Call handling
│       │       ├── PjActions.java    # Action definitions
│       │       └── ...
│       ├── jniLibs/                   # Native libraries (.so files)
│       │   ├── arm64-v8a/
│       │   ├── armeabi-v7a/
│       │   ├── x86/
│       │   └── x86_64/
│       └── AndroidManifest.xml
├── build.gradle
├── package.json
└── README.md
```

## Core Components

### PjSipService
Main service that handles all SIP operations:
- Library initialization
- Account management
- Call handling
- Event broadcasting
- Background processing

### PjSipAccount
Manages SIP account lifecycle:
- Account creation and configuration
- Registration/deregistration
- Account state management

### PjSipCall
Handles individual call operations:
- Call state management
- Audio/video control
- Call actions (answer, hangup, hold, etc.)

### PjActions
Defines all available actions and events:
- Action constants
- Event names
- Parameter definitions

### DTO Classes
Data Transfer Objects for configuration:
- `AccountConfigurationDTO` - Account settings
- `CallSettingsDTO` - Call parameters
- `ServiceConfigurationDTO` - Service settings
- `SipMessageDTO` - SIP message data

## Usage

### For React Native
```javascript
import { Sip2Core } from 'react-native-sip2';

// Initialize the library
await Sip2Core.start();

// Create account
const account = await Sip2Core.createAccount({
  id: 'sip:user@domain.com',
  password: 'password',
  domain: 'domain.com',
  port: 5060
});

// Make a call
const call = await Sip2Core.makeCall(account.id, 'sip:destination@domain.com');
```

### For Flutter
```dart
import 'package:flutter_sip2/flutter_sip2.dart';

// Initialize the library
await FlutterSip2.start();

// Create account
final account = await FlutterSip2.createAccount({
  'id': 'sip:user@domain.com',
  'password': 'password',
  'domain': 'domain.com',
  'port': 5060,
});

// Make a call
final call = await FlutterSip2.makeCall(account['id'], 'sip:destination@domain.com');
```

### For Native Android
```java
import org.telon.sip2.PjSipService;
import org.telon.sip2.dto.AccountConfigurationDTO;

// Start the service
Intent intent = new Intent(this, PjSipService.class);
intent.setAction(PjActions.ACTION_START);
startService(intent);

// Create account
AccountConfigurationDTO config = new AccountConfigurationDTO();
config.setId("sip:user@domain.com");
config.setPassword("password");
config.setDomain("domain.com");
config.setPort(5060);

Intent createIntent = new Intent(this, PjSipService.class);
createIntent.setAction(PjActions.ACTION_ACCOUNT_CREATE);
createIntent.putExtra("config", config);
startService(createIntent);
```

## Building

### Prerequisites
- Android SDK 33+
- Java 17+
- Gradle 7.0+

### Build Commands
```bash
# Build the library
npm run build

# Run tests
npm run test

# Clean build
cd android && ./gradlew clean
```

### Output
The build produces an Android AAR library that can be included in other projects.

## Dependencies

### Native Libraries
- `libpjsua2.so` - PJSIP core library
- `libopenh264.so` - H.264 video codec

### Android Dependencies
- `androidx.annotation:annotation:1.5.0`
- `junit:junit:4.13.2` (test)
- `androidx.test.ext:junit:1.1.5` (test)
- `androidx.test.espresso:espresso-core:3.5.1` (test)

## Permissions

The library requires the following Android permissions:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
```

## Events

The library broadcasts the following events:

- `org.telon.account.started` - Service started
- `org.telon.account.created` - Account created
- `org.telon.registration.changed` - Registration state changed
- `org.telon.call.changed` - Call state changed
- `org.telon.call.terminated` - Call terminated
- `org.telon.call.received` - Incoming call received
- `org.telon.call.screen.locked` - Screen locked during call
- `org.telon.message.received` - SIP message received
- `org.telon.handled` - Action handled

## Configuration

### Service Configuration
```java
ServiceConfigurationDTO config = new ServiceConfigurationDTO();
config.setUserAgent("My SIP Client");
config.setStunServers("stun:stun.l.google.com:19302");
```

### Account Configuration
```java
AccountConfigurationDTO config = new AccountConfigurationDTO();
config.setId("sip:user@domain.com");
config.setPassword("password");
config.setDomain("domain.com");
config.setPort(5060);
config.setTransport("UDP");
config.setRegTimeout(300);
```

### Call Settings
```java
CallSettingsDTO settings = new CallSettingsDTO();
settings.setAudioCount(1);
settings.setVideoCount(0);
settings.setFlag(0);
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

MIT License - see LICENSE file for details.

## Related Projects

- [react-native-sip2](https://github.com/telon/react-native-sip2) - React Native wrapper
- [flutter_sip2](https://github.com/telon/flutter_sip2) - Flutter wrapper
- [pjsip-android-lib](https://github.com/telon/pjsip-android-lib) - JNI library

## Support

For issues and questions:
- Create an issue on GitHub
- Check the documentation
- Review the example applications 