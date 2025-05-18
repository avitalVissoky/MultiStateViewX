# MultiStateViewX

MultiStateViewX is a lightweight and flexible Android library that helps developers easily manage UI states: `LOADING`, `EMPTY`, `ERROR`, and `CONTENT` — all with one reusable view.

## Features
- One view to rule all states 
- Easy switching between states
- Built-in default layouts for loading, empty and error
- Option to provide custom layouts or views per state
- Retry button with callback support for `ERROR` state
- Smooth transitions (fade in, slide..) between Activities with `TransitionType`

---

## Setup

### 1. Add the following to your settings.gradle.kts file:
    pluginManagement {
         repositories {
            // your existing repos
            maven("https://jitpack.io") // <-- add this here
         }
    }

### 2. Add to your settings.gradle.kts at the end of repositories`:
```
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url = uri("https://jitpack.io") }
		}
	}
```

### 3. Add the dependency to your build.gradle.kts:
```
dependencies {
      implementation("com.github.avitalVissoky:MultiStateViewX:1.0.0")
}
```

---

## Usage

### Add `MultiStateView` to your layout
```xml
<com.avitaliskhakov.multistateviewx.MultiStateView
    android:id="@+id/multiStateView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

### In your activity:
```
multiStateView.setState(State.LOADING);
multiStateView.setState(State.EMPTY);
multiStateView.setState(State.ERROR);
multiStateView.setState(State.CONTENT);
```

---

## Custom Views

```java
// Set content
View content = LayoutInflater.from(this).inflate(R.layout.my_content, null);
multiStateView.setContentView(content);

// Provide custom layout for EMPTY state
multiStateView.setCustomLayout(State.EMPTY, R.layout.my_empty);

// Revert back to default
multiStateView.resetToDefault(State.EMPTY);
```

---

## Retry Button

The `state_error.xml` includes a button with ID `btnRetry`. Just hook the listener:

```java
multiStateView.setOnRetryClickListener(v -> {
    // Retry logic here
});
```

---

## Activity Transitions
Use the built-in `MultiStateNavigator` class to simplify screen transitions:

```java
MultiStateNavigator.startActivity(this, SecondActivity.class, TransitionType.SLIDE);
MultiStateNavigator.finishWithTransition(this, TransitionType.FADE);
```

---

## Screenshots

<div>
<img src="https://github.com/user-attachments/assets/aca745b9-5206-4fe7-b320-114647787c56"style="height:400px;"/>
<img src="https://github.com/user-attachments/assets/021295a5-d08f-47d0-8baf-7a5fdcb02beb"style="height:400px;"/>
</div>

---

##  License
This project is licensed under the MIT License.

---

Happy coding!

> Made with ♥ and ☕ by Avital Iskhakov
