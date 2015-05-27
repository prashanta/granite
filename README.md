# README #

A REST Service for Vantage ERP.

##PACKAGE: com.gemt.granite.rest.erp.Part

**granite/api/part/info/{partnum}**
Returns basic part information of given "partnum"
	
**granite/api/part/detail/{partnum}**
Returns detail part information of given "partnum"
	
**granite/api/part/rev/{partnum}**
Returns part revisions of given "partnum"	
	
**granite/api/part//approvedrev/{partnum}**
Returns approved revision of given "partnum" 
	
**granite/api/part//bin/{partnum}**
Returns primary bin number of given "partnum" 

**granite/api/part/search/{partnum}**
Search for part numbers containing text "partnum" 
	
**granite/api/part/searchbydesc/{description}**
Search for part desctiption containing text "description" 

**granite/api/part//class/{class}**
Search for class containing text "class"

	
**granite/api/part/plant/{partnum}**
Returns plate related information for given "partnum". E.g:

REST: http://192.168.74.250/granite/api/part/plant/7188
RESULT:
{
	"partNum": "7188",
	"leadTime": 45,
	"processMRP": true,
	"daysOfSupply": 10
}

---

##PACKAGE: com.gemt.granite.rest.erp.Material

**granite/api/mtl/{partnum}/{revnum}**
Returns child parts of given parent "partnum" and "revnum". E.g.:

REST: http://192.168.74.250/granite/api/mtl/7371/-
RESULT:
[
	{
		"partNum": ""7371,
		"revisionNum": "-",
		"mtlSeq": 10,
		"qtyPer": 1,
		"mtlPartNum": "7251",
		"pullAsAsm": false,
		"viewAsAsm": false
	},
	{
		"partNum": "7371",
		"revisionNum": "-",
		"mtlSeq": 20,
		"qtyPer": 1,
		"mtlPartNum": "4485",
		"pullAsAsm": false,
		"viewAsAsm": false
	},
	{
		"partNum": "7371",
		"revisionNum": "-",
		"mtlSeq": 30,
		"qtyPer": 1,
		"mtlPartNum": "6331-09",
		"pullAsAsm": false,
		"viewAsAsm": false
	}
]

	
**granite/api/mtl//{partnum}**
Returns child parts of approved revision of given parent "partnum".

**granite/api/mtl/detail/{partnum}/{revnum}**
Returns child parts with detail information for given parent "partnum" and "revnum". E.g.:

REST: http://192.168.74.250/granite/api/mtl/detail/7371/-
RESULT:
[
	{
		"partNum": "7371",
		"mtlSeq": 10,
		"qtyPer": 1,
		"mtlPartNum": "7251",
		"bubbleNum": "",
		"pullAsAsm": false,
		"viewAsAsm": false,
		"partDescription": "PLATE SIDE LEFT,7251",
		"partClass": "HORC",
		"invUM": "PC",
		"partType": "P",
		"nonStock": false,
		"manufacturer": "",
		"manufacturerNumber": "",
		"project": "",
		"estimatedCost": 0,
		"mfgComment": "",
		"purComment": "",
		"leadTime": 45,
		"processMRP": false,
		"daysOfSupply": 10,
		"genPOSuggestion": true,
		"revisionNum": "E",
		"revisionEffectiveDate": "24-Jan-2007",
		"approvedVendor": null,
		"primaryBin": "FT013"
	},
	...
]

**granite/api/mtl/detail/{partnum}**
Returns child parts with detail information of approved revision of given parent "partnum" and "revnum".