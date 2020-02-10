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
        <p> <a href="updateUser.jsp">update user</a></p>
         </center>
         <h1>
         Products:
         </h1>
         <table border="1">
         <thead>
         <tr>
             <th>Product Name </th>
             <th>Price</th>
             <th>Quantity</th>
         </tr>
         </thead>

         <tbody>
         <% List<Product> productList= (List<Product>) session.getAttribute("productsList"); %>

          <%for(int i=0;i<productList.size();i++) { %>
          <tr>
               <td> <%= productList.get(i).getName()%> </td>
               <td> <%= productList.get(i).getPrice()%> </td>
               <td> <%= productList.get(i).getQuantity()%> </td>
         </tr>

         <% } %>
         </tbody>
         </table>

 <h1>
         Your products:
         </h1>
         <table border="1">
         <thead>
         <tr>
             <th>Product Name </th>
             <th>Price</th>
             <th>Quantity</th>
         </tr>
         </thead>

         <tbody>
         <% List<Product> userProductList= (List<Product>) session.getAttribute("userProductsList"); %>

          <%
          if(userProductList!=null) for(int i=0;i<userProductList.size();i++) { %>
          <tr>
               <td> <%= userProductList.get(i).getName()%> </td>
               <td> <%= userProductList.get(i).getPrice()%> </td>
               <td> <%= userProductList.get(i).getQuantity()%> </td>
         </tr>

         <% } %>
         </tbody>
         </table>


         <h1>
          Add product
         </h1>

         	<form action="productServlet" method="POST">

         			Product:
         			<input type="text" name="pr"/><br>

         			Quantity:
         			<input type="number" name="qu"/><br>

         			<input type="submit" value="add">
         		</form>


          <h1>
                   Delete product
                  </h1>

                  	<form action="userServlet" method="GET">

                  			Product:
                  			<input type="text" name="pr"/><br>

                  			Quantity:
                  			<input type="number" name="qu"/><br>

                  			<input type="submit" value="delete">
                  		</form>

      </body>



   </html>