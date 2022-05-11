package io.quarkus.scheduler.kotlin.runtime

import io.quarkus.arc.Unremovable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import jakarta.annotation.PreDestroy
import jakarta.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class ApplicationCoroutineScope : CoroutineScope, AutoCloseable {
    override val coroutineContext: CoroutineContext = SupervisorJob()

    @PreDestroy
    override fun close() {
        coroutineContext.cancel()
    }
}
