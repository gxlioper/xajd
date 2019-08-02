
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
            var mon = '${pbxxlist.mon}';
            var tues = '${pbxxlist.tues}';
            var wed = '${pbxxlist.wed}';
            var thur = '${pbxxlist.thur}';
            var fri = '${pbxxlist.fri}';
            var sat = '${pbxxlist.sat}';
            var sun = '${pbxxlist.sun}';
            var mond = mon.split(",");
            var tuesd = tues.split(",");
            var wedd = wed.split(",");
            var thurd = thur.split(",");
            var frid = fri.split(",");
            var satd = sat.split(",");
            var sund = sun.split(",");
            if(mond!=null){
                for(var i=0;i<mond.length;i++){
                    jQuery("input:checkbox[name='mond'][value='"+mond[i]+"']").prop("checked","checked");
                }
            }
            if(tuesd!=null){
                for(var i=0;i<tuesd.length;i++){
                    jQuery("input:checkbox[name='tuesd'][value='"+tuesd[i]+"']").prop("checked","checked");
                }
            }
            if(wedd!=null){
                for(var i=0;i<wedd.length;i++){
                    jQuery("input:checkbox[name='wedd'][value='"+wedd[i]+"']").attr("checked","checked");
                }
            }
            if(thurd!=null){
                for(var i=0;i<thurd.length;i++){
                    jQuery("input:checkbox[name='thurd'][value='"+thurd[i]+"']").prop("checked","checked");
                }
            }
            if(frid!=null){
                for(var i=0;i<frid.length;i++){
                    jQuery("input:checkbox[name='frid'][value='"+frid[i]+"']").prop("checked","checked");
                }
            }
            if(satd!=null){
                for(var i=0;i<satd.length;i++){
                    jQuery("input:checkbox:checkbox[name='satd'][value='"+satd[i]+"']").prop("checked","checked");
                }
            }
            if(sund!=null){
                for(var i=0;i<sund.length;i++){
                    jQuery("input:checkbox[name='sund'][value='"+sund[i]+"']").prop("checked","checked");
                }
            }

        })
        function savePbzyz(){
            var checkId = 'fdkm-fdsmc-fjid';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            var url = "xyfd_pbjg.do?method=save&type=update";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }

        function selectFds() {
            showDialog("�������б�",700,500,"xyfd_fdjswh.do?method=selectFds");
        }

	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdjswh" method="post" styleId="demoForm">
	<html:hidden name="pbxxlist" property="jgid" styleId="jgid"/>
	<div style='width:100%;height:500px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="7">
					<span>��־Ը����Ϣ</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="10%">����</th>
				<td width="20%">
						${xsxxlist.xm}
				</td>
				<th width="10%">�Ա�</th>
				<td width="20%">
					<logic:equal value="1" name="xsxxlist" property="xb">
						<label>��</label>
					</logic:equal>
					<logic:equal value="2" name="xsxxlist" property="xb">
						<label>Ů</label>
					</logic:equal>
				</td>
				<th width="12%">ѧ��</th>
				<td width="20%">
						${xsxxlist.xh}
					<input name="xh" id="xh" value="${xsxxlist.xh}" style="display: none"/>
				</td>
				<th rowspan="2">
					<div align="center">
						<img id="zhaopian" src="xsxx_xsgl.do?method=showPhoto&xh=${xsxxlist.xh}" alt="" style="height: 133px;width: 100px;" border="0"/>
					</div>
				</th>
			</tr>
			<tr>
				<th>��Ժ</th>
				<td>
						${xsxxlist.symc}
				</td>
				<th>ѧԺ</th>
				<td>
						${xsxxlist.xymc}
				</td>
				<th>�༶</th>
				<td>
						${xsxxlist.zybjmc}
				</td>
			</tr>
			<tr>
				<th>רҵ����</th>
				<td>
						${zypmlist.pm0}
				</td>
				<th>ѧ���ɲ���ְ</th>
				<td>
					<html:text name="pbxxlist" property="xsgbrz" styleId="xsgbrz"/>
				</td>
				<th>��������ѧ�𼰱��ý���</th>
				<td colspan="2">
						${jlxx}
				</td>
			</tr>
			<tr>
				<th>��ϵ�绰</th>
				<td colspan="3">
					<html:text name="pbxxlist" property="lxdh" styleId="lxdh"/>
				</td>
				<th>E-mail</th>
				<td colspan="2">
					<html:text name="pbxxlist" property="dzyx" styleId="dzyx"/>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>������Ŀ</th>
				<td colspan="3">
					<html:text name="pbxxlist" property="fdkm" styleId="fdkm"/>
				</td>
				<th><span class="red">*</span>������</th>
				<td colspan="2">
					<html:hidden name="pbxxlist" property="fds" styleId="fds"/>
					<html:text name="fdsxx" property="fdsmc" styleId="fdsmc" readonly="readonly"/>
					<button class="btn_01" type="button" onclick="selectFds();">ѡ��</button>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>����</th>
				<td colspan="6">
					<html:hidden name="pbxxlist" property="fjid" styleId="fjid"/>
					<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
					<script type="text/javascript">
                        //���ø���
                        jQuery(function(){
                            jQuery.MultiUploader({
                                maxcount : 3,
                                //��׺
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //����ļ���С ��λM
                                maxsize: 10,
                                //��Ÿ������������id
                                elementid : 'fjid'
                            });
                        });
					</script>
				</td>
			</tr>
			<tr>
				<th rowspan="7">����ʱ��</th>
				<th colspan="2">��һ</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="mond" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="mond" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">�ܶ�</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="tuesd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="tuesd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">����</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="wedd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="wedd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">����</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="thurd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="thurd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">����</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="frid" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="frid" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">����</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="satd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="satd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">����</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="sund" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="sund" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
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
						<button type="button" type="button" onclick="savePbzyz();return false;" >
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

