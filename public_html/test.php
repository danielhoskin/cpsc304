<html> 
<?php 
 
if ($c=OCILogon("ora_h8y9a", "a37594132", "dbhost.ugrad.cs.ubc.ca:1522/ug")) { 
  echo "Successfully connected to Oracle.\n"; 
  $cmd = 'select * from users';
  $statement = OCIParse($c, $cmd);

  if (!$statement) {
    echo "<br> Can't parse the command: $cmd <br>";
  } else {
    echo "<br>Executing the command: $cmd <br>";
    OCIExecute($statement, OCI_DEFAULT);
  }
  
  while ($row = OCI_Fetch_Array($statement, OCI_BOTH)) {
    echo $row[0] . "    " . $row[1] . "    " . $row[2] . "    " . $row[3] . "    " . "<br>"; 
  }
  
  OCILogoff($c); 
} else { 
  $err = OCIError(); 
  echo "Oracle Connect Error " . $err['message']; 
} 
 
?> 
</html>
