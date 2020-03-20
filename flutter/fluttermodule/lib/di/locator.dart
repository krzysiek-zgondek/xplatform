import 'package:get_it/get_it.dart';

import '../channel/mainchannel.dart';
import '../service/navigation.dart';

class Locator {
  var getIt = GetIt.instance;

  void setup() {
    getIt.registerLazySingleton(() => NavigationService());
    getIt.registerSingleton(MainChannel());
  }

  T get<T>(){
    return getIt.get<T>();
  }

  T call<T>(){
    return get();
  }
}

final locator = Locator();


