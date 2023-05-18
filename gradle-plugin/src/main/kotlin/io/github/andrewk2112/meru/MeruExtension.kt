package io.github.andrewk2112.meru

import org.gradle.api.Named
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Describes [MeruPlugin] configurations can be set from Gradle build scripts.
 */
abstract class MeruExtension @Inject constructor(objects: ObjectFactory) : Named {

    /**
     * Returning any random value here doesn't affect anything.
     */
    override fun getName(): String = "meru"

    /** A configuration property can be set within Gradle build scripts by using its exact name here. */
    val targetAnnotationClass: Property<String> = objects.property(String::class.java)

}
