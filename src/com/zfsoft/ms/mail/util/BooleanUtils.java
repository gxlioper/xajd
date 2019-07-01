package com.zfsoft.ms.mail.util;

public abstract class BooleanUtils extends org.apache.commons.lang.BooleanUtils{

    public static boolean parse(String str){
        //������ֶ�Ϊ��ѡ��,����֤���ֶζ�Ӧ�����Ƿ�Ϊ��
        if(str!=null){
            if("yes".equalsIgnoreCase(str.trim())||"true".equalsIgnoreCase(str.trim())||"1".equalsIgnoreCase(str.trim())){
                return true;
            }else if("no".equalsIgnoreCase(str.trim())||"false".equalsIgnoreCase(str.trim())||"0".equalsIgnoreCase(str.trim())){
                return false;
            }else{
                throw new RuntimeException(str.trim() + " can't convert to a boolean value!");
            }
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(BooleanUtils.parse("0"));
    }

}
