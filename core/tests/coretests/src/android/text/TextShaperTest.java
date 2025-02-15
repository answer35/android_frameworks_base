/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.text;

import static com.google.common.truth.Truth.assertWithMessage;

import android.graphics.fonts.Font;
import android.graphics.fonts.FontFileUtil;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class TextShaperTest {

    @Test
    public void testFontWithPath() {
        TextPaint p = new TextPaint();
        p.setFontVariationSettings("'wght' 900");
        TextShaper.shapeText("a", 0, 1, TextDirectionHeuristics.LTR, p,
                (start, end, glyphs, paint) -> {
                // This test only passes if the font of the Latin font is variable font.
                Font font = glyphs.getFont(0);
                String psName = FontFileUtil.getPostScriptName(font.getBuffer(), 0);
                String message = "psName = " + psName + ", file = " + font.getFile();
                assertWithMessage(message).that(glyphs.getFont(0).getFile())
                        .isNotNull();

            });
    }
}
