function Mascara(obj,funcao,opcao){
v_obj = obj;
v_fun = funcao;
v_opc = opcao;
setTimeout("execmascara()",0);
}
function execmascara(){
v_obj.value = v_fun(v_obj.value, v_opc);
}
function Integer(v){
return v.replace(/\D/g,"");
}
function strReplaceChr(v, o){
var charOK = '';
var charNOK = /[ÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÁÉÍÓÚÃÕÇ]/gi;
var char_NOK = ' ÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÁÉÍÓÚÃÕÇ';
var char_OK =  ' AEIOUAEIOUAEIOUAEIOUAOC';
var aux = '';
switch (o){
case "AdNum":
charOK = /[A-Z0-9 *ªº\-]/gi;
break;
case "OnlyNum":
charOK = /[0-9]/gi;
break;
case "OnlyAlfas":
charOK = /[A-Z ]/gi;
break;
case "AlfasNum":
	charOK = /[A-Z0-9 ]/gi;
	break;
case "AlfaNum":
charOK = /[A-Z0-9]/gi;
break;
case "AlfaNchar":
charOK = /[A-Z0-9 !@#$%*()._ªº,+\-]/gi;
break;
case "Email":
charOK = /[A-Z0-9 @._º\-]/gi;
break;
case "AlfaChar":
charOK = /[A-Z *ªº-]/gi;
break;
default:
charOK = /[A-Z0-9 *ªº\-]/gi;
}
if (o == "login"){
v = v.toLowerCase();
}else{
v = v.toUpperCase();
}
for(i = 0; i < v.length; i++){
	if(v.charAt(i).match(charNOK))
		aux += char_OK.substr(char_NOK.search(v.charAt(i)),1)
	else if(v.charAt(i).match(charOK))
		aux += v.charAt(i);
}
return aux;
}