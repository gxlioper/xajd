<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function querygo(){
			 	document.forms[0].action = "/xgxt/zpxxquery.do?act=go&doType=query";
			 	document.forms[0].submit();
	    }
		function deleteuser(username) {	
			var url="/xgxt/deleteuser_jyzp.do?act=go&doType=del&username=";
			url=url+username;
			 document.forms[0].action = url;
			 document.forms[0].submit();
        }
		
		
		function adduser(){
		    var username = document.getElementById("username").value;
		    var gsmc = document.getElementById("gsmc").value;
		    
		    if(username==""){
		    alert("�û�������Ϊ�գ�");
		    return false;
		    }
		    if(gsmc==""){
		    alert("��ָ�����û��Ĺ�˾���ƣ�");
		    return false;
		    }
		    		    
		    document.forms[0].action = "/xgxt/adduser_jyzp.do?act=go&doType=add";
		 	document.forms[0].submit();
		}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ģ��ά�� - ��ҵ��Ƹά��</a>
			</p>
		</div>
		<html:form action="/data_search" method="post">
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="adduser()">
											�� ��
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>�û���
								</th>
								<td>
									<html:text name="rs1" property="username" style="width:200px" />
								</td>
								<th>
									<font color="red">*</font>��˾����
								</th>
								<td>
									<logic:equal name="xxdm" value="10491">
										<html:select name="rs1" property="gsmc" styleId="gsmc"
											style="width:200px">
											<html:option value=""></html:option>
											<html:options collection="gsmcList" property="dwmc"
												labelProperty="dwmc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="xxdm" value="10491">
										<html:select name="rs1" property="gsmc" styleId="gsmc"
											style="width:200px">
											<html:option value=""></html:option>
											<html:options collection="gsmcList" property="gsmc"
												labelProperty="gsmc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td align="center">
										����
									</td>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="1">
											<td align="center">
												<bean:write name="v" />
												<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
											</td>
										</logic:iterate>
										<td align="center" width="10%">
											<button class="button2"
												onclick="deleteuser(this.parentNode.parentNode.cells[1].innerText)"
												style="width:60px">
												ɾ��
											</button>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
		</html:form>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                       alert("ɾ���ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("ɾ��ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="add">
			<logic:equal name="add" value="ok">
				<script>
                       alert("�ύ�ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="add" value="no">
				<script>
                      alert("�ύʧ�ܣ������Ƿ��ظ��ύ");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
