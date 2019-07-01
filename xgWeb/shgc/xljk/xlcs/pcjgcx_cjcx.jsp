<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXljkSjm.js'></script>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="javascript">
		function cjjlb_search(){
			var xztj=document.all['xztj'].value;
			var fz=document.all['fz'].value;
			if(xztj!="" && fz==""){
				alert("����д��ֵ��");
				document.all['fz'].focus();
				return false;
			}else if(fz!="" && xztj==""){
				alert("ѡ��������");
				document.all['xztj'].focus();
				return false;
			}
		    allNotEmpThenGo('/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=cjb_search');
		}
		
		function cjjlb_dtjl(){
			if (curr_row == null) {
				alert("��ѡ��Ҫ�鿴�ļ�¼��\n��������Ӧ���У�");
				return false;
			}else{
				var cjjlb_xnid=curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin('/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=dtjlb_search&cjjlb_xnid='+cjjlb_xnid,750,500);
			}
		}
		
		function cjjlb_view(){
			var cjjlb_xnid=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin('/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=cjb_view&cjjlb_xnid='+cjjlb_xnid,480,370);
		}
		
		function cjjlb_dtjgfx(){
			if (curr_row == null) {
				alert("��ѡ��Ҫ�鿴�ļ�¼��\n��������Ӧ���У�");
				return false;
			}else{
				var cjjlb_xnid=curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin('/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=dtjgfxb_search&cjjlb_xnid='+cjjlb_xnid,750,500);
			}
		}
		
		function checkSjmz(){
			var sjlsh = document.getElementById("sjlsh").value;
			if ("" != sjlsh && null != sjlsh){
				getXljkSjm.get_sjmcBy_sjlsh(sjlsh,function(data){
				    if(data == "�����������Ծ�"){
				    	refreshForm("/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=cjb_search_xjlk");
				   	}else if(data == "���������Ծ�"){
				   		refreshForm("/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=cjb_search_gxcs");
				   	}else{
				    	return;
				    }
				}); 
			}   	
		}
	</script>
	</head>
	<body>
		<html:form action="/xljk_xlcs_pcjgcx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">
			<logic:equal value="yes" name="writeAble" scope="request">
			 <div class="buttonbox">
			    <ul>
				  <li> <a href="#" onclick="cjjlb_dtjl()" class="btn_zj"> �����¼ </a> </li>
			      <li> <a href="#" onclick="cjjlb_dtjgfx()" class="btn_xg"> ���������� </a> </li>
			    </ul>
			 </div>
			 </logic:equal>
			<div class="searchtab">
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">	
			              <input type="hidden" name="go" value="a" />
			              <button class="btn_cx" id="search_go" 
			              	 onclick="cjjlb_search();">
			              	�� ѯ
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	�� ��
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>

					<tbody>
						<tr>
							<th align="left">
								�Ծ���
							</th>
							<td>
								<html:select property="sjlsh" styleId="sjlsh"
									style="width:150px" onchange="checkSjmz();">
									<html:option value=""></html:option>
									<html:options collection="sjList" property="SJLSH"
										labelProperty="SJM" />
								</html:select>
							</td>
							<th>
								ѧ ��
							</th>
							<td>
								<html:text property="xh" styleId="xh" onblur="" onkeypress=""
									onkeyup="chkInput('xh')" />
								<button
									onclick="showTopWin('/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=stu_info',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>

							<th>
								����
							</th>
							<td>
								<html:text style="cursor:hand; width:85px;" styleId="dateF"
									property="dtrq"
									onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="readonly" />
							</td>
						</tr>

						<tr>
							<th>
								�� ��(����)
							</th>
							<td>
								<html:select property="xztj" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="xztjList" property="DMH"
										labelProperty="DMMC" />
								</html:select>
							</td>
							<th>
								�� ��(��ֵ)
							</th>
							<td>
								<html:text property="fz" style="width:110px"
									onkeyup="chkInput('fz')" />
							</td>
							<td colspan="2">
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	��ǰ��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������</font>
			 		 </logic:notEmpty>
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
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" ondblclick="cjjlb_view()"
								style="cursor:hand">
								<td>
									<input type="hidden" id="XN_ID" name="XN_ID"
										value="<bean:write name="s" property="XN_ID"/>" />
									<bean:write name="s" property="XH" />
								</td>
								<td>
									<bean:write name="s" property="DTSJ" />
								</td>
								<td>
									<bean:write name="s" property="SJLSH" />
								</td>
								<td>
									<bean:write name="s" property="CJ" />
								</td>
								<td>
									<bean:write name="s" property="DTYS" />
								</td>
								<td>
									<bean:write name="s" property="BZ" />
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>
