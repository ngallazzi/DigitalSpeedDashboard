[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-DigitalSpeedDashboard-green.svg?style=flat )]( https://android-arsenal.com/details/1/7413 )
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
<br>
# DigitalSpeedDashboard

A simple speed dashboard displayed with a 7-segment display
<div>
  <img src="https://raw.githubusercontent.com/ngallazzi/DigitalSpeedDashboard/master/2pivz4.gif" width="250" hspace="20" />
  <br/>
</div>

# Usage

in your build.gradle (Module)
```groovy
implementation 'com.github.ngallazzi:DigitalSpeedDashboard:master-SNAPSHOT'
```

in your build.gradle (Project)
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
# In your .xml
```groovy
<com.ngallazzi.speedandrpmdashboard.DigitalSpeedDashboard
            android:id="@+id/srDashboard"
            android:layout_width="350dp"
            android:layout_height="350dp"
            android:background="#212121"
            app:circleIndicatorColor="#303F9F"
            app:idleColor="#35ABABAB"
            app:ringBaseColor="#3F51B5"
            app:speedColor="#FFFFFF"
            android:paddingBottom="8dp" android:paddingTop="8dp"
            android:layout_marginStart="8dp"
            app:layout_constraintStart_toStartOf="parent" android:layout_marginTop="8dp"
            app:layout_constraintTop_toTopOf="parent" android:layout_marginEnd="8dp"
            app:layout_constraintEnd_toEndOf="parent"/>
```

# Options
 - custom **idleColor**: "custom:idleColor" - sets the background color of the seven segments display
 - custom **speedColor**: "custom:speedColor" - sets the color of the seven segments display
 - custom **backgroundColor**: "custom:backgroundColor" - sets the color of the dashboard background
 - custom **ringBaseColor**: "custom:ringBaseColor" - sets the base color for circle progress
 - custom **circleIndicatorColor**: "custom:circleIndicatorColor" - sets the color of the circle indicator
 
# Public Methods

- setSpeed(speed: Int): sets the current speed in the layout
- setMaxSpeed(speed: Int): sets the maximum speed progress for the circle speed bar. When the speed reaches the max speed limit, the speed bar is totally full
 
 # License
```groovy 
Copyright 2018 Nicola Gallazzi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
