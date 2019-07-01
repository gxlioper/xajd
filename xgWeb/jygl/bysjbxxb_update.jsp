<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript">
	
	 function refreshweb(){
        document.forms[0].action = "/xgxt/turnJyglInfoUpdate.do?doNow=change&doType=update";
	 	document.forms[0].submit();
    }
	
	
	function bysinfoupdateall123(){
	    var url ="/xgxt/JyglInfoUpdate.do?doType=update&doType2=update2";
    	var sfzh = document.getElementById("id").value;
    	var name = document.getElementById("name").value;
   	
   	    if(name==""){
    	alert("��������Ϊ�գ�");
    	return false;
        }
    	if(checkSfzh(sfzh)){
		 		document.forms[0].action = url;
		 		document.forms[0].submit();
        }
    }
  
  function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
    
    function checkSfzh(sfzh) {
	var OldID;
	if(sfzh.length == 15){
		OldID = sfzh;
		return true;
	}else if(sfzh.length == 18){
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
	}else{
		alert("��������ȷ�����֤���룡","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
	var i, j, S;
	var NewID, ID, strNF;
	NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	S = 0;
	for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	}
	S = S % 11;
	if(sfzh != NewID + A[S]){
		alert("��������ȷ�����֤���룡","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	}
	return true;
}
    
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

	function loadXian(){
		var shi = document.getElementById("jgshi").value;	
		getStuDetails.getXianList(shi,function(data){
			if (data != null) {
					var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);							
						DWRUtil.addOptions(objId,data,"xiandm","xianmc");
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}		
		});
	}
	</script>
	</head>
	
	<body>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ѧ����Ϣ - ��ϸ��Ϣ</a>
				</p>
			</div>
			<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�ֶ�ά��</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th  width="16%" >
							ѧ�����
						</th>
						<td width="34%">
							<html:text name="rs" property="xslb" style="width=100%"
								readonly="true" />
						</td>
						<th  width="16%" >
							����<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td width="34%">
							<html:text name="rs" property="xymc" readonly="true" />
						</td>
					</tr>
					<tr>
						<th  >
							ѧ��
						</th>
						<td >
							<html:text name="rs" property="xsxh" readonly="true" />
						</td>
						<th   >
							<font color="red">*</font>���֤��
						</th>
						<td >
							<html:text name="rs" property="id" />
						</td>
					</tr>
					<tr>
						<th   >
							<font color="red">*</font>����
						</th>
						<td >
							<html:text name="rs" property="name" />
						</td>
						<th   >
							<font color="red">*</font>�Ա�
						</th>
						<td >
							<html:select name="rs" property="xbdm" style="width=100%">
								<html:option value="1">��</html:option>
								<html:option value="2">Ů</html:option>
							</html:select>
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
				<tr>
					<th   >
						����
					</th>
					<td >
						<html:text name="rs" property="mz" readonly="true" />
					</td>
					<th   >
						������ò
					</th>
					<td >
						<html:text name="rs" property="zzmm" readonly="true" />
					</td>
				</tr>
				</logic:equal>
					<tr>
						<th   >
							ѧУ
						</th>
						<td  >
							<html:text name="rs" property="xxdm" readonly="true" />
						</td>
						<th   >
							ѧУ����
						</th>
						<td >
							<html:text name="rs" property="xxmc" readonly="true" />
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
				<tr>
					<th   >
						<bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td >
						<html:text name="rs" property="xymc" readonly="true" />
					</td>
					<th   >
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td  >
						<html:text name="rs" property="xydm" readonly="true" />
					</td>
				</tr>
				</logic:equal>
					<tr>
						<th   >
							<font color="red">*</font>רҵ
						</th>
						<td >
							<html:text name="rs" property="zydm" style="width:150px"
								readonly="true" />
						</td>
						<th   >
							רҵ����
						</th>
						<td >
							<html:text name="rs" property="zymc" readonly="true" />
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
				<tr>
					<th   >
						�༶
					</th>
					<td >
						<html:text name="rs" property="bjdm"></html:text>
					</td>
					<th   >
						��ϵ��ʽ
					</th>
					<td >
						<html:text name="rs" property="lxfs" ></html:text>
					</td>
				</tr>
				</logic:equal>
					<tr>
						<th   >
							<font color="red">*</font>ѧ��
						</th>
						<td >
							<html:select name="rs" property="xldm" style="width:150px">
								<html:options collection="xldmList" property="xldm"
									labelProperty="xl" />
							</html:select>
						</td>
						<th   >
							ѧ��
						</th>
						<td >
							<html:text name="rs" property="xzdm" readonly="true" />
						</td>
					</tr>
					<tr>
						<th   >
							<font color="red">*</font>��Դ����
						</th>
						<td >
						<logic:equal value="10355" name="xxdm" scope="session">
							<html:select name="rs" property="sydqdm" onchange="loadShi()"
								styleId="jgshen">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select><br>
							<html:select name="rs" property="jgshi" styleId="jgshi"
								onchange="loadXian()">
								<html:options collection="shiList" property="shidm"
									labelProperty="shimc" />
							</html:select><br>
							<html:select name="rs" property="jgx" styleId="jgxian">
								<html:options collection="xianList" property="xiandm"
									labelProperty="xianmc" />
							</html:select>
					</logic:equal>
						<logic:notEqual value="10355" name="xxdm" scope="session">
						<logic:notEqual value="10491" name="xxdm">
							<html:select name="rs" property="sydqdm" style="width:150px">
								<html:options collection="sydqList" property="sydqdm"
									labelProperty="sydq" />
							</html:select>
							</logic:notEqual>
							<logic:equal value="10491" name="xxdm">
							<html:select name="rs" property="sydqdm" onchange="loadShi()" styleId="jgshen">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select><br>
							<html:select name="rs" property="jgshi" styleId="jgshi"
								onchange="loadXian()">
								<html:options collection="shiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							</logic:equal>
							</logic:notEqual>
						</td>
						<th   >
							<font color="red">*</font>������ʽ
						</th>
						<td >
							<html:select name="rs" property="pyfsdm" style="width:150px">
								<html:options collection="pyfsList" property="pyfsdm"
									labelProperty="pyfs" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th   >
							��ѧ���
						</th>
						<td >
							<html:text name="rs" property="nd" readonly="true" />
						</td>
						<th   >
							<font color="red">*</font>��ҵ���
						</th>
						<td >
							<html:select name="rs" property="bynd" style="width:150px">
								<html:option value="2006">
										2006
									</html:option>
								<html:option value="2007">
										2007
									</html:option>
								<html:option value="2008">
										2008
									</html:option>
								<html:option value="2009">
										2009
									</html:option>
								<html:option value="2010">
										2010
									</html:option>
								<html:option value="2011">
										2011
									</html:option>
								<html:option value="2012">
										2012
									</html:option>
								<html:option value="2013">
										2013
									</html:option>
								<html:option value="2014">
										2014
									</html:option>
								<html:option value="2015">
										2015
									</html:option>
							</html:select>
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
				<tr>
					<th   >
						����
					</th>
					<td >
						<html:text name="rs" property="lxdzxx"></html:text>
					</td>
					<th   >
						QQ
					</th>
					<td >
						<html:text name="rs" property="qq"></html:text>
					</td>
				</tr>
				</logic:equal>
					<tr>
						<th   >
							�ϱ�ʱ��
						</th>
						<td >
							<html:text name="rs" property="sbsj" readonly="true" />
						</td>
						<logic:equal value="10491" name="xxdm">
					<th   >
						Э������
					</th>
					<td >
						<html:text name="rs" property="xysbh"></html:text>
					</td>
					</logic:equal>
						<logic:notEqual value="10491" name="xxdm">
						<th   >
							��ע
						</th>
						<td>
							<html:text name="rs" property="memo" maxlength="10"/>
						</td>
						</logic:notEqual>
					</tr>
					<logic:equal value="10491" name="xxdm">
					<tr>
						<th  >
							��ע
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='memo' 
							style="word-break:break-all;width:99%" rows='5' />
						</td>
					</tr>
					</logic:equal>
					</tbody>
					<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button onclick="bysinfoupdateall123();" id="buttonSave">
											�� ��
										</button>
										<button class="button2" onclick="Close();return false;" id="buttonQx">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
				</table>
			</div>
			
			<logic:equal name="updated1" value="que">
				<script>
                      //alert("ѡ���Ϊ�գ�");
                    </script>
			</logic:equal>
			<logic:equal name="updated" value="ok">
				<script>
                    alert("�޸ĳɹ���");
                    if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
					}
                    </script>
			</logic:equal>
			<logic:equal name="updated" value="no">
				<script>
                      alert("�޸�ʧ��!");
                      window.dialogArguments.document.getElementById('query_go').click();
                    </script>
			</logic:equal>
		</html:form>
	</body>
</html>
