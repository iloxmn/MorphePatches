package app.template.patches.duolingo

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.patch

@Suppress("unused")
val sessionEndPatch = patch(
    name = "Skip Session-End Screens",
    description = "Pass an empty session-end screen list to Duolingo.",
) {
    execute {
        val callIndex = SessionEndFingerprint.instructionMatches.single().index
        SessionEndFingerprint.method.addInstructions(
            callIndex,
            """
                invoke-static {}, Ljava/util/Collections;->emptyList()Ljava/util/List;
                move-result-object v1
            """,
        )
    }
}
