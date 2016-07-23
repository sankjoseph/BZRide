<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BZride</title>
<link href="bzride_style.css" type="text/css" rel="stylesheet" />
</head>
<body>
 <?php include("header.php"); ?> 
  <div class="div_center">
<!--------------------------------------------------------------------------------------------------------------->
    <table border="2" >
<caption> <h3> REGISTRATION </h3> </caption>
<form action="register_drive_act.php" method="post" enctype="multipart/form-data">

 <tr>
<td> First Name </td>
<td> <input type="text" name="txtfirstname" id="txtfirstname" />
<br />
</td>
 </tr>
 
 <tr>
<td> Last Name </td>
<td> <input type="text" name="txtlastname" id="txtlastname" />
<br />
</td>
 </tr>
 
  <tr>
<td> Email ID </td>
<td> <input type="text" name="txtemail"id="txtemail" />
<br />
</td>
 </tr>

<tr>
<td> Password </td>
<td> <input type="password" name="txtpass"id="txtpass" />
<br />
</td>
 </tr>

 <tr>
<td> Confirm Password </td>
<td> <input type="password" name="txtconpass"id="txtconpass" />
<br />
</td>
 </tr>
 
  <tr>
<td> Address 1 </td>
<td> <textarea rows="4" cols="50" name="txtaddr1" id="txtaddr1" ></textarea>
<br />
</td>
 </tr>
 
   <tr>
<td> Address 2 </td>
<td> <textarea rows="4" cols="50" name="txtaddr2" id="txtaddr2" ></textarea>
<br />
</td>
 </tr>

  <tr>
<td> Telephone No: </td>
<td> <input type="text" name="txtph"id="txtph" />
<br />
</td>
 </tr>
 
<!--  <tr>
<td> Social Security No: </td>
<td> <input type="text" name="txtssn"id="txtssn" />
<br />
</td>
 </tr>-->
 <!--------------------------------------------------------------------------->
 <tr><td></td> <td> <b>VEHICLES DETAILS</b></td></tr>
 
  <tr>
<td> Year </td>
<td> <input type="text" name="txtyear" id="txtyear" />
<br />
</td>
 </tr>
 
  <tr>
<td> Model </td>
<td> <input type="text" name="txtmodel"id="txtmodel" />
<br />
</td>
 </tr>

<tr>
<td> Make </td>
<td> <input type="text" name="txtmake"id="txtmake" />
<br />
</td>
 </tr>

 <tr>
<td> Color </td>
<td> <input type="text" name="txtcolor"id="txtcolor" />
<br />
</td>
 </tr>
 <!-------------------------------------------------------------------------->
  <tr><td></td> <td> <b>REGISTRATION DETAILS</td></tr></b>
 
  <tr>
<td> Registration No: </td>
<td> <input type="text" name="txtregno" id="txtregno" />
<br />
</td>
 </tr>
 
  <tr>
<td> Registration State </td>
<td> <input type="text" name="txtregstate"id="txtregstate" />
<br />
</td>
 </tr>

<tr>
<td> Date of Registration </td>
<td> <input type="text" name="txtregdate"id="txtregdate" />
<br />
</td>
 </tr>

 <tr>
<td> Date of Expiry </td>
<td> <input type="text" name="txtregexpiry"id="txtregexpiry" />
<br />
</td>
 </tr>
 <!-------------------------------------------------------------------------->
   <tr><td></td> <td> <b>INSURANCE DETAILS</td></tr></b>
 
  <tr>
<td> Insurance Company </td>
<td> <input type="text" name="txtinscompany" id="txtinscompany" />
<br />
</td>
 </tr>
 
  <tr>
<td> Policy No: </td>
<td> <input type="text" name="txtpolicyno"id="txtpolicyno" />
<br />
</td>
 </tr>

<tr>
<td> Ploicy Started from: </td>
<td> <input type="text" name="txtinsdate"id="txtinsdate" />
<br />
</td>
 </tr>

 <tr>
<td> Date of Expiry </td>
<td> <input type="text" name="txtinsexpiry"id="txtinsexpiry" />
<br />
</td>
 </tr>
 <!-------------------------------------------------------------------------->
    <tr><td></td> <td> <b>LICENCE DETAILS</td></tr></b>
 
  <tr>
<td> Licence No: </td>
<td> <input type="text" name="txtlicenseno" id="txtlicenseno" />
<br />
</td>
 </tr>
 
  <tr>
<td> State Issued </td>
<td> <input type="text" name="txtlicensestate"id="txtlicensestate" />
<br />
</td>
 </tr>

<tr>
<td> Date of Issue </td>
<td> <input type="text" name="txtlicenseissue"id="txtlicenseissue" />
<br />
</td>
 </tr>

 <tr>
<td> Date of Expiry </td>
<td> <input type="text" name="txtlicenseexpiry"id="txtlicenseexpiry" />
<br />
</td>
 </tr>
 <!-------------------------------------------------------------------------->
<!--  <tr>
<td> Upload Photo </td>

<td><input type="file" name="image"id="image" />
<br />
</td>
 </tr>-->
 
 <tr>
 <tr>
 <th></th>
 <td><input type="SUBMIT" value="submit" onclick="return disp()"/>
 </td>
 </tr>
 
 </form>
 </table>

<!--------------------------------------------------------------------------------------------------------------->    
    <ul class="header_menu">
        <li><a href="register_drive.php">Register To Drive </a></li>
        <li><a href="register_newuser.php">Register New User</a></li>
    </ul>
    
    
  </div>

  <!--<div class="div_right">
    <div class="latest_blog">
      <h3> Latest News</h3>
      <div class="div_blog_top">
        <div class="blog_image"></div>
        <div class="blog_heading"> Hi all</div>
      </div>
      <div class="blog_description">
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.... </p>
      </div>
    </div>
    <div class="latest_blog">
      <h3>Lattest Blog</h3>
      <div class="blog_image"></div>
      <div class="blog_heading"> new blog</div>
      <div class="blog_description">
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. </p>
      </div>
    </div>
  </div>-->
</div>
<?php include("footer.php"); ?> 
</body>
</html>
