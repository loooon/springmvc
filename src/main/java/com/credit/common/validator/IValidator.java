package com.credit.common.validator;

import javax.servlet.http.HttpServletRequest;

/**
 * validator.IValidator：验证器接口，设计思路是结果不是预期的结果，直接返回异常
 *
 * @author huawei
 * @version <版本信息>
 * @see <参见的内容>
 */
public interface IValidator
{
    void setParent(IValidator validator);

    boolean hasNext();

    boolean hasParent();

    IValidator getNext();

    IValidator getParent();

    /**
     * doValidate：验证方法
     * 
     * @param object
     * @return
     * @throws ValidateException
     * @author huawei
     * @see <参见的内容>
     */
    boolean doValidate(Object object) throws ValidateException;

    /**
     * setValidateMessage：出现异常的信息
     * 
     * @param validateMessage
     * @author huawei
     * @see <参见的内容>
     */
    void setValidateMessage(String validateMessage);

    /**
     * setErrorPath：设置验证不通过的异常的跳转流程
     * 
     * @param path
     * @author huawei
     * @see <参见的内容>
     */
    void setErrorPath(String path);

    String getErrorPath();

    void setHttpRequest(HttpServletRequest request);
}
