
# GlobalExceptionHandler

![header](https://github.com/Alonew0lfxx/GlobalExceptionHandler/blob/c56bf46d4497edb24425896abf680cc3155a7579/assets/header.png?raw=true)

Rather than showing the default boring system error dialog, it serves to open the
desired Activity whenever the Application crashes. And it has only 2 functions




## Usage/Examples

App.kt
```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.initialize(this,CrashActivity::class.java)
    }
}
```

App.kt
```kotlin
class CrashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalExceptionHandler.getThrowableFromIntent(intent).let { throwable ->
            // Report the crash error to your servers or etc...
        }
        setContentView(view)
    }
```


## Functions

#### Initalize the GlobalExceptionHandler

```kotlin
  GlobalExceptionHandler.initalize(applicationContext, activityToBeLaunched)
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `applicationContext` | `Context` | Required to launch Intent |
| `activityToBeLaunched` | `Activity` | The activity to be launched whenerver app crashes |

#### Get Throwable from Intent

```kotlin
  GlobalExceptionHandler.getThrowableFromIntent(intent): Throwable?
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `intent`  | `Intent` | Retrives crash data from intent. It should be called inside of the **activityToBeLaunched** Activity. |

## Screenshots
With GlobalExceptionHandler | Without GlobalExceptionHandler
--- | ---
![](https://github.com/Alonew0lfxx/GlobalExceptionHandler/blob/master/assets/gif1.gif?raw=true) | ![](https://github.com/Alonew0lfxx/GlobalExceptionHandler/blob/master/assets/gif0.gif?raw=true)

