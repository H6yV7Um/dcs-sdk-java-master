/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baidu.duer.dcs.devicemodule.screen.extend.card;

public class ApiConstants {
    public static final String NAMESPACE = "ai.dueros.device_interface.screen_extended_card";

    public static final class Directives {
        public static final class RenderDate {
            public static final String NAME = RenderDate.class.getSimpleName();
        }

        public static final class RenderWeather {
            public static final String NAME = RenderWeather.class.getSimpleName();
        }

        public static final class RenderAirQuality {
            public static final String NAME = RenderAirQuality.class.getSimpleName();
        }

        public static final class RenderTrafficRestriction {
            public static final String NAME = RenderTrafficRestriction.class.getSimpleName();
        }

        public static final class RenderStock {
            public static final String NAME = RenderStock.class.getSimpleName();
        }

        public static final class RenderNoticeMessage {
            public static final String NAME = RenderNoticeMessage.class.getSimpleName();
        }

        public static final class RenderAudioList {
            public static final String NAME = RenderAudioList.class.getSimpleName();
        }

        //播放音乐相关信息
        public static final class RenderPlayerInfo {
            public static final String NAME = RenderPlayerInfo.class.getSimpleName();
        }


        public static final class RenderAlbumList {
            public static final String NAME = RenderAlbumList.class.getSimpleName();
        }

        public static final class RenderActiveAlarm {
            public static final String NAME = RenderActiveAlarm.class.getSimpleName();
        }

        public static final class RenderTimerList {
            public static final String NAME = RenderTimerList.class.getSimpleName();
        }

        public static final class RenderActiveTimer {
            public static final String NAME = RenderActiveTimer.class.getSimpleName();
        }
    }
}