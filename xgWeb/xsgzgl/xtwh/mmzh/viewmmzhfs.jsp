<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/pagehead_V4.ini"%>
    <script type='text/javascript' src="js/comm/message.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xtwh/mmzh/js/mmzh.js"></script>
</head>
<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ��:</em><a>ϵͳά�� - ����ά�� - �ܱ������޸�</a>
    </p>
</div>

<html:form action="/mmzhgl_mmzh" method="post" styleId="MmZhForm" onsubmit="return false;">
    <div class="tab">
        <table width="100%"  border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4"><span>�����һط�ʽ</span></th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
                    <div class="btn">
                        <button type="button" name="�޸�" onclick="bc()">����</button>
                    </div></td>
            </tr>
            </tfoot>
            <tbody>
            <tr>
                <th >email<input value="email" type="hidden" name="email"/></th>
                <td >
                    <select name="email_qyzt">
                        <option value="1" <logic:equal value="1" name="email">selected="selectd"</logic:equal>>����</option>
                        <option value="0" <logic:equal value="0" name="email">selected="selectd"</logic:equal>>ͣ��</option>
                    </select>
                </td>
                <td>ͨ���ܱ����䣨ϵͳ�����������˻�Ԥ�����䷢����֤�룬�����Ԥ�������ѡ����</td>
            </tr>
            <tr>
                <th >phone<input value="phone" type="hidden" name="phone"/></th>
                <td >
                    <select name="phone_qyzt">
                        <option value="1" <logic:equal value="1" name="phone">selected="selectd"</logic:equal>>����</option>
                        <option value="0" <logic:equal value="0" name="phone">selected="selectd"</logic:equal>>ͣ��</option>
                    </select>
                </td>
                <td>ͨ���ܱ��ֻ���ϵͳ�����������˻�Ԥ���ֻ����뷢����֤�룬�����Ԥ���ֻ������ѡ����</td>
            </tr>
            <tr>
                <th >�ܱ�����<input value="mb" type="hidden" name="mb"/></th>
                <td >
                    <select name="mb_qyzt">
                        <option value="1" <logic:equal value="1" name="mb">selected="selectd"</logic:equal>>����</option>
                        <option value="0" <logic:equal value="0" name="mb">selected="selectd"</logic:equal>>ͣ��</option>
                    </select>
                </td>
                <td>ͨ���ܱ����⣨���ù��ܱ����⣩</td>
            </tr>
            </tbody>
        </table>
    </div>
</html:form>
</body>
</html>
