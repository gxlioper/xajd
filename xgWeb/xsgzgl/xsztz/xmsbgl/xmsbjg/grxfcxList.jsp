<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/xmsbjg/js/xmsbjg.js"></script>
		<script type="text/javascript">
		//�鿴ѧ������
		function xhLink(cellValue, rowObject) {
<%--			return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""--%>
<%--					+ rowObject["jgid"] + "\",\"" + cellValue + "\",\"" + rowObject["xmdm"] + "\");'>" + cellValue--%>
<%--					+ "</a>";--%>
                if(rowObject["csms"] == '1'){
                	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
    				+ rowObject["xsckjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
    				+ "</a>";
                }else if(rowObject["csms"] == '2'){
                	return "<a href='javascript:void(0);' class='name' onclick='TtsqView(\""
            		+ rowObject["xmdm"] + "\",\"" + cellValue + "\");'>" + cellValue
            		+ "</a>";
                }else{
                    return cellValue
                }
				
		}

		function SqjgView(jgid, xh,xmdm) {
<%--			var url = "";--%>
<%--			if(null==xmdm||""==xmdm||"null"==xmdm){--%>
<%--				url="xwtzgl_xmjg.do?method=XmjgView&sqid=";--%>
<%--				}--%>
<%--			else{--%>
<%--				url="xmsqgl_xmjg.do?method=XmjgView&jgid=";--%>
<%--				}--%>
<%--			showDialog("�鿴", 770, 450, url + jgid + "&xh=" + xh);--%>
		showDialog("�鿴", 770, 450, "xmsqgl_xmjg.do?method=XmjgView&jgid="
		+ jgid + "&xh=" + xh);
		}

		function TtsqView(xmdm, xh) {
			showDialog("������չ��Ŀ����鿴", 770, 450, "ttxm_jg.do?method=TtsqView2&xmdm="
					+ xmdm+"&xh="+xh);
		}
				
		jQuery(function(){
			var gridSetting = {
				caption : "����ѧ�ֲ�ѯ�б�",
				pager : "pager",
				url : "xmsqgl_xmjg.do?method=grxfcxList&type=query",
				colList : [
							{ label : 'key', name : 'jgid', index : 'jgid',key:true,hidden : true },
							{ label : 'key', name : 'xmjgid', index : 'xmjgid',hidden : true },
							{ label : 'key', name : 'xmdm', index : 'xmdm',hidden : true },
							{ label : 'sfrm', name : 'sfrm', index : 'sfrm',hidden : true },
							{ label : 'csms', name : 'csms', index : 'csms',hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '6%',formatter : xhLink },
							{ label : '����', name : 'xm', index : 'xm', width : '5%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '3%' },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '5%' },
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '5%' },
							{ label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '5%',formatter : xmLink  },
							{ label : '��Ŀ����', name : 'csmsmc', index : 'csmsmc', width : '5%'},
							{ label : '������Ŀ', name : 'sskmmc', index : 'sskmmc', width : '5%' },
							{ label : '��Ŀ����', name : 'xmjbmc', index : 'xmjbmc', width : '5%' },
							//{ label : '����ѧ��', name : 'jcxf', index : 'jcxf', width : '5%' },
							{ label : '��ý���', name : 'jxmc', index : 'jxmc', width : '5%' },
							{ label : 'xsckjgid', name : 'xsckjgid', index : 'xsckjgid', width : '4%',hidden : true },
							{ label : 'xmckjgid', name : 'xmckjgid', index : 'xmckjgid', width : '4%',hidden : true },
							{ label : '���ѧ��', name : 'zxf', index : 'zxf', width : '3%' }
							
							<logic:equal name="xxdm" value="13627">
							   ,{label : '��ע1',name : 'bz1',index : 'bz1',width : '8%'}
							   ,{label : '��ע2',name : 'bz2',index : 'bz2',width : '8%'}
							   ,{label : '��ע3',name : 'bz3',index : 'bz3',width : '8%'}
							   ,{label : '��ע4',name : 'bz4',index : 'bz4',width : '8%'}
							   ,{label : '��ע5',name : 'bz5',index : 'bz5',width : '8%'}
							   </logic:equal>
							
							],
							
							<logic:equal name="xxdm" value="13627">
							shrinkToFit:false,    
							autoScroll: true,
							width:1500
							</logic:equal>
							
					 }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		function xmLink(cellValue, rowObject) {
			if('1'==rowObject["sfrm"]){
				cellValue+="<font color='red'>�����š�</font>";
			}
			if(null==rowObject.xmdm||""==rowObject.xmdm){
				return cellValue;
			}else{
<%--				return "<a href='javascript:void(0);' class='name' onclick='xmcxView(\""--%>
<%--				+ rowObject["xmjgid"] + "\");'>" + cellValue + "</a>";--%>
					return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
					+ rowObject["xmckjgid"] + "\");'>" + cellValue + "</a>";
				}
}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xmsqgl_xmjg">
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="grxfExport();return false;">����</a></li>
						<!-- ���չ����Ƽ�ְҵѧԺ -->
						<logic:equal name="xxdm" value="13344">
							<li>
								<a href="javascript:void(0);" onclick="dyXftjbCommon();return false;"
									class="btn_dy">ѧ��ͳ�Ʊ��ӡ</a>
							</li>
						</logic:equal>
						
						<logic:equal name="xxdm" value="11032">
							<li>
								<a href="javascript:void(0);" onclick="xscjPrint();return false;"
									class="btn_dy">ѧ�ֳɼ����ӡ</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����ѧ�ֲ�ѯ�б�
				<logic:equal value="stu" name="usertype">
					<label style="color:blue;margin-left:95px">�ѻ��ѧ�֣�${grzxf}</label>
				</logic:equal>
				</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				</div>
				<div id="pager"></div>
			
		</div>
	</body>
</html>
