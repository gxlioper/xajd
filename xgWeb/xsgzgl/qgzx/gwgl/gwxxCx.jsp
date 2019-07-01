<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/qgzx/gwgl/gwgl.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});

		//����
		function copyGwxx1(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				var array = jQuery("[name=div_pkValue]:checked");
				var num = 0;
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(2).text();
					if(str.indexOf(pkValue)==-1){
						str += pkValue+"!!@@!!";
						num++;
					}
				}
				var idList = "";
				jQuery("input[type='checkbox'][name='div_pkValue']:checked").each(function(){
					idList += jQuery(this).val() + "!!@@!!";        
				});
				url = "qgzx_gwgl_ajax.do?method=gwxxFz&str="+str+"&num="+num+"&len="+len +"&idList="+idList;
				showDialog('', 380, 200, url)
			}else{
				alertInfo("�빴ѡ��Ҫ���Ƶļ�¼��",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		</script>
	</head>
	<body>

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/qgzx_gwgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
            <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li> 
							<a href="#" onclick="showDialog('', 760, 520, 'qgzx_gwgl.do?method=gwxxZj');return false;" class="btn_zj">���Ӹ�λ</a>
						</li>
						<li>
							<a href="#" onclick="showModi('gwxxXg');return false;" class="btn_xg">�޸ĸ�λ</a>
						</li>
						<li>
							<a href="#" onclick="deleteGwxx();return false;" class="btn_sc">ɾ����λ</a>
						</li>
						<li>
							<a href="#" onclick="copyGwxx1();return false;" class="btn_fz">���Ƹ�λ</a>
						</li>
						<logic:equal value="10407" name="xxdm">
                        <li>
                            <a href="javascript:;" onclick="gwxxDr();return false;" id="btn_dr" class="btn_dr">��λ����</a>
                       </li>
						 <li>
                            <a href="javascript:;" onclick="gwryDr();return false;" id="btn_dr" class="btn_dr">��λ��Ա����</a>
                       </li>
						</logic:equal>
						</logic:equal>
						<logic:equal name="xxdm" value="10052">
						<li>
							<a href="#" onclick="showModi('ryxxZj');return false;" class="btn_zj">��Ա����</a>
						</li>
						<li>
							<a href="#" onclick="showModi('ryxxTg');return false;" class="btn_sc">��Ա�˸�</a>
						</li>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10052">
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showModi('ryxxZj');return false;" class="btn_zj">��Ա����</a>
						</li>
						<li>
							<a href="#" onclick="showModi('ryxxTg');return false;" class="btn_sc">��Ա�˸�</a>
						</li>
						</logic:equal>
						</logic:notEqual>
						<li>
							<a href="#" class="btn_ck" onclick="showModi('gwxxCk');return false;">�鿴��ϸ</a>
						</li>
					</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxGwglForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="tempDiv" style="display: none">
				<!-- ��ʾ��Ϣ -->
				<div class="prompt" id="promptTs">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p>
						���ܽ�ѡ�и�λ���Ƶ��Ǹ�λ����ѧ����
					</p>
					<a class="close" title="����"
					   onclick="this.parentNode.style.display='none'"></a>
				</div>
				<!-- ��ʾ��Ϣ end-->	
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>���Ƹ�λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��ѡ��λ
							</th>
							<td>
								<font id="yxgw"></font>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>Ŀ��ѧ��
							</th>
							<td>
								<html:select property="xn" styleId="xn" >
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="����" onclick="gwxxDivSave();">
										����
									</button>
									<button type="button" name="ȡ��" onclick="closeWindown();return false;">
										ȡ ��
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