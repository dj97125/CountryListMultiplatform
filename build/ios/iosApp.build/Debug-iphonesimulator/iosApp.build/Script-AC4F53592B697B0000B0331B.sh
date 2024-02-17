#!/bin/sh
# Type a script or drag a script file from your workspace to insert its path.
"$SRCROOT/../gradlew" -p "$SRCROOT/../" :ComposeApp:copyFrameworkResourcesToApp \
    -Pmoko.resources.PLATFORM_NAME="$PLATFORM_NAME" \
    -Pmoko.resources.CONFIGURATION="$CONFIGURATION" \
    -Pmoko.resources.ARCHS="$ARCHS" \
    -Pmoko.resources.BUILT_PRODUCTS_DIR="$BUILT_PRODUCTS_DIR" \
    -Pmoko.resources.CONTENTS_FOLDER_PATH="$CONTENTS_FOLDER_PATH" 

