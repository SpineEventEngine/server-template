/*
 * Copyright 2019, TeamDev. All rights reserved.
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
package io.spine.quickstart.task;

import io.spine.quickstart.CreateTask;
import io.spine.quickstart.Task;
import io.spine.quickstart.TaskCreated;
import io.spine.quickstart.TaskId;
import io.spine.quickstart.TaskVBuilder;
import io.spine.server.aggregate.Aggregate;
import io.spine.server.aggregate.Apply;
import io.spine.server.command.Assign;

/**
 * Definition of the {@code Task} aggregate.
 *
 * <p>Within this small example it only handles a single command and emits one event.
 */
public final class TaskAggregate extends Aggregate<TaskId, Task, TaskVBuilder> {

    TaskAggregate(TaskId id) {
        super(id);
    }

    @Assign
    TaskCreated handle(CreateTask cmd) {
        TaskCreated result = TaskCreated
                .vBuilder()
                .setTitle(cmd.getTitle())
                .setId(cmd.getId())
                .build();
        return result;
    }

    @Apply
    private void on(TaskCreated event) {
        builder().setId(event.getId())
                 .setTitle(event.getTitle());
    }
}
