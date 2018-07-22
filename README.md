# Biometric-Compat

It also provides an easy way to implement fingerprint authentication without having to deal with all the boilerplate stuff going on inside. It is also compatible from Android M to Android P.

<img src="https://img.shields.io/badge/API-23%2B-blue.svg?style=flat" style="max-width:100%;" alt="API" data-canonical-src="https://img.shields.io/badge/API-23%2B-blue.svg?style=flat" style="max-width:100%;">


<p><a href="https://github.com/nirav-tukadiya/Biometric-Compat/blob/master/media/1.png" target="_blank"><img src="https://github.com/nirav-tukadiya/Biometric-Compat/blob/master/media/1.png" width="250" style="max-width:100%;"></a></p>
</br></br></br>



<b>Gradle Dependecy</b></br>

```gradle
dependencies {
 //To be uploaded
}
```

<h2>Usage</h2>

```

 BiometricCompat.BiometricBuilder(context)
                    .setTitle("Add a title")
                    .setSubtitle("Add a subtitle")
                    .setDescription("Add a description")
                    .setNegativeButtonText("Add a cancel button")
                    .build()
                    .authenticate(biometricCallback)
```

The ```BiometricCallback``` class has the following callback methods:

```
//Will be optimized to simpler callback

```



