# &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; :hospital: &emsp;DentalCura App &emsp;:hospital:
<br/><br/><br/><br/><br/><br/>


## &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; UML Class Diagram
![UML](webapp_uml.drawio.png)
<br/><br/><br/><br/>

## &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; Enhanced Entity-Relationship Model
![Enhanced entity-relationship model](eer_diagram.png)

<br/><br/>

## &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; REST API
<br/>

### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; [Interactive Swagger UI](https://app.swaggerhub.com/apis-docs/fr3m3n/DentalCura/1.0.0#/) 

<br/>


#### PATIENT:
###### GET $~~~~~~$ &emsp; http://localhost:8080/patient $~~~~~~~$ &emsp; Get all patients
###### GET $~~~~~~$ &emsp; http://localhost:8080/patient/{id} &emsp; Get patient by id
###### POST $~~~$ &emsp; http://localhost:8080/patient $~~~~~~~$ &emsp; Create new patient
###### PUT $~~~~~~$ &emsp; http://localhost:8080/patient/{id} &emsp; Update patient by id
###### DELETE &emsp; http://localhost:8080/patient/{id} &emsp; Delete patient by id
<br/>

#### DENTIST:
###### GET $~~~~~~$ &emsp; http://localhost:8080/dentist $~~~~~~~$ &emsp; Get all dentists
###### GET $~~~~~~$ &emsp; http://localhost:8080/dentist/{id} &emsp; Get dentist by id
###### POST $~~~$ &emsp; http://localhost:8080/dentist $~~~~~~~$ &emsp; Create new dentist
###### PUT $~~~~~~$ &emsp; http://localhost:8080/dentist/{id} &emsp; Update dentist by id
###### DELETE &emsp; http://localhost:8080/dentist/{id} &emsp; Delete dentist by id
<br/>

#### APPOINTMENT:
###### GET $~~~~~~$ &emsp; http://localhost:8080/appointment $~~~~~~~$ &emsp; Get all appointments
###### GET $~~~~~~$ &emsp; http://localhost:8080/appointment/{id} &emsp; Get appointment by id
###### POST $~~~$ &emsp; http://localhost:8080/appointment $~~~~~~~$ &emsp; Create new appointment
###### PUT $~~~~~~$ &emsp; http://localhost:8080/appointment/{id} &emsp; Update appointment by id
###### DELETE &emsp; http://localhost:8080/appointment/{id} &emsp; Delete appointment by id
<br/>

#### USERs:
###### GET $~~~~~~$ &emsp; http://localhost:8080/user $~~~~~~~$ &emsp; Get all users
###### GET $~~~~~~$ &emsp; http://localhost:8080/user/{id} &emsp; Get user by id
###### POST $~~~$ &emsp; http://localhost:8080/user $~~~~~~~$ &emsp; Create new user
###### PUT $~~~~~~$ &emsp; http://localhost:8080/user/{id} &emsp; Update user by id
###### DELETE &emsp; http://localhost:8080/user/{id} &emsp; Delete user by id