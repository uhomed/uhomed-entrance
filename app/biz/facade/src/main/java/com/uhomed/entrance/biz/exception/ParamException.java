package com.uhomed.entrance.biz.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
public class ParamException extends RuntimeException{

    /**  */
    private static final long serialVersionUID = -3699304942160244103L;

    private String message  = null;
    //容纳所有异常
    private List<Throwable> causes = new ArrayList<>();
    //构造函数，传递一个异常列表
    public ParamException(List<? extends Throwable> _causes,String message){
        causes.addAll(_causes);
        this.message = message;
    }
    public ParamException(String message){
        this.message = message;
    }


    //读取所有的异常
    public List<Throwable> getException(){
        return causes;
    }
    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }
    /**
     * Setter method for property <tt>message</tt>.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Getter method for property <tt>causes</tt>.
     *
     * @return property value of causes
     */
    public List<Throwable> getCauses() {
        return causes;
    }
    /**
     * Setter method for property <tt>causes</tt>.
     *
     * @param causes value to be assigned to property causes
     */
    public void setCauses(List<Throwable> causes) {
        this.causes = causes;
    }

}