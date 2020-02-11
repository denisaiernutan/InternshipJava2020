<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="com.arobs.entities.User,java.util.List,com.arobs.entities.Product"
   %>

   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

   <html>

      <head>
         <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1256">
         <title>   User Logged Successfully   </title>
      </head>

      <body>

         <center>
            <% User currentUser = (User )(session.getAttribute("currentSessionUser")); %>
            Welcome <%= currentUser.getFirstName() + " " + currentUser.getLastName()%>

         </center>

         	<form action="userServlet" method="POST">

         			Username:
         			<input type="text" name="un"  value="<%=currentUser.getUsername()%>"/><br>

         			First Name:
         			<input type="test" name="fn" value="<%=currentUser.getFirstName()%>"><br>

         			Last Name:
                    <input type="test" name="ln" value="<%=currentUser.getLastName()%>"/><br>

         			<input type="submit" value="submit">
         		</form>

      </body>



   </html>