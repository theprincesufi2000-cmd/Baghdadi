#!/usr/bin/env bash
set -euo pipefail

RES_DIR="app/src/main/res"

if [[ ! -d "$RES_DIR" ]]; then
  echo "ERROR: $RES_DIR does not exist"
  exit 1
fi

# Android resource directories do not accept extensionless files.
# Remove accidental editor/temp files such as res/drawable/a and res/values/a.
for dir in "$RES_DIR/drawable" "$RES_DIR/values" "$RES_DIR/mipmap" "$RES_DIR/mipmap-anydpi" "$RES_DIR/mipmap-hdpi" "$RES_DIR/mipmap-mdpi" "$RES_DIR/mipmap-xhdpi" "$RES_DIR/mipmap-xxhdpi" "$RES_DIR/mipmap-xxxhdpi"; do
  if [[ -d "$dir" ]]; then
    while IFS= read -r -d '' f; do
      echo "Removing invalid Android resource: $f"
      rm -f -- "$f"
    done < <(find "$dir" -maxdepth 1 -type f ! -name '*.*' -print0)
  fi
done

# Validate drawable/values extensions after cleanup.
if find "$RES_DIR/drawable" "$RES_DIR/values" -type f ! -name '*.*' -print -quit 2>/dev/null | grep -q .; then
  echo "ERROR: extensionless Android resource file remains"
  exit 1
fi

# values accepts XML only; drawable accepts XML/PNG (and other image formats only when explicitly supported).
if find "$RES_DIR/values" -type f ! -name '*.xml' -print -quit 2>/dev/null | grep -q .; then
  echo "ERROR: non-XML file found under res/values"
  find "$RES_DIR/values" -type f ! -name '*.xml' -print
  exit 1
fi
if find "$RES_DIR/drawable" -type f ! \( -name '*.xml' -o -name '*.png' \) -print -quit 2>/dev/null | grep -q .; then
  echo "ERROR: unsupported file found under res/drawable"
  find "$RES_DIR/drawable" -type f ! \( -name '*.xml' -o -name '*.png' \) -print
  exit 1
fi

echo "Android resources validated successfully."
