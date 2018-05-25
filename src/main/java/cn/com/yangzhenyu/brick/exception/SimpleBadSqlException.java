package cn.com.yangzhenyu.brick.exception;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.lang.Nullable;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/18
 * Time: 10:07
 */
public class SimpleBadSqlException extends BadSqlGrammarException {

    private int errorCode;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SimpleBadSqlException(String task, String sql, SQLException ex) {
        super(task, sql, ex);
        this.errorCode = ex.getErrorCode();
        this.message = ex.getMessage();
    }

}
