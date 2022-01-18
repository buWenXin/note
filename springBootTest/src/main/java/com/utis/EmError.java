package com.utis;



/**
 * @author martin
 * @Name EmBusinessError
 * @Description 业务错误枚举
 */
public enum EmError implements CommomError {

    //基本类型
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败")




    ;

    private int code;
    private String msg;

    EmError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getErrCode() {
        return this.code;
    }

    @Override
    public String getErrMsg() {
        return this.msg;
    }

    @Override
    public CommomError setErrMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public EmError codeOf(int index){
        for(EmError state : values()){
            if(state.getErrCode() == index){
                return state;
            }
        }
        return null;
    }

}
