<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<%@page import="org.displaytag.decorator.TableDecorator"%>
<%@page import="com.bjsxt.drp.business.itemmgr.model.Item"%>

<html>
	<head>

		<script type="text/javascript">
			var contextPath = getContextPath();
			function getContextPath(){
			    var pathName = document.location.pathname;
			    var index = pathName.substr(1).indexOf("/");
			    var result = pathName.substr(0,index+1);
			    return result;
			};
			function checkall() {
				for (var i = 0; i < document.getElementsByName("_chk").length; i++) {
					document.getElementsByName("_chk")[i].checked = document.getElementById("ifAll").checked;
				}
			}
		</script>
		<!-- 
		<link href="<%=request.getContextPath()%>/theme/displaytag.css"
			rel="stylesheet" type="text/css" />
		
			<link href="<%=request.getContextPath()%>/theme/teststyles.css"
				rel="stylesheet" type="text/css" /> 
		-->
		<link href="<%=request.getContextPath()%>/theme/content.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/theme/Global.css" rel="stylesheet" type="text/css" />	
		<jsp:scriptlet>
         request.setAttribute("dyndecorator", new TableDecorator()
    	 {
            public String addRowClass()
            {
                return ((Item)getCurrentRowObject()).getItemNo().compareTo("dd") > 0 ? "good" : "bad";
            }
			public String addRowId()
			{
			return "myrow" + evaluate("itemNo");
			}
        });
       	org.displaytag.decorator.CheckboxTableDecorator decorator = 
       				new org.displaytag.decorator.CheckboxTableDecorator();
        decorator.setId("itemNo");
        decorator.setFieldName("_chk");
        pageContext.setAttribute("checkboxDecorator", decorator);
        </jsp:scriptlet>

	</head>
	<body>
	<script type="text/javascript">
		$(function(){
			$("#birthdate").datepicker();
		});
		function pageChange(sel){
			//alert($(sel).val());
			window.location.href=$(sel).val();
 		}
	</script>
	<input  name="birthdate"
			id="birthdate" tabindex="1" type="text" />
	
		<div>
		<display:table name="rs.results" export="true" pagesize="${rs.pagesize}"
			requestURI="/basedata/item.do" sort="list" uid="element" style="true"
			partialList="true" size="rs.total"
			decorator="checkboxDecorator"  excludedParams="_chk">
			<display:column title="<input id='ifAll' type='checkbox' onclick='checkall()'>" property="checkbox"/>		
			<display:column title="编号" property="itemNo" sortable="true"
				headerClass="sortable" />
			<display:column title="名称" property="itemName" sortable="true"
				headerClass="sortable" />
			<display:column title="规格" property="spec" sortable="true"
				class="money" headerClass="sortable" />
			<display:column title="Click on the link to test">
				<a href="#"
					onclick="alert('Row id: ' + this.parentNode.parentNode.id)">row
					id</a>
			</display:column>
		</display:table>
		</div>
		<div style="float:none;clear:both;text-align:center;">
			<a
				href="<%=request.getContextPath()%>/basedata/item.do?command=list&amp;pageNo=2&amp;pageSize=2">
				返回列表
			</a>
		</div>
	</body>
</html>