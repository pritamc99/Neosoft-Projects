<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title><tiles:getAsString name="title"/></title>  
</head>  
<body>  
        <div><tiles:insertAttribute name="header" /></div>  
        <div><tiles:insertAttribute name="menu" /></div>
        <div><tiles:insertAttribute name="adminPanel" /></div>
        <div><tiles:insertAttribute name="bodyContent" /></div>  
        <div><tiles:insertAttribute name="slider" /></div>
        <div><tiles:insertAttribute name="promo" /></div>  
        <div><tiles:insertAttribute name="body" /></div>  
        <div><tiles:insertAttribute name="productWidget" /></div> 
         <div><tiles:insertAttribute name="brand" /></div>
        <div><tiles:insertAttribute name="footer" /></div>  
  
</body>  
</html> 