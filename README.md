
# react-native-upload-image

## Getting started

`$ npm install git+https://github.com/pj0579/react-native-alipay.git`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-react-native-upload-image` and add `RNReactNativeUploadImage.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNReactNativeUploadImage.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactNativeUploadImagePackage;` to the imports at the top of the file
  - Add `new RNReactNativeUploadImagePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-native-upload-image'
  	project(':react-native-react-native-upload-image').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-upload-image/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-native-upload-image')
  	```
## Usage
```javascript
import RNReactNativeUploadImage from 'react-native-react-native-upload-image';

// TODO: What to do with the module?
RNReactNativeUploadImage;
```
  # react-native-upload-image
