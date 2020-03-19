//channels
import 'dart:convert';

import 'package:flutter/services.dart';

import 'package:flutter/material.dart';
import 'package:fluttermodule/todo.dart';
import 'myhomepage.dart';
import 'mytododetail.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  static const MethodChannel _channel =
      const MethodChannel('com.xplatform.flutter/app');

  Future<dynamic> _platformCallHandler(
      BuildContext context, MethodCall call) async {
    switch (call.method) {
      case 'navigate':
        print('flt: received ${call.method} : arguments = ${call.arguments}');

        var todo = Todo.fromJson(jsonDecode(call.arguments));
        Navigator.pushNamed(context, '/details', arguments: todo);
        print('pushed navigator');

        _channel.invokeMethod("result", "${call.arguments}");
        print('invoked channel');
        return Future.value('called from platform!');
      default:
        print('Unknowm method ${call.method}');
        throw MissingPluginException();
        break;
    }
  }



  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    _channel.setMethodCallHandler((call) {
      return _platformCallHandler(context, call);
    });

    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or press Run > Flutter Hot Reload in a Flutter IDE). Notice that the
        // counter didn't reset back to zero; the application is not restarted.
        primarySwatch: Colors.blue,
      ),
      initialRoute: "/",
      routes: {
        "/": (context) => MyHomePage(title: 'Flutter Demo Home Page'),
        "/details": (context) => MyTodoDetails(),
      },
    );
  }
}
