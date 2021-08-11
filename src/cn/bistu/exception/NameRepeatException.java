package cn.bistu.exception;

/**
 * 姓名重复自定义异常类
 */
public class NameRepeatException extends Exception{
    public NameRepeatException(String message) {
        super(message);
    }
}
