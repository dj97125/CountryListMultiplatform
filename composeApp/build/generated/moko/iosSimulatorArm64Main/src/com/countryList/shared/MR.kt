package com.countryList.shared

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.FileResource
import dev.icerock.moko.resources.FontResource
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.ResourceContainer
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.utils.loadableBundle
import kotlin.String
import platform.Foundation.NSBundle

public actual object MR {
  private val bundle: NSBundle by lazy { NSBundle.loadableBundle("com.countryList.shared.MR") }

  private val contentHash: String = "a309e0b54354b8aab702ab303d6425df"

  public actual object strings : ResourceContainer<StringResource> {
    public actual val app_name: StringResource = StringResource(resourceId = "app_name", bundle =
        bundle)

    public override val nsBundle: NSBundle = bundle
  }

  public actual object plurals : ResourceContainer<PluralsResource> {
    public override val nsBundle: NSBundle = bundle
  }

  public actual object images : ResourceContainer<ImageResource> {
    public override val nsBundle: NSBundle = bundle
  }

  public actual object fonts : ResourceContainer<FontResource> {
    public override val nsBundle: NSBundle = bundle
  }

  public actual object files : ResourceContainer<FileResource> {
    public override val nsBundle: NSBundle = bundle
  }

  public actual object colors : ResourceContainer<ColorResource> {
    public override val nsBundle: NSBundle = bundle
  }

  public actual object assets : ResourceContainer<AssetResource> {
    public override val nsBundle: NSBundle = bundle
  }
}
