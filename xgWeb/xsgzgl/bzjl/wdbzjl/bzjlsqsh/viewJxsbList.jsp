<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<style>
			/*���ղ���Ŀ����ʱ����ҳ�б����ʾ��ȫ�������б��������ʾ*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/jxsb.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
		
		<script type="text/javascript">
			jQuery(function(){
				var dialog = showDialog("ѡ���ϱ�����",300,80,"bzjl_sqsh.do?method=selectSbjx",{max:false,min:false});
				
				jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
					var index = jQuery(this).attr("index");
					var gridSetting = getGridSetting();
					jQuery("#dataTable").initGrid(gridSetting);
				});
				
				loadCookie();
			});

			//װ��cookie
			function loadCookie(){
				var indexStr = jQuery.cookie("jxsbGrid");
				var indexArray = indexStr.split("-");
				
				jQuery.each(indexArray,function(i,n){
					
					if (n != ""){
						jQuery("[name=zcxm][index="+n+"]").attr("checked",true);
					}
				});
			}
			
			//����cookie
			function setJxsbCookie(){
				var chekedZcxm = jQuery("[name=zcxm]:checked:not(:disabled)");
				var indexStr = "";
				
				jQuery.each(chekedZcxm,function(i,n){
					var index = jQuery(n).attr("index");
					indexStr += index+"-";
				});
				jQuery.cookie("jxsbGrid",indexStr, { expires: 7});
			}
			
		</script>
	</head>

	<body onunload="setJxsbCookie();">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >ʹ�ð���</a>
			</p>
		</div>
	
		<input type="hidden" id="xmdm"/>
		
		<html:form action="/bzjl_sqsh" method="post" styleId="zcxmForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						�ο�ѧ���۲����������ѡ��ѧ�������ϱ���ϵͳ���Զ��ж�ѡ��ѧ���Ƿ�������������
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="resetPjxm();" class="btn_sx">�л��ϱ�����</a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>
					<font color="blue">${cssz.zqmc}&nbsp;</font>����ѧ���б� 
					&nbsp;&nbsp;�ϱ����<label style="color: blue;" id="jxmc"></label>
				</span>
			</h3>
			
			<logic:present name="zcxmList">
				<logic:iterate id="z" name="zcxmList" indexId="i">
					
					<logic:equal value="N" name="z" property="fjdm">
						<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" checked="checked" disabled="disabled" name="zcxm"/> ${z.xmmc }
					</logic:equal>
					
					<logic:notEqual value="N" name="z" property="fjdm">
						<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" name="zcxm" index="${i }"
						/> ${z.xmmc }
					</logic:notEqual>
				
				</logic:iterate>
			</logic:present>
			
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
