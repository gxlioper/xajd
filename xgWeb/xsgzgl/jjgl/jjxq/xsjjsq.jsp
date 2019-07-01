<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
</head>
<body style="width: 100%">
<html:form action="/jjgl_jjxq" method="post" styleId="jjxqForm" onsubmit="return false;">
    <input type="hidden" name="xqid" value="${xqwhMap.xqid}"/>
    <input type="hidden" name="jjcz" value="1"/>
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
                <th width="18%">�ҽ̱��</th>
                <td width="82%" colspan="3">
                        ${xqwhMap.xqid}
                </td>
            </tr>
            <tr>
                <th width="18%">
                    �Ǽ���
                </th>
                <td width="32%">
                        ${xqwhMap.jzxm}
                </td>
                <th width="18%">
                    �ҳ�����
                </th>
                <td width="32%" style="display: none">
                        ${xqwhMap.jzxm}
                </td>
            </tr>
            <tr>
                <th width="18%">
                    ��Ů����
                </th>
                <td width="32%">
                        ${xqwhMap.znxm}
                </td>
                <th width="18%">
                    ��Ů�Ա�
                </th>
                <td width="32%">
                        ${xqwhMap.znxb}
                </td>
            </tr>

            <tr>
                <th width="18%">
                    �貹��Ŀ
                </th>
                <td width="32%">
                        ${xqwhMap.jjxk}
                </td>
                <th width="18%">
                    ��Ů�꼶
                </th>
                <td width="32%">
                        ${xqwhMap.jjnj}
                </td>
            </tr>

            <tr>
                <th width="18%">
                    �ҽ̵ص�
                </th>
                <td width="32%">
                        ${xqwhMap.jjdd}
                </td>
                <th width="18%">
                    ���ʱн
                </th>
                <td width="32%">
                        ${xqwhMap.jjsx}
                </td>
            </tr>

            <tr>
                <th width="18%">
                    ��ϵ�绰
                </th>
                <td width="32%" colspan="3" style="display: none">
                        ${xqwhMap.lxdh}
                </td>
            </tr>

            <tr>
                <th width="18%">
                    �ҽ�Ҫ��
                </th>
                <td colspan="3" width="82%">
                        ${xqwhMap.jjlsyq}
                </td>

            </tr>
            <tr>
                <th width="18%">
                    ��ע
                </th>
                <td colspan="3" width="82%">
                        ${xqwhMap.bz}
                </td>

            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th>
                        �ҽ̲���
                    </th>
                    <td colspan="3">
                        �ɼҽ�
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>��������</th>
                    <td colspan="3">
                        <textarea id="sqly" name="sqly" rows="4" style="width:99%;"></textarea>
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
                        <button type="button" onclick="xssqSave();">
                            �ύ����
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

<script type="text/javascript">
    /**
     * ѧ���ҽ�����ı���
     * @returns {boolean}
     */
    function xssqSave(){
        if (jQuery("#sqly").val() == ""){
            showAlert("�뽫��������д������");
            return false;
        }
        var url = "jjgl_jjxq.do?method=xssq";
        ajaxSubFormWithFun("jjxqForm",url,function(data){
            if(data["message"]=="�ύ�ɹ���"){
                showAlert(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                }});
            }else{
                showAlert(data["message"]);
            }
        });
    }
</script>
</body>
</html>

