<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	    function czmxquerygo(){
	    var userid =document.getElementById("userid").value;
	    var dowhat =document.getElementById("dowhat").value;
	    var whichtable =document.getElementById("whichtable").value;
	    var xjsj = document.getElementById("xjsj").value;
	    
	    if(userid==""&&dowhat==""&&whichtable==""&&xjsj==""){
	      if(confirm("��δѡ���κ���������ѯ�����ѽϳ�ʱ�䣬��Ը��ȴ���")){
	        document.forms[0].action = "/xgxt/userczmxquery.do?act=go&doType=query";
		 	document.forms[0].submit();
		 	return true;
	      }else{
	      return false;
	      }
	    }
		 	document.forms[0].action = "/xgxt/userczmxquery.do?act=go&doType=query";
		 	document.forms[0].submit();
        }
        
		function czmxdelete(doType){
		var url = "/xgxt/userczmxdelete.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		    } else {
			if (confirm("ȷ��Ҫɾ������������")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		function czmxdeleteall(doType){
		var url = "/xgxt/userczmxdeleteall.do?doType2=delall&doType=query&act=go";
			
		if (doType == "delall") {
			if (confirm("ȷ��Ҫ��յ�ǰ������")) {
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		}
      }
		
		 function  jyglbyqxDataExport(){
	       document.forms[0].action = "/xgxt/czmxDataExport.do?tableName=jygl_userczmxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ģ��ά�� - �û�������ϸ</a>
			</p>
		</div>

		<html:form action="/openCzmxWeb" method="post">
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="czmxdelete('del');" class="btn_sc"> ɾ�� </a>
						</li>
						<li>
							<a href="#" onclick="czmxdeleteall('delall')" class="btn_hsz">
								��� </a>
						</li>
						<li>
							<a href="#" onclick="jyglbyqxDataExport()" class="btn_dc"> ����
							</a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="czmxquerygo()">
											�� ѯ
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
									�û���
								</th>
								<td>
									<html:text name="rs1" property="userid" style="width:130px" />
								</td>
								<th>
									����
								</th>
								<td>
									<html:select name="rs1" property="dowhat" style="width:80px">
										<html:option value=""></html:option>
										<html:option value="����">����</html:option>
										<html:option value="ɾ��">ɾ��</html:option>
										<html:option value="�޸�">�޸�</html:option>
									</html:select>
								</td>
								<th>
									����
								</th>
								<td>
									<html:select name="rs1" property="whichtable"
										style="width:150px">
										<html:option value=""></html:option>
										<html:option value="ѧ��������Ϣ��">ѧ��������Ϣ��</html:option>
										<html:option value="��ҵȥ���">��ҵȥ���</html:option>
										<html:option value="��ҵЭ���">��ҵЭ���</html:option>
										<html:option value="���˼�����">���˼�����</html:option>
										<html:option value="��ѯ��ʦ��Ϣ��">��ѯ��ʦ��Ϣ��</html:option>
										<html:option value="��ѯԤԼ�����">��ѯԤԼ�����</html:option>
										<html:option value="�����ļ���">�����ļ���</html:option>
										<html:option value="��Ƹ��Ϣ��">��Ƹ��Ϣ��</html:option>
										<html:option value="��ҵ���������">��ҵ���������</html:option>
										<html:option value="��Ƹ��Ϣ�����">��Ƹ��Ϣ�����</html:option>
										<html:option value="��ҵЭ��ά����">��ҵЭ��ά����</html:option>
										<html:option value="��ҵ��Ƹά����">��ҵ��Ƹά����</html:option>
									</html:select>
								</td>
								<th>
									ʱ��
								</th>
								<td>
									<html:select name="rs1" property="xjsj" style="width:75px">
										<html:option value=""></html:option>
										<html:option value="-1">����</html:option>
										<html:option value="-2">������</html:option>
										<html:option value="-7">һ����</html:option>
										<html:option value="-15">������</html:option>
										<html:option value="-30">һ����</html:option>
										<html:option value="-90">������</html:option>
										<html:option value="-180">������</html:option>
										<html:option value="-365">һ����</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>  </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="1">
											<td align="center">
												<bean:write name="v" />
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>

				<logic:notEmpty name="delete">
					<logic:equal name="delete" value="ok">
						<script>
                      alert("ɾ���ɹ�!");
                    </script>
					</logic:equal>
					<logic:equal name="delete" value="no">
						<script>
                      alert("ɾ��ʧ��");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="deleteall">
					<logic:equal name="deleteall" value="ok">
						<script>
                      alert("�ѽ���ǰ��¼���!");
                    </script>
					</logic:equal>
					<logic:equal name="deleteall" value="no">
						<script>
                      alert("��ղ���ʧ��");
                    </script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
		</script>
	</body>
</html>
