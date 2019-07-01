<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}
//����--����ݸ���ύ
function save(url){
	var checkId ="pjjszgh-pj-ylzd1";
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	var pj = jQuery("#pj").val();
	if(pj.length < 200||pj.length > 1000){
		return showAlert("����������������ΧΪ200-1000��");
	}
	ajaxSubFormWithFun("jspjsqForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
	});
}
		
		</script>
	</head>
	<body>
		<html:form method="post" styleId="jspjsqForm" action="/dekt_jspjglsq"
			enctype="multipart/form-data">
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
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
							<th align="right" width="15%">
								ѧ��
							</th>
							<td align="left" width="30%">
								${dqxn}
							</td>
							<th align="right" width="20%">
								ѧ��
							</th>
							<td align="left" width="30%">
								${dqxq}
							</td>
						</tr>
						<tr>
							<th align="right">
								<logic:equal name="lx" value="0"><span class="red">*</span></logic:equal>������ʦ
							</th>
							<td align="left">
							<logic:equal name="lx" value="0">
							<!--<html:text styleId="pjjszgh" property="pjjszgh" />-->
									<html:text property="pjjszgh" styleId="pjjszgh" style="width:100px" value="${jsList.zgh}" readonly="true"/>
									<button type="button" onclick="showDialog('��ʦѡ��',680,480,'szdw_fdyjtff.do?method=showFdys&goto=${path}');return false;" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
							 </logic:equal >
							<logic:notEqual name="lx" value="0">
							<html:hidden property="pjjszgh" styleId="pjjszgh" value ="${jszgh}"/>
									${jsxm}
							</logic:notEqual>
							</td>
							<th align="right">
								<span class="red">*</span>��ʶ;��&nbsp;
							</th>
							<td>
								<html:select property="ylzd1" styleId="ylzd1">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="rstjList" property="rstjdm" labelProperty="rstjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>����&nbsp;
								<br />
								<font color="red">(������200��,<br />��1000��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="7" property="pj" styleId="pj" 
									style="width:97%" ></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('dekt_jspjglsq.do?method=add&type=save');return false;"
										id="buttonSave">
										����ݸ�
									</button>
									<button type="button"
										onclick="save('dekt_jspjglsq.do?method=add&type=submit');return false;"
										id="buttonSave">
										�ύ����
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
