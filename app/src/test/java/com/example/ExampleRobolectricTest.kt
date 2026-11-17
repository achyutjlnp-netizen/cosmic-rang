package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Cosmic Rang", appName)
  }

  @Test
  fun `catalog resolves English and Hindi translations`() {
    val firstService = com.example.domain.model.CosmicCatalog.services.first()
    assertEquals("Vedic Astrology", firstService.title.resolve(com.example.core.i18n.AppLanguage.ENGLISH))
    assertEquals("वैदिक ज्योतिष", firstService.title.resolve(com.example.core.i18n.AppLanguage.HINDI))
  }
}
