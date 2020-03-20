import 'dart:convert';

import 'package:flutter/services.dart';
import '../di/locator.dart';
import '../model/todo.dart';

import '../service/navigation.dart';

class MainChannel {
  static const MethodChannel _channel =
      const MethodChannel('com.xplatform.flutter/app');

  MainChannel() {
    _channel.setMethodCallHandler(_platformCallHandler);
  }

  void nativeCall(Todo todo) {
    var json = jsonEncode(todo);
    _channel.invokeMethod("result", json);
    print('invoked channel with $json');
  }

  Future<dynamic> _platformCallHandler(MethodCall call) async {
    switch (call.method) {
      case 'navigate':
        print('flt: received ${call.method} : arguments = ${call.arguments}');

        var todo = Todo.fromJson(jsonDecode(call.arguments));

        var nav = locator<NavigationService>();
        nav.navigateTo('/details', arguments: todo);
        print('pushed navigator');

        return Future.value('called from platform!');
      default:
        print('Unknowm method ${call.method}');
        throw MissingPluginException();
        break;
    }
  }
}
