package com.xplatform.xplatformandroid;

import android.content.Context;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;

public class MainFlutterFragment extends FlutterFragment {
    ChannelMain channel;

    @Override
    public FlutterEngine provideFlutterEngine(@NotNull Context context) {
        return FlutterEngineCache.getInstance().get(FlutterBridging.CachedEngineFragmentId);
    }

    @Override
    public void configureFlutterEngine(@NotNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        if (channel == null) {
            channel = ChannelMain.registerChannel(
                    flutterEngine.getDartExecutor().getBinaryMessenger(),
                    (methodCall, result) -> {
                        Toast.makeText(getContext(), methodCall.arguments.toString(), Toast.LENGTH_LONG).show();
                        return null;
                    });
        }
    }
}
