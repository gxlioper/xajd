<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <title>�ҽ̰�ȫЭ����</title>
</head>

<body>
<form>
    <div class="tab" style="overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
        <input type="hidden" id="xqid" value="${jjxqForm.xqid}"/>
        <table width="100%" border="0" class="formlist">
            <thead>
                <tr>
                    <th colspan="5">
                        <h2 align="center" style="color:#565656;font-size: medium">�ҽ̰�ȫЭ����</h2>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="5">
                        <p style="color:#181818;font-size: small">
                            �׷���������ͨ��ѧ�ڹ���ѧ�칫��<br/>

                            �ҷ���������ͨ��ѧͬѧ<br/>

                            һ��ͬѧ����������ͨ��ѧ�ڹ���ѧ�칫�����룬��Ը�μ��ڹ���ѧ��������ð칫��ί�ɣ����£��ҽ�/У���ְ��������<br/>

                            �����ҷ��ڴ����ڹ���ѧ�ڼ䣬Ӧ���ع��ҷ��ɡ����档<br/>

                            �����ҷ��ڴ����ڹ���ѧ�ڼ䣬Ӧ����ѧУ��������ƶȣ��Ͻ�ҹ�����ޡ�<br/>

                            �ġ��ҷ���ŵ�����ڹ���ѧ�����Ӱ���Լ���ѧҵ��<br/>

                            �塢�ҷ������ڹ���ѧ�Ӧע������ȫ����ֹ������ȫ�¹ʡ������ط��ɷ���򲻰�ѧУ�������˵�λҪ���������������¹ʣ��׷����е����Ρ�<br/>

                            �����ҷ�δ���׷����˽�Դ��¼ҽ̻����������������ְ�ȫ�¹ʣ������Ը���<br/>

                            �ߡ�������������֮һ�ģ��ҷ�Ӧ���е���Ӧ���Σ��׷����е����Σ�<br/>

                            ��һ�����Դ��¼���˫��Э��ȷ����Χ�����������������𺦷����ģ�<br/>

                            ������������Ϊ�����𺦷����ģ�<br/>

                            ���������������ʡ��ض����������쳣����״̬��δ��֪�׷������𺦷����ġ�<br/>

                            �ˡ��ҷ��ڴ����ڹ���ѧ�ʱ��Ӧ���ŵ��壬��Ϊ������<br/>

                            �š��ҷ��ڴ����ڹ���ѧ�����������������Ӧ��ʱ��׷���ӳ���׷�Ӧ���ݾ��������ʱ���������ɴ������һ�к�������ҷ���ȫ�е���<br/>

                            ʮ���ҷ��Ӽ׷��õ����ڹ���ѧ��λ����������ת�á��ҷ�����Ƹ��ҽ̵ļҳ��������˵�λ��ȥ����ʱ��Ӧ��ʱ֪ͨ�׷���<br/>

                            ʮһ����Э���Խ�������Ч��<br/>

                            ʮ����ΥԼ������һ�к����ΥԼ�����ге���<br/>

                            ʮ������Э��ס���˫���Ѿ�ȫ���Ķ������������
                        </p>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div style="height: 50px"></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="5">
                <div style="display:inline-block">
                    <input type="checkbox" id="yyxys"/>
                    �����Ķ���ͬ��ǩ����Э��
                </div>
                <div class="btn">
                    <button type="button" name="����"  onclick="tyaqxys();return false;">
                        ȷ��
                    </button>
                    <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
                        �� ��
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</form>

<script type="text/javascript">
    function tyaqxys() {
        if(jQuery("#yyxys:checked").length>0){
            var xqid = jQuery("#xqid").val();
            window.location.href = "jjgl_jjxq.do?method=toXssqPage&xqid="+xqid;
        }else{
            showAlert("�빴ѡ\"�����Ķ���ͬ��ǩ����Э��\"");
        }
    }
</script>
</body>
</html>
