/*
 * Created on 2006-3-18
 * 
 * Author xuji
 * 
 * Copyright (C) 2005, HC SOFTWARE.
 */
package com.credit.common.util.file;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Zip相关函数
 * 
 * @author xuji
 * @version 0.9
 * @since 0.9
 */
public class ZipUtil
{
    public ZipUtil()
    {
    }

    /**
     * 压缩文件
     * 
     * @param srcFiles
     *            : 要被压缩的文件名
     * @param dstDir
     *            : 压缩后的文件名
     * @param comment
     *            : 文件注释
     */

    public static void zip(String[] srcFiles, String dstFile, String comment) throws IOException
    {
        if (srcFiles.length == 0)
        {
            return;
        }
        File fileDst = new File(dstFile);
        File parentDir = fileDst.getParentFile();
        if (!parentDir.exists())
        {
            parentDir.mkdirs();
        }
        if (fileDst.exists())
        {
            fileDst.delete();
        }
        fileDst.createNewFile();

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileDst));
        zos.setMethod(ZipOutputStream.DEFLATED);

        if (comment != null)
        {
            zos.setComment(comment);
        }

        DataOutputStream dos = new DataOutputStream(zos);

        for (int i = 0; i < srcFiles.length; i++)
        {
            String entryPath = srcFiles[i];
            zos.putNextEntry(new ZipEntry(entryPath));

            File fileEntry = new File(entryPath);
            FileInputStream fis = new FileInputStream(fileEntry);

            byte[] buff = new byte[8192];
            int len = 0;

            while (true)
            {
                len = fis.read(buff);
                if (len == -1 || len == 0)
                {
                    break;
                }

                dos.write(buff, 0, len);
            }

            zos.closeEntry();
            fis.close();
        }
        dos.close();
        zos.close();
    }

    public static void zip(String[] srcFiles, String dstFile) throws IOException
    {
        zip(srcFiles, dstFile, null);
    }

    /**
     * 解压缩文件
     * 
     * @param srcFile
     *            : 压缩文件名
     * @param dstDir
     *            : 要解压到的目录
     * @param entryName
     *            : 要解压哪个文件，如果为null则解压所有文件。
     * @param retainPath
     *            : 是否保留文件路径信息，true-保留，false-不保留。
     */
    public static void unzip(String srcFile, String dstDir, String entryName, boolean retainPath) throws Exception
    {

        ZipFile zipFile = null;

        try
        {
            zipFile = new ZipFile(srcFile);
            Enumeration entryEnu = zipFile.entries();
            while (entryEnu.hasMoreElements())
            {
                File fileItem = null;
                ZipEntry entry = (ZipEntry) entryEnu.nextElement();

                String name = entry.getName();
                if (entryName != null && !entryName.equalsIgnoreCase(name))
                    continue;
                if (dstDir != null)
                {
                    if (!retainPath)
                    {
                        File f = new File(name);
                        name = f.getName();
                    }
                    else
                    {
                        if (System.getProperty("os.name").indexOf("Windows") != -1)
                        {

                            if (name.matches("^[a-zA-Z]:.*"))
                            {
                                // 如果是WINDOWS系统，将文件名中的驱动器符号去掉
                                name = name.substring(name.indexOf('\\') + 1);
                            }
                        }
                    }
                    fileItem = new File(dstDir + File.separator + name);
                }
                else
                {
                    fileItem = new File(name);
                }

                File parentDir = fileItem.getParentFile();
                if (!parentDir.exists())
                {
                    parentDir.mkdirs();
                }
                if (fileItem.exists())
                    fileItem.delete();
                fileItem.createNewFile();
                FileOutputStream fos = null;
                InputStream is = null;

                try
                {
                    fos = new FileOutputStream(fileItem);
                    is = zipFile.getInputStream(entry);

                    byte[] buff = new byte[8192];
                    int len = 0;
                    while ((len = is.read(buff)) != -1)
                    {
                        fos.write(buff, 0, len);
                    }
                }
                finally
                {
                    try
                    {
                        fos.flush();
                        fos.close();
                        is.close();
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new Exception("解压缩失败：" + e.getMessage());

        }
        finally
        {
            try
            {
                zipFile.close();
            }
            catch (Exception e)
            {
            }
        }

    }

    /**
     * 将文件解压缩到指定的目录，保持被压缩文件的路径信息。
     */
    public static void unzip(String zipFile, String dstDir) throws Exception
    {
        unzip(zipFile, dstDir, null, true);
    }

    /**
     * 解压缩文件，覆盖被压缩的文件。
     */
    public static void unzip(String zipFile) throws Exception
    {
        unzip(zipFile, null, null, true);
    }

}
