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
      var dz = document.getElementById("dz").value;
      var lxbm = document.getElementById("lxbm").value;
      var lxr = document.getElementById("lxr").value;
      var dwjj = document.getElementById("dwjj").value;
     
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
      if(dz==""){
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
      if(dwjj==""){
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
				<em>���ĵ�ǰλ��:</em><a>��λ��Ϣ¼��</a>
			</p>
		</div>


		<html:form action="/stuxsjyxxinput" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ¼��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button onclick="add()">
										�� ��
									</button>
									<button type="reset">
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
								<html:hidden name="rs" property="dwid"/>
							</th>
							<td width="30%">
								<html:text name="rs" property="dwmc" style="width:60%" />
							</td>
							<th width="16%">
								<font color="red">*</font>��λ����
							</th>
							<td width="30%">
								<html:select name="rs" property="dwxz" styleId="dwxz"
									style="width:210px">
									<html:option value=""></html:option>
									<html:options collection="dwxxdm" property="dwxz" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��λ���
							</th>
							<td>
								<html:text name="rs" property="dwlb" style="width:60%" />
							</td>
							<th>
								<font color="red">*</font>���ܲ���
							</th>
							<td>
								<html:select name="rs" property="zgbm" styleId="zgbm"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="sydzgbmList" property="zgbm"
										labelProperty="zgbm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>������ҵ
							</th>
							<td colspan="3">
								<html:select name="rs" property="hyfl" styleId="sshy"
									style="width:225px">
									<html:option value=""></html:option>
									<html:options collection="hyflList" property="hyfl"
										labelProperty="hyfl" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								���ڵ���
							</th>
							<td colspan="3">
								<html:select name="rs" property="szdqsh" onchange="loadShi()"
									styleId="jgshen">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="ssList" property="ssdm"
										labelProperty="ssmc" />
								</html:select>
								<html:select name="rs" property="szdqsi" styleId="jgshi">
									<html:options collection="shiList" property="shidm"
										labelProperty="shimc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:text name="rs" property="clrq"
									onclick="return showCalendar('clrq','y-mm-dd');" />
							</td>
							<th>
								<font color="red">*</font>ͨѶ��ַ
							</th>
							<td>
								<html:text name="rs" property="dz" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:text name="rs" property="yb" />
							</td>
							<th>
								<font color="red">*</font>��ϵ����
							</th>
							<td>
								<html:text name="rs" property="lxbm" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ϵ��
							</th>
							<td>
								<html:text name="rs" property="lxr" />
							</td>
							<th>
								<font color="red">*</font>��ϵ�绰
							</th>
							<td>
								<html:text name="rs" property="lxdh" />
							</td>
						</tr>
						<tr>
							<th>
								��λ����
							</th>
							<td>
								<html:text name="rs" property="cz" />
							</td>
							<th>
								����
							</th>
							<td>
								<html:text name="rs" property="email" />
							</td>
						</tr>
						<tr>
							<th>
								��λ��ҳ
							</th>
							<td>
								<html:text name="rs" property="dwzy"  />
							</td>
							<th>
								������������
							</th>
							<td>
								<html:text name="rs" property="dayzbm" />
							</td>
							
						</tr>
						<tr>
							<th>
								����ת�ĵ�ַ
							</th>
							<td colspan="3">
								<html:text name="rs" property="dazjdz" style="width:80%" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��λ����
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="dwjj" style="width:80%"
									rows="5"></html:textarea>
							</td>
						</tr>
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

