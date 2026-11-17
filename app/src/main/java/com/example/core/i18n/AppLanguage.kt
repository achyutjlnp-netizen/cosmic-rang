package com.example.core.i18n

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

/**
 * Scalable language registry for Cosmic Rang.
 * New regional languages (e.g., Marathi, Tamil, Telugu, Gujarati, Bengali) can be added here
 * along with matching `values-<bcp47>/strings.xml` resource qualifiers.
 */
enum class AppLanguage(
    val code: String,
    val shortLabel: String,
    val nativeName: String
) {
    ENGLISH(code = "en", shortLabel = "EN", nativeName = "English"),
    HINDI(code = "hi", shortLabel = "हिं", nativeName = "हिन्दी");

    fun toLocale(): Locale = Locale.forLanguageTag(code)
}

@Immutable
data class LocalizedText(
    val translations: Map<AppLanguage, String>
) {
    constructor(en: String, hi: String) : this(
        mapOf(
            AppLanguage.ENGLISH to en,
            AppLanguage.HINDI to hi
        )
    )

    fun resolve(language: AppLanguage): String =
        translations[language]
            ?: translations[AppLanguage.ENGLISH]
            ?: translations.values.firstOrNull().orEmpty()
}

val LocalAppLanguage = staticCompositionLocalOf { AppLanguage.ENGLISH }

val LocalLocalizedContext = staticCompositionLocalOf<Context?> { null }

@Composable
fun rememberLocalizedContext(language: AppLanguage): Context {
    val baseContext = LocalContext.current
    return remember(baseContext, language) {
        val locale = language.toLocale()
        val config = Configuration(baseContext.resources.configuration).apply {
            setLocale(locale)
        }
        baseContext.createConfigurationContext(config)
    }
}

@Composable
fun localizedString(@StringRes resId: Int): String {
    val context = LocalLocalizedContext.current ?: LocalContext.current
    return context.getString(resId)
}
