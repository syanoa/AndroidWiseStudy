# Retrofit学习资料

---

>## create使用动态代理.
  在调用 `platform.isDefaultMethod(method)` 判断不是一般方法之后 `loadServiceMethod(method).invoke(args)`.

---

>## `loadServiceMethod` 里的逻辑
```kotlin
  ServiceMethod<?> loadServiceMethod(Method method) {
    ServiceMethod<?> result = serviceMethodCache.get(method);
    if (result != null) return result;

    synchronized (serviceMethodCache) {
      result = serviceMethodCache.get(method);
      if (result == null) {
        result = ServiceMethod.parseAnnotations(this, method);
        serviceMethodCache.put(method, result);
      }
    }
    return result;
  }
```
#### 要点：
 1. 从 `serviceMethodCache` 这个 `Map<Method, ServiceMethod<?>>` 的类型里通过`Method`取缓存过的`ServiceMethod`.
 2. 同步双重空判断机制处理可能发生的并发原子性问题.
 3. 如果缓存map里没有, 就调用 `ServiceMethod.parseAnnotations` 根据注解去解析出来，在返回结果之前存到缓存map里面.

 ---