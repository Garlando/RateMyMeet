<script src="https://sdk.amazonaws.com/js/aws-sdk-2.154.0.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

function sayHello(name){
    document.getElementById("result").innerHTML = 'Hello ' + name + '!';
}


function xmlGet(){
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "https://www.codecademy.com/", false);
  xhr.send()
  console.log(xhr.status);
  console.log(xhr.statusText);
  document.getElementById("xmlGetTestOutPut").innerHTML = xhr.statusText + '!';
}

AWS.config.update({region: 'eu-west-2'});
AWS.config.credentials = new AWS.CognitoIdentityCredentials({IdentityPoolId: 'adminuser'});

var lambda = new AWS.Lambda({region: REGION, apiVersion: '2015-03-31'});
// create JSON object for parameters for invoking Lambda function
var pullParams = {
  FunctionName : 'Hello',
  InvocationType : 'RequestResponse',
  LogType : 'None'
};
// create variable to hold data returned by the Lambda function
var pullResults;

lambda.invoke(pullParams, function(error, data) {
  if (error) {
    prompt(error);
  } else {
    pullResults = JSON.parse(data.Payload);
  }
});
