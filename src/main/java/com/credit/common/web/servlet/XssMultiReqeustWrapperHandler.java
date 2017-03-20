package com.credit.common.web.servlet;//package utn.app.servlet;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItem;
//
//
//public class XssMultiReqeustWrapperHandler extends CommonsMultipartRequestHandler {
//	
//	@Override
//	protected void addTextParameter(HttpServletRequest request, FileItem item) {
//        String name = item.getFieldName();
//        String value = null;
//        boolean haveValue = false;
//        String encoding = null;
//
//        if (item instanceof DiskFileItem) {
//            encoding = ((DiskFileItem)item).getCharSet();
//            if (log.isDebugEnabled()) {
//                log.debug("DiskFileItem.getCharSet=[" + encoding + "]");
//            }
//        }
//
//        if (encoding == null) {
//            encoding = request.getCharacterEncoding();
//            if (log.isDebugEnabled()) {
//                log.debug("request.getCharacterEncoding=[" + encoding + "]");
//            }
//        }
//
//        if (encoding != null) {
//            try {
//                value = item.getString(encoding);
//                haveValue = true;
//            } catch (Exception e) {
//                // Handled below, since haveValue is false.
//            }
//        }
//
//        if (!haveValue) {
//            try {
//                value = item.getString("ISO-8859-1");
//            } catch (java.io.UnsupportedEncodingException uee) {
//                value = item.getString();
//            }
//
//            haveValue = true;
//        }
//        
//        if(null != value && !"".equals(value))
//        {
//        	value = XssUtil.xssEncode(value);
//        }
//
//        if (request instanceof MultipartRequestWrapper) {
//            MultipartRequestWrapper wrapper = (MultipartRequestWrapper) request;
//
//            wrapper.setParameter(name, value);
//        }
//
//        String[] oldArray = (String[]) getTextElements().get(name);
//        String[] newArray;
//
//        if (oldArray != null) {
//            newArray = new String[oldArray.length + 1];
//            System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
//            newArray[oldArray.length] = value;
//        } else {
//            newArray = new String[] { value };
//        }
//
//        getTextElements().put(name, newArray);
//        getAllElements().put(name, newArray);
//    }
//}
