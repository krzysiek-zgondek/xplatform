//channels

import 'di/locator.dart';
import 'package:flutter/material.dart';
import 'ui/myhomepage.dart';
import 'ui/mytododetail.dart';
import 'service/navigation.dart';

void main() {
  //make sure that messenger is configured
  WidgetsFlutterBinding.ensureInitialized();

  //setup dep injection
  locator.setup();

  //start the app singleton
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      navigatorKey: locator<NavigationService>().navigatorKey,
      theme: ThemeData(
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
