<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
        function saveZwsq(){
            var checkId = 'zgh-xmdm-pxxd';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            var url = "szdw_fdy_ywxxypxjg.do?method=add&type=save";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
        function showFdysNotF5CallBack(rowData){
            jQuery("#zgh").val(rowData["zgh"]);
            jQuery("#xm").html(rowData["xm"]);
            jQuery("#xb").html(rowData["xbmc"]);
            jQuery("#mz").html(rowData["mzmc"]);
            jQuery("#szbm").html(rowData["bmmc"]);
            jQuery("#szsy").html(rowData["symc"]);
            jQuery("#zzmm").html(rowData["zzmmmc"]);
            jQuery("#lxdh").html(rowData["lxdh"]);
            jQuery("#dxgzsj").html(rowData["rxgzsj"]);
        }
        function showpxxmsNotF5CallBack(rowData){
            jQuery("#xmdm").val(rowData["xmdm"]);
            jQuery("#xmmc").val(rowData["xmmc"]);
            jQuery("#zzbm").html(rowData["zzbm"]);
            jQuery("#pxxs").html(rowData["pxxs"]);
            jQuery("#pxsj").html(rowData["pxsj"]);
            jQuery("#pxjj").html(rowData["pxjj"]);
        }
    </script>
</head>
<body style="width:100%">
<html:form action="/szdw_fdy_ywxxypxjg" method="post" styleId="demoForm">
    <div style='width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th width="20%"><span class="red">*</span>ְ����</th>
                    <td width="40%">
                        <input name="zgh" id="zgh" readonly="readonly"/>
                        <button type="button" onclick="showDialog('����Աѡ��',720,550,'szdw_fdyjtff.do?method=showFdysNotF5');return false;" class="btn_01" id="buttonFindStu">
                            ѡ��
                        </button>
                    </td>
                    <th width="20%">����</th>
                    <td width="20%" id="xm"></td>
                </tr>
                <tr>
                    <th >�Ա�</th>
                    <td  id="xb"></td>
                    <th>����</th>
                    <td id="mz"></td>
                </tr>
                <tr>
                    <th>���ڲ���</th>
                    <td id="szbm"></td>
                    <th>������Ժ</th>
                    <td id="szsy"></td>
                </tr>
                <tr>
                    <th>������ò</th>
                    <td id="zzmm"></td>
                    <th>��ϵ�绰</th>
                    <td id="lxdh"></td>
                </tr>
                <tr>
                    <th>��У����ʱ��</th>
                    <td colspan="3" id="dxgzsj"></td>
                </tr>
            </tbody>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>��ѵ��Ϣ</span>
                    </th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>��ѵ����</th>
                <td>
                    <html:hidden property="xmdm" styleId="xmdm" />
                    <input type="text" name="xmmc" id="xmmc" readonly="true"/>
                    <button class="btn_01" type="button" onclick="showDialog('��ѵ��Ŀѡ��',720,550,'szdw_fdypxxmwh.do?method=pxxmList');return false;">ѡ��</button>
                </td>
                <th>��ѵʱ��</th>
                <td id="pxsj">
                </td>
            </tr>
            <tr>
                <th>��֯����</th>
                <td id="zzbm">
                </td>
                <th>ѧʱ</th>
                <td id="pxxs">
                </td>
            </tr>
            <tr>
                <th>��ѵ����</th>
                <td colspan="3" id="pxjj">
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>��ѵ�����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>�������</th>
                <td colspan="3">
                    <input type="radio" name="kqqk" value="����" checked="checked">����</input>
                    <input type="radio" name="kqqk" value="ȱ��">ȱ��</input>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>��ѵ�ĵ�</th>
                <td colspan="3">
                    <html:textarea property='pxxd' style="width:95%" styleId="pxxd" rows='5' onblur="checkLen(this,1000);"/>
                </td>
            </tr>
            <tr>
                <th>
                    ����
                </th>
                <td  colspan="3">
                    <html:hidden property="filepath" styleId="filepath" />
                    <input type="file" id="filepath_f" name="filepath" />
                    <script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            jQuery('#filepath_f').multiUploader({
                                maxcount : 3,
                                //��׺
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //����ļ���С ��λM
                                maxsize: 10,
                                //��Ÿ������������id
                                elementid : 'filepath',
                                eid : 'filepath_f'
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="bz">"<span class="red">*</span>"Ϊ������</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveZwsq();return false;" >
                            ����
                        </button>
                        <button type="button" name="�� ��" onclick="iFClose();">
                            �� ��
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

