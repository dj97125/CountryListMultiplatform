package com.countryList.shared

import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.FileResource
import dev.icerock.moko.resources.FontResource
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.ResourceContainer
import dev.icerock.moko.resources.StringResource

public actual object MR {
  private val stringsBundle: String = "localization/countryListhared_mokoBundle"

  private val pluralsBundle: String = "localization/countryListhared_mokoPluralsBundle"

  public actual object strings : ResourceContainer<StringResource> {
    public override val resourcesClassLoader: ClassLoader = MR::class.java.classLoader

    public actual val app_name: StringResource = StringResource(resourcesClassLoader =
    resourcesClassLoader, bundleName = stringsBundle, key = "app_name")

  }

  public actual object plurals : ResourceContainer<PluralsResource> {
    public override val resourcesClassLoader: ClassLoader = MR::class.java.classLoader
  }

  public actual object images : ResourceContainer<ImageResource> {
    public override val resourcesClassLoader: ClassLoader = MR::class.java.classLoader
  }

  public actual object fonts : ResourceContainer<FontResource> {
    public override val resourcesClassLoader: ClassLoader = MR::class.java.classLoader
  }

  public actual object files : ResourceContainer<FileResource> {
    public override val resourcesClassLoader: ClassLoader = MR::class.java.classLoader
  }

  public actual object colors : ResourceContainer<ColorResource> {
    public override val resourcesClassLoader: ClassLoader = MR::class.java.classLoader
  }

  public actual object assets : ResourceContainer<AssetResource> {
    public override val resourcesClassLoader: ClassLoader = MR::class.java.classLoader
  }
}
