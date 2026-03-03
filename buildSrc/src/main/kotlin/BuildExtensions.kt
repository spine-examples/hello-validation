import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec

const val REMOTE_DEBUG_PORT = 5566

/**
 * Sets the remote debug option for this [JavaExec] task.
 *
 * The port number is [5566][REMOTE_DEBUG_PORT].
 *
 * @param enabled If `true` the task will be suspended.
 */
fun JavaExec.remoteDebug(enabled: Boolean = true) {
    debugOptions {
        this@debugOptions.enabled.set(enabled)
        port.set(REMOTE_DEBUG_PORT)
        server.set(true)
        suspend.set(true)
    }
}

/**
 * Sets the remote debug option for the task of [JavaExec] type with the given name.
 *
 * The port number is [5566][REMOTE_DEBUG_PORT].
 *
 * @param enabled If `true` the task will be suspended.
 * @throws IllegalStateException if the task with the given name is not found, or,
 *  if the taks is not of [JavaExec] type.
 */
fun Project.setRemoteDebug(taskName: String, enabled: Boolean = true) {
    val task = tasks.findByName(taskName)
    check(task != null) {
        "Could not find a task named `$taskName` in the project `$name`."
    }
    check(task is JavaExec) {
        "The task `$taskName` is not of type `JavaExec`."
    }
    task.remoteDebug(enabled)
}

/**
 * Sets remote debug options for the `launchSpineCompiler` task.
 *
 * @param enabled if `true` the task will be suspended.
 *
 * @see remoteDebug
 */
fun Project.spineCompilerRemoteDebug(enabled: Boolean = true) =
    setRemoteDebug("launchSpineCompiler", enabled)

/**
 * Sets remote debug options for the `launchTestSpineCompiler` task.
 *
 * @param enabled if `true` the task will be suspended.
 *
 * @see remoteDebug
 */
fun Project.testSpineCompilerRemoteDebug(enabled: Boolean = true) =
    setRemoteDebug("launchTestSpineCompiler", enabled)
