package com.xplatform.xplatformandroid.flutter;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.FragmentKt;

import com.xplatform.xplatformandroid.R;
import com.xplatform.xplatformandroid.dto.Todo;

import org.jetbrains.annotations.NotNull;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import kotlin.Unit;

import static com.xplatform.xplatformandroid.flutter.ChannelMain.Companion.Flutter;
import static com.xplatform.xplatformandroid.flutter.ChannelMain.registerChannel;

public class MainFlutterFragment extends FlutterFragment {
    public ChannelMain channel;

    @Override
    public void onStart() {
        super.onStart();
        callFlutterMethod();
    }

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

    private void callFlutterMethod() {
        Todo todo = new Todo(
                "Android Todo",
                "Received from Android"
        );

        channel.navigate("/details", todo);
    }

    /* This is unsafe as it can be called outside lifecycle but just for this example i will omit
     * all the lifecycle logic
     * */
    private Unit mainChannelReceiver(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
            case Flutter.resultMethod:
                executeNativeTodo(
                        Todo.fromJson(methodCall.arguments.toString())
                );
                break;

            default:
                throw new IllegalArgumentException();
        }

        Toast.makeText(getContext(), methodCall.arguments.toString(), Toast.LENGTH_LONG).show();
        return null;
    }

    /*You should never call host like this, but for the sake of simplicity lets accept that*/
    private void executeNativeTodo(Todo todo) {
        Bundle bundle = new Bundle();
        bundle.putString("title", todo.getTitle());
        bundle.putString("description", todo.getDescription());

        Fragment fragment = (Fragment) ((Object) this);
        FragmentKt.findNavController(fragment).navigate(R.id.action_FlutterFragment_to_SecondFragment, bundle);
    }
}
