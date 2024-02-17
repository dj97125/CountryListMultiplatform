package com.countryList.shared

import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.FileResource
import dev.icerock.moko.resources.FontResource
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.ResourceContainer
import dev.icerock.moko.resources.StringResource

public expect object MR {
  public object strings : ResourceContainer<StringResource> {
    public val app_name: StringResource
  }

  public object plurals : ResourceContainer<PluralsResource>

  public object images : ResourceContainer<ImageResource>

  public object fonts : ResourceContainer<FontResource>

  public object files : ResourceContainer<FileResource>

  public object colors : ResourceContainer<ColorResource>

  public object assets : ResourceContainer<AssetResource>
}
