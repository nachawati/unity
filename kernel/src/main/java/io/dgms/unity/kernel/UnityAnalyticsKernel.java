/*
 * بسم الله الرحمن الرحيم 
 *
 * In the name of Allah, the Most Compassionate, the Most Merciful
 *
 * This file is part of Unity DGMS <https://www.dgms.io/>
 *
 * Unity DGMS is free software; redistribution and use in source and binary
 * forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 1. Redistributions of source code must retain the above notice, this list of
 *    conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above notice, this list
 *    of conditions and the following disclaimer in the documentation and/or
 *    other materials provided with the distribution.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHORS DISCLAIM ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package io.dgms.unity.kernel;

import io.dgms.unity.kernel.engine.UnityAnalyticsEngine;
import io.dgms.unity.kernel.engine.UnityAnalyticsEngineManager;
import io.github.spencerpark.jupyter.kernel.BaseKernel;
import io.github.spencerpark.jupyter.kernel.LanguageInfo;
import io.github.spencerpark.jupyter.kernel.display.DisplayData;

public class UnityAnalyticsKernel extends BaseKernel
{
    private final UnityAnalyticsEngineManager manager;
    private final UnityAnalyticsEngine        engine;

    public UnityAnalyticsKernel(UnityAnalyticsEngineManager manager, UnityAnalyticsEngine engine)
    {
        this.manager = manager;
        this.engine = engine;
    }

    @Override
    public LanguageInfo getLanguageInfo()
    {
        return engine.getFactory().getLanguageInfo();
    }

    @Override
    public DisplayData eval(String expr) throws Exception
    {
        Object result = engine.eval(expr, manager.getContext());

        if (result != null) {
            DisplayData displayData = new DisplayData();
            displayData.putText(result.toString());
            return displayData;

        }
        return null;
    }
}
