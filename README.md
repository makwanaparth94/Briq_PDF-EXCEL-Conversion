# Briq_PDF-EXCEL-Conversion
## Table of contents
* [Project info](#project-info)
* [Folder Structure](#folder-structure)
* [Setup](#setup)

## Project info
This Project is to take Project name from PDF File and GENERATE run time PDF by removing project name and extract PDF Tables into EXCEL
It is developed by using Java Project only by introducing pom.xml

## Folder Structure
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;src/main/java <br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;conversion.project.main [Used for defining and executing a logic from PDF to Excel]  <br />
          
* From input transport.pdf, It takes a project name and replace it with null & generate a new pdf file named 'Updated_Text.pdf'. Use it as an input for the conversion. 

## Setup
To run this project, pull it in local:

```
$ cd Briq_PDF-EXCEL-Conversion
$ mvn clean install
```
