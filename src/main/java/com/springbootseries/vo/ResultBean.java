package com.springbootseries.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rendong on 11/12/17.
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ResultBean<T> implements Serializable {

    public enum Status {
        SUCCESS,
        ERROR
    }

    private Status status = Status.SUCCESS;

    private T data;

    private String msg;

    public ResultBean() {}

    public ResultBean(T data) {
        this.data = data;
        this.status = Status.SUCCESS;
    }


}
