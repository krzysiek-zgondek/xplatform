package com.xplatform.xplatformandroid.flutter;

import android.content.Context;
import android.widget.Toast;

import com.xplatform.xplatformandroid.dto.Todo;
import com.xplatform.xplatformandroid.ui.MainActivity;

import org.jetbrains.annotations.NotNull;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import kotlin.Unit;

import static com.xplatform.xplatformandroid.flutter.ChannelMain.Companion.*;
import static com.xplatform.xplatformandroid.flutter.ChannelMain.registerChannel;

public class MainFlutterFragment extends FlutterFragment {
    public ChannelMain channel;

    @Override
    public FlutterEngine provideFlutterEngine(@NotNull Context context) {
        return FlutterEngineCache.getInstance().get(FlutterBridging.CachedEngineFragmentId);
    }

    @Override
    public void configureFlutterEngine(@NotNull FlutterEngine flutterEngine) {
        /*this can be configured right away in application singleton and passed to ViewModel ie.*/
        super.configureFlutterEngine(flutterEngine);
        if (channel == null) {
            channel = registerChannel(
                    flutterEngine.getDartExecutor().getBinaryMessenger(),
                    this::mainChannelReceiver
            );
        }
    }

    /* This is unsafe as it can be called outside lifecycle but just for this example i will omit
     * all the lifecycle logic
     * */
    private Unit mainChannelReceiver(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
            case Flutter.resultMethod:
                executeNativeTodo(methodCall.arguments.toString());
                break;

            default:
                throw new IllegalArgumentException();
        }

        Toast.makeText(getContext(), methodCall.arguments.toString(), Toast.LENGTH_LONG).show();
        return null;
    }

    /*You should never call host like this, but for the sake of simplicity lets accept that*/
    private void executeNativeTodo(String json) {
        if (getActivity() instanceof MainActivity) {
            MainActivity activity = (MainActivity) getActivity();
            activity.showNativeTodoFragment(Todo.fromJson(json));
        }
    }
}
