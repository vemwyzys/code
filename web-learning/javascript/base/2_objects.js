/**
 * js‘s object is unordered collection data type
 * composed fo key-value
 */
var xiaoming = {
    name: 'nick',
    birth: 1990,
    school: 'No.1 Middle School',
    'middle-school': 'sth',
    height: 1.70,
    weight: 65,
    score: null
}
//get the value fo the object.
var birth = xiaoming.birth;
var middleSchool = xiaoming['middle-school'];

//delete the properties.
delete xiaoming["middle-school"];
delete xiaoming.height;

//judge if the object has some properties.
'name' in xiaoming;  //true;
'grade' in xiaoming; //false;

//even some properties inherit from super.
'toStirng' in xiaoming;
//or use the hasOwnProperty() to exclude the inherits;
xiaoming.hasOwnProperty('name'); //true;
xiaoming.hasOwnProperty('toString');//false; 
