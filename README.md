# troLy - Voice Assistant (Android phone)

Control phone by voice (Vietnamese): 
- Make phone call
- Sent message, email
- Add contact
- Set alarm
- Find way (using Google Maps)
- Turn on/off wifi, bluetooth, GPS
- Increase/decrease brightness, volume
- Open other application

Can set to allways active. That means can work even the screen is turn off(but not the app) or in the others app.
This app uses GOOGLE SPEECH RECOGNITION service to recognize the input voice(Vietnamese), 
uses SVM(Support Vector Machine) to improve the accuracy and also to classify and find out which comand is.

The accuracy of the app is based on the training data (bigger is better).
The app include a file which contains the identification model file.This is create outside the app using SVM on linux.

