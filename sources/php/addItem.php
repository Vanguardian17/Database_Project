<?php
//include DatabaseHelper.php file
require_once('DatabaseHelper.php');

//instantiate DatabaseHelper class
$database = new DatabaseHelper();

//Grab variables from POST request
$Name = '';
if(isset($_POST['Name'])){
    $Name = $_POST['Name'];
}

$Description = '';
if(isset($_POST['Description'])){
    $Description = $_POST['Description'];
}

$Price = '';
if(isset($_POST['Price'])){
    $Price = $_POST['Price'];
}

$Seller_Name = '';
if(isset($_POST['Seller_Name'])){
    $Seller_Name = $_POST['Seller_Name'];
}

// Insert method
$success = $database->insertIntoItem($Name, $Description, $Price, $Seller_Name);

// Check result
if ($success){
    echo "Item '{$Name} {$Description} {$Price} {$Seller_Name}' successfully added!'";
}
else{
    echo "Error can't insert Item '{$Name} {$Description} {$Price} {$Seller_Name}'!";
}
?>

<!-- link back to index page-->
<br>
<a href="index.php">
    go back
</a>