# ebean-issue

This project illustrate an issue with the ebean query bean generator. Issue as of `ebean` version `13.22.1` can be reproduced by running `mvn compile` on this example project.

When generating the query bean for entities that use `@Embeddable` inner class for primary keys (e.g. `embedded.example.OrderItem` with embeddable primary key class `embedded.example.OrderItem.Key`), it generates the following non-compilable code.
 * A `Q<ENTITY_NAME>$<EMBEDDABLE_CLASS_NAME>` class is generated for the embeddable primary key class but this class is not referenced by any of the generated code (e.g. `QOrderItem$Key.java` is generated under the package path `query` for class `Key`).
 * The generated `query.Q<ENTITY_NAME>` class references a non-existent `Q<EMBEDDABLE_CLASS_NAME>` class without an import clause possibly expecting it to be in the same package (e.g. `query.QOrderItem` import and refer to the class `embedded.example.OrderItem.query.assoc.QAssocKey`)

This is possibly just a naming issue when generating class name for the inner class since the `$` notation in the unused generated file name is how java refers to inner classes in `Class.getName()` and `Class.getTypeName()`. For reference,
```
  embedded.example.OrderItem.Key.class.
    getName():                 "embedded.example.OrderItem$Key"
    getTypeName():             "embedded.example.OrderItem$Key"
    getCannonicalName():       "embedded.example.OrderItem.Key"
    getSimpleName():           "Key"
```
