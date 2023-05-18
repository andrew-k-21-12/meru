## ❓ What is it?

This is a small Kotlin compiler plugin template. 
It's primarily focused on how to set up a corresponding Gradle project.

## ✅ Why this template?

In contrary to other templates can be found on GitHub, this one:
1. Contains detailed explanations about each component's purpose and configuration.
2. Doesn't have any additional complications like publishing, CI/CD and other extra configurations.

## 📖 How to deal with it?

It's better to clone this repository and open in the IDE, 
as there are some helpful entry-point run configurations provided.
1. Start with investigating the **compiler-plugin** module. 
Open and review `MeruCompilerPluginRegistrar` 
as it is an entry point for all configurations and logic of the compiler plugin itself.
2. Then check the **gradle-plugin** module which represents a Gradle plugin 
to apply and configure the compiler plugin within Gradle build scripts (a kind of Gradle wrapper for it).
3. And eventually open the **sandbox** project 
to see how the plugin can be applied and configured for Gradle modules.
