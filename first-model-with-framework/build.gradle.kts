/*
 * Copyright 2026, TeamDev. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Redistribution and use in source and/or binary forms, with or without
 * modification, must retain the above copyright notice and the following
 * disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

plugins {
    module
    id("io.spine.core-jvm") version "2.0.0-SNAPSHOT.080"
}

// A runtime must be at least as new as the Protobuf gencode it loads. The Validation docs
// build rewrites the plugin versions above to match the main Validation build, whose plugins
// generate gencode `4.36.x`, while the runtime resolved transitively may be older.
// Force the runtime to `Protobuf.version` from the `buildSrc` of the main Validation build;
// it also works with the older gencode of the plugin versions committed here.
dependencies {
    val protobufVersion = "4.36.2"
    configurations.all {
        resolutionStrategy {
            force(
                "com.google.protobuf:protobuf-java:$protobufVersion",
                "com.google.protobuf:protobuf-java-util:$protobufVersion",
                "com.google.protobuf:protobuf-kotlin:$protobufVersion"
            )
        }
    }
}
