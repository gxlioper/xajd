
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
        jQuery(function(){
            jQuery('input[name="yxzt"]').click(function () {
                jQuery("#qkmstr").empty();
                var yxzt = jQuery('input[name="yxzt"]:checked').val();
                if(yxzt=="3"){
                    jQuery("#qkmstr").append("<th><span class='red'>*</span>�������</th>");
                    jQuery("#qkmstr").append("<td colspan='3'><textarea name='qkms' id='qkms' style='width: 500px;height: 100px;'></textarea></td>");
                }
            })
        })
        function saveFds(){
            var checkId = 'fdsmc-fdsdd-syksrq-syjsrq-sykssj-syjssj';
            var yxzt = jQuery('input[name="yxzt"]:checked').val();
            if(yxzt=="3"){
                checkId += '-qkms';
            }
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            save();
        }
        function save(){
            var url = "xyfd_fdswh.do?method=addfds&type=save";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
        function selectJs() {
            showDialog("��ʦ�б�",700,500,"xyfd_fdjswh.do?method=selectTeacher");
        }
        function selectFds() {
            
        }

    </script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdswh" method="post" styleId="demoForm">
    <div style='width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="7">
                    <span>������ʦ��Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="10%"><span class="red">*</span>����</th>
                <td width="20%">
                    <input id="djh" name="djh" style="display: none" disabled="disabled"/>
                    <input id="zgh" name="zgh" value="${jsxx.zgh}" style="display: none"/>
                    <input id="xm" name="xm" value="${jsxx.xm}" readonly="readonly"/>
                    <button class="btn_01" type="button" onclick="selectJs();">ѡ��</button>
                </td>
                <th width="10%">�Ա�</th>
                <td width="20%" id="xb">
                </td>
                <th width="12%">ְ��/ְ��</th>
                <td width="20%" id="zc">
                </td>
                <th rowspan="2">
                    <div align="center">
                        <img id="zhaopian" src="teaPic.jsp?zgh=${jsxx.zgh}" alt="" style="height: 133px;width: 100px;" border="0"/>
                    </div>
                </th>
            </tr>
            <tr>
                <th>���ڵ�λ</th>
                <td id="bmmc">
                </td>
                <th><span class="red">*</span>�ο�����</th>
                <td>
                    <input id="kcmc" name="kcmc"/>
                </td>
                <th><span class="red">*</span>ѧ��/רҵ</th>
                <td>
                    <input id="xkzy" name="xkzy"/>
                </td>
            </tr>
            <tr>
                <th>��ϵ�绰</th>
                <td colspan="3">
                    <input id="lxdh" name="lxdh"/>
                </td>
                <th>E-mail</th>
                <td colspan="2">
                    <input id="dzyx" name="dzyx"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>������Ŀ</th>
                <td colspan="3">
                    <input id="fdkm" name="fdkm"/>
                </td>
                <th><span class="red">*</span>������</th>
                <td colspan="2">
                    <input id="fds" name="fds" style="display: none"/>
                    <input id="fdmc" name="fdmc" readonly="readonly"/>
                    <button class="btn_01" type="button" onclick="selectFds();">ѡ��</button>
                </td>
            </tr>
            <tr>
                <th rowspan="7">����ʱ��</th>
                <th colspan="2">��һ</th>
                <td colspan="4"></td>
            </tr>
            <tr>
                <th colspan="2">�ܶ�</th>
                <td colspan="4"></td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4"></td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4"></td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4"></td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4"></td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="position:fixed;bottom:0;width: 100%">
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="bz">"<span class="red">*</span>"Ϊ������</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveFds();return false;" >
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

