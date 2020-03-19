import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor.DartEntrypoint

@Suppress("NOTHING_TO_INLINE")
inline fun Application.createCachedFlutterEngine(id: String) {
    FlutterEngine(this).apply {
        dartExecutor.executeDartEntrypoint(
            DartEntrypoint.createDefault()
        )

        FlutterEngineCache.getInstance().put(id, this)
    }
}