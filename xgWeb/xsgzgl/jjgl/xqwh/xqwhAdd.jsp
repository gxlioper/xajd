<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/jjgl/xqwh/js/xqwh.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
</head>
<body style="width: 100%">
<html:form action="/jjgl_xqwhgl" method="post" styleId="xqwhForm" onsubmit="return false;">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�ҽ���Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="18%">
                    <font class="red">*</font>�ҳ�����
                </th>
                <td width="32%">
                    <html:hidden property="sqr" styleId="sqr" ></html:hidden>
                    <input id="jzxm" type="jzxm"/><button class="btn_01" type="button" onclick="showJzxxList()">ѡ��</button>
                </td>
                <th width="18%">
                    �Ǽ���
                </th>
                <td width="32%">
                    <input type="hidden" name="djr" value="${djr}"/>
                    ${djr}
                </td>
            </tr>
            <tr>
                <th width="18%">
                    <font color="red">*</font>��Ů����
                </th>
                <td width="32%">
                    <select name="znid" id="znid" style="width:173px" onchange="changeZnxx()">
                    </select>
                </td>
                <th width="18%">
                    ��Ů�Ա�
                </th>
                <td width="32%">
                    <span id="znxb"></span>
                </td>
            </tr>

            <tr>
                <th width="18%">
                    <font color="red">*</font>�貹��Ŀ
                </th>
                <td width="32%">
                    <html:text property="jjxk" styleId="jjxk" />
                </td>
                <th width="18%">
                    <font color="red">*</font>��Ů�꼶
                </th>
                <td width="32%">
                    <html:text property="jjnj" styleId="jjnj" />
                </td>
            </tr>

            <tr>
                <th width="18%">
                    <font color="red">*</font>�ҽ̵ص�
                </th>
                <td width="32%">
                    <html:text property="jjdd" styleId="jjdd" ></html:text>
                </td>
                <th width="18%">
                    <font color="red">*</font>���ʱн
                </th>
                <td width="32%">
                    <html:text property="jjsx" styleId="jjsx" onkeyup="checkMoneyForKeyup(this)" onblur="checkMoneyForBlur(this)"></html:text> Ԫ/ʱ
                </td>
            </tr>

            <tr>
                <th width="18%" >
                    ��ϵ�绰
                </th>
                <td width="32%" colspan="3">
                    <span id="lxdh"></span>
                </td>
            </tr>
            <tr>
                <th width="18%">
                    <font color="red">*</font>�ҽ�Ҫ��<br/>
                    <font color="red">��������500���ڣ�</font>
                </th>
                <td colspan="3" width="82%">
                    <html:textarea property="jjlsyq" styleId="jjlsyq"
                                   onkeypress="checkLen(this,500);"
                                   style="width:99%;" rows="4"></html:textarea>
                </td>

            </tr>
            <tr>
                <th width="18%">
                    ��ע<br/>
                    <font color="red">��������500���ڣ�</font>
                </th>
                <td colspan="3" width="82%">
                    <html:textarea property="bz" styleId="bz"
                                   onkeypress="checkLen(this,500);"
                                   style="width:99%;" rows="4"></html:textarea>
                </td>

            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save();">
                            ����
                        </button>
                        <button type="button" onclick="iFClose();">
                            �ر�
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</html:form>
</body>
</html>

