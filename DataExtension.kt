package io.eliq.core.utilities.extensions

fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}

fun Any?.isNotNull(): Boolean {
    return this != null
}

fun Any?.isNull(): Boolean {
    return this == null
}

fun LinkedHashMap<String, String>.getOrKey(key: String): String {
    return this.getOrElse(key = key) { key }
}