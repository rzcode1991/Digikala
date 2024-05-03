import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val apiKeyPropertiesFile: File = rootProject.file("key.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(apiKeyPropertiesFile.inputStream())

android {
    namespace = "com.example.digikala"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.digikala"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "X_API_KEY", apiKeyProperties.getProperty("X_API_KEY"))
        buildConfigField("String", "KEY", apiKeyProperties.getProperty("KEY"))
        buildConfigField("String", "IV", apiKeyProperties.getProperty("IV"))
        buildConfigField("String", "ZARIN_MERCHANT_ID", apiKeyProperties.getProperty("ZARIN_MERCHANT_ID"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    //noinspection GradleCompatible
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //room db
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    /*implementation("androidx.room:room-paging:2.6.1")*/

    //data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // dagger hilt with ksp
    implementation("com.google.dagger:hilt-android:2.48")
    /*ksp("com.google.dagger:hilt-android-compiler:2.48")
    ksp ("androidx.hilt:hilt-compiler:1.2.0")*/
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp ("com.google.dagger:dagger-compiler:2.48") // Dagger compiler
    ksp ("com.google.dagger:hilt-compiler:2.48")   // Hilt compiler

    //compose navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    //Gson
    implementation ("com.google.code.gson:gson:2.10.1")

    //lottie animation
    implementation ("com.airbnb.android:lottie-compose:6.3.0")

    //coil - load image from url
    implementation("io.coil-kt:coil-compose:2.6.0")

    //swipe refresh
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.27.0")

    //system ui controller
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    //Accompanist-Pager
    implementation ("com.google.accompanist:accompanist-pager:0.35.0-alpha")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.35.0-alpha")

    //zarinpal
    implementation("com.zarinpal:payment-provider-ktx:0.5.3")


}