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

fun String.fromHtml(): Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT)

fun <T : Any> multipleLetCheck(vararg variables: T?, block: (List<T>) -> Unit): Unit? {
    return if (variables.all { variable -> variable != null }) {
        block(variables.filterNotNull())
    } else {
        null
    }
}

fun <T : Any> multipleNotNull(vararg variables: T?): Boolean =
    variables.all { variable -> variable.isNotNull() }