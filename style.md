# style

## Must

1. 文件编码 `UTF-8`
2. 换行符 `LF`
3. 用于数据交换的类，包括`PO`,`DTO`,`VO`等
    1. 如果业务上上不可变，属性定义为final 使用`@AllArgsConstrcutor/@RequiredArgsConstructor/@Getter`
       注解或者使用`record`类型
    2. 如果可变，使用`@Getter/@Setter`注解

## recommends

1. 模型尽量提供静态构造方法，写在模型中，收拢所有new的操作
2. 字段定义时，在业务中不会为`null`
   的场景，尽量定义为基础类型而不是包状类；对于非内置方法，需要使用`@org.springframework.lang.NonNull`
   标识，且在构造器、Setter、静态实例化方法中做检查

## Lombok

1. 建议使用`@Getter`,`@Setter`
2. 谨慎使用`@Data`,`@Value`,`@AllArgsConstrcutor`, `@NoArgsConstructor`
3. 尽量避免`@ToString`, `@EqualsAndHashcode`