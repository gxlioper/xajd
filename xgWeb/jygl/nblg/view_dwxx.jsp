<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript">
	function loadShi(){
		var shen = document.getElementById("jgshen").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}	
	function add(){
      var dwmc = document.getElementById("dwmc").value;   
      var dwxz = document.getElementById("dwxz").value; 
      var zgbm = document.getElementById("zgbm").value; 
      var txdz = document.getElementById("txdz").value;
      var lxbm = document.getElementById("lxbm").value;
      var lxr = document.getElementById("lxr").value;
      var dwjs = document.getElementById("dwjs").value;
     
      if(dwmc==""){
      alert("��λ���Ʋ���Ϊ�գ�");
      return false;
      }
      if(dwxz==""){
      alert("����д��λ����ѡ�");
      return false;
      }
      if(zgbm==""){
      alert("���ܲ��Ų���Ϊ�գ�");
      return false;
      }
      if(txdz==""){
      alert("ͨѶ��ַ����Ϊ�գ�");
      return false;
      }
      if(lxbm==""){
      alert("��ϵ���Ų���Ϊ�գ�");
      return false;
      }
      if(lxr==""){
      alert("��ϵ�˲���Ϊ�գ�");
      return false;
      }
      if(dwjs==""){
      alert("��λ���ٲ���Ϊ�գ�");
      return false;
      }
      
         BatAlert.showTips('�����ύ�����Ժ�...');
		 document.forms[0].action = "dwxxInput.do?act=save";
		 document.forms[0].submit();
        
    }
    
	</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��λ��Ϣ�鿴</a>
			</p>
		</div>

		<html:form action="/stuxsjyxxinput" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ�鿴</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="close()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>��λ����
							</th>
							<td width="30%">
								<html:text name="rs" property="dwmc" readonly="true" />
							</td>
							<th width="16%">
								<font color="red">*</font>��λ����
							</th>
							<td width="30%">
								<html:text name="rs" property="dwxz" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								��λ���
							</th>
							<td>
								<html:text name="rs" property="dwlb" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>���ܲ���
							</th>
							<td>
								<html:text name="rs" property="zgbm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>������ҵ
							</th>
							<td>
								<html:text name="rs" property="hyfl"  readonly="true" />
							</td>
							<th>
								���ڵ���
							</th>
							<td>
								<html:text property="sshy" value="${rs.szdqsh}${rs.szdqsi}"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:text name="rs" property="clrq" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>ͨѶ��ַ
							</th>
							<td>
								<html:text name="rs" property="dz" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:text name="rs" property="yb" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>��ϵ����
							</th>
							<td>
								<html:text name="rs" property="lxbm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ϵ��
							</th>
							<td>
								<html:text name="rs" property="lxr" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>��ϵ�绰
							</th>
							<td>
								<html:text name="rs" property="lxdh" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��λ����
							</th>
							<td>
								<html:text name="rs" property="cz" readonly="true" />
							</td>
							<th>
								����
							</th>
							<td>
								<html:text name="rs" property="email" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��λ��ҳ
							</th>
							<td>
								<html:text name="rs" property="dwzy"
									readonly="true" />
							</td>
							<th>
								������������
							</th>
							<td>
								<html:text name="rs" property="dayzbm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								����ת�ĵ�ַ
							</th>
							<td colspan="3">
								<html:text name="rs" property="dazjdz" readonly="true" style="width:80%"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��λ����
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="dwjj" rows="5" style="width:80%"
									readonly="true"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("�ύ�ɹ���");
                      Close();
                      window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("�ظ��ύ������ʧ��!");
                      Close();
                       window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

