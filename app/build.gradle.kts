plugins {
    id("com.android.application")
}

android {
    namespace = "algonquin.cst2335.pajaappfinal"
    compileSdk = 34

    defaultConfig {
        applicationId = "algonquin.cst2335.pajaappfinal"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.github.bumptech.glide:glide:4.16.0") //Implemented by Allan
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.recyclerview:recyclerview:1.+")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //Added by Peace
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("com.android.volley:volley:1.2.1")
    implementation ("com.google.code.gson:gson:2.8.8")
    
    //    Dependecies added by Alessandra:
    implementation ("com.squareup.picasso:picasso:2.71828")
    
  
 

}