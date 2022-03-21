

## 1.定义响应状态码

```java
@AllArgsConstructor
@Getter
public enum ApiStatusEnum {
    success(200,"操作成功"),
    fail(502,"操作失败");
    private final Integer code;
    private final String desc;
}
```

## 2.定义统一响应结果类

```java
@Data
@AllArgsConstructor
public class ApiResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <O> ApiResult<O> success() {
        return new ApiResult<>(ApiStatusEnum.success.getCode(), ApiStatusEnum.success.getDesc(), null);
    }

    public static <O> ApiResult<O> success(O data) {
        return new ApiResult<>(ApiStatusEnum.success.getCode(), ApiStatusEnum.success.getDesc(), data);
    }

    public static <O> ApiResult<O> fail() {
        return new ApiResult<>(ApiStatusEnum.fail.getCode(), ApiStatusEnum.fail.getDesc(), null);
    }

    public static <O> ApiResult<O> fail(Integer code, String desc) {
        return new ApiResult<>(code, desc, null);
    }

    public static <O> ApiResult<O> fail(String desc) {
        return new ApiResult<>(ApiStatusEnum.fail.getCode(), desc, null);
    }
}
```
