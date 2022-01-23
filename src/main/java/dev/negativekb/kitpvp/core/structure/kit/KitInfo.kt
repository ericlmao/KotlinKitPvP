package dev.negativekb.kitpvp.core.structure.kit

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class KitInfo(val name: String, val cost: Long = 0)