package cn.com.yangzhenyu.brick.exception;

import java.sql.SQLException;

/**
 * 显示常见错误
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/4/18
 * Time: 13:57
 */
public class MySQLException extends SQLException {

    private int errorCode;
    private String errorMessage;

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MySQLException(int errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = getErrorMessage(errorCode);
    }

    public MySQLException(int errorCode,String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private String getErrorMessage(int errorCode){
        switch (errorCode) {
            case 1005:
                return "MYSQL创建表失败";
            case 1007:
                return "MYSQL数据库已存在，创建数据库失败";
            case 1008:
                return "MYSQL数据库不存在，删除数据库失败";
            case 1009:
                return "MYSQL不能删除数据库文件导致删除数据库失败";
            case 1010:
                return "MYSQL不能删除数据目录导致删除数据库失败";
            case 1011:
                return "MYSQL删除数据库文件失败";
            case 1012:
                return "MYSQL不能读取系统表中的记录";
            case 1016:
                return "文件无法打开，使用后台修复";
            case 1017:
                return "服务器非法关机，导致该文件损坏";
            case 1020:
                return "MYSQL记录已被其他用户修改";
            case 1021:
                return "硬盘剩余空间不足，请加大硬盘可用空间";
            case 1022:
                return "MYSQL关键字重复，更改记录失败";
            case 1023:
                return "MYSQL关闭时发生错误";
            case 1024:
                return "MYSQL读文件错误";
            case 1025:
                return "MYSQL更改名字时发生错误";
            case 1026:
                return "MYSQL写文件错误";
            case 1030:
                return "可能是服务器不稳定。（具体原因不是很清楚）";
            case 1032:
                return "MYSQL记录不存在";
            case 1036:
                return "MYSQL数据表是只读的，不能对它进行修改";
            case 1037:
                return "系统内存不足，请重启数据库或重启服务器";
            case 1038:
                return "MYSQL用于排序的内存不足，请增大排序缓冲区";
            case 1040:
                return "MYSQL已到达数据库的最大连接数，请加大数据库可用连接数";
            case 1041:
                return "系统内存不足";
            case 1042:
                return "无效的主机名";
            case 1043:
                return "无效连接";
            case 1044:
                return "MYSQL当前用户没有访问数据库的权限";
            case 1045:
                return "MYSQL不能连接数据库，服务器、数据库名、用户名或密码错误";
            case 1046:
                return "没有选择数据库";
            case 1048:
                return "MYSQL字段不能为空";
            case 1049:
                return "MYSQL数据库不存在";
            case 1050:
                return "MYSQL数据表已存在";
            case 1051:
                return "MYSQL数据表不存在";
            case 1054:
                return "MYSQL字段不存在，自行建立字段";
            case 1060:
                return "字段重复，导致无法插入这个字段";
            case 1062:
                return "字段值重复，入库失败";
            case 1064:
                return "MYSQL不支持错误提示中的编码";
            case 1065:
                return "MYSQL无效的SQL语句，SQL语句为空";
            case 1067:
                return "MYSQL版本为5，不支持空的默认值";
            case 1081:
                return "MYSQL不能建立Socket连接";
            case 1114:
                return "MYSQL数据表已满，不能容纳任何记录";
            case 1116:
                return "MYSQL打开的数据表太多";
            case 1129:
                return "MYSQL数据库出现异常，请重启数据库";
            case 1130:
                return "MYSQL连接数据库失败，没有连接数据库的权限";
            case 1133:
                return "MYSQL数据库用户不存在";
            case 1135:
                return "可能是内存不足够，请联系空间商解决";
            case 1141:
                return "MYSQL当前用户无权访问数据库";
            case 1142:
                return "MYSQL当前用户无权访问数据表";
            case 1143:
                return "MYSQL当前用户无权访问数据表中的字段";
            case 1146:
                return "MYSQL数据表不存在";
            case 1147:
                return "MYSQL未定义用户对数据表的访问权限";
            case 1149:
                return "MYSQL语句语法错误";
            case 1169:
                return "MYSQL字段值重复，更新记录失败";
            case 1177:
                return "MYSQL打开数据表失败";
            case 1180:
                return "MYSQL提交事务失败";
            case 1181:
                return "MYSQL回滚事务失败";
            case 1203:
                return "MYSQL当前用户和数据库建立的连接已到达数据库的最大连接数，请增大可用的数据库连接数或重启数据库";
            case 1205:
                return "MYSQL加锁超时";
            case 1211:
                return "MYSQL当前用户没有创建用户的权限";
            case 1216:
                return "MYSQL外键约束检查失败，更新子表记录失败";
            case 1217:
                return "MYSQL外键约束检查失败，删除或修改主表记录失败";
            case 1226:
                return "MYSQL当前用户使用的资源已超过所允许的资源，请重启数据库或重启服务器";
            case 1227:
                return "MYSQL权限不足，您无权进行此操作";
            case 1235:
                return "MYSQL版本过低，不具有本功能";
            case 1250:
                return "客户端不支持服务器要求的认证协议，请考虑升级客户端";
            case 1251:
                return "Client 不能支持 authentication protocol 的要求";
            case 1267:
                return "不合法的混合字符集";
            case 2002:
                return "服务器端口不对，请咨询空间商正确的端口";
            case 2003:
                return "MYSQL服务没有启动，请启动该服务";
            case 2013:
                return "远程连接数据库是有时会有这个问题，MySQL 服务器在执行一条 SQL 语句的时候失去了连接造成的";
            default:
                return "数据库未知错误";
        }
    }
}
