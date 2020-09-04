Safer is a little JVM error handling library made to catch errors in critical points of the program execution when they are not expected to happen.

# Whats the point
Safer is designed to prevent fatal errors in an application when you don't expect an Exception to be thrown.
It's designed to be used with [Mixin](https://github.com/SpongePowered/Mixin), a framework to make runtime bytecode injectons. Since it requires a lot of unsafe class casts, the Type Inference is damaged and a lot of different exceptions may appear and set the production on fire.


When to use Safer
- When you are implementing a critical code fragment
- When you are injecting a code fragment into another class' bytecode

When **not** to use Safer
- **When you are sure something will go wrong:** Safer is meant to be used as **the last resource** of recovering from a fatal error. If you know an error will be throw, ``try/catch`` it.

# Installation
build.gradle
```groovy
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
```
```groovy
    dependencies {
        implementation 'com.github.NathanPB:Safer:VERSION'
    }
```

See the versions [in the releases page](https://github.com/NathanPB/Safer/releases)

# How to use
```java
public void unsafeCodeFragment() {
    Safer.run(() -> {
      // Critical and unsafe code fragment
    })
}
```

```java
public String unsafeCodeFragment() {
    return Safer.returning("default value to return if something goes wrong", () -> {
      // Critical and unsafe code fragment
    })
}
```

For more examples check [the tests files](tree/master/src/test/java)
