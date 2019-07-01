/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-24 ����02:56:32 
 */  
package com.zfsoft.xgxt.comm.attachupload.utils;

import org.apache.commons.io.FileUtils; 
import org.apache.commons.io.filefilter.*; 
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;


import java.io.*;

/** 
* �ļ������� 
*	1036
*/ 
public final class FileToolkit { 
        private static final Log log = LogFactory.getLog(FileToolkit.class);

        /** 
         * �����ļ�����Ŀ¼,����ǰ���ļ���ȫһ���� 
         * 
         * @param resFilePath Դ�ļ�·�� 
         * @param distFolder    Ŀ���ļ��� 
         * @IOException �����������쳣ʱ�׳� 
         */ 
        public static void copyFile(String resFilePath, String distFolder) throws IOException { 
                File resFile = new File(resFilePath); 
                File distFile = new File(distFolder); 
                if (resFile.isDirectory()) { 
                        FileUtils.copyDirectoryToDirectory(resFile, distFile); 
                } else if (resFile.isFile()) { 
                        FileUtils.copyFileToDirectory(resFile, distFile, true); 
                } 
        }

        /**
         * 
         * �����ļ�
         * @param resFile
         * @param distFloder
         * @throws IOException
         * void �������� 
         * @throws
         */
        public static void copyFile(File resFile , String distFloder) throws IOException {
        	
        	String fileName = resFile.getName();
        	
        }
        
        /** 
         * ɾ��һ���ļ�����Ŀ¼ 
         * 
         * @param targetPath �ļ�����Ŀ¼·�� 
         * @IOException �����������쳣ʱ�׳� 
         */ 
        public static void deleteFile(String targetPath) throws IOException { 
                File targetFile = new File(targetPath); 
                if (targetFile.isDirectory()) { 
                        FileUtils.deleteDirectory(targetFile); 
                } else if (targetFile.isFile()) { 
                        targetFile.delete(); 
                } 
        }
        
        
        /** 
         * �������ļ����ļ��� 
         * 
         * @param resFilePath Դ�ļ�·�� 
         * @param newFileName ������ 
         * @return �����ɹ���ʶ 
         */ 
        public static boolean renameFile(String resFilePath, String newFileName) { 
                String newFilePath = StringToolkit.formatPath(StringToolkit.getParentPath(resFilePath) + "/" + newFileName); 
                File resFile = new File(resFilePath); 
                File newFile = new File(newFilePath); 
                return resFile.renameTo(newFile); 
        }

        /** 
         * ��ȡ�ļ�����Ŀ¼�Ĵ�С 
         * 
         * @param distFilePath Ŀ���ļ������ļ��� 
         * @return �ļ�����Ŀ¼�Ĵ�С�������ȡʧ�ܣ��򷵻�-1 
         */ 
        public static long getFileSize(String distFilePath) { 
                File distFile = new File(distFilePath); 
                if (distFile.isFile()) { 
                        return distFile.length(); 
                } else if (distFile.isDirectory()) { 
                        return FileUtils.sizeOfDirectory(distFile); 
                } 
                return -1L; 
        }

        /** 
         * �ж�һ���ļ��Ƿ���� 
         * 
         * @param filePath �ļ�·�� 
         * @return ���ڷ���true�����򷵻�false 
         */ 
        public static boolean isExist(String filePath) { 
                return new File(filePath).exists(); 
        }

        /** 
         * ����ĳ��Ŀ¼�µ��ļ��б����ݹ飩 
         * 
         * @param folder ftp�ϵ�ĳ��Ŀ¼ 
         * @param suffix �ļ��ĺ�׺��������.mov.xml) 
         * @return �ļ������б� 
         */ 
        public static String[] listFilebySuffix(String folder, String suffix) { 
                IOFileFilter fileFilter1 = new SuffixFileFilter(suffix); 
                IOFileFilter fileFilter2 = new NotFileFilter(DirectoryFileFilter.INSTANCE); 
                FilenameFilter filenameFilter = new AndFileFilter(fileFilter1, fileFilter2); 
                return new File(folder).list(filenameFilter); 
        }

        /** 
         * ���ַ���д��ָ���ļ�(��ָ���ĸ�·�����ļ��в�����ʱ��������޶�ȥ�������Ա�֤����ɹ���) 
         * 
         * @param res            ԭ�ַ��� 
         * @param filePath �ļ�·�� 
         * @return �ɹ���� 
         */ 
        public static boolean string2File(String res, String filePath) { 
                boolean flag = true; 
                BufferedReader bufferedReader = null; 
                BufferedWriter bufferedWriter = null; 
                try { 
                        File distFile = new File(filePath); 
                        if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs(); 
                        bufferedReader = new BufferedReader(new StringReader(res)); 
                        bufferedWriter = new BufferedWriter(new FileWriter(distFile)); 
                        char buf[] = new char[1024];         //�ַ������� 
                        int len; 
                        while ((len = bufferedReader.read(buf)) != -1) { 
                                bufferedWriter.write(buf, 0, len); 
                        } 
                        bufferedWriter.flush(); 
                        bufferedReader.close(); 
                        bufferedWriter.close(); 
                } catch (IOException e) { 
                        flag = false; 
                        e.printStackTrace(); 
                } 
                return flag; 
        } 

} 
