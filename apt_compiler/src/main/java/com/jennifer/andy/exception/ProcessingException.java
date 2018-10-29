package com.jennifer.andy.exception;

import javax.lang.model.element.Element;

/**
 * Author:  andy.xwt
 * Date:    2018/10/29 11:30
 * Description:处理器自定义异常
 */

public class ProcessingException extends Exception {

    Element element;

    public ProcessingException(Element element, String msg, Object... args) {
        super(String.format(msg, args));
        this.element = element;
    }

    public Element getElement() {
        return element;
    }
}
