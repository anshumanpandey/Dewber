Dewber app.

/***** Only 1 time *****/
keytool -genkey -v -keystore dewber.keystore -alias dewber -keyalg RSA -keysize 2048 -validity 10000

dewber@123

keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore -storepass android -keypass android | openssl sha1 -binary | openssl base64


/***** TO BE RUN EACH TIME *****/

jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore dewber.keystore /Users/jayesh/www/dewber/platforms/android/app/build/outputs/apk/release/app-release-unsigned.apk dewber

/Users/jayesh/Library/Android/sdk/build-tools/28.0.0-rc2

./zipalign -v 4 /Users/jayesh/www/dewber/platforms/android/app/build/outputs/apk/release/app-release-unsigned.apk dewber.apk