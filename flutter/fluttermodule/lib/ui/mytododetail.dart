import 'package:flutter/material.dart';
import '../channel/mainchannel.dart';
import '../di/locator.dart';
import '../model/todo.dart';

class MyTodoDetails extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final Todo todo = ModalRoute.of(context).settings.arguments;

    // Use the Todo to create the UI.
    return Scaffold(
      appBar: AppBar(
        title: Text(todo.title),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            Text(todo.description),
            FlatButton(
              onPressed: () {
                locator<MainChannel>()
                    .nativeCall(Todo("Flutter Todo", "Received from Flutter"));
              },
              child: Text("invoke android call"),
            )
          ],
        ),
      ),
    );
  }
}
