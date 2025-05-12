# 🎬 Dogs Challenge

This is an Android project that displays a list of dogs from a [Mock Endpoint](https://jsonblob.com/api/1151549092634943488), the app includes the following features:

- Fetches the dogs list from a remote source and caches it locally for future offline access.
- Refreshes the data using a swipe-to-refresh gesture.
- Implements a retry mechanism for failed requests.
  
---

## 📌 Technologies & Concepts Used

### 🛠 Technologies
- **Kotlin**
- **Coroutines** (including Flow)
- **Hilt** for dependency injection
- **Jetpack Compose** for UI
- **Retrofit** for networking
- **Rooom** to store data in cache

### 🧪 Testing
- **JUnit**
- **Mockito**
- **Hamcrest**
- **Coroutines Test**

### 💡 Architecture & Patterns
- **Clean Architecture**
- **Clean Code**
- **SOLID principles**
- **MVVM**
- **Repository pattern**

---

## 🧪 UI Tests

### ▶️ Get Dogs Data

| Get data from Remote | Get data from Local | Error and Retry |
|-|-|-|
| <video src="https://github.com/user-attachments/assets/79204012-13a5-41bb-81d3-ec5b964db7be" width="360" controls></video> | <video src="https://github.com/user-attachments/assets/17d8ee11-f282-41c9-a3c1-878001d729d7" width="360" controls></video> | <video src="https://github.com/user-attachments/assets/a081887e-d948-4f90-bf2c-329ee05deda9" width="360" controls></video> |

### 🔄 Refresh Dogs Data


| Refresh data | Error and Retry |
|-|-|
| <video src="https://github.com/user-attachments/assets/7f3cbaaf-ff0c-46fd-80a6-d349766a599e" width="360" controls></video> | <video src="" width="360" controls></video> |



https://github.com/user-attachments/assets/62ececd7-c9f6-476b-95a3-1ec8594ee086


## 🧪 How to Run & Test the Project


You can build app or run the  Unit Tests Integration Tests with 

```
./gradlew testDebugUnitTest //Unit Test
./gradlew connectedDebugAndroidTest //Integration test
```


