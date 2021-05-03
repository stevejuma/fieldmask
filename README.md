## Documentation

## Requirements and Downloads

Requirements:

* Java 1.8
* Spring Framework Boot > 2.x.x (web)

Gradle:

```gradle
repositories {
    mavenCentral()
}

dependencies {
  implementation 'ma.ju:fieldmask-starter:1.0.0'
}
```

# fieldmask

Fieldmask is a framework for supporting partial JSON responses in  
RESTful web services by allowing users to supply arbitrary queries in the URL.

Fieldmask supports partial responses in the following web frameworks:

* 	Spring Boot
* 	SpringMVC

## What is a partial response?
By default, the server will send back the full representation of a rest
resource for every request.  Partial responses let you request only
the elements you are interested in, instead of the full
resource representation.

This allows your client application to avoid transferring, parsing,
and storing unneeded fields, so you can utilize network and memory
resources more efficiently.

For example, take the two responses below.  Both are requests for the  
same resource, but let's assume we are only interested in the following fields:

*	Artist Name
*	Album Count
*   Album Names

### Full Resource Representation
```json
{
  "id": "12345",
  "name": "Avril Lavigne",
  "albumCount": 2,
  "songCount": 24,
  "albums": [
    {
        "id": "45678",
        "title": "Let Go",
        "artistId": "12345",
        "releaseDate": "2002-06-04"        
    }
  ],
  "songs": [
     {
       "id": "98765",
       "title": "Complicated",
       "albumId": 45678
     }
  ]
}
```

### Partial Resource Representation
[https://fieldmask.ju.ma/artists/12345?fields=name,albumCount,albums(title)](https://fieldmask.ju.ma/artists/12345?fields=name,albumCount,albums(name))
```json
{
  "name": "Avril Lavigne",
  "albumCount": 2,
  "albums": [
    { 
        "title": "Let Go"     
    }
  ]
}
```
As you can see, the partial response is a significant reduction in
payload size and message complexity.  By allowing the consumer of the
API to specify the fields they are interested in you can significantly
reduce the complexity of response messages as well as improve
performance over the wire.

## Getting FieldMask
Catnap libraries are available from JCenter.

* [fieldmask-core](fieldmask-core) - Use this library if you are only interested in the fieldmask masking functionality
* [fieldmask-boot-autoconfigure](fieldmask-boot-autoconfigure) - Configuration for use with spring MVC
* [fieldmask-starter](fieldmask-starter) - Use this library if you are integrating fieldmask with spring boot

