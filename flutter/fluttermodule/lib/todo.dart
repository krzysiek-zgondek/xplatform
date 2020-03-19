class Todo {
  final String title;
  final String description;

  Todo(this.title, this.description);

  Todo.fromJson(Map<String, dynamic> json):
      title = json['title'],
      description = json['description'];
}
