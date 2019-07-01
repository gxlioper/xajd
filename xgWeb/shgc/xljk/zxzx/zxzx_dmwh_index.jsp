<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function SelectrwOption(a,b){
   			document.forms[0].tname.value=a;
   			document.forms[0].action=b;
   			document.forms[0].submit();
		}
		
		function thoughtLoad(defaultid){
		   if(document.forms[0].tname.value!= ""){
			  document.getElementById(document.forms[0].tname.value+"l").className = "xxk_on_l";
			  document.getElementById(document.forms[0].tname.value).className = "xxk_on_m";
			  document.getElementById(document.forms[0].tname.value+"r").className = "xxk_on_r";
		   }else{
			  document.getElementById(defaultid+"l").className = "xxk_on_l";
			  document.getElementById(defaultid).className = "xxk_on_m";
			  document.getElementById(defaultid+"r").className = "xxk_on_r";
			  document.forms[0].tname.value = defaultid;
		   }
		}
		
		function dmwh_add(ss){
			if("zxszy_sjd"==ss){ //ʱ���
				showTopWin('/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=sjd_add',450,300);
			}else if("zxszy_dd"==ss){ //�ض�	
				showTopWin('/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=dd_add',450,300);
			}else if("xlxhhd_hdxs"==ss){ //������ʽ	
				showTopWin('/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=jyxs_add_be',450,300);
			}
		}
		
		function dmwh_del(){	
			if (curr_row == null) {
				alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
				return false;
			} 
			var xn_id = curr_row.cells[0].getElementsByTagName("input")[0].value;
			var url = "/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&xn_id="+xn_id + "&doType=";
			if (confirm("ȷ��Ҫɾ������������")) {
				var ss=document.forms[0].tname.value;
				underDealWith();
				if("zxszy_sjd" == ss){		
					url += "sjd_del";
				}else if("zxszy_dd" == ss){
					url += "dd_del";
				}else if("xlxhhd_hdxs" == ss){
					url += "jyxs_del";
				}
				refreshForm(url);
			}
		}
		
		function dmwh_modi(){
			var ss=document.forms[0].tname.value;
			if (curr_row == null) {
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return false;
			} 
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			if("zxszy_sjd"==ss){		
				showTopWin("/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=sjd_modi&xn_id="+xn_id,450,300);
			}else if("zxszy_dd"==ss){
				showTopWin("/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=dd_modi&xn_id="+xn_id,450,300);
			}else if("xlxhhd_hdxs"==ss){
				showTopWin("/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=jyxs_view&xn_id="+xn_id,450,300);
			}
		}	
	</script>
	<script language="javascript" src="js/lrh_new_js.js"></script>
	</head>
	<body onload="$($('tname').value).className='ha'">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������ - ������ѯ - ʱ��ص�ά��</a>
			</p>
		</div>
	
		<html:form action="/xljk_zxzx_dmwh" method="post">
			<input type="hidden" id="tname" name="tname"
				value="<bean:write name="form" property="ss"/>" />
			
			<div class="comp_title">
				<ul>
					<li id="zxszy_sjd"><a href="#" onclick="SelectrwOption('zxszy_sjd','/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh')" >
							<span>ʱ���</span>
						</a>
					</li>
					<li id="zxszy_dd"><a href="#" onclick="SelectrwOption('zxszy_dd','/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh')" >
							<span>�� ��</span>
						</a>
					</li>
					<li id="xlxhhd_hdxs"><a href="#" onclick="SelectrwOption('xlxhhd_hdxs','/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh')" >
							<span>������ʽ</span>
						</a>
					</li>
				</ul>
			</div>
			
			<div class="toolbox">
				<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="dmwh_add(document.forms[0].tname.value);"
									class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#"
									onclick="dmwh_modi();"
									class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#"
									onclick="dmwh_del();"
									class="btn_sc"> ɾ�� </a>
							</li>
						</ul>
					</div>
			
					
					<div class="formbox">
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand">
										<td>
											<input type="hidden" id="xn_id" name="xn_id"
												value="<bean:write name="s" property="XN_ID"/>" />
											<bean:write name="s" property="DMH" />
										</td>
										<td>
											<bean:write name="s" property="DMMC" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
			</div>
		</html:form>
	</body>
	<button onclick="allNotEmpThenGo('xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh')" style="display:none" id="search_go"></button>
	<div id="tmpdiv"></div>
	<logic:notEmpty name="delete">
		<logic:equal name="delete" value="ok">
			<script>
				alert("ɾ���ɹ�!");
			</script>
		</logic:equal>
		<logic:equal name="delete" value="no">
			<script>
				alert("ɾ��ʧ��!");
			</script>
		</logic:equal>
	</logic:notEmpty>
</html>
