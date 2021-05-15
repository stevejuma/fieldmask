## Documentation

[![Maven Central](https://img.shields.io/maven-central/v/ma.ju.fieldmask/fieldmask-starter.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22ma.ju.fieldmask%22%20AND%20a:%22fieldmask-starter%22)

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
  // If using the core library only
  implementation 'ma.ju.fieldmask:fieldmask-core:1.0.6'
  // If using Spring Boot
  implementation 'ma.ju.fieldmask:fieldmask-starter:1.0.6'
  // If using Spring MVC
  implementation 'ma.ju.fieldmask:fieldmask-spring:1.0.6'
}
```

Maven:

```xml
<dependency>
  <groupId>ma.ju.fieldmask</groupId>
  <artifactId>fieldmask-core</artifactId>
  <version>1.0.6</version>
</dependency>

<dependency>
  <groupId>ma.ju.fieldmask</groupId>
  <artifactId>fieldmask-starter</artifactId>
  <version>1.0.6</version>
</dependency>

<dependency>
  <groupId>ma.ju.fieldmask</groupId>
  <artifactId>fieldmask-spring</artifactId>
  <version>1.0.6</version>
</dependency>
```
# FieldMask

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
* [fieldmask-spring](fieldmask-spring) - Configuration for use with spring MVC
* [fieldmask-starter](fieldmask-starter) - Use this library if you are integrating fieldmask with spring boot



# Working with partial resources 

Another way to improve the performance of your API calls is by requesting only the portion of the data that you're interested in. This lets your application avoid transferring, parsing, and storing unneeded fields, so it can use resources including network, CPU, and memory more efficiently.

Example 

The following example shows the use of the fields parameter with a generic (fictional) "Demo" API.

**Simple request**: This HTTP `GET` request omits the `fields` parameter and returns the full resource.

	http://localhost:8080/demo/v1

**Full resource response**: The full resource data includes the following fields, along with many others that have been omitted for brevity.

```json
{
  "kind": "demo",
  ...
  "items": [
  {
    "title": "First title",
    "comment": "First comment.",
    "characteristics": {
      "length": "short",
      "accuracy": "high",
      "followers": ["Jo", "Will"],
    },
    "status": "active",
    ...
  },
  {
    "title": "Second title",
    "comment": "Second comment.",
    "characteristics": {
      "length": "long",
      "accuracy": "medium"
      "followers": [ ],
    },
    "status": "pending",
    ...
  },
  ...
  ]
}
```

**Request for a partial response**: The following request for this same resource uses the fields parameter to significantly reduce the amount of data returned.

```
http://localhost:8080/demo/v1?fields=kind,items(title,characteristics/length)
```

```
200 OK
```
	
```json
{
  "kind": "demo",
  "items": [{
    "title": "First title",
    "characteristics": {
      "length": "short"[^]
    }
  }, {
    "title": "Second title",
    "characteristics": {
      "length": "long"
    }
  },
  ...
  ]
}
```

Note that the response is a JSON object that includes only the selected fields and their enclosing parent objects.

Details on how to format the fields parameter is covered next, followed by more details about what exactly gets returned in the response 

## Fields parameter syntax summary 

The format of the `fields` request parameter value is loosely based on XPath syntax. The supported syntax is summarized below, and additional examples are provided in the following section.

* Use a comma-separated list to select multiple fields.
* Use `a/b` to select a field `b` that is nested within field `a`; use `a/b/c` to select a field `c` nested within `b`
* Use a sub-selector to request a set of specific sub-fields of arrays or objects by placing expressions in parentheses `"( )"`.

For example: `fields=items(id,author/email)` returns only the item ID and author's email for each element in the items array. You can also specify a single sub-field, where `fields=items(id) `is equivalent to `fields=items/id`.

* Use wildcards in field selections, if needed.
For example: `fields=items/pagemap/*` selects all objects in a pagemap. You can also omit the wildcard if it's at the end of the selector. The above is similar to `fields=items/pagemap`

**Identify the fields you want returned, or make field selections.**

* `items` 	
	* Returns all elements in the items array, including all fields in  each element, but no other fields. 

* `etag,items` 	
	* Returns both the **etag** field and all elements in the items array.

* `items/title` 	
	* Returns only the **title** field for all elements in the items array  Whenever a nested field is returned, the response includes the enclosing  parent objects. The parent fields do not include any other child fields  unless they are also selected explicitly

* `context/facets/label`
	* Returns only the **label** field for all members of the **facets** array,  which is itself nested under the **context** object. 

* `items/pagemap/*/title`
	* For each element in the items array, returns only the **title** field  (if present) of all objects that are children of **pagemap**. 	

* `title` 
	* Returns the `title` field of the requested resource.

* `author/uri`
	* Returns the `uri` sub-field of the `author` object in the requested resource.

* `links/*/href`
	* Returns the `href` field of all objects that are children of `links`.

* `items(title,author/uri)`
	* Returns only the values of the `title` and author's `uri` for each element in the items array.

## Aliases
You can also specify aliases as part of your request. The format for aliases is `<alias>:<field-selector>` so a field selection of `data:items(name:title)` this will return data with the `items` field aliased to `data` and the `title` field aliased to `name` 


*Without aliases* 
```
http://localhost:8080/demo/v1?fields=kind,items(title)
200 OK
```

```
{
 "kind": "demo",
 "items": [{
	 "title": "First title",
	 ...
 }]
}
```

*With Aliases* 

```
http://localhost:8080/demo/v1?fields=kind,data:items(name:title)
200 OK
```

```
{
 "kind": "demo",
 "data": [{
	 "name": "First title",
	 ...
 }]
}
```

# Handling partial responses 
After a server processes a valid request that includes the fields query parameter, it sends back an `HTTP 200 OK` status code, along with the requested data. If the fields query parameter has an error or is otherwise invalid, the server returns an `HTTP 400 Bad Request` status code, along with an error message telling the user what was wrong with their fields selection (for example, `"Invalid field selection a/b"`).


