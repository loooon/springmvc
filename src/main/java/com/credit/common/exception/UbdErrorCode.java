package com.credit.common.exception;

public class UbdErrorCode extends ErrorCode
{
    /**
     * 用户相关错误
     */
    public static final int USER_REGISTER_ERROR = 50000;                /**用户错误：注册错误(包括验证用户输入参数错误)*/
    public static final int USER_INVALID_USER = 50001;                  /**用户错误：用户不存在*/
    public static final int USER_EXISTED = 50002;                       /**用户错误：用户已存在*/
    public static final int USER_INVALID_PASSWORD = 50003;              /**用户错误：密码错误*/
    public static final int USER_INVALID_VERIFY_CODE = 50004;           /**用户错误：验证码错误*/
    public static final int USER_INVALID_QUESTION = 50005;              /**用户错误：问题错误*/
    public static final int USER_INVALID_ANSWER = 50006;                /**用户错误：问题的答案错误*/
    public static final int USER_NO_LOGIN = 50007;                      /**用户错误：用户没有登录*/
    public static final int USER_LOCKED = 50008;                        /**用户错误：用户已被锁定*/
    public static final int USER_NEED_REALAUTH = 50009;                 /**用户错误：用户需要实名认证*/
    public static final int USER_NEED_EMAILACTIVED = 50010;             /**用户错误：用户需要EMAIL激活*/
    public static final int USER_QUESTION = 50011;                      /**用户错误：用户选择的问题错误*/
    public static final int USER_ANSWER = 50013;                        /**用户错误：用户用户回答的问题错误*/
    public static final int USER_NEED_MOBILEACTIVED = 50015;            /**用户错误：用户需要手机激活*/
    public static final int USER_INVALID_CODE_NULL = 50016;             /**用户错误：验证码为null*/
    public static final int USER_INVALID_EMAIL = 50017;                 /**用户错误：邮箱错误*/
    public static final int USER_INVALID_MOBILE = 50018;                /**用户错误：手机错误*/
    public static final int USER_MOBILE_SAFETY_CODE = 50019;            /**用户错误：用户手机安全码错误*/
    public static final int USER_EMAIL_EXISTED = 50024;                 /**用户错误：邮箱已存在*/
    public static final int USER_INVALID_ORGCODE = 50025;                 /**用户错误：推荐码错误*/
    
    public static final int REQUEST_VERIFY_IMG_TIMEOUT = 50020;         /**ICP查询错误：请求验证码图片超时*/
    public static final int VERIFY_IMG_ERROR = 50021;                   /**ICP查询错误：解析验证码图片错误*/
    public static final int CONNECT_TIMEOUT = 50022;                    /**ICP查询错误：请求页面超时*/
    public static final int ANALYSIS_HTML_ERROR = 50023;                /**ICP查询错误：解析HTML页面错误*/
    
    /**
     * 网站所有者相关错误
     */
    public static final int OWNER_ADD_ERROR = 60000;              /**网站所有者错误：添加机构失败*/
    public static final int OWNER_ORGNAME_EXISTS = 60001;              /**网站所有者错误：机构名称已存在*/
    
    /**
     * 邮件发送相关错误定义
     */
    public static final int SEND_MAIL_INIT_ERROR        = 20001;                /**初始化错误*/
    public static final int SEND_MAIL_SEND_ERROR        = 20002;                /**邮件发送错误*/
    public static final int SEND_MAIL_INVALID_HOST      = 20003;                /**服务器错误*/
    public static final int SEND_MAIL_INVALID_SENDER    = 20004;                /**发送者错误*/
    public static final int SEND_MAIL_INVALID_RECEIVER  = 20005;                /**接收者错误*/
    public static final int SEND_MAIL_INVALID_PASS      = 20006;                /**密码错误*/
    
    
    /**
     * 站点验证相关错误定义
     */
    public static final int VERIFY_SITE_ERROR           = 30001;                /**验证错误*/
    public static final int VERIFY_SITE_CONN_ERROR      = 30002;                /**连接错误*/
    public static final int VERIFY_SITE_HTML_ERROR      = 30003;                /**页面错误*/
    public static final int VERIFY_SITE_EXISTED_ERROR   = 30004;                /**网站已存在*/
    
    public static final int MD5_ENCODED_ERROR   = 17;                            /**md5信息摘要错误*/
    
    //放心购物，电子收据相关错误定义 
    public static final int WEBSITE_EXIST_ERROR = 400001;  //网站已收录
}
