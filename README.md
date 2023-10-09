# ebean-issue

This project illustrate an issue with the ebean query bean generator. Issue can be reproduced by running `mvn compile` on this example project.

When generating the query bean for entities that use `@Embeddable` inner class for primary keys (e.g. `embedded.example.OrderItem` with embeddable primary key class `embedded.example.OrderItem.Key`), it generates the following non-compilable code.
 * A `QAssoc<ENTITY_NAME>$<EMBEDDABLE_CLASS_NAME>` (e.g. `QAssocOrderItem$Key.java` is generated for `Key` under the package path `query.assoc` ) class is generated for the embeddable primary key class but this class is not referenced by any of the generated code.
 * `Q<ENTITY_NAME>` and `QAssoc<ENTITY_NAME>` classes reference a non-existent `QAssoc<EMBEDDABLE_CLASS_NAME>` type with the import clause `import <PACKAGE_PATH_TO_ENTITY_CLASS>.<ENTITY_CLASS>.query.assoc.QAssoc<EMBEDDABLE_CLASS_NAME>` (e.g. `QOrderItem` and `QAssocOrderItem` imports and refer to the class `embedded.example.OrderItem.query.assoc.QAssocKey`)
